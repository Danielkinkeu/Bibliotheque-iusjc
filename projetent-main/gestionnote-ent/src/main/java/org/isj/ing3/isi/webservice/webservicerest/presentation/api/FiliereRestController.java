package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Email;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Filiere;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Note;
import org.isj.ing3.isi.webservice.webservicerest.service.IFiliere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filiere")
@Slf4j
public class FiliereRestController {

	@Autowired
	private IFiliere iFiliere;

	@PostMapping("/save")
	public String enregistrer(@RequestBody Filiere create) throws IsjException {

		try {
			iFiliere.saveFiliere(create);
		}catch (IsjException exception) {
			return exception.getMessage();
		}

		return "Enregistrement réussi";
	}

	@PostMapping("/update")
	public String modifier (@RequestBody Filiere create) throws  IsjException{
		try {
			iFiliere.updateFiliere(create);
		}catch (IsjException exception){
			return exception.getMessage();
		}
		return "modification reussi";
	}



	@GetMapping("/{code}/data")
	public ResponseEntity<?> getFiliereByCode(@PathVariable("code") Long code) throws IsjException {

		try {
			return ResponseEntity.ok(iFiliere.getFiliereByCode(code));
		}catch (IsjException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}
	}


	@GetMapping("/all")
	public ResponseEntity<List<Filiere>> getAllFiliere() {

		return ResponseEntity.ok(iFiliere.listFilieres());
	}

	@GetMapping("/{code}/delete")
	public int deteleFiliere(@PathVariable("code") Long code)
	{
		return iFiliere.deleteFiliere(code);
	}


	@GetMapping("/{libelleFiliere}/search")
	public ResponseEntity<?> getNoteByLibelleFiliere(@PathVariable("libelleFiliere") String libelleFiliere) throws IsjException {

		try {
			return ResponseEntity.ok(iFiliere.getFiliereByLibelleFiliere(libelleFiliere));
		}catch (IsjException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}