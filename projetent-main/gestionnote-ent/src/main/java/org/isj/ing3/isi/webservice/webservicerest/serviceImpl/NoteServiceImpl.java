package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.*;
import org.isj.ing3.isi.webservice.webservicerest.repositories.EstInscritRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.NoteRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.INote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zalando.problem.Status;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class NoteServiceImpl implements INote {
    @Autowired
    NoteRepository noteRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public int saveNote(Note note) throws IsjException {

        Utilisateur createur = utilisateurRepository.findById(note.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = createur;
        note.setCreateur(createur);
        note.setModificateur(modificateur);
        Note noteSave = noteRepository.save(note);
        if (noteSave == null) {
            throw new IsjException("Un problème est survenu lors de l'enregistrement, veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return noteSave.getCode().intValue();
    }

    @Override
    public int updateNote (Note note) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(note.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(note.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        note.setCreateur(createur);
        note.setModificateur(modificateur);
        Note noteUpdate = noteRepository.save(note);
        if (noteUpdate == null) {
            throw new IsjException("Un problème est survenu lors de la mise à jour, veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return noteUpdate.getCode().intValue();
    }

    @Override
    public List<Note> listNotes() {
        return noteRepository.findAll();
    }

    @Override
    public List<Note> listNotesWithLimit() {
        return noteRepository.listeNotes();
    }

    @Override
    public int deleteNote(Long code) {
        noteRepository.deleteById(noteRepository.findById(code).get().getCode());
        return 1;
    }

    @Override
    public Note getNoteByCode(Long code) throws IsjException {
        return noteRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
    }

    @Override
    public Note getNoteByCodeInscritCodeEvaluation(Long inscrit, Long eval) throws IsjException {
        return noteRepository.retrouverNote(inscrit, eval).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND)) ;   }

    @Override
    public List<Note> getNotesByFiliereAndSpecialiteAndNiveauAndAnneDebutOrderByCandidat(String libellefiliere, String libellespecialite, int numero, int anneDebut) {
        return noteRepository.listNotesByFiliereAndSpecialiteAndNiveauAndAnneDebutOrderByCandidat(libellefiliere, libellespecialite, numero, anneDebut);
    }

    @Override
    public List<Note> getNotesByFiliereAndSpecialiteAndNiveauAndAnneDebutAndSemestreAndTypeEvaluationAndUeOrderByCandidat(String libellefiliere, String libellespecialite, int numero, int anneDebut, String libelleSemestre, String libelleTypeNote, String codeUe) {
        return noteRepository.listNotesByFiliereAndSpecialiteAndNiveauAndAnneDebutAndSemestreAndTypeEvaluationAndUeOrderByCandidat(libellefiliere, libellespecialite, numero, anneDebut, libelleSemestre, libelleTypeNote, codeUe);
    }


}
