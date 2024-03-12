package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Enseignement;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Specialite;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.TypeNoteCC;
import org.isj.ing3.isi.webservice.webservicerest.service.ISpecialite;
import org.isj.ing3.isi.webservice.webservicerest.service.ITypeNoteCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/TypeNoteCC")

public class TypeNoteCCRestController {
    @Autowired
    private ITypeNoteCC iTypeNoteCC;

    @PostMapping("/save")
    public String enregistrer(@RequestBody TypeNoteCC create) throws IsjException {
        try {
            iTypeNoteCC.saveTypeNoteCC(create);
        }catch (IsjException exception) {
            return exception.getMessage();
        }

        return "enregistrement reussi";

    }

    @GetMapping("/all")
    public ResponseEntity<List<TypeNoteCC>> getAllNote() {

        return ResponseEntity.ok(iTypeNoteCC.listTypeNoteCC());
    }

    @GetMapping("/{code}/delete")
    public ResponseEntity<?> deteleSpecialite(@PathVariable("code") Long code) throws IsjException {
        try {
            return ResponseEntity.ok(iTypeNoteCC.deleteTypeNoteCC(code));
        }catch (IsjException exception) {
            return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{typeNoteCC}/{filiere}/recherche")
    public ResponseEntity<?> searchTypeNoteCCByEnseignementOrNumero_CC (@PathVariable("enseignement") Long enseignement, @PathVariable("numero_cc") int numero_cc ) throws IsjException {
        try {
            return ResponseEntity.ok(iTypeNoteCC.searchTypeNoteCCByEnseignementOrNumero_CC(enseignement,numero_cc));
        }catch (IsjException exception) {
            return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/update")
    public String modifier(@RequestBody TypeNoteCC create) throws IsjException {
        try {
            iTypeNoteCC.updateTypeNoteCC(create);
        }catch (IsjException exception) {
            return exception.getMessage();
        }

        return "modification reussi";

    }
}