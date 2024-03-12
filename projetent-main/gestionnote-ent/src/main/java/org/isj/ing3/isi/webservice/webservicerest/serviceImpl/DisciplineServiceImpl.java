package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.*;
import org.isj.ing3.isi.webservice.webservicerest.repositories.*;
import org.isj.ing3.isi.webservice.webservicerest.service.IClasse;
import org.isj.ing3.isi.webservice.webservicerest.service.IDiscipline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zalando.problem.Status;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DisciplineServiceImpl implements IDiscipline {
    @Autowired
    DisciplineRepository disciplineRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    SemestreRepository semestreRepository;

    @Override
    public int saveDiscipline(Discipline discipline) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(discipline.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = createur;
        Etudiant etudiant = etudiantRepository.findById(discipline.getEtudiant().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Semestre semestre = semestreRepository.findById(discipline.getSemestre().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        discipline.setEtudiant(etudiant);
        discipline.setSemestre(semestre);
        discipline.setCreateur(createur);
        discipline.setModificateur(modificateur);
        Discipline disciplineSave = disciplineRepository.save(discipline);
        if (disciplineSave == null) {
            throw new IsjException("Un problème est survenu lors de l'enregistrement', veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }

        return disciplineSave.getCode().intValue();
    }

    @Override
    public List<Discipline> listDisciplines() {
        return disciplineRepository.findAll();
    }

    @Override
    public int deleteDiscipline(Long code) throws IsjException{
        disciplineRepository.deleteById(getDisciplineByCode(code).getCode());
        return 1;
    }

    @Override
    public Discipline getDisciplineByCode(Long code) throws IsjException {
        return disciplineRepository.findById(code).orElseThrow(() -> new IsjException("discipline introuvable ", Status.NOT_FOUND));
    }

    @Override
    public int updateDiscipline(Discipline discipline) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(discipline.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(discipline.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Etudiant etudiant = etudiantRepository.findById(discipline.getEtudiant().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Semestre semestre = semestreRepository.findById(discipline.getSemestre().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        discipline.setEtudiant(etudiant);
        discipline.setSemestre(semestre);
        discipline.setCreateur(createur);
        discipline.setModificateur(modificateur);
        Discipline disciplineUpdate = disciplineRepository.save(discipline);
        if (disciplineUpdate == null) {
            throw new IsjException("Un problème est survenu lors de la modification, veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return disciplineUpdate.getCode().intValue();
    }

    @Override
    public Discipline searchDisciplineByEtudiantOrSemestreOrDate(Etudiant etudiant, Semestre semestre, Date dateRetard) throws IsjException {
        return disciplineRepository.retrouverDiscipline(etudiant,semestre,dateRetard).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND)) ;
    }
}
