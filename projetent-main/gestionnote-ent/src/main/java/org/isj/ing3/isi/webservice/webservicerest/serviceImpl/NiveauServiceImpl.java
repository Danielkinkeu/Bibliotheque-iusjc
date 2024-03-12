package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Filiere;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Niveau;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.NiveauRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.INiveau;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zalando.problem.Status;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class NiveauServiceImpl implements INiveau {
    @Autowired
    NiveauRepository niveauRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public int saveNiveau(Niveau niveau) throws IsjException{

        Utilisateur createur = utilisateurRepository.findById(niveau.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = createur;
        niveau.setCreateur(createur);
        niveau.setModificateur(modificateur);
        Niveau niveauSave = niveauRepository.save(niveau);
        if (niveauSave == null) {
            throw new IsjException("Un problème est survenu lors de l'enregistrement, veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return niveauSave.getCode().intValue();

    }

    @Override
    public int updateNiveau (Niveau niveau) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(niveau.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(niveau.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        niveau.setCreateur(createur);
        niveau.setModificateur(modificateur);
        Niveau niveauUpdate = niveauRepository.save(niveau);
        if (niveauUpdate == null) {
            throw new IsjException("Un problème est survenu lors de la mise à jour, veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return niveauUpdate.getCode().intValue();
    }

    @Override
    public List<Niveau> listNiveaus() {
        return niveauRepository.findAll();
    }

    @Override
    public int deleteNiveau(Long code) throws IsjException {
        niveauRepository.deleteById(getNiveauByCode(code).getCode());
        return 1;
    }

    @Override
    public Niveau getNiveauByCode(Long code) throws IsjException {
        return niveauRepository.findById(code).orElseThrow(() -> new IsjException("Niveau introuvable", Status.NOT_FOUND));
    }

    @Override
    public Niveau getNiveauByNumero(long niveau) throws IsjException {
        return niveauRepository.retrouverNiveau(niveau).orElseThrow(() -> new IsjException("Niveau introuvable", Status.NOT_FOUND)) ;   }
}
