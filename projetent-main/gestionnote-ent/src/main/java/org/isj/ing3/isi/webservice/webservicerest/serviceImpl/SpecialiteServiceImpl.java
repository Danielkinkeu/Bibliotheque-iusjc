package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Sms;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Specialite;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.SpecialiteRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.ISpecialite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zalando.problem.Status;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class SpecialiteServiceImpl implements ISpecialite {
    @Autowired
    SpecialiteRepository specialiteRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public Long saveSpecialite(Specialite specialiteDto) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(specialiteDto.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = createur;
        specialiteDto.setCreateur(createur);
        specialiteDto.setModificateur(modificateur);
        Specialite specialiteSave = specialiteRepository.save(specialiteDto);
        if (specialiteSave == null) {
            throw new IsjException("Un problème est survenu lors de l'enregistrement'', veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return specialiteSave.getCode();
    }

    @Override
    public List<Specialite> listSpecialite() {

        return specialiteRepository.findAll();
    }

    @Override
    public int deleteSpecialiteByCode(Long code) throws IsjException {
        specialiteRepository.deleteById(specialiteRepository.findById(code).get().getCode());
        return 1;
    }

    @Override
    public Specialite searchSpecialiteBySpecialiteOrfiliere (String specialite, String filiere) throws IsjException {
        return specialiteRepository.retrouverSpecialite(specialite,filiere ).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
    }

    @Override
    public Long updateSpecialite(Specialite specialiteDto) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(specialiteDto.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(specialiteDto.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));

        specialiteDto.setCreateur(createur);
        specialiteDto.setModificateur(modificateur);
        Specialite specialiteUpdate = specialiteRepository.save(specialiteDto);
        if (specialiteUpdate == null) {
            throw new IsjException("Un problème est survenu lors de la modification'', veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return specialiteUpdate.getCode();
    }
}
