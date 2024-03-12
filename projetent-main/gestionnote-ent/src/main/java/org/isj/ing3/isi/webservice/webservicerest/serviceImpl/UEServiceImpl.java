package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Module;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.UE;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UERepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.IUE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UEServiceImpl implements IUE {
    @Autowired
    UERepository ueRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;


    @Override
    public int saveUE(UE ue) throws IsjException {

        Utilisateur createur = utilisateurRepository.findById(ue.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(ue.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        ue.setCreateur(createur);
        ue.setModificateur(modificateur);

        return ueRepository.save(ue).getCode().intValue();
    }

    @Override
    public List<UE> listUEs() {
        return ueRepository.findAll();
    }

    @Override
    public int deleteUE(Long code) throws IsjException {
        ueRepository.deleteById(getUEByCode(code).getCode());
        return 1;
    }

    @Override
    public UE getUEByCode(Long code) throws IsjException {

        return ueRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
    }

}
