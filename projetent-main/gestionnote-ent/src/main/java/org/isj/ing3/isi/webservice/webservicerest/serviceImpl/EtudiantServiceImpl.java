package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Etudiant;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.EtudiantRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.IEtudiant;
import org.isj.ing3.isi.webservice.webservicerest.service.IPrintService;
import org.isj.ing3.isi.webservice.webservicerest.utils.etats.GeneratePDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zalando.problem.Status;

import java.util.List;

@Service
@Transactional
public class EtudiantServiceImpl implements IEtudiant {
    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public int saveEtudiant(Etudiant etudiant) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(etudiant.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = createur;
        etudiant.setCreateur(createur);
        etudiant.setModificateur(modificateur);
        Etudiant etudiantSave = etudiantRepository.save(etudiant);
        if(etudiantSave == null){
            throw new IsjException("un problème est survenu lors de l'enregistrement, veuillez reéssayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return etudiantSave.getCode().intValue();

    }
    @Override
    public int deleteEtudiant(Long code) throws  IsjException {
      etudiantRepository.deleteById(etudiantRepository.findById(code).get().getCode());
        return 1;
    }

    @Override
    public List<Etudiant> listEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public int updateEtudiant(Etudiant etudiant) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(etudiant.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(etudiant.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        etudiant.setCreateur(createur);
        etudiant.setModificateur(modificateur);
        Etudiant etudiantUpdate = etudiantRepository.save(etudiant);
        if (etudiantUpdate == null){
            throw new IsjException("Un problème est survenu lors de la mise à jour, veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return etudiantUpdate.getCode().intValue();
    }


    @Override
    public Etudiant searchEtudiantByCodeEtudiant(Long codeEtudiant) throws  IsjException{
        return etudiantRepository.retrouverEtudiant(codeEtudiant).orElseThrow(()-> new IsjException("Etudiant introuvable",Status.NOT_FOUND));

    }



    @Override
    public Etudiant getEtudiantByCode(Long code) throws IsjException {
        return etudiantRepository.findById(code).orElseThrow(() -> new IsjException("Etudiant introuvable",Status.NOT_FOUND));
    }

    @Override
    public Etudiant getStudentByMatricule(String matricule) throws IsjException {
        return etudiantRepository.findByMatricule(matricule).orElseThrow(() -> new IsjException("Matricule introuvable",Status.NOT_FOUND));
    }


    @Override
    public void generateAttestationReusite() throws Exception {

    }

    @Override
    public int nbEtudiant() {
        return etudiantRepository.getNbStudent();
    }
}
