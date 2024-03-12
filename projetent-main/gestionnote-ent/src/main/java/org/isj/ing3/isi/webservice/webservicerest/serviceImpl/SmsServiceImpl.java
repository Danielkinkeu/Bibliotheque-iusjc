package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Session;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Sms;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.SmsRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.ISms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zalando.problem.Status;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class SmsServiceImpl implements ISms {
    @Autowired
    SmsRepository smsRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public Long saveSms(Sms sms) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(sms.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = createur;
        sms.setCreateur(createur);
        sms.setModificateur(modificateur);
        Sms smsSave = smsRepository.save(sms);
        if (smsSave == null) {
            throw new IsjException("Un problème est survenu lors de l'enregistrement'', veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return smsSave.getCode();
    }

    @Override
    public List<Sms> listSms() {
        return smsRepository.findAll();
    }

    @Override
    public int deleteSmsByCode(Long code) throws IsjException {
        smsRepository.deleteById(smsRepository.findById(code).get().getCode());
        return 1;
    }

    @Override
    public Long updateSms(Sms sms) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(sms.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(sms.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));

        sms.setCreateur(createur);
        sms.setModificateur(modificateur);
        Sms smsUpdate = smsRepository.save(sms);
        if (smsUpdate == null) {
            throw new IsjException("Un problème est survenu lors de la modification'', veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return smsUpdate.getCode();
    }
}
