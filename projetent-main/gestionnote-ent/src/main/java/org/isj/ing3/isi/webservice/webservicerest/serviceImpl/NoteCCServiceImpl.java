package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.*;
import org.isj.ing3.isi.webservice.webservicerest.repositories.MessageRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.NoteCCRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.ICandidat;
import org.isj.ing3.isi.webservice.webservicerest.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zalando.problem.Status;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class NoteCCServiceImpl implements INoteCC {
    @Autowired
    NoteCCRepository noteCCRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    ICandidat iCandidat;
    @Autowired
    ITypeNoteCC iTypeNoteCC;

    @Override
    public int saveNoteCC(NoteCC noteCC) throws IsjException{

        Utilisateur createur = utilisateurRepository.findById(noteCC.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = createur;
        noteCC.setCreateur(createur);
        noteCC.setModificateur(modificateur);
        NoteCC noteCCSave = noteCCRepository.save(noteCC);
        if (noteCCSave == null) {
            throw new IsjException("Un problème est survenu lors de l'enregistrement', veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }

        return noteCCSave.getCode().intValue();
    }

    @Override
    public List<NoteCC> listNoteCC() {
        List<NoteCC> noteCC = noteCCRepository.findAll();
        return noteCC;
    }

    @Override
    public int deleteNoteCC(Long code) throws IsjException {
        noteCCRepository.deleteById(getNoteCCByCode(code).getCode());
        return 1;
    }

    @Override
    public NoteCC getNoteCCByCode(Long code) throws IsjException {
        return noteCCRepository.findById(code).orElseThrow(() -> new IsjException("NoteCC introuvable" , Status.NOT_FOUND));
    }

    @Override
    public NoteCC searchNoteCCByCandidatOrTypeNoteCC(Long codeCandidat, Long codeTypeNoteCC) throws IsjException {
        Candidat candidat = iCandidat.getCandidatByCode(codeCandidat);
        TypeNoteCC typeNoteCC = iTypeNoteCC.getTypeNoteCCByCode(codeCandidat);
        return noteCCRepository.retrouverNoteCC(candidat,typeNoteCC).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
    }

    @Override
    public int updateNoteCC(NoteCC noteCC) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(noteCC.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(noteCC.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        noteCC.setCreateur(createur);
        noteCC.setModificateur(modificateur);
        NoteCC noteCCUpdate = noteCCRepository.save(noteCC);
        if (noteCCUpdate == null) {
            throw new IsjException("Un problème est survenu lors de la modification, veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return noteCCUpdate.getCode().intValue();
    }


}

