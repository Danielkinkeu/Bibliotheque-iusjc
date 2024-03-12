package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Session;
import org.isj.ing3.isi.webservice.webservicerest.service.ISession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/session")
public class SessionRestController {
    @Autowired
    private ISession iSession;

    @PostMapping("/save")
    public String enregistrer(@RequestBody Session create) {
        try {
            iSession.saveSession(create);
        }catch (IsjException exception) {
            return exception.getMessage();
        }

        return "enregigistrement reussi";

    }

    @GetMapping("/all")
    public ResponseEntity<List<Session>> getAllNote() {
        return ResponseEntity.ok(iSession.listSession());
    }

    @GetMapping("/{code}/delete")
    public ResponseEntity<?> deteleSession(@PathVariable("code") Long code) throws IsjException {
        try {
            return ResponseEntity.ok(iSession.deleteSessionByCode(code));
        }catch (IsjException exception) {
            return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/update")
    public String modifier(@RequestBody Session create) {
        try {
            iSession.updateSession(create);
        }catch (IsjException exception) {
            return exception.getMessage();
        }

        return "emodification reussi";

    }

}
