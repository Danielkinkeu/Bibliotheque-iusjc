package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Filiere;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Module;
import org.isj.ing3.isi.webservice.webservicerest.service.IAnonymat;
import org.isj.ing3.isi.webservice.webservicerest.service.IModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/module")
@Slf4j
public class ModuleRestController {

	@Autowired
	private IModule iModule;

	@PostMapping("/save")
	public String enregistrer(@RequestBody Module create) throws IsjException {

		try {
			iModule.saveModule(create);
		}catch (IsjException exception) {
			return exception.getMessage();
		}

		return "Enregistrement réussi";

	}


	@GetMapping("/{code}/data")
	public ResponseEntity<?> getModuleByCode(@PathVariable("code") Long code) throws IsjException {

		try {
			return ResponseEntity.ok(iModule.getModuleByCode(code));
		}catch (IsjException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/all")
	public ResponseEntity<List<Module>> getAllModule() {
		return ResponseEntity.ok(iModule.listModules());
	}


	@GetMapping("/{code}/delete")
	public int deteleModule(@PathVariable("code") Long code) throws IsjException {
		return iModule.deleteModule(code);
	}

	@GetMapping("/{codeModule}/search")
	public ResponseEntity<?> getModuleByCodeModule(@PathVariable("codeModule") String codeModule) throws IsjException {

		try {
			return ResponseEntity.ok(iModule.getModuleByCodeModule(codeModule));
		}catch (IsjException exception) {
			return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

}