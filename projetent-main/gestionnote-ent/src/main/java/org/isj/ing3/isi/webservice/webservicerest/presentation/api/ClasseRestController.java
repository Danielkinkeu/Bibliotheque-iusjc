package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Candidat;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Classe;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.TypeNoteCC;
import org.isj.ing3.isi.webservice.webservicerest.service.ICandidat;
import org.isj.ing3.isi.webservice.webservicerest.service.IClasse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classe")
@Slf4j
public class ClasseRestController {

	@Autowired
	private IClasse iClasse;

	@PostMapping("/save")
	public String enregistrer(@RequestBody Classe create) throws IsjException {
		try {
			iClasse.saveClasse(create);
		}catch (IsjException exception) {
			return exception.getMessage();
		}

		return "enregistrement reussi";


	}


	@GetMapping("/{code}/data")
	public ResponseEntity<?> getClassByCode(@PathVariable("code") Long code) throws IsjException {
		try {
			return ResponseEntity.ok(iClasse.getClasseByCode(code));
		}catch (IsjException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}

	}


	@GetMapping("/all")
	public ResponseEntity<List<Classe>> getAllClass() {
		return ResponseEntity.ok(iClasse.listClasses());
	}

	@GetMapping("/{code}/delete")
	public ResponseEntity<?> deteleClasse(@PathVariable("code") Long code) throws IsjException{
		try {
			return ResponseEntity.ok(iClasse.deleteClass(code));
		}catch (IsjException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}

	}
	@GetMapping("/{libClasse}/recherche")
	public ResponseEntity<?> searchClasseBylibClasse (@PathVariable("libClasse") String libClasse) throws IsjException {
		try {
			return ResponseEntity.ok(iClasse.searchClasseBylibClasse(libClasse));
		}catch (IsjException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/updade")
	public String modifier(@RequestBody Classe create) throws IsjException {
		try {
			iClasse.updateClasse(create);
		}catch (IsjException exception) {
			return exception.getMessage();
		}

		return "modification reussi";


	}

}