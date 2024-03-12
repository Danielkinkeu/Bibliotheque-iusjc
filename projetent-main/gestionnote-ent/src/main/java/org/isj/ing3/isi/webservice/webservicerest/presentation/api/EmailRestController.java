package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Email;
import org.isj.ing3.isi.webservice.webservicerest.service.IDroit;
import org.isj.ing3.isi.webservice.webservicerest.service.IEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/email")
@Slf4j
public class EmailRestController {

	@Autowired
	private IEmail iemail;

	@PostMapping("/save")
	public String enregistrer(@RequestBody Email create) throws  IsjException {
		try {
			iemail.saveEmail(create);
		}catch (IsjException exception){
			return exception.getMessage();
		}
		return "Enregistrement reussi";
	}

	@PostMapping("/update")
	public String modifier (@RequestBody Email create) throws  IsjException{
		try {
			iemail.updateEmail(create);
		}catch (IsjException exception){
			return exception.getMessage();
		}
		return "modification reussi";
	}

	@GetMapping("/{code}/data")
	public ResponseEntity<?> getEmailCode(@PathVariable("code") Long code) throws IsjException {

		try {
			return ResponseEntity.ok(iemail.getByEmailCode(code));
		}catch (IsjException exception){
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}
	}


	@GetMapping("/all")
	public ResponseEntity<List<Email>> getAllEmail() {
		return ResponseEntity.ok(iemail.listEmails());
	}

	@GetMapping("/{code}/delete")
	public ResponseEntity<?> deteleEmail(@PathVariable("code") Long code) throws IsjException{

			try {
				return ResponseEntity.ok(iemail.deleteEmail(code));
			}catch (IsjException exception){
				return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
			}
	}

}