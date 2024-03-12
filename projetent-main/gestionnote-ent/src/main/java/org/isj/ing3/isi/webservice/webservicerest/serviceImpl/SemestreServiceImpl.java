package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.AnneeAcademique;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Role;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Semestre;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.SemestreRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.ISemestre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zalando.problem.Status;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class SemestreServiceImpl implements ISemestre {

    @Autowired
    SemestreRepository semestreRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public Long saveSemestre(Semestre semestre) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(semestre.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = createur;
        semestre.setCreateur(createur);
        semestre.setModificateur(modificateur);
        Semestre semestreSave = semestreRepository.save(semestre);
        if (semestreSave == null) {
            throw new IsjException("Un problème est survenu lors de l'eneregistrement'', veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return semestreSave.getCode();
    }

    @Override
    public List<Semestre> listSemestre() {
        return semestreRepository.findAll();
    }

    @Override
    public int deleteSemestreByCode(Long code) {
        semestreRepository.deleteById(semestreRepository.findById(code).get().getCode());
        return 1;
    }

    @Override
    public Semestre searchSemestreByLibelleOrAnneeAcademique(String libelle, Long annee_academique) throws IsjException {
        return semestreRepository.retrouverSemestre(libelle,annee_academique).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
    }

    @Override
    public Long updateSemestre(Semestre semestre) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(semestre.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(semestre.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));

        semestre.setCreateur(createur);
        semestre.setModificateur(modificateur);
        Semestre semestreUpdate = semestreRepository.save(semestre);
        if (semestreUpdate == null) {
            throw new IsjException("Un problème est survenu lors de la modification', veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return semestreUpdate.getCode();
    }
}
