package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Filiere;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.UE;
import org.isj.ing3.isi.webservice.webservicerest.service.IFiliere;
import org.isj.ing3.isi.webservice.webservicerest.service.IUE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ue")
@Slf4j
public class UERestController {

    @Autowired
    private IUE iue;

    @PostMapping("/save")
    public void enregistrer(@RequestBody UE create) throws IsjException {
        iue.saveUE(create);
    }


    @GetMapping("/{code}/data")
    public ResponseEntity<UE> getUEByCode(@PathVariable("code") Long code) throws IsjException {

        return ResponseEntity.ok(iue.getUEByCode(code));
    }


    @GetMapping("/all")
    public ResponseEntity<List<UE>> getAllUE() {
        return ResponseEntity.ok(iue.listUEs());
    }

    @GetMapping("/{code}/delete")
    public int deleteUE(@PathVariable("code") Long code) throws IsjException{
        return iue.deleteUE(code);
    }

}