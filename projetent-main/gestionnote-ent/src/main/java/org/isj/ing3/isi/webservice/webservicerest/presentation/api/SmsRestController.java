package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Sms;
import org.isj.ing3.isi.webservice.webservicerest.service.ISession;
import org.isj.ing3.isi.webservice.webservicerest.service.ISms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sms")
public class SmsRestController {
    @Autowired
    private ISms iSms;

    @PostMapping("/save")
    public String enregistrer(@RequestBody Sms create) {
        try {
            iSms.saveSms(create);
        }catch (IsjException exception) {
            return exception.getMessage();
        }

        return "enregistrement reussi";

    }

    @GetMapping("/all")
    public ResponseEntity<List<Sms>> getAllNote() {
        return ResponseEntity.ok(iSms.listSms());
    }

    @GetMapping("/{code}/delete")
    public ResponseEntity<?> deteleSms(@PathVariable("code") Long code) throws IsjException {
        try {
            return ResponseEntity.ok(iSms.deleteSmsByCode(code));
        }catch (IsjException exception) {
            return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping("/update")
    public String modifier(@RequestBody Sms create) {
        try {
            iSms.updateSms(create);
        }catch (IsjException exception) {
            return exception.getMessage();
        }

        return "modification reussi";

    }
}
