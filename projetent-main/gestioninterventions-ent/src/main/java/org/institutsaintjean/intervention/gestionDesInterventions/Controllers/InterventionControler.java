package org.institutsaintjean.intervention.gestionDesInterventions.Controllers;


import javax.persistence.EntityNotFoundException;

import org.institutsaintjean.intervention.gestionDesInterventions.Entities.*;
import org.institutsaintjean.intervention.gestionDesInterventions.Enumerations.Statut;
import org.institutsaintjean.intervention.gestionDesInterventions.Repositories.*;
import org.institutsaintjean.intervention.gestionDesInterventions.Services.InterventionService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

import java.io.IOException;

import java.util.List;


@CrossOrigin("*")
@RestController
public class InterventionControler {

    @Autowired
    private InterventionService interventionService;


    @Autowired
    private InterventionRepository interventionRepository;

    @Autowired
    private DepartementRepository departementRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private PersonnelRepository personnelRepository;

    @Autowired
    private PieceJointeRepository pieceJointeRepository;


    @PostMapping(value = "/soumettre/{codeEtudiant}/{idCategorie}")
    public Intervention creerIntervention(@PathVariable("codeEtudiant") Long codeEtudiant,
                                          @PathVariable("idCategorie") Long idCategorie,
                                          @RequestParam(value = "pieceJointe",required = false)   List<MultipartFile> fichiers,
                                          @RequestParam(value = "file",required = false)  String file,
                                          @RequestParam(value = "DescriptionIntervention",required = false)  String DescriptionIntervention,
                                          @RequestParam(value = "idSousIntervention",required = false)  Long idSousIntervention
    )  {
        return interventionService.creerIntervention(codeEtudiant, idCategorie, fichiers,file,DescriptionIntervention, idSousIntervention);
    }


    @GetMapping("/Liste/Departement/{idPersonnel}/{statut}")
    public List<Intervention> ListeInterventionParDepartement(@PathVariable Long idPersonnel,@PathVariable Statut statut) {
        Personnel personnel = personnelRepository.findByIdPersonnel(idPersonnel);
        Departement departement = departementRepository.findDepartementByIdDepartement(personnel.getDepartement().getIdDepartement());
        return interventionRepository.findByDepartementAndStatut(departement, statut);

    }

    @GetMapping("/Liste/etudiant/{codeEtudiant}")
    public List<Intervention> ListeInterventionParEtudiant(@PathVariable long codeEtudiant) {
        Etudiant etudiant = etudiantRepository.findByCode(codeEtudiant);
        return interventionRepository.findByEtudiant(etudiant);
    }

    @GetMapping("/Liste/Departement/{idPersonnel}")
    public List<Intervention> ListeInterventionParDepartement(@PathVariable long idPersonnel) {
        Personnel personnel = personnelRepository.findByIdPersonnel(idPersonnel);
        Departement departement = departementRepository.findDepartementByIdDepartement(personnel.getDepartement().getIdDepartement());
        return interventionRepository.findByDepartement(departement);
    }


    @PutMapping("/prendre-en-charge/{interventionId}/{personnelId}")
    public Intervention prendreEnChargeIntervention(
            @PathVariable("interventionId") Long interventionId,
            @PathVariable("personnelId") Long personnelId) {
        return interventionService.prendreEnChargeIntervention(interventionId, personnelId);
    }

    @PutMapping("/Termine/{interventionId}")
    public Intervention finDeTraitementDuneIntervention(@PathVariable("interventionId") Long interventionId,
                                                        @RequestParam(value = "emailContent",required = false) String emailContent,
                                                        @RequestParam(value = "piecesJointes",required = false) List<MultipartFile> piecesJointes)
    {
        return interventionService.terminerUneIntervention(interventionId,emailContent,piecesJointes);
    }


    @GetMapping("/personnel/{personnelId}/statut/{statut}")
    public List<Intervention> ListeInterventionDuPersonneParStatut(
            @PathVariable Long personnelId,
            @PathVariable Statut statut) {
        Personnel personnel = personnelRepository.findById(personnelId)
                .orElseThrow(() -> new EntityNotFoundException("Personnel introuvable."));
        return interventionRepository.findByPersonnelAndStatut(personnel, statut);
    }

    @GetMapping("/Etudiant/{codeEtudiant}/statut/{statut}")
    public List<Intervention> ListeInterventionDeLetudiantParStatut(
            @PathVariable Long codeEtudiant,
            @PathVariable Statut statut) {
        Etudiant etudiant = etudiantRepository.findById(codeEtudiant)
                .orElseThrow(() -> new EntityNotFoundException("Etudiant introuvable"));
        return interventionRepository.findByEtudiantAndStatutOrderByDateCreationInterAsc(etudiant, statut);
    }
//    @PostMapping("/upload")
//    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("nomDepartement") String nomDepartement) {
//        // Logique de gestion des fichiers ici
//        // Vous pouvez enregistrer le fichier, effectuer des opérations, etc.
//
//        if (!file.isEmpty()) {
//            try {
//                // Spécifiez le chemin où vous souhaitez enregistrer le fichier
//                String filePath = "C:\\Users\\pc\\Desktop\\gestIntervention\\" + file.getOriginalFilename();
//                file.transferTo(new File(filePath));
//                System.out.println(filePath);
//                System.out.println(nomDepartement);
//                // Vous pouvez également effectuer des opérations supplémentaires ici
//
//                return ResponseEntity.ok("File uploaded successfully");
//            } catch (IOException e) {
//                e.printStackTrace();
//                return ResponseEntity.status(500).body("Failed to upload file");
//            }
//        } else {
//            return ResponseEntity.badRequest().body("Please select a file to upload");
//        }
//    }
//    @GetMapping("/download/{interventionId}/piece-jointe")
//    public ResponseEntity<Resource> downloadPieceJointe(@PathVariable Long interventionId) {
//        try {
//            Resource resource = interventionService.downloadPieceJointe(interventionId);
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                    .body(resource);
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Gérez l'exception selon vos besoins, par exemple, retournez une réponse d'erreur appropriée
//            return ResponseEntity.status(500).body(null);
//        }
//    }


//
//    @GetMapping("/pieces-jointes/{idPieceJointe}")
//    public ResponseEntity<Resource> telechargerPieceJointe(@PathVariable Long idPieceJointe) throws IOException {
//        // Récupérer la pièce jointe par son ID
//        PieceJointe pieceJointe = interventionService.getPieceJointeById(idPieceJointe);
//
//        // Créer un InputStreamResource à partir du contenu de la pièce jointe
//        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(pieceJointe.getContenu()));
//
//        // Créer l'en-tête Content-Disposition avec le nom du fichier
//        String filename = pieceJointe.getNom();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        headers.setContentDispositionFormData("attachment", filename);
//
//        // Retourner la pièce jointe en tant que Resource avec l'en-tête approprié
//        return ResponseEntity.ok()
//                .headers(headers)
//                .body(resource);
//    }

    @PostMapping("/cancel/{interventionId}")
    public ResponseEntity<String> cancelIntervention(@PathVariable Long interventionId) {
        try {
            interventionService.cancelIntervention(interventionId);
            return ResponseEntity.ok("Intervention annulée avec succès.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/pieceJointe/{idPieceJointe}")
    public PieceJointe pieceJointeByIdPiecejointe(@PathVariable long idPieceJointe) {
        return pieceJointeRepository.findByIdPieceJointe(idPieceJointe);
    }


}
