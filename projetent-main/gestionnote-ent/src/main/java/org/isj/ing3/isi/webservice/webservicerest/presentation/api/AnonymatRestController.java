package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Anonymat;
import org.isj.ing3.isi.webservice.webservicerest.service.IAnonymat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anonymat")
@Slf4j
public class AnonymatRestController {

	@Autowired
	private IAnonymat iAnonymat;

	@PostMapping("/save")
	public String enregistrer(@RequestBody Anonymat create) throws IsjException {

		try {
			iAnonymat.saveAnonymat(create);
		} catch (IsjException exception) {
			return exception.getMessage();
		}

		return "Enregistrement réussi";
	}

	@PostMapping("/update")
	public String modifier(@RequestBody Anonymat create) throws IsjException {
		try {
			iAnonymat.UpdateAnonymat(create);
		} catch (IsjException exception) {
			return exception.getMessage();
		}
		return "modification reussi";
	}

	@GetMapping("/{code}/data")
	public ResponseEntity<?> getAnonymatByCode(@PathVariable("code") Long code) throws IsjException {

		try {
			return ResponseEntity.ok(iAnonymat.getAnonymatByCode(code));
		} catch (IsjException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}
	}


	@GetMapping("/all")
	public ResponseEntity<List<Anonymat>> getAllAnonymat() {
		return ResponseEntity.ok(iAnonymat.listAnonymat());
	}

	@GetMapping("/{code}/delete")
	public ResponseEntity<?> deteleAnonymat(@PathVariable("code") Long code) throws IsjException {

		try {
			return ResponseEntity.ok(iAnonymat.deleteAnonymat(code));
		} catch (IsjException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{num_anonymat}/searchNumAnonymat")
	public ResponseEntity<?> getAnonymatByNumeroAnonymat(@PathVariable("num_anonymat") String num_anonymat) throws IsjException {
		try {
			return ResponseEntity.ok(iAnonymat.getAnonymatByNumeroAnonymat(num_anonymat));
		} catch (IsjException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{code}/searchEvaluation")
	public ResponseEntity<?> ListAnonymatByEvaluation(@PathVariable("code") Long code) throws IsjException {
		try {
			return ResponseEntity.ok(iAnonymat.ListAnonymatByEvaluation(code));
		} catch (IsjException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
/*
	@GetMapping("/{code}/{code1}/searchCodeInscritAndCodeEvaluaion")
	public ResponseEntity<?> getAnonymatByCodeInscritAndCodeEvaluaion(@PathVariable("code") Long code, @PathVariable(" code1") Long code1) throws IsjException {
		try {
			return ResponseEntity.ok(iAnonymat.getAnonymatByCodeInscritAndCodeEvaluaion(code, code1));
		} catch (IsjException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}
	}*/
}

