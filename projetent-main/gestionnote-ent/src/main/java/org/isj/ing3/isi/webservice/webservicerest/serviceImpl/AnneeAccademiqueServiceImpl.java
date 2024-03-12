package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.AnneeAcademique;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.*;
import org.isj.ing3.isi.webservice.webservicerest.service.IAnneeAcademique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zalando.problem.Status;

import java.util.Date;
import java.util.List;

@Service
public class AnneeAccademiqueServiceImpl implements IAnneeAcademique {
    @Autowired
    AnneeAcademiqueRepository anneeAcademiqueRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;


    @Override
    public int saveAnneeAcademique(AnneeAcademique annee_academique) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(annee_academique.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = createur;
        annee_academique.setCreateur(createur);
        annee_academique.setLibelle((annee_academique.getDateDebut().getYear()+1900)+"/"+(annee_academique.getDateCloture().getYear()+1900));
        annee_academique.setModificateur(modificateur);
        AnneeAcademique anneeAcademiqueSave = anneeAcademiqueRepository.save(annee_academique);
        if (anneeAcademiqueSave == null){
            throw new IsjException("un problème est survenu lors de l'enregistrement,veuillez reéssayer plus tard ", Status.INTERNAL_SERVER_ERROR);
        }
        return  anneeAcademiqueSave.getCode().intValue();
    }

    @Override
    public int updateAnneeAcademique(AnneeAcademique anneeAcademique) throws IsjException {
        Utilisateur createur =utilisateurRepository.findById(anneeAcademique.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(anneeAcademique.getCreateur().getCode()).orElseThrow(()->new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        anneeAcademique.setCreateur(createur);
        anneeAcademique.setModificateur(modificateur);
        AnneeAcademique anneeAcademiqueUpdate = anneeAcademiqueRepository.save(anneeAcademique);
        if (anneeAcademiqueUpdate == null){
            throw new IsjException("Un problème est survenu lors de la mise à jour, veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return anneeAcademiqueUpdate.getCode().intValue();
    }

    @Override
    public List<AnneeAcademique> listAnneeAcademique() {
        return anneeAcademiqueRepository.findAll();
    }

    @Override
    public int deleteAnneAcademique(Long code) throws IsjException {
        anneeAcademiqueRepository.deleteById(getAnneAcademiqueByCode(code).getCode());
        return 1;
    }

    @Override
    public AnneeAcademique getAnneAcademiqueByCode(Long code) throws IsjException {
        return anneeAcademiqueRepository.findById(code).orElseThrow(() -> new IsjException("Année academique introuvable",Status.NOT_FOUND));
    }

    @Override
    public AnneeAcademique getAnneeAcademiqueByDate(Date date) throws IsjException {
        return anneeAcademiqueRepository.retrouverAnneeAcademique(date).orElseThrow(()-> new IsjException("année academique introuvable",Status.NOT_FOUND));
    }
}
