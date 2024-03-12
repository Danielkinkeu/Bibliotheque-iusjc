package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Enseignant;
import org.isj.ing3.isi.webservice.webservicerest.service.IEnseignant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enseignant")
@Slf4j

public class EnseignantRestController {

    @Autowired
    private IEnseignant iEnseignant;

    @PostMapping("/save")
    public String enregistrer(@RequestBody Enseignant create) throws IsjException {
        System.out.println(create.toString());
        try {
            iEnseignant.saveEnseignant(create);
        }catch (IsjException exception) {
            return exception.getMessage();
        }

        return "Enregistrement réussi";
    }

    @PostMapping("/update")
        public String modifier(@RequestBody Enseignant create) throws IsjException{
           try {
               iEnseignant.updateEnseignant(create);
           }catch (IsjException exception){
               return exception.getMessage();
           }
           return "modification reussi";
        }

    @GetMapping("/{code}/data")
    public ResponseEntity<?> getEnseignantByCode(@PathVariable("code") Long code) throws IsjException {


        try {
            return ResponseEntity.ok(iEnseignant.getEnseignantByCode(code));
        }catch (IsjException exception) {
            return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/all")
    public ResponseEntity<List<Enseignant>> getAllEtudiant() {
        return ResponseEntity.ok(iEnseignant.listEnseignants());
    }

    @GetMapping("/{code}/delete")
    public ResponseEntity<?> deleteEnseignant(@PathVariable("code") Long code){

        try {
            return ResponseEntity.ok(iEnseignant.deleteEnseignant(code));
        }catch (IsjException exception) {
            return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/nb-enseignant")
    public ResponseEntity<Integer> nbEnseignant(){

        return ResponseEntity.ok(iEnseignant.nbEnseignant());

    }

}


