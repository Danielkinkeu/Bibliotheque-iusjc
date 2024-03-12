package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Droit;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Role;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Session;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.securite.Connexion;
import org.isj.ing3.isi.webservice.webservicerest.service.IRole;
import org.isj.ing3.isi.webservice.webservicerest.service.ISession;
import org.isj.ing3.isi.webservice.webservicerest.service.IUtilisateur;
import org.isj.ing3.isi.webservice.webservicerest.utils.enumaration.Statut;
import org.isj.ing3.isi.webservice.webservicerest.utils.enumaration.StatutSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zalando.problem.Status;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class UtilisateurServiceImpl implements IUtilisateur {
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    ISession iSession;
    @Autowired
    IRole iRole;
    static Map<String, Droit> map = new HashMap<String, Droit>();

    @Override
    public Utilisateur saveUtilisateur(Utilisateur utilisateur) throws IsjException {
  /*      Utilisateur createur = utilisateurRepository.findById(utilisateur.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));;
        Utilisateur modificateur = createur;

        utilisateur.setCreateur(createur);
        utilisateur.setModificateur(modificateur);*/

        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur utilisateur) throws IsjException {
   /*     Utilisateur createur = utilisateurRepository.findById(utilisateur.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));;
        Utilisateur modificateur = utilisateurRepository.findById(utilisateur.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));;

        utilisateur.setCreateur(createur);
        utilisateur.setModificateur(modificateur);*/

        return utilisateurRepository.save(utilisateur);

    }

    @Override
    public List<Utilisateur> listUtilisateur() {
        return utilisateurRepository.findAll();
    }

    @Override
    public int deleteUtilisateur(Long code) {
        utilisateurRepository.deleteById(utilisateurRepository.findById(code).get().getCode());
        return 1;
    }

    @Override
    public Utilisateur getUtilisateurByCode(Long code) throws IsjException {
        return utilisateurRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
    }

    @Override
    public Utilisateur login(String login, String pw, Statut statut) throws IsjException {
        Utilisateur utilisateur = utilisateurRepository.getUserByLoginAndPw(login, pw, statut).orElseThrow(() -> new IsjException("Utilisateur inconnu, nom d'utilisateur ou mot de passe incorrect", Status.BAD_REQUEST));
        Session session = new Session();
        session.setStatut(StatutSession.ACTIF);
        session.setCreateur(utilisateur);
        session.setModificateur(utilisateur);
        session.setUtilisateur(utilisateur);
        session.setMachineCliente(Connexion.getComputerFullName());
        session.setDateConnection(new Date());
        Session newSession = iSession.saveSession(session);
        if (newSession == null) {
            throw new IsjException("Un problème est survenu lors de l'enregistrement", Status.BAD_REQUEST);
        }
        return utilisateur;
    }

    @Override
    public void deconnect(Utilisateur utilisateur) throws IsjException {
        Session session = iSession.findLastSessionUser(utilisateur.getCode());
        session.setDateDeconnection(new Date());
        iSession.saveSession(session);
    }

    @Override
    public Utilisateur ajouterDroitAUnUser(Utilisateur utilisateur, Long codeDroit) throws IsjException {
        Role role = iRole.getRoleByCode(codeDroit);
        List<Role> roles = utilisateur.getRoles();
        roles.add(role);
        utilisateur.setRoles(roles);
        return updateUtilisateur(utilisateur);
    }

    @Override
    public Map<String, Droit> droitsFinaux(Utilisateur u) throws IsjException {
        for (int i = 0; i < u.getRoles().size(); i++) {
            /*if (map.isEmpty()) {
                map.put(roles.get(i).getDroits().get(i).getLibelle(), roles.get(i).getDroits().get(i));
            }
            else */
            Role roleCourant = u.getRoles().get(i);
            //map.put(roles.get(i).getDroits().get(i).getLibelle(),(map.get(roles.get(i).getDroits().get(i).getLibelle()). || roles.get(i).getDroits().get(i)));
            for (int j = 0; j < roleCourant.getDroits().size(); j++) {
                Droit droitCourant = roleCourant.getDroits().get(j);
                if (map.containsKey(droitCourant.getCategorie())) {
                    Droit droitExistant = map.get(droitCourant.getCategorie());
                    droitCourant.setEcriture(droitCourant.isEcriture() || droitExistant.isEcriture());
                    droitCourant.setLecture(droitCourant.isLecture() || droitExistant.isLecture());
                    droitCourant.setModification(droitCourant.isModification() || droitExistant.isModification());
                    droitCourant.setSuppression(droitCourant.isSuppression() || droitExistant.isSuppression());

                    map.replace(droitCourant.getCategorie(), droitCourant);
                } else {
                    map.put(droitCourant.getCategorie(), droitCourant);
                }
            }
        }
        return map;
    }

    /**
     * ces fonctions permettent de definir si un utilisateur à des droits pour une classe précise
     *
     * @param c classe
     * @return vrai ou faux en fonction de si l'utilisateur peut ou ne peut pas
     */
    @Override
    public boolean peutLire(Class c, Long code) throws IsjException {
        return peutLire(c.getSimpleName(), code);
    }

    private boolean peutLire(String c, Long codeUser) throws IsjException{
        Utilisateur utilisateur = getUtilisateurByCode(codeUser);
        Map<String, Droit> droitMap = droitsFinaux(utilisateur);
        if (droitMap.get(c) != null && droitMap.get(c).isLecture())
              return true;
        else {
            //Mettre une exception de refus d'accès (vous ne pouvez pas accéder ces données
            throw new IsjException("vous ne pouvez pas accéder ces données", Status.BAD_REQUEST) ;

        }
    }

    @Override
    public boolean peutEcrire(Class c, Long code) throws IsjException {
        return peutEcrire(c.getSimpleName(), code);
    }

    private boolean peutEcrire(String c, Long codeUser) throws IsjException {
        Utilisateur utilisateur = getUtilisateurByCode(codeUser);
        Map<String, Droit> droitMap = droitsFinaux(utilisateur);
        if (map.get(c) != null && map.get(c).isEcriture())
            return true;
        else {
            throw new IsjException("vous ne pouvez pas accéder ces données", Status.BAD_REQUEST) ;

        }
    }
    @Override
    public boolean peutModifier(Class c, Long code) throws IsjException {
        return peutModifier(c.getSimpleName(), code);
    }
    private boolean peutModifier(String c, Long code) throws IsjException {
        return peutEcrire(c, code);
    }

    @Override
    public boolean peutSupprimer(Class c, Long code) throws IsjException {
        return peutSupprimer(c.getSimpleName(), code);
    }

    @Override
    public Utilisateur findByEmail(String email) {
        return utilisateurRepository.getUtilisateurByEmail(email);
    }

    public boolean peutSupprimer(String c, Long codeUser) throws IsjException {
        Utilisateur utilisateur = getUtilisateurByCode(codeUser);
        Map<String, Droit> droitMap = droitsFinaux(utilisateur);
        if (map.get(c) != null && map.get(c).isSuppression())
            return true;
        else {
            //Mettre une exception de refus d'accès (vous ne pouvez pas accéder ces données
            throw new IsjException("vous ne pouvez pas accéder ces données", Status.BAD_REQUEST) ;
        }
    }
}
