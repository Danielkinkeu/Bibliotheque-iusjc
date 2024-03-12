package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.*;
import org.isj.ing3.isi.webservice.webservicerest.service.IDiscipline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/discipline")
@Slf4j
public class DisciplineRestController {

	@Autowired
	private IDiscipline iDiscipline;

	@PostMapping("/save")
	public String enregistrer(@RequestBody Discipline create) throws IsjException {
		try {
			iDiscipline.saveDiscipline(create);
		}catch (IsjException exception) {
			return exception.getMessage();
		}

		return "Enregistrement réussi";

	}


	@GetMapping("/{code}/data")
	public ResponseEntity<?> getDisciplineByCode(@PathVariable("code") Long code) throws IsjException {
		try {
			return ResponseEntity.ok(iDiscipline.getDisciplineByCode(code));
		}catch (IsjException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}

	}


	@GetMapping("/all")
	public ResponseEntity<List<Discipline>> getAllDiscipline() {
		return ResponseEntity.ok(iDiscipline.listDisciplines());
	}

	@GetMapping("/{code}/delete")

		public ResponseEntity<?> deteleDiscipline(@PathVariable("code") Long code){
		try {
			return ResponseEntity.ok(iDiscipline.deleteDiscipline(code));
		}catch (IsjException exception) {

			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/update")
	public String modifier(@RequestBody Discipline create) throws IsjException {
		try {
			iDiscipline.updateDiscipline(create);
		}catch (IsjException exception) {
			return exception.getMessage();
		}

		return "Modification réussie";

	}

	@GetMapping("/{etudiant}/{semestre}/dateRetard")
	public ResponseEntity<?> searchDisciplineByEtudiantOrSemestreOrDate (@PathVariable("etudiant") Etudiant etudiant, @PathVariable("semestre") Semestre semestre ,@PathVariable("dateRetard") Date dateRetard) throws IsjException {
		try {
			return ResponseEntity.ok(iDiscipline.searchDisciplineByEtudiantOrSemestreOrDate(etudiant,semestre,dateRetard));
		}catch (IsjException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}