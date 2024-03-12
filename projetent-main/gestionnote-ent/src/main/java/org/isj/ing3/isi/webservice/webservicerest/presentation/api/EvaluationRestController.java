package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Evaluation;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Note;
import org.isj.ing3.isi.webservice.webservicerest.service.IAnneeAcademique;
import org.isj.ing3.isi.webservice.webservicerest.service.IEvaluation;
import org.isj.ing3.isi.webservice.webservicerest.service.ITypeEvaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/evaluation")
@Slf4j

public class EvaluationRestController {


        @Autowired
        private IEvaluation iEvaluation;
        @Autowired
        private ITypeEvaluation iTypeEvaluation;

        @PostMapping("/save")
        public String enregistrer(@RequestBody Evaluation create) throws IsjException {

            try {
                iEvaluation.saveEvaluation(create);
            }catch (IsjException exception) {
                return exception.getMessage();
            }

            return "Enregistrement réussi";
        }

        @PostMapping("/update")
        public  String modifier(@RequestBody Evaluation create) throws  IsjException {

            try {
                iEvaluation.updateEvaluation(create);
            } catch (IsjException exception) {
                return exception.getMessage();
            }
            return "modification reussi";
        }


        @GetMapping("/{code}/data")
        public ResponseEntity<?> getEvaluationByCode(@PathVariable("code") Long code) throws IsjException {

            try {
                return ResponseEntity.ok(iEvaluation.getEvaluationByCode(code));
            }catch (IsjException exception) {
                return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
            }
        }


        @GetMapping("/all")
        public ResponseEntity<List<Evaluation>> getAllEvaluation() {
            return ResponseEntity.ok(iEvaluation.listEvaluation());
        }

        @GetMapping("/{code}/delete")
        public ResponseEntity<?> deleteEvaluation(@PathVariable("code") Long code) throws IsjException {

            try {
                return ResponseEntity.ok( iEvaluation.deleteEvaluation(code));
            }catch (IsjException exception) {
                return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
            }
        }

        @GetMapping("/{code}/searchEvaluation")
    public ResponseEntity<?>searchTypeEvaluation(@PathVariable("code")Long code )throws IsjException{

            try {
                return  ResponseEntity.ok(iEvaluation.searchTypeEvaluation(code));
            }catch (IsjException exception) {
                return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
            }
        }

       @GetMapping("/{TypeEv}/{codeUe}/{an}/searchTypeEtudiant")
    public ResponseEntity<?>searchTypeEtudiant(@PathVariable("TypeEv") String TypeEv,@PathVariable("codeUe") String codeUe,@PathVariable("an")Long an) throws  IsjException{

            try {
                return ResponseEntity.ok(iEvaluation.searchTypeEtudiant(TypeEv,codeUe,an));
            }catch (IsjException exception) {
                return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
            }
        }
    }

