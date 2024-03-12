package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Filiere;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Note;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.FiliereRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.IFiliere;
import org.isj.ing3.isi.webservice.webservicerest.utils.CHeckNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zalando.problem.Status;

import java.util.List;
import java.util.stream.Collectors;
@Transactional
@Service
public class FiliereServiceImpl implements IFiliere {
    @Autowired
    FiliereRepository filiereRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public int saveFiliere(Filiere filiere) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(filiere.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = createur;
        filiere.setCreateur(createur);
        filiere.setModificateur(modificateur);
        Filiere filiereSave = filiereRepository.save(filiere);
        if (filiereSave == null) {
            throw new IsjException("Un problème est survenu lors de l'enregistrement, veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return filiereSave.getCode().intValue();
    }

    @Override
    public List<Filiere> listFilieres() {
        return filiereRepository.findAll();
    }

    @Override
    public int deleteFiliere(Long code) {
        filiereRepository.deleteById(filiereRepository.findById(code).get().getCode());
        return 1;
    }

    @Override
    public int updateFiliere (Filiere filiere) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(filiere.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(filiere.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        filiere.setCreateur(createur);
        filiere.setModificateur(modificateur);
        Filiere filiereUpdate = filiereRepository.save(filiere);
        if (filiereUpdate == null) {
            throw new IsjException("Un problème est survenu lors de la mise à jour, veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return filiereUpdate.getCode().intValue();
    }

    @Override
    public Filiere getFiliereByCode(Long code) throws IsjException {
        return filiereRepository.findById(code).orElseThrow(() -> new IsjException("Filiere introuvable", Status.NOT_FOUND));
    }

    @Override
    public Filiere getFiliereByLibelleFiliere(String libelleFiliere) throws IsjException {
        return filiereRepository.retrouverFiliere(libelleFiliere).orElseThrow(() -> new IsjException("Filiere introuvable", Status.NOT_FOUND)) ;   }
}
