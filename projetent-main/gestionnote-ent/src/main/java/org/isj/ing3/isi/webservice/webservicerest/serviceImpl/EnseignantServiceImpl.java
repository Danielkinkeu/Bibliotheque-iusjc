package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Enseignant;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Filiere;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.EnseignantRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.IEnseignant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zalando.problem.Status;

import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional
public class EnseignantServiceImpl implements IEnseignant {
    @Autowired
    EnseignantRepository enseignantRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public int saveEnseignant(Enseignant enseignant) throws IsjException {
        System.out.println(enseignant.getCreateur().getCode());
        Utilisateur createur = utilisateurRepository.findById(enseignant.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = createur;
        enseignant.setCreateur(createur);
       enseignant.setModificateur(modificateur);
       Enseignant enseignantSave = enseignantRepository.save(enseignant);
       if(enseignantSave == null){
           throw  new IsjException("un problème est survenu lors de l'enregistrement, veuillez reéssayer plus tard", Status.INTERNAL_SERVER_ERROR);
       }
        return enseignantSave.getCode().intValue();
    }

    @Override
    public int deleteEnseignant(Long code) throws  IsjException{
        enseignantRepository.deleteById(enseignantRepository.findById(code).get().getCode());
        return 1;
    }

    @Override
    public int updateEnseignant(Enseignant enseignant) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(enseignant.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(enseignant.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        enseignant.setCreateur(createur);
        enseignant.setModificateur(modificateur);
        Enseignant enseignantUpdate = enseignantRepository.save(enseignant);
        if (enseignantUpdate == null) {
            throw new IsjException("Un problème est survenu lors de la mise à jour, veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return enseignantUpdate.getCode().intValue();
    }


    @Override
    public List<Enseignant> listEnseignants() {
        return enseignantRepository.findAll();
    }

    @Override
    public Enseignant getEnseignantByCode(Long code) throws IsjException {
        return enseignantRepository.findById(code).orElseThrow(() -> new IsjException("Enseignant introuvable",Status.NOT_FOUND));
    }

    @Override
    public int nbEnseignant() {
        return enseignantRepository.nbEtudiant();
    }


}
