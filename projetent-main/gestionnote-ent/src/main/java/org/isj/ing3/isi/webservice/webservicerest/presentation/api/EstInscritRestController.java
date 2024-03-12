package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;

import org.isj.ing3.isi.webservice.webservicerest.model.entities.Enseignement;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.EstInscrit;
import org.isj.ing3.isi.webservice.webservicerest.service.IEstInscrit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/est-inscrit")
public class EstInscritRestController {
    @Autowired
    private IEstInscrit iEstInscrit;


    @PostMapping("/save")
    public String enregistrer(@RequestBody EstInscrit create) throws IsjException {

        try {
            iEstInscrit.saveInscrit(create);
        }catch (IsjException exception) {
            return exception.getMessage();
        }

        return "Enregistrement réussi";

    }

    @PostMapping("/update")
    public String modifier(@RequestBody EstInscrit create) throws  IsjException{
        try {
            iEstInscrit.updateEstInscrit(create);
        }catch (IsjException exception){
            return exception.getMessage();
        }
        return "modification reussi";
    }


    @GetMapping("/{code}/data")
    public ResponseEntity<?> getInscritByCodeBy(@PathVariable("code") Long code) throws IsjException {

        try {
            return ResponseEntity.ok(iEstInscrit.getInscritByCode(code));
        }catch (IsjException exception) {
            return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/all")
    public ResponseEntity<List<EstInscrit>> getAllEstInscrit() {
        return ResponseEntity.ok(iEstInscrit.listInscrit());
    }

    @GetMapping("/{code}/delete")
    public ResponseEntity<?> deleteInscrit(@PathVariable("code") Long code) throws IsjException{

        try {
            return ResponseEntity.ok(iEstInscrit.deleteInscrit(code));
        }catch (IsjException exception) {
            return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{code}/searchInscrit")
    public ResponseEntity<?> ListeDesInscrit(@PathVariable("code")Long code) throws IsjException{

        try {
            return ResponseEntity.ok(iEstInscrit.ListeDesInscrit(code));
        }catch (IsjException exception) {
            return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
