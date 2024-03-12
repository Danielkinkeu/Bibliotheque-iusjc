package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Candidat;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Enseignement;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.EstInscrit;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.EnseignementRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.EstInscritRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.ICandidat;
import org.isj.ing3.isi.webservice.webservicerest.service.IEnseignement;
import org.isj.ing3.isi.webservice.webservicerest.service.IEstInscrit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zalando.problem.Status;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EstInscritServiceImpl implements IEstInscrit {
    @Autowired
    EstInscritRepository estInscritRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    IEnseignement iEnseignement;
    @Autowired
    EnseignementRepository enseignementRepository;
    @Autowired
    ICandidat iCandidat;


    @Override
    public int saveInscrit(EstInscrit estInscrit) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(estInscrit.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = createur;

        estInscrit.setCreateur(createur);
        estInscrit.setModificateur(modificateur);
        EstInscrit estInscritSave = estInscritRepository.save(estInscrit);
        if (estInscritSave == null){
            throw  new IsjException("un problème est survenu lors de l'enregistrement, veuillez reéssayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return estInscritSave.getCode().intValue();
    }

    @Override
    public int deleteInscrit(Long code) throws IsjException {
        estInscritRepository.deleteById(estInscritRepository.findById(code).get().getCode());
        return 1;
    }

    @Override
    public int updateEstInscrit(EstInscrit estInscrit) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(estInscrit.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(estInscrit.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        estInscrit.setCreateur(createur);
        estInscrit.setModificateur(modificateur);
        EstInscrit estInscritUpdate  = estInscritRepository.save(estInscrit);
        if (estInscritUpdate == null){
            throw new IsjException("Un problème est survenu lors de la mise à jour, veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return estInscritUpdate.getCode().intValue();
    }

    @Override
    public List<EstInscrit> listInscrit() {
        return estInscritRepository.findAll();
    }


    @Override
    public EstInscrit ListeDesInscrit(Long code) throws IsjException {
        Enseignement enseignement= iEnseignement.getEnseignementByCode(code);
        return estInscritRepository.retrouverListeEstInscrit(enseignement).orElseThrow(()-> new IsjException("Enseignement introuvable",Status.NOT_FOUND));

    }


    @Override
    public EstInscrit getInscritByCode(Long code) throws IsjException {
        return estInscritRepository.findById(code).orElseThrow(() -> new IsjException("Inscrit introuvable", Status.NOT_FOUND));
    }

    @Override
    public EstInscrit getInscritByCandidatInscritAndEnseignement(Long CodeCandidat, Long CodeEnseignement) throws IsjException {
        Enseignement enseignement = iEnseignement.getEnseignementByCode(CodeEnseignement);
        Candidat candidatInscrit = iCandidat.getCandidatByCode(CodeCandidat);
        return estInscritRepository.retrouverEstInscrit(candidatInscrit,enseignement).orElseThrow(()-> new IsjException("le candidat Inscrit  et l'enseignement sont introuvable",Status.NOT_FOUND));
    }

}
