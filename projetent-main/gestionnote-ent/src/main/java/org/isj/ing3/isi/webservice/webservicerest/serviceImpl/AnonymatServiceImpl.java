package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.*;
import org.isj.ing3.isi.webservice.webservicerest.repositories.*;
import org.isj.ing3.isi.webservice.webservicerest.service.IAnonymat;
import org.isj.ing3.isi.webservice.webservicerest.service.IEstInscrit;
import org.isj.ing3.isi.webservice.webservicerest.service.IEvaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zalando.problem.Status;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AnonymatServiceImpl implements IAnonymat {
    @Autowired
    AnonymatRepository anonymatRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    NoteRepository noteRepository;
    @Autowired
    EstInscritRepository estInscritRepository;
    @Autowired
    EvaluationRepository evaluationRepository;
    @Autowired
     IEvaluation iEvaluation;
    @Autowired
    IEstInscrit iEstInscrit;

    @Override
    public int saveAnonymat(Anonymat anonymat) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(anonymat.getCreateur().getCode()).get();
        Utilisateur modificateur = createur;
        Note note = noteRepository.findById(anonymat.getNote().getCode()).get();
      /*  EstInscrit estInscrit = estInscritRepository.findById(anonymat.getEstInscrit().getCode()).get();
        Evaluation evaluation = evaluationRepository.findById(anonymat.getEvaluation().getCode()).get();
*/
        anonymat.setCreateur(createur);
        anonymat.setModificateur(modificateur);
        /*anonymat.setEstInscrit(estInscrit);
        anonymat.setEvaluation(evaluation);
        anonymat.setNote(note);*/
        Anonymat anonymatSave = anonymatRepository.save(anonymat);
        if (anonymatSave == null){
            throw  new IsjException("un problème est survenu lors de l'enregistrement, veuillez reéssayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return anonymatSave.getCode().intValue();
    }

    @Override
    public List<Anonymat> listAnonymat() {
        return anonymatRepository.findAll();
    }

    @Override
    public int deleteAnonymat(Long code) throws IsjException {
        anonymatRepository.deleteById(anonymatRepository.findById(code).get().getCode());
        return 1;
    }

    @Override
    public Anonymat getAnonymatByCode(Long code) throws IsjException {
        return anonymatRepository.findById(code).orElseThrow(() -> new IsjException("Anonymat introuvable", Status.NOT_FOUND));
    }

    @Override
    public int UpdateAnonymat(Anonymat anonymat) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(anonymat.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(anonymat.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        anonymat.setCreateur(createur);
        anonymat.setModificateur(modificateur);
        Anonymat anonymatUpdate =anonymatRepository.save(anonymat);
        if (anonymatUpdate == null){
            throw new IsjException("Un problème est survenu lors de la mise à jour, veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);

        }
         return anonymatUpdate.getCode().intValue();
    }

    @Override
    public Anonymat getAnonymatByNumeroAnonymat(String num_anonymat) throws IsjException {
        return anonymatRepository.retrouverAnonymatNote(num_anonymat).orElseThrow(()-> new  IsjException("Numéro d'anonymat introuvable",Status.NOT_FOUND));
    }

    @Override
    public List<Anonymat> ListAnonymatByEvaluation(Long code)throws IsjException {
        Evaluation evaluation = iEvaluation.getEvaluationByCode(code);
        return anonymatRepository.retrouverListeAnonymat(evaluation).orElseThrow(() -> new IsjException("Evaluation introuvable",Status.NOT_FOUND));
    }

  /* @Override
    public Anonymat getAnonymatByCodeInscritAndCodeEvaluaion(Long code, Long code1) throws IsjException {
        Evaluation evaluation = iEvaluation.getEvaluationByCode(code);
        EstInscrit estInscrit = iEstInscrit.getInscritByCode(code1);
        return anonymatRepository.retrouverAnonymat(evaluation,estInscrit);
    }*/
}
