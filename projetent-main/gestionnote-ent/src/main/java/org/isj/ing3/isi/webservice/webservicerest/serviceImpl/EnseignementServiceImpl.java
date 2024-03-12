package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Enseignement;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Filiere;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.EnseignementRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.IEnseignement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zalando.problem.Status;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EnseignementServiceImpl implements IEnseignement {
    @Autowired
    EnseignementRepository enseignementRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public int saveEnseignement(Enseignement enseignement) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(enseignement.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur =createur;

        enseignement.setCreateur(createur);
        enseignement.setModificateur(modificateur);
        Enseignement enseignementSave = enseignementRepository.save(enseignement);
        if (enseignementSave==null){
            throw  new IsjException("un problème est survenu lors de l'enregistrement, veuillez reéssayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return enseignementSave.getCode().intValue();
    }

    @Override

    public int deleteEnseignement(Long code) throws IsjException {
        enseignementRepository.deleteById(getEnseignementByCode(code).getCode());
        return 1;
    }

    @Override
    public int updateEnseignement(Enseignement enseignement) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(enseignement.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(enseignement.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        enseignement.setCreateur(createur);
        enseignement.setModificateur(modificateur);
        Enseignement enseignementUpdate = enseignementRepository.save(enseignement);
        if (enseignementUpdate == null){
            throw new IsjException("Un problème est survenu lors de la mise à jour, veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);

        }
        return enseignementUpdate.getCode().intValue();
    }


    @Override
    public List<Enseignement> listEnseignements() {
        return enseignementRepository.findAll();
    }


    @Override
    public Enseignement getEnseignementByCode(Long code) throws IsjException {
        return enseignementRepository.findById(code).orElseThrow(() -> new IsjException("Enseignement introuvable",Status.NOT_FOUND));
    }

}
