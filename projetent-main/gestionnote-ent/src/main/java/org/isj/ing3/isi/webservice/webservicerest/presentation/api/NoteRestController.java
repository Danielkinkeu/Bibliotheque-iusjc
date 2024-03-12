package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.ModelPostDataForHigthRequest.PostNoteForList;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Note;
import org.isj.ing3.isi.webservice.webservicerest.service.IAnonymat;
import org.isj.ing3.isi.webservice.webservicerest.service.INote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/note")
@Slf4j
public class NoteRestController {

	@Autowired
	private INote iNote;

	@PostMapping("/save")
	public String enregistrer(@RequestBody Note create) throws IsjException {

		try {
			iNote.saveNote(create);
		}catch (IsjException exception) {
			return exception.getMessage();
		}

		return "Enregistrement réussi";

	}


	@GetMapping("/{code}/data")
	public ResponseEntity<?> getNoteByCode(@PathVariable("code") Long code) throws IsjException {

		try {
			return ResponseEntity.ok(iNote.getNoteByCode(code));
		}catch (IsjException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}

	}


	@GetMapping("/all")
	public ResponseEntity<List<Note>> getAllNote() {
		return ResponseEntity.ok(iNote.listNotes());
	}

	@GetMapping("/{code}/delete")
	public int deleteNote(@PathVariable("code") Long code){
		return iNote.deleteNote(code);
	}


	@GetMapping("/{inscrit}/{eval}/search")
	public ResponseEntity<?> getNoteByCodeInscritCodeEvaluation(@PathVariable("inscrit") Long inscrit, @PathVariable("eval") Long eval) throws IsjException {
		try {
			return ResponseEntity.ok(iNote.getNoteByCodeInscritCodeEvaluation(inscrit, eval));
		}catch (IsjException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/list/note/byfiliere/specialite/niveau/semestre/ue")
	public ResponseEntity<List<Note>>  getNotesByFiliereAndSpecialiteAndNiveauAndAnneDebutAndSemestreAndTypeEvaluationAndUeOrderByCandidat(@RequestBody PostNoteForList note) {
		return new ResponseEntity<>(iNote.getNotesByFiliereAndSpecialiteAndNiveauAndAnneDebutAndSemestreAndTypeEvaluationAndUeOrderByCandidat(note.getLibellefiliere(), note.getLibellespecialite(), note.getNumero(), note.getAnneDebut(), note.getLibelleSemestre(), note.getLibelleTypeNote(), note.getCodeUe()), HttpStatus.NOT_FOUND);
	}

	@PostMapping("/list/note/byfiliere/specialite/niveau/")
	public ResponseEntity<List<Note>>  getNotesByFiliereAndSpecialiteAndNiveauAndAnneDebutOrderByCandidat(@RequestBody PostNoteForList note) {
		return new ResponseEntity<>(iNote.getNotesByFiliereAndSpecialiteAndNiveauAndAnneDebutOrderByCandidat(note.getLibellefiliere(), note.getLibellespecialite(), note.getNumero(), note.getAnneDebut()), HttpStatus.OK);
	}
}