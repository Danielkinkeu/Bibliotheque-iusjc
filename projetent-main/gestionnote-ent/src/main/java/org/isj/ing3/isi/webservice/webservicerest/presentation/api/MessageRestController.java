package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Message;
import org.isj.ing3.isi.webservice.webservicerest.service.IAnonymat;
import org.isj.ing3.isi.webservice.webservicerest.service.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
@Slf4j
public class MessageRestController {

	@Autowired
	private IMessage iMessage;

	@PostMapping("/save")
	public String enregistrer(@RequestBody Message create) throws IsjException {

		try {
			iMessage.saveMessage(create);
		}catch (IsjException exception) {
			return exception.getMessage();
		}

		return "Enregistrement réussi";

	}


	@GetMapping("/{code}/data")
	public ResponseEntity<?> getMessageByCode(@PathVariable("code") Long code) throws IsjException {

		try {
			return ResponseEntity.ok(iMessage.getMessageByCode(code));
		}catch (IsjException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}

	}


	@GetMapping("/all")
	public ResponseEntity<List<Message>> getAllMessage() {
		return ResponseEntity.ok(iMessage.listMessages());
	}


	@GetMapping("/{code}/delete")
	public ResponseEntity<?> deteleMessage(@PathVariable("code") Long code) throws IsjException {

		try {
			return ResponseEntity.ok(iMessage.getMessageByCode(code));
		}catch (IsjException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}