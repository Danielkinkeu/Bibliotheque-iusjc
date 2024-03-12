package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Droit;
import org.isj.ing3.isi.webservice.webservicerest.service.IClasse;
import org.isj.ing3.isi.webservice.webservicerest.service.IDiscipline;
import org.isj.ing3.isi.webservice.webservicerest.service.IDroit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/droit")
@Slf4j
public class DroitRestController {

	@Autowired
	private IDroit idroit;

	@PostMapping("/save")
	public String enregistrer(@RequestBody Droit create) throws IsjException {
		try {
			idroit.saveDroit(create);
		}catch (IsjException exception) {
			return exception.getMessage();
		}

		return "Enregistrement réussi";


	}

	@GetMapping("/{code}/data")
	public ResponseEntity<?> getDroitByCode(@PathVariable("code") Long code) throws IsjException {

		try {
			return ResponseEntity.ok(idroit.getDroitByCode(code));
		}catch (IsjException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}
	}


	@GetMapping("/all")
	public ResponseEntity<List<Droit>> getAllDroit() {
		return ResponseEntity.ok(idroit.listDroits());
	}

	@GetMapping("/{code}/delete")
	public int deleteDroit(@PathVariable("code") Long code){
			return idroit.deleteDroit(code);
	}

	@PostMapping("/update")
	public String modifier(@RequestBody Droit create) throws IsjException {
		try {
			idroit.updateDroit(create);
		}catch (IsjException exception) {
			return exception.getMessage();
		}

		return "Modification réussie";

	}
}