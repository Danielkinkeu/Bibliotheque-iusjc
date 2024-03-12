package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Filiere;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.HistoriqueNote;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.HistoriqueNoteRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.IHistoriqueNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zalando.problem.Status;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class HistoriqueNoteServiceImpl implements IHistoriqueNote {
    @Autowired
    HistoriqueNoteRepository historiqueNoteRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public int saveHistoriqueNote(HistoriqueNote historiqueNote) throws IsjException {

        Utilisateur createur = utilisateurRepository.findById(historiqueNote.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = createur;
        historiqueNote.setCreateur(createur);
        historiqueNote.setModificateur(modificateur);
        HistoriqueNote historiqueNoteSave = historiqueNoteRepository.save(historiqueNote);
        if (historiqueNoteSave == null) {
            throw new IsjException("Un problème est survenu lors de l'enregistrement, veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return historiqueNoteSave.getCode().intValue();

    }

    @Override
    public int updateHistoriqueNote (HistoriqueNote historiqueNote) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(historiqueNote.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(historiqueNote.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        historiqueNote.setCreateur(createur);
        historiqueNote.setModificateur(modificateur);
        HistoriqueNote historiqueNoteUpdate = historiqueNoteRepository.save(historiqueNote);
        if (historiqueNoteUpdate == null) {
            throw new IsjException("Un problème est survenu lors de la mise à jour, veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return historiqueNoteUpdate.getCode().intValue();
    }

    @Override
    public List<HistoriqueNote> listHistoriqueNotes() {
        return historiqueNoteRepository.findAll();
    }

    @Override
    public int deleteHistoriqueNote(Long code) {
        historiqueNoteRepository.deleteById(historiqueNoteRepository.findById(code).get().getCode());
        return 1;
    }

    @Override
    public HistoriqueNote getHistoriqueNoteByCode(Long code) throws IsjException {
        return historiqueNoteRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
    }


}
