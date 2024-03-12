package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.AnneeAcademique;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Semestre;
import org.isj.ing3.isi.webservice.webservicerest.service.ISemestre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/semestre")
public class SemestreRestController {
    @Autowired
    private ISemestre iSemestre;

    @PostMapping("/save")
    public String enregistrer(@RequestBody Semestre create) {
        try {
            iSemestre.saveSemestre(create);
        }catch (IsjException exception) {
            return exception.getMessage();
        }
        return "enregistrement reussi";

    }

    @GetMapping("/all")
    public ResponseEntity<List<Semestre>> getAllNote() {
        return ResponseEntity.ok(iSemestre.listSemestre());
    }

    @GetMapping("/{code}/delete")
    public ResponseEntity<?> deteleSemestre(@PathVariable("code") Long code) throws IsjException {
        try {
            return ResponseEntity.ok(iSemestre.deleteSemestreByCode(code));
        }catch (IsjException exception) {
            return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/{libelle}/{annee_academique}/recherche")
    public ResponseEntity<?> searchSemestreByLibelleOrAnneeAcademique (@PathVariable("libelle") String libelle, @PathVariable("annee_academique")Long annee_academique ) throws IsjException {
        try {
            return ResponseEntity.ok(iSemestre.searchSemestreByLibelleOrAnneeAcademique(libelle,annee_academique));
        }catch (IsjException exception) {
            return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping("/update")
    public String modifier(@RequestBody Semestre create) {
        try {
            iSemestre.updateSemestre(create);
        }catch (IsjException exception) {
            return exception.getMessage();
        }

        return "modification reussi";

    }
}
