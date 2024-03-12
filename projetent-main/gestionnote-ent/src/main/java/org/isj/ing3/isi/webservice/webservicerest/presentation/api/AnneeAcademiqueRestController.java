package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.AnneeAcademique;
import org.isj.ing3.isi.webservice.webservicerest.service.IAnneeAcademique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/annee-accademique")
@Slf4j
public class AnneeAcademiqueRestController {

	@Autowired
	private IAnneeAcademique ianneeAcademique;

	@PostMapping("/save")
	public String enregistrer(@RequestBody AnneeAcademique create) throws IsjException {

		try {
			ianneeAcademique.saveAnneeAcademique(create);
		}catch (IsjException exception) {
			return exception.getMessage();
		}

		return "Enregistrement réussi";
	}


	@PostMapping("/update")
	public  String modifier (@RequestBody AnneeAcademique create) throws  IsjException{
		try {
			ianneeAcademique.updateAnneeAcademique(create);
		}catch (IsjException exception){
			return exception.getMessage();
		}
		return "modification réussie";
	}
	@GetMapping("/{code}/data")
	public ResponseEntity<?> getAnneeAcademiqueByCode(@PathVariable("code") Long code) throws IsjException {

		try {
			return ResponseEntity.ok(ianneeAcademique.getAnneAcademiqueByCode(code));
		}catch (IsjException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}
	}


	@GetMapping("/all")
	public ResponseEntity<List<AnneeAcademique>> getAllAnneeAccademique() {
		return ResponseEntity.ok(ianneeAcademique.listAnneeAcademique());
	}

	@GetMapping("/{code}/delete")
	public ResponseEntity<?> deteleAnneeAccademique(@PathVariable("code") Long code) throws IsjException{

		try {
			return ResponseEntity.ok(ianneeAcademique.deleteAnneAcademique(code));
		}catch (IsjException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{date}/search")
	public  ResponseEntity<?> getAnneeAcademiqueByDate(@PathVariable("date") Date date) throws IsjException{
		try {
			return ResponseEntity.ok(ianneeAcademique.getAnneeAcademiqueByDate(date));
		}catch (IsjException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}