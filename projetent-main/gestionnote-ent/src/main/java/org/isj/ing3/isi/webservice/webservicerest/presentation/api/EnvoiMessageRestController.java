package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.EnvoiMessage;
import org.isj.ing3.isi.webservice.webservicerest.service.IEnvoiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/envoi-message")
@Slf4j
public class EnvoiMessageRestController {
    @Autowired
    private IEnvoiMessage iEnvoiMessage;

    @PostMapping("/save")
    public String enregistrer(@RequestBody EnvoiMessage create) throws IsjException {

        try {
            iEnvoiMessage.saveEnvoiMessage(create);
        }catch (IsjException exception) {
            return exception.getMessage();
        }

        return "Enregistrement réussi";
    }

    @PostMapping("/update")
    public  String modifier(@RequestBody EnvoiMessage create) throws  IsjException{
        try {
            iEnvoiMessage.updateEnvoiMessage(create);
        }catch(IsjException exception){
            return exception.getMessage();
        }
        return "modification reussi";
    }

    @GetMapping("/{code}/data")
    public ResponseEntity<?> getEnvoiMessageByCode(@PathVariable("code") Long code) throws IsjException {


        try {
            return ResponseEntity.ok(iEnvoiMessage.getEnvoiMessageByCode(code));
        }catch (IsjException exception) {
            return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }

    }


    @GetMapping("/all")
    public ResponseEntity<List<EnvoiMessage>> getAllEnvoiMessage() {
        return ResponseEntity.ok(iEnvoiMessage.listEnvoiMessage());
    }

    @GetMapping("/{code}/delete")
    public ResponseEntity<?> deleteEnvoiMessage(@PathVariable("code") Long code) throws IsjException{

        try {
            return ResponseEntity.ok( iEnvoiMessage.deleteEnvoiMessage(code));
        }catch (IsjException exception) {
            return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

}

