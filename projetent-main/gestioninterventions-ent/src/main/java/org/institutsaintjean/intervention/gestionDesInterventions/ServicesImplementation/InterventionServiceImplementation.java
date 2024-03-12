package org.institutsaintjean.intervention.gestionDesInterventions.ServicesImplementation;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import org.institutsaintjean.intervention.gestionDesInterventions.Components.EmailManeger;
import org.institutsaintjean.intervention.gestionDesInterventions.Entities.*;
import org.institutsaintjean.intervention.gestionDesInterventions.Enumerations.Statut;
import org.institutsaintjean.intervention.gestionDesInterventions.Repositories.*;
import org.institutsaintjean.intervention.gestionDesInterventions.Services.EmailService;
import org.institutsaintjean.intervention.gestionDesInterventions.Services.InterventionService;
import org.institutsaintjean.intervention.gestionDesInterventions.exceptions.PieceJointeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.nio.file.Files;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class InterventionServiceImplementation implements InterventionService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private PersonnelRepository personnelRepository;

    @Autowired
    private EmailRepository emailRepository;



    @Autowired
    private EmailService emailService;

    @Autowired
    private DepartementRepository departementRepository;

    @Autowired
    private PieceJointeRepository pieceJointeRepository;

    @Autowired
    private EmailMessageRepository emailMessageRepository;

    @Autowired
    private PieceJointeEmailRepository pieceJointeEmailRepository;

    @Autowired
    private SousInterventionRepository sousInterventionRepository;



    @Autowired
    private InterventionRepository interventionRepository;

    @Autowired
    private EmailManeger emailManeger;

    @Value("${user.home}")
    private String homeDirectory;
    @Override
    public Intervention creerIntervention(long codeEtudiant, Long idCategorie,
                                          List<MultipartFile> fichiers,
                                          String file,
                                          String DescriptionIntervention,
                                          Long idSousIntervention) {


        // Récupération de la sousIntervention
        SousIntervention sousIntervention = sousInterventionRepository.findById(idSousIntervention)
                .orElseThrow(() -> new EntityNotFoundException("Sous-intervention introuvable avec l'ID : " + idSousIntervention));

        // Récupération de la Categorie
        Categorie categorie = categorieRepository.findById(idCategorie)
                .orElseThrow(() -> new EntityNotFoundException("Categorie introuvable avec l'ID : " + idCategorie));



        Long idDepartement = sousIntervention.getDepartement().getIdDepartement();

        // Récupération de l'étudiant et du département
        Etudiant etudiant = etudiantRepository.findByCode(codeEtudiant);
        Departement departement = departementRepository.findById(idDepartement).orElse(null);

        // Vérification si l'étudiant et le département existent
        if (etudiant == null || departement == null) {
            throw new EntityNotFoundException("Étudiant ou département introuvable.");
        }
        Intervention intervention = new Intervention();

        intervention.setEtudiant(etudiant);
        intervention.setDepartement(departement);
        intervention.setDescription(DescriptionIntervention);
        intervention.setStatut(Statut.nonTraite);
        intervention.setDateCreationInter(new Date());



//        Set<PieceJointe> piecesJointes = new HashSet<>();
//        if (pieceJointeList != null && !pieceJointeList.isEmpty()) {
//            for (MultipartFile pieceJointe : pieceJointeList) {
//                PieceJointe pieceJointeEntity = new PieceJointe();
//                pieceJointeEntity.setNomFichier(pieceJointe.getOriginalFilename());
//
//                // Enregistrement de la pièce jointe
//                String dossierPieceJointe = homeDirectory + File.separator + "Desktop" + File.separator + "Piece-jointe-intervention";
//                File dossier = new File(dossierPieceJointe);
//                if (!dossier.exists()) {
//                    if (dossier.mkdirs()) {
//                        System.out.println("Dossier Piece-jointe-intervention créé avec succès.");
//                    } else {
//                        System.out.println("Échec de la création du dossier Piece-jointe-intervention.");
//                    }
//                }
//                try {
//                    String nomFichier = pieceJointe.getOriginalFilename();
//                    String cheminFichier = dossierPieceJointe + File.separator + nomFichier;
//                    File fichier = new File(cheminFichier);
//                    pieceJointe.transferTo(fichier);
//
//                    // Configuration du chemin de stockage de la pièce jointe
//                    pieceJointeEntity.setCheminStockage(dossierPieceJointe + File.separator + nomFichier);
//                    piecesJointes.add(pieceJointeEntity);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

//        if (pieceJointe != null && !pieceJointe.isEmpty()) {
//            try {
//                // Spécifiez le chemin où vous souhaitez enregistrer le fichier
//                String filePath = "C:\\Users\\pc\\Desktop\\gestIntervention\\" + pieceJointe.getOriginalFilename();
//                pieceJointe.transferTo(new File(filePath));
//                intervention.setFile(filePath);
////                System.out.println(filePath);
////                System.out.println(nomDepartement);
//                // Vous pouvez également effectuer des opérations supplémentaires ici
//
////                return ResponseEntity.ok("File uploaded successfully");
//            } catch (IOException e) {
//                e.printStackTrace();
////                return ResponseEntity.status(500).body("Failed to upload file");
//            }
//        } else {
////            return ResponseEntity.badRequest().body("Please select a file to upload");
//        }







        intervention.setSousIntervention(sousIntervention);
        intervention.setCategorie(categorie);
        EmailMessage emailMessage  = new EmailMessage();
        //envoie d'email
        List<Personnel> personnelList = personnelRepository.findByDepartementIdDepartement(idDepartement);
        List<String> emailsList = personnelList.stream()
                .map(Personnel::getEmailPersonnel)
                .collect(Collectors.toList());
        String emailContent;
        emailContent = "Cher Monsieur/Madame "+",\n\n";
        emailContent += "\t\tNous avons le plaisir de vous informer qu'une nouvelle demande d'intervention" +
                " vient d'être reçue pour votre département. L'objet de cette demande est le suivant :" + intervention.getSousIntervention().getIntituleSousIntervention()
                + ".\n\t\tNous comptons sur votre expertise et votre engagement habituels pour traiter cette demande avec efficacité et professionnalisme. " +
                "Votre contribution est inestimable et joue un rôle essentiel dans la résolution des situations d'urgence et des défis auxquels nous sommes confrontés. \n\n"
                + "Merci pour votre collaboration.\n\n";

        emailMessage.setContent(emailContent);
        emailMessage.setSubject("NOUVELLE DEMANDE D'INTERVENTION" );

        System.out.println("belom jordan mail");

            try {
                for (String email : emailsList){
                    emailMessage.setEmail(email);
                    Email emaile  = new Email();
                    emaile.setEmail(email);
                    emaile.setContent(emailContent);
                    emaile.setSubject(emailMessage.getSubject());
                    emailManeger.addToEmailMessagePermanent(emaile);
                    if(emailManeger.isConnectedToNetwork()){
                        emailService.EnvoieEmail(emailMessage);
                        System.out.println("belom jordan mail");

                    }else {
                        emailManeger.addToEmailMessage(emailMessage);
                    }
                }
            } catch (MessagingException e) {
                e.printStackTrace();
            }

        intervention = interventionRepository.save(intervention);

        if (intervention.getPiecesJointes() == null) {
            intervention.setPiecesJointes(new HashSet<>());
        }
        if (fichiers != null && !fichiers.isEmpty()) {
            try {
                for (MultipartFile fichier : fichiers) {
                    PieceJointe pieceJointe = new PieceJointe();
                    pieceJointe.setContenu(fichier.getBytes());
                    pieceJointe.setNom(fichier.getOriginalFilename());
                    pieceJointe.setType(fichier.getContentType());
                    intervention.getPiecesJointes().add(pieceJointe);
                    pieceJointe.setIntervention(intervention);
                    // Enregistrez pieceJointe dans la base de données (utilisez votre repository correspondant)
                    pieceJointeRepository.save(pieceJointe);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return intervention;
    }


    @Override
    public Intervention prendreEnChargeIntervention(Long interventionId, Long personnelId) {
        Intervention intervention = interventionRepository.findById(interventionId)
                .orElseThrow(() -> new EntityNotFoundException("Intervention introuvable."));

        Personnel personnel = personnelRepository.findById(personnelId)
                .orElseThrow(() -> new EntityNotFoundException("Personnel introuvable."));

        intervention.setStatut(Statut.enCours);
        intervention.setPersonnel(personnel);
        intervention.setTaken(true);
        EmailMessage emailMessage  = new EmailMessage();
        String emailContent;
        emailContent = "Cher(e) étudiant(e), "+"\n\n";
        emailContent += "\t\tNous avons bien reçu votre demande d'intervention. Nous sommes heureux de vous informer qu'elle a été prise en charge" +
                " par :" + personnel.getNomPersonnel() + " " + personnel.getPrenomPersonnel() +" ayant pour Email" +  personnel.getEmailPersonnel()
                + ".\n\t\tNotre équipe se mobilise pour résoudre votre problème dans les plus brefs délais." +
                " N'hésitez pas à nous contacter si vous avez des questions ou des préoccupations supplémentaires\n\n" +
                "Merci pour votre collaboration.\n\n" +
                "Cordialement,\n Le departement" + intervention.getDepartement().getIntituleDepartement()+
                "\nINSTITUT UNIVERSITAIRE SAINT JEAN."
        ;

        emailMessage.setContent(emailContent);
        emailMessage.setSubject("PRISE EN CHARGE DE VOTRE DEMANDE D'INTERVENTION" );
        emailMessage.setEmail(intervention.getEtudiant().getEmailEtudiant());

        System.out.println("belom jordan mail");
        Email emaile  = new Email();
        emaile.setEmail(intervention.getEtudiant().getEmailEtudiant());
        emaile.setContent(emailContent);
        emaile.setSubject(emailMessage.getSubject());
        emailManeger.addToEmailMessagePermanent(emaile);

        try {
                if(emailManeger.isConnectedToNetwork()){
                    emailService.EnvoieEmail(emailMessage);
                    System.out.println("belom jordan mail");
                }else {
                    emailManeger.addToEmailMessage(emailMessage);
                }

        } catch (MessagingException e) {
            e.printStackTrace();
        }


        return interventionRepository.save(intervention);

    }

    @Override
    public Intervention terminerUneIntervention(Long interventionId,String emailContent,List<MultipartFile> piecesJointes) {
        Intervention intervention = interventionRepository.findById(interventionId)
                .orElseThrow(() -> new EntityNotFoundException("Intervention introuvable."));
        intervention.setStatut(Statut.traite);
        EmailMessage emailMessage  = new EmailMessage();
        System.out.println("belom dans l'envoie d'email" + emailContent);
        emailMessage.setContent(emailContent);
        emailMessage.setSubject("FIN DE LA PRISE EN CHARGE DE VOTRE DEMANDE D'INTERVENTION" );
        emailMessage.setEmail(intervention.getEtudiant().getEmailEtudiant());
        System.out.println("belom jordan mail");
        // Enregistrez EmailMessage dans la base de données
        emailMessageRepository.save(emailMessage);


        Email emaile  = new Email();
        emaile.setEmail(intervention.getEtudiant().getEmailEtudiant());
        emaile.setContent(emailContent);
        emaile.setSubject(emailMessage.getSubject());

        emailRepository.save(emaile);


        Set<PiecejointeEmail> PieceJointeListEmail = emaile.getPiecejointeEmails();
        if (PieceJointeListEmail == null) {
            PieceJointeListEmail = new HashSet<>(); // Initialisation de la liste
        }

        Set<PiecejointeEmail> PieceJointeListEmailMessage = emailMessage.getPiecejointeEmails();
        if (PieceJointeListEmailMessage == null) {
            PieceJointeListEmailMessage = new HashSet<>(); // Initialisation de la liste
        }


        if (piecesJointes != null && !piecesJointes.isEmpty()) {
            try {
                for (MultipartFile fichier : piecesJointes) {
                    PiecejointeEmail piecejointeEmail = new PiecejointeEmail();
                    piecejointeEmail.setContenu(fichier.getBytes());
                    piecejointeEmail.setNom(fichier.getOriginalFilename());
                    piecejointeEmail.setType(fichier.getContentType());
                    piecejointeEmail.setEmail(emaile);
                    piecejointeEmail.setEmailMessage(emailMessage);
                    // Enregistrez pieceJointe dans la base de données (utilisez votre repository correspondant)
                    pieceJointeEmailRepository.save(piecejointeEmail);
                    PieceJointeListEmail.add(piecejointeEmail);
                    PieceJointeListEmailMessage.add(piecejointeEmail);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        emaile.setPiecejointeEmails(PieceJointeListEmail);
        emailMessage.setPiecejointeEmails(PieceJointeListEmailMessage);
        emailManeger.addToEmailMessagePermanent(emaile);

        try {

            if(emailManeger.isConnectedToNetwork()){
                emailService.EnvoieEmailEtudiantFin(emailMessage);
                System.out.println("belom jordan mail");
            }else {
                emailManeger.addToEmailMessage(emailMessage);
            }



        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return interventionRepository.save(intervention);
    }

//    @Override
//    public Resource downloadPieceJointe(Long interventionId) throws IOException {
//        Intervention intervention = interventionRepository.findById(interventionId).orElse(null);
//        if (intervention == null || intervention.getFile() == null) {
//            throw new FileNotFoundException("Fichier introuvable pour cette intervention.");
//        }
//
//        String filePath = intervention.getFile();
//        File file = new File(filePath);
//        byte[] fileBytes = Files.readAllBytes(file.toPath());
//        return new ByteArrayResource(fileBytes);
//    }

    public PieceJointe getPieceJointeById(Long idPieceJointe) {
        // Appelez votre repository pour récupérer la pièce jointe par son ID
        // Utilisez votre repository pour rechercher la pièce jointe par son ID
        Optional<PieceJointe> optionalPieceJointe = pieceJointeRepository.findById(idPieceJointe);
        // Vérifiez si la pièce jointe existe, sinon lancez une exception ou gérez l'absence de pièce jointe comme approprié
        return optionalPieceJointe.orElseThrow(() -> new PieceJointeNotFoundException("Piece jointe introuvable avec l'ID : " + idPieceJointe));
    }

    public void cancelIntervention(Long interventionId) {
        Optional<Intervention> optionalIntervention = interventionRepository.findById(interventionId);
        if (optionalIntervention.isPresent()) {
            Intervention intervention = optionalIntervention.get();
            if (!intervention.isTaken()) {
                intervention.setCanceled(true);
                interventionRepository.save(intervention);
            } else {
                throw new RuntimeException("L'intervention a déjà été prise en charge et ne peut pas être annulée.");
            }
        } else {
            throw new RuntimeException("Intervention non trouvée.");
        }
    }


}
