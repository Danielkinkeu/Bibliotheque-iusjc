package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.AnneeAcademique;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Semestre;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Specialite;
import org.isj.ing3.isi.webservice.webservicerest.service.ISpecialite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specialite")
public class SpecialiteRestController {
    @Autowired
    private ISpecialite iSpecialite;

    @PostMapping("/save")
    public String enregistrer(@RequestBody Specialite create) {
        try {
            iSpecialite.saveSpecialite(create);
        }catch (IsjException exception) {
            return exception.getMessage();
        }

        return "enregigistrement reussi";

    }

    @GetMapping("/all")
    public ResponseEntity<List<Specialite>> getAllNote() {

        return ResponseEntity.ok(iSpecialite.listSpecialite());
    }

    @GetMapping("/{code}/delete")
    public ResponseEntity<?> deteleSpecialite(@PathVariable("code") Long code) throws IsjException {
        try {
            return ResponseEntity.ok(iSpecialite.deleteSpecialiteByCode(code));
        }catch (IsjException exception) {
            return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/{specialite}/{filiere}/recherche")
    public ResponseEntity<?> searchSpecialiteBySpecialiteOrfiliere (@PathVariable("specialite") String specialite, @PathVariable("filiere") String filiere ) throws IsjException {
        try {
            return ResponseEntity.ok(iSpecialite.searchSpecialiteBySpecialiteOrfiliere(specialite,filiere));
        }catch (IsjException exception) {
            return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping("/update")
    public String modifier(@RequestBody Specialite create) {
        try {
            iSpecialite.updateSpecialite(create);
        }catch (IsjException exception) {
            return exception.getMessage();
        }

        return "modification reussi";

    }
}
