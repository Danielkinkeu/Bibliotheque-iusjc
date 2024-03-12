package org.institutsaintjean.intervention.gestionDesInterventions.Services;

import org.institutsaintjean.intervention.gestionDesInterventions.Entities.Intervention;

import org.institutsaintjean.intervention.gestionDesInterventions.Entities.PieceJointe;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.util.List;


public interface InterventionService {
    public Intervention creerIntervention(long codeEtudiant, Long idCategorie,
                                          List<MultipartFile> fichiers,
                                          String file,
                                          String DescriptionIntervention,
                                          Long idSousIntervention) ;
    public Intervention prendreEnChargeIntervention(Long interventionId, Long personnelId);

    public Intervention terminerUneIntervention(Long interventionId,String emailContent,List<MultipartFile> piecesJointes);

//    Resource downloadPieceJointe(Long interventionId) throws IOException;
   public PieceJointe getPieceJointeById(Long idPieceJointe);

    public void cancelIntervention(Long interventionId);
}
