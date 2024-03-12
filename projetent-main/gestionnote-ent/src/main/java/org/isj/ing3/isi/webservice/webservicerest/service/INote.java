package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.EstInscrit;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Evaluation;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Filiere;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Note;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface INote {

    int saveNote (Note note) throws IsjException;
    List<Note> listNotes();
    List<Note> listNotesWithLimit();
    int deleteNote(Long code);
    Note getNoteByCode(Long code) throws IsjException;
    int updateNote (Note note) throws IsjException;
    Note getNoteByCodeInscritCodeEvaluation(Long inscrit, Long eval) throws IsjException;
    List<Note> getNotesByFiliereAndSpecialiteAndNiveauAndAnneDebutOrderByCandidat(String libellefiliere, String libellespecialite, int numero, int anneDebut);
    List<Note> getNotesByFiliereAndSpecialiteAndNiveauAndAnneDebutAndSemestreAndTypeEvaluationAndUeOrderByCandidat(String libellefiliere, String libellespecialite, int numero, int anneDebut, String libelleSemestre, String libelleTypeNote, String codeUe);
}
