package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Filiere;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Niveau;
import org.isj.ing3.isi.webservice.webservicerest.service.IAnonymat;
import org.isj.ing3.isi.webservice.webservicerest.service.INiveau;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/niveau")
@Slf4j
public class NiveauRestController {

	@Autowired
	private INiveau iNiveau;

	@PostMapping("/save")
	public String enregistrer(@RequestBody Niveau create) throws IsjException {
		try {
			iNiveau.saveNiveau(create);
		}catch (IsjException exception) {
			return exception.getMessage();
		}

		return "Enregistrement réussi";

	}


	@GetMapping("/{code}/data")
	public ResponseEntity<?> getNiveauByCode(@PathVariable("code") Long code) throws IsjException {

		try {
			return ResponseEntity.ok(iNiveau.getNiveauByCode(code));
		}catch (IsjException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}

	}


	@GetMapping("/all")
	public ResponseEntity<List<Niveau>> getAllNiveau() {
		return ResponseEntity.ok(iNiveau.listNiveaus());
	}

	@GetMapping("/{code}/delete")
	public ResponseEntity<?> deteleNiveau(@PathVariable("code") Long code) throws IsjException {
		try {
			return ResponseEntity.ok(iNiveau.getNiveauByCode(code));
		}catch (IsjException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{niveau}/search")
	public ResponseEntity<?> getNiveauByNumero(@PathVariable("niveau") long niveau) throws IsjException {

		try {
			return ResponseEntity.ok(iNiveau.getNiveauByNumero(niveau));
		}catch (IsjException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}