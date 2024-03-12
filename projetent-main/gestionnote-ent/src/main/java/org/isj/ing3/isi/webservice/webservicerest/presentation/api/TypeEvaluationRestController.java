package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.AnneeAcademique;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Evaluation;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.TypeEvaluation;
import org.isj.ing3.isi.webservice.webservicerest.service.ITypeEvaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/type-evaluation")
@Slf4j
public class TypeEvaluationRestController {

    @Autowired
     private ITypeEvaluation iTypeEvaluation;


    @PostMapping("/save")
    public String enregistrer(@RequestBody TypeEvaluation create) throws IsjException {

        try {
            iTypeEvaluation.saveTypeEvaluation(create);
        }catch (IsjException exception) {
            return exception.getMessage();
        }
        return "Enregistrement réussi";
    }

    @PostMapping("/update")
    public  String modifier(@RequestBody TypeEvaluation create) throws  IsjException {

        try {
            iTypeEvaluation.updateTypeEvaluation(create);
        } catch (IsjException exception) {
            return exception.getMessage();
        }
        return "modification reussi";
    }


    @GetMapping("/{code}/data")
    public ResponseEntity<?> getTypeEvaluationByCode(@PathVariable("code") Long code) throws IsjException {


        try {
            return ResponseEntity.ok(iTypeEvaluation.getTypeEvaluationByCode(code));
        }catch (IsjException exception) {
            return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/all")
    public ResponseEntity<List<TypeEvaluation>> getAllTypeEvaluation() {
        return ResponseEntity.ok(iTypeEvaluation.listTypeEvaluation());
    }

    @GetMapping("/{code}/delete")
    public ResponseEntity<?> deleteTypeEvaluation(@PathVariable("code") Long code) throws  IsjException{

        try {
            return ResponseEntity.ok (iTypeEvaluation.deleteTypeEvaluation(code));
        }catch (IsjException exception) {
            return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
