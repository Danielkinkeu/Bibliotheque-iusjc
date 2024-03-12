package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.*;
import org.isj.ing3.isi.webservice.webservicerest.repositories.EvaluationRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.TypeEvaluationRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.IEvaluation;
import org.isj.ing3.isi.webservice.webservicerest.service.ITypeEvaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zalando.problem.Status;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EvaluationServiceImpl implements IEvaluation {
    @Autowired
    EvaluationRepository evaluationRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    ITypeEvaluation iTypeEvaluation;
    @Autowired
    TypeEvaluationRepository typeEvaluationRepository;

    @Override
    public int saveEvaluation(Evaluation evaluation) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(evaluation.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = createur;
        evaluation.setCreateur(createur);
        evaluation.setModificateur(modificateur);
        Evaluation evaluationSave = evaluationRepository.save(evaluation);
        if (evaluationSave == null){
            throw  new IsjException("un problème est survenu lors de l'enregistrement, veuillez reéssayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return evaluationSave.getCode().intValue();

    }

    @Override
    public int deleteEvaluation(Long code) throws IsjException {
        evaluationRepository.deleteById(evaluationRepository.findById(code).get().getCode());
        return 1;
    }

    @Override
    public int updateEvaluation(Evaluation evaluation) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(evaluation.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));;
        Utilisateur modificateur = utilisateurRepository.findById(evaluation.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));;
        evaluation.setCreateur(createur);
        evaluation.setModificateur(modificateur);
        Evaluation evaluationUpdate = evaluationRepository.save(evaluation);
        if (evaluationUpdate == null){
            throw new IsjException("Un problème est survenu lors de la mise à jour, veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return evaluationUpdate.getCode().intValue();
    }


    @Override
    public List<Evaluation> listEvaluation() {
        return evaluationRepository.findAll();
    }

    @Override
    public Evaluation getEvaluationByCode(Long code) throws IsjException {
        return evaluationRepository.findById(code).orElseThrow(() -> new IsjException("Evaluation introuvable",Status.NOT_FOUND));
    }

   /* @Override
    public List<Note> ListeNote(Long code) throws IsjException{
        Evaluation evaluation = getEvaluationByCode(code);
        return evaluationRepository.retrouverListeNote(evaluation);
    }*/

  @Override
    public Evaluation searchTypeEtudiant(String TypeEv, String codeUe, Long an) throws IsjException {
        return evaluationRepository.retrouverTypeEvaluation(TypeEv,codeUe,an).orElseThrow(() -> new IsjException("TypeEtudiant introuvable",Status.NOT_FOUND));


}

    @Override
    public Evaluation searchTypeEvaluation(Long code) throws IsjException {
        TypeEvaluation typeEvaluation = iTypeEvaluation.getTypeEvaluationByCode(code);
        return evaluationRepository.retrouverEvaluation(typeEvaluation).orElseThrow(() -> new IsjException("TypeEvaluation introuvable",Status.NOT_FOUND));
    }
}
