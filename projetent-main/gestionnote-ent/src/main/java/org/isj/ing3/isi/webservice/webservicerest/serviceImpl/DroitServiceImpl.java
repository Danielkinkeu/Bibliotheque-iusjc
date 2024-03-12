package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Discipline;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Droit;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Role;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.*;
import org.isj.ing3.isi.webservice.webservicerest.service.IDiscipline;
import org.isj.ing3.isi.webservice.webservicerest.service.IDroit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zalando.problem.Status;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DroitServiceImpl implements IDroit {
    @Autowired
    DroitRepository droitRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public int saveDroit(Droit droit) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(droit.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = createur;
        Role role = roleRepository.findById(droit.getRole().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        droit.setCreateur(createur);
        droit.setModificateur(modificateur);
        droit.setRole(role);
        Droit droitSave = droitRepository.save(droit);
        if (droitSave == null) {
            throw new IsjException("Un problème est survenu lors de l'enregistrement, veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return droitSave.getCode().intValue();
    }

    @Override
    public List<Droit> listDroits() {
        return droitRepository.findAll();
    }

    @Override
    public int deleteDroit(Long code) {
        droitRepository.deleteById(droitRepository.findById(code).get().getCode());
        return 1;
    }

    @Override
    public Droit getDroitByCode(Long code) throws IsjException {
        return droitRepository.findById(code).orElseThrow(() -> new IsjException("Droit introuvable ", Status.NOT_FOUND));
    }

    @Override
    public int updateDroit(Droit droit) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(droit.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(droit.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Role role = roleRepository.findById(droit.getRole().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        droit.setCreateur(createur);
        droit.setModificateur(modificateur);
        droit.setRole(role);
        Droit droitUpdate = droitRepository.save(droit);
        if (droitUpdate == null) {
            throw new IsjException("Un problème est survenu lors de la modification, veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return droitUpdate.getCode().intValue();
    }
}
