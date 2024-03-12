package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.HistoriqueNote;
import org.isj.ing3.isi.webservice.webservicerest.service.IAnonymat;
import org.isj.ing3.isi.webservice.webservicerest.service.IHistoriqueNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historiqueNote")
@Slf4j
public class HistoriqueNoteRestController {

	@Autowired
	private IHistoriqueNote iHistoriqueNote;

	@PostMapping("/save")
	public String enregistrer(@RequestBody HistoriqueNote create) throws IsjException {

		try {
			iHistoriqueNote.saveHistoriqueNote(create);
		}catch (IsjException exception) {
			return exception.getMessage();
		}
		return "Enregistrement réussi";

	}


	@GetMapping("/{code}/data")
	public ResponseEntity<?> getHistoriqueNoteByCode(@PathVariable("code") Long code) throws IsjException {

		try {
			return ResponseEntity.ok(iHistoriqueNote.getHistoriqueNoteByCode(code));
		}catch (IsjException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}

	}


	@GetMapping("/all")
	public ResponseEntity<List<HistoriqueNote>> getAllHistoriqueNote() {
		return ResponseEntity.ok(iHistoriqueNote.listHistoriqueNotes());
	}

	@GetMapping("/{code}/delete")
	public int deteleHistoriqueNote(@PathVariable("code") Long code){
		return iHistoriqueNote.deleteHistoriqueNote(code);
	}

}