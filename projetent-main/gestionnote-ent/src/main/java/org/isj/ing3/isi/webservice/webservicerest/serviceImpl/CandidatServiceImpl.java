package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Candidat;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Classe;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.*;
import org.isj.ing3.isi.webservice.webservicerest.service.ICandidat;
import org.isj.ing3.isi.webservice.webservicerest.service.IClasse;
import org.isj.ing3.isi.webservice.webservicerest.utils.CHeckNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zalando.problem.Status;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CandidatServiceImpl implements ICandidat {
    @Autowired
    CandidatRepository candidatRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    ClasseRepository classeRepository;
    @Autowired
    IClasse iClasse;

    @Override
    public Candidat saveCandidat(Candidat candidat) throws IsjException {

        CHeckNull.checkStringIsNull(candidat.getEmail());
        checkEmailsAlreadyUsed(candidat.getEmail());
        Utilisateur createur = utilisateurRepository.findById(candidat.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = createur;
        Classe classe = classeRepository.findById(candidat.getClasse().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        candidat.setClasse(classe);
        candidat.setCreateur(createur);
        candidat.setModificateur(modificateur);
        Candidat candidatSave = candidatRepository.save(candidat);
        if (candidatSave == null) {
            throw new IsjException("Un problème est survenu lors de l'enregistrement', veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return candidatRepository.save(candidat);
    }

    @Override
    public List<Candidat> listCandidats() {
        return candidatRepository.findAll();
    }

    @Override
    public int deleteCandidat(Long code) throws IsjException{
        candidatRepository.deleteById(getCandidatByCode(code).getCode());
        return 1;
    }

    @Override
    public Candidat getCandidatByCode(Long code) throws IsjException {
        CHeckNull.checkLongIsNull(code);
        return candidatRepository.findById(code).orElseThrow(() -> new IsjException("Candidat introuvable", Status.NOT_FOUND));
    }

    @Override
    public List<Candidat> searchCandidatByClasse(Classe classe) throws IsjException {
        return candidatRepository.findCandidatByClasse(classe).get().stream().collect(Collectors.toList());
    }


    @Override
    public Candidat searchCandidatBytelephone(int telephone) throws IsjException {
        return candidatRepository.retrouverCandidatSms(telephone);
    }

    @Override
    public int updateCandidat(Candidat candidat) throws IsjException {
        CHeckNull.checkStringIsNull(candidat.getEmail());
        checkEmailsAlreadyUsed(candidat.getEmail());
        Utilisateur createur = utilisateurRepository.findById(candidat.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));;
        Utilisateur modificateur = utilisateurRepository.findById(candidat.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));;
        Classe classe = classeRepository.findById(candidat.getClasse().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));;
        candidat.setClasse(classe);
        candidat.setCreateur(createur);
        candidat.setModificateur(modificateur);
        Candidat candidatUpdate = candidatRepository.save(candidat);
        if (candidatUpdate == null) {
            throw new IsjException("Un problème est survenu lors de la modification, veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return candidatRepository.save(candidat).getCode().intValue();
    }

    @Override
    public Candidat searchCandidatByEmail(String email) throws IsjException {
        return candidatRepository.retrouverCandidatEmail(email);
    }


    private void checkEmailsAlreadyUsed(String email) throws IsjException {
        if (candidatRepository.findCandidatByEmail(email).isPresent()) throw new IsjException("Candidat introuvable",Status.NOT_FOUND);
    }
}
