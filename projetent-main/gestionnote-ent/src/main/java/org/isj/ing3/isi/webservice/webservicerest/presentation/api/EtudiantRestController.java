package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Etudiant;
import org.isj.ing3.isi.webservice.webservicerest.service.IEtudiant;
import org.isj.ing3.isi.webservice.webservicerest.service.IPrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/etudiant")
@Slf4j

public class EtudiantRestController {
    @Autowired
    private IEtudiant iEtudiant;
    @Autowired
    IPrintService iPrintService;

    @PostMapping("/save")
    public String enregistrer(@RequestBody Etudiant create) throws IsjException {
        try {
            iEtudiant.saveEtudiant(create);
        }catch (IsjException exception){
            return exception.getMessage();
        }
        return "enregistrement reussi";
    }

    @PostMapping("/update")
    public String modifier(@RequestBody Etudiant create) throws IsjException{
        try {
            iEtudiant.updateEtudiant(create);
        }catch (IsjException exception){
            return exception.getMessage();
        }
        return "modification reussi";
    }

    @GetMapping("/{code}/data")
    public ResponseEntity<?> getEtudiantByCode(@PathVariable("code") Long code) throws IsjException {
        try {
            return ResponseEntity.ok(iEtudiant.getEtudiantByCode(code));
        }catch (IsjException exception){
            return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }

    }


    @GetMapping("/all")
    public ResponseEntity<List<Etudiant>> getEtudiant() {
        return ResponseEntity.ok(iEtudiant.listEtudiants());
    }

    @GetMapping("/{code}/delete")
    public ResponseEntity<?> deteleEtudiant(@PathVariable("code") Long code)throws IsjException{
       try {
           return ResponseEntity.ok(iEtudiant.deleteEtudiant(code));
       }catch (IsjException exception){
           return new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
       }

    }

    @SneakyThrows
    @GetMapping("/{matricule}/get")
    public ResponseEntity<?> getEtudiantByMatricule(@PathVariable("matricule") String matricule) throws IsjException {
        try {
            return ResponseEntity.ok(iEtudiant.getStudentByMatricule(matricule));
        }catch (IsjException exception){
            return  new ResponseEntity<String>(exception.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/nb-student")
    public ResponseEntity<?> getNbStudent() throws IsjException {
        return ResponseEntity.ok(iEtudiant.nbEtudiant());

    }
 }