package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.*;
import org.isj.ing3.isi.webservice.webservicerest.service.IFiliere;
import org.isj.ing3.isi.webservice.webservicerest.service.INoteCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notecc")
@Slf4j
public class NoteCCRestController {
    @Autowired
    private INoteCC iNoteCC;

    @PostMapping("/save")
    public String enregistrer(@RequestBody NoteCC create) throws IsjException {

        try {
            iNoteCC.saveNoteCC(create);
        }catch (IsjException exception) {
            return exception.getMessage();
        }

        return "enregistrement reussi";
    }


    @GetMapping("/{code}/data")
    public ResponseEntity<?> getNoteCCByCode(@PathVariable("code") Long code) throws IsjException {

        try {
            return ResponseEntity.ok(iNoteCC.getNoteCCByCode(code));
        }catch (IsjException exception) {
            return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }

    }


    @GetMapping("/all")
    public ResponseEntity<List<NoteCC>> getAllNoteCC() {

        return ResponseEntity.ok(iNoteCC.listNoteCC());
    }

    @GetMapping("/{code}/delete")
    public ResponseEntity<?> deleteNoteCC(@PathVariable("code") Long code) throws IsjException {
        try {
            return ResponseEntity.ok(iNoteCC.deleteNoteCC(code));
        }catch (IsjException exception) {
            return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }


    }


    @GetMapping("/{candidat}/{typeNoteCC}/recherche")
    public ResponseEntity<?> searchNoteCCByCandidatOrTypeNoteCC (@PathVariable("candidat") Long candidat, @PathVariable("typeNoteCC") Long typeNoteCC ) throws IsjException {
        try {
            return ResponseEntity.ok(iNoteCC.searchNoteCCByCandidatOrTypeNoteCC(candidat,typeNoteCC));
        }catch (IsjException exception) {
            return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/update")
    public String modifier(@RequestBody NoteCC create) throws IsjException {

        try {
            iNoteCC.updateNoteCC(create);
        }catch (IsjException exception) {
            return exception.getMessage();
        }

        return "modification reussi";
    }

}

