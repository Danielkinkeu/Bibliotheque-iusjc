package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Enseignement;
import org.isj.ing3.isi.webservice.webservicerest.service.IEnseignement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/enseignement")
public class EnseignementRestController {
    @Autowired
    private IEnseignement iEnseignement;

    @PostMapping("/save")
    public String enregistrer(@RequestBody Enseignement create) throws  IsjException {

        try {
            iEnseignement.saveEnseignement(create);
        }catch (IsjException exception) {
            return exception.getMessage();
        }

        return "Enregistrement réussi";
    }

    @PostMapping("/update")
    public String modifier(@RequestBody Enseignement create)throws IsjException{
        try {
            iEnseignement.updateEnseignement(create);
        }catch (IsjException exception){
            return exception.getMessage();
        }
        return "modification reussi";
    }

    @GetMapping("/{code}/data")
    public ResponseEntity<?> getEnseignementByCode(@PathVariable("code") Long code) throws IsjException {

        try {
            return ResponseEntity.ok(iEnseignement.getEnseignementByCode(code));
        }catch (IsjException exception) {
            return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/all")
    public ResponseEntity<List<Enseignement>> getAllEnseignements() {
        return ResponseEntity.ok(iEnseignement.listEnseignements());
    }

    @GetMapping("/{code}/delete")
    public  ResponseEntity<?> deleteEnseignements(@PathVariable("code") Long code) throws IsjException {

        try {
            return ResponseEntity.ok(iEnseignement.deleteEnseignement(code));
        }catch (IsjException exception) {
            return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }


    }

}


