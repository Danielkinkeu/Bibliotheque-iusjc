package org.isj.ing3.isi.webservice.webservicerest.securite;


import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Droit;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Role;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Session;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.model.modeletat.Connection;
import org.isj.ing3.isi.webservice.webservicerest.repositories.SessionRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.ISession;
import org.isj.ing3.isi.webservice.webservicerest.service.IUtilisateur;
import org.isj.ing3.isi.webservice.webservicerest.utils.enumaration.Statut;
import org.isj.ing3.isi.webservice.webservicerest.utils.enumaration.StatutSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.NoResultException;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * cette classe regroupe les methodes permettant l'authentification, la hachage du mot de passe, la creation et l'attribution des  droits et roles
 */
@RestController
@RequestMapping("/api/connexion")
@Slf4j
public class Connexion {
    /**
     * fonction permettant de creer des bits
     *
     * @param array tableau des codes
     * @return sb.toString().toUpperCase()
     */
    public static Utilisateur utilisateurCourant;
    @Autowired
    ISession iSession;
    @Autowired


    static String toHexString(byte[] array) {
        StringBuilder sb = new StringBuilder(array.length * 2);

        for (byte b : array) {

            int value = 0xFF & b;
            String toAppend = Integer.toHexString(value);
            sb.append(toAppend).append("-");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString().toUpperCase();
    }

    /**
     * Fonction permettant d'&voir un message hashé avec sha-512;
     *
     * @param a a est une chaine de caractere representant un mot de passe a hasher
     * @return Une chaine hashé à partir d'une chaine simple
     */
    public String hachage(String a) {
        String msgHash = null;
        try {

            MessageDigest md = MessageDigest.getInstance("sha-512");

            byte[] hash = md.digest(a.getBytes());
            //System.out.println("message:");
            msgHash = toHexString(hash);

            //System.out.println("message hash:" +msgHash);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return msgHash;

    }

    /**
     * Fonction qui permet de retourner le nom d'une machine
     */
    public static String getComputerFullName(){
        String hostname = null;
        try{
            final InetAddress ADDR = InetAddress.getLocalHost();
            hostname = new String(ADDR.getHostName());
        }catch(final Exception e){
            e.printStackTrace();
        }
        return hostname;
    }

    Session session = new Session();
    //SessionFacade sessionFacade = new SessionFacade();
    /**
     * Fonction qui permet authentifier un utilisateur
     * Fonction qui ouvre une session lorsqu'un utilisateur est trouvé
     * @param login le nom d'utilisateur
     * @param mdp   le mot de passe de l'utilisateur
     * @return Un utilisateur ou null en fonction de s'il est présent ou pas en base de donnée
     */

    @Autowired
    private ISession sessionRepository;

    @PostMapping("/connect")
    public ResponseEntity<Utilisateur> connect(@RequestBody Connection connection) throws IsjException {
        String login=connection.getLogin();
        String mdp=connection.getPassword();
        String hashmdp = hachage(mdp);
        utilisateurCourant=authentification(login, hashmdp);
        if (utilisateurCourant != null) {
            droitsFinaux(utilisateurCourant);
/*            //d.getRoles();
            UtilisateurFacade uf = new UtilisateurFacade();
            Utilisateur utilisateur = uf.find(new Long(1));
            List<Role> roles = utilisateur.getRoles();
            for (int i = 0; i < roles.size(); i++) {
                System.out.println(roles.get(i).getLibelle());
                List<Droit> droits = roles.get(i).getDroits();
                for (int j = 0; j < droits.size(); j++) {
                    System.out.println(droits.get(j).toString());
                }
            }*/
            Date dateConnexion = new Date();
            String hostname = getComputerFullName();
            session.setDateConnection(dateConnexion);
            session.setMachineCliente(hostname);
            session.setStatut(StatutSession.ACTIF );
            session.setCreateur(utilisateurCourant);
            sessionRepository.saveSession(session);
        }
        return  ResponseEntity.ok(utilisateurCourant);
    }

    @Autowired
    private IUtilisateur utilisateurService;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur authentification(String login, String password) throws NoResultException {
        try {
            return utilisateurRepository.getUserByLoginAndPw(login, (password), Statut.ACTIVE).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Fonction permettant de mettre à jour la session d'un utilisateur en inscrivant la date de deconnexion et le statut
     */
    public  void  deconnexion(){
        Date dateDeconnexion = new Date();
        session.setStatut(StatutSession.NONACTIF);
        session.setDateDeconnection(dateDeconnexion);
        //sessionFacade.merge(session);
    }

    static Map<String, Droit> map = new HashMap<String, Droit>();

    /**
     * Fonction qui permet de lister les autorisation d'un utilisateur
     *
     * @param u c'est un utilisateur
     * @return les droits de cet utilisaateur
     */
    public Map<String, Droit> droitsFinaux(Utilisateur u) {


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
     * Fonction qui permet affecter un utilisateur a un role
     *
     * @param user c'est un utilisateur
     * @param role c'est un role
     */
    public void affecterUtilisateurRole(Utilisateur user, Role role) {
        role.getUtilisateurs().add(user);
        user.getRoles().add(role);
    }

    /**
     * ces fonctions permettent de definir si un utilisateur à des droits pour une classe précise
     *
     * @param c classe
     * @return vrai ou faux en fonction de si l'utilisateur peut ou ne peut pas
     */
    public static boolean peutLire(Class c) {
        return peutLire(c.getSimpleName());
    }

    public static boolean peutLire(String c) {

        if (map.get(c) != null && map.get(c).isLecture())
            return true;
        else {
            //Mettre une exception de refus d'accès (vous ne pouvez pas accéder ces données
            return false;
        }
    }

    public static boolean peutEcrire(Class c) {
        return peutEcrire(c.getSimpleName());
    }

    public static boolean peutEcrire(String c) {
        if (map.get(c) != null && map.get(c).isEcriture())
            return true;
        else {
         /*   Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(Appli.getPrimaryStage);
            alert.setTitle("ISJ");
            alert.setHeaderText("Droits d'accès");
            alert.setContentText("Vous ne pouvez enregistrer ces données !");
            alert.show();*/
            return false;
        }
    }
    public static boolean peutModifier(Class c) {
        return peutModifier(c.getSimpleName());
    }
    public static boolean peutModifier(String c) {
        return peutEcrire(c);
    }

    public static boolean peutSupprimer(Class c) {
        return peutSupprimer(c.getSimpleName());
    }
    public static boolean peutSupprimer(String c) {

        if (map.get(c) != null && map.get(c).isSuppression())
            return true;
        else {
            //Mettre une exception de refus d'accès (vous ne pouvez pas accéder ces données
            return false;
        }
    }

    /**
     * Fonction permettant de lister les droits d'un utilisateur
     *

     */


    int tableau[] = new int[4];


    /**
     * fonction permettant d'affecter des roles a un utilisateur
     *
     * @param u represente l'utilisateur
     * @param r represente la liste des roles
     */
    public void affecterRole(Utilisateur u, List<Role> r) {

        for (int i = 0; i < r.size(); i++) {
            u.getRoles().add(r.get(i));
        }
       //ajouter un rôle un à un user
    }

}

