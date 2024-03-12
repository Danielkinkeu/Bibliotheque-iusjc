package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.model.entities.*;
import org.isj.ing3.isi.webservice.webservicerest.model.modeletat.*;
import org.isj.ing3.isi.webservice.webservicerest.service.*;
import org.isj.ing3.isi.webservice.webservicerest.utils.etats.GeneratePDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PrintServiceImpl implements IPrintService {

    @Autowired
    INote iNote;
    @Autowired
    IClasse iClasse;
    @Autowired
    IFiliere iFiliere;
    @Autowired
    IEtudiant iEtudiant;
    @Autowired
    ICandidat iCandidat;
    @Autowired
    IAnneeAcademique iAnneeAcademique;


    @Autowired
    GeneratePDF generatePDF;

    @Override
    public File generateCarteEtudiant(CarteEtudiant carteEtudiant) throws Exception {

        Classe classe = iClasse.getClasseByCode(carteEtudiant.getCodeClasse());
        int annee = (carteEtudiant.getAnnee().getYear()+1900);
        //CarteEtudiant carteEtudiant = new CarteEtudiant("Nkot-a-Nzok", "Honoré Etienne", "05-01-2002", "699278957", "690581290", "2021i321", "Edea", "Informatique et Système d'information", "ISI", "3", 2021);
        //return generatePDF.genererCartesEtudiants("Ingenieur", "Informatique et systèmes d'information", 2020, 3);
        return generatePDF.genererCartesEtudiants(classe.getSpecialite().getFiliere().getLibelle(), classe.getSpecialite().getLibelle(), annee, classe.getNiveau().getNumero());

    }

    @Override
    public byte[] generateAttestationReusite(AttestationEtDiplome attestation) throws Exception {
        try {

            Classe classe = iClasse.getClasseByCode(attestation.getCodeClasse());
            List<Note> notes = iNote.getNotesByFiliereAndSpecialiteAndNiveauAndAnneDebutOrderByCandidat(classe.getSpecialite().getFiliere().getLibelle(), classe.getSpecialite().getLibelle(), classe.getNiveau().getNumero(), attestation.getAnnee());

            //generatePDF.genererDiplome(noteEnvois, "LIC 3", 2020, "2020-07-18", "Concepteur Développeur d’Applications pour l’Economie Numérique", "Licence Professionnelle", 3);
            return generatePDF.genererAttestation(notes, classe.getLibelle(), attestation.getAnnee(), attestation.getDateJury(),
                    classe.getSpecialite().getLibelle(), classe.getSpecialite().getFiliere().getLibelle(),
                     classe.getNiveau().getNumero(), null, (String) null);

        }catch (Exception e) {
            throw new Exception("Erreur lors de l'impression des attestations");
        }
    }

    @Override
    public byte[] generateDiplome(AttestationEtDiplome diplome) throws Exception {

        try {

            Classe classe = iClasse.getClasseByCode(diplome.getCodeClasse());
            List<Note> notes = iNote.getNotesByFiliereAndSpecialiteAndNiveauAndAnneDebutOrderByCandidat(classe.getSpecialite().getFiliere().getLibelle(), classe.getSpecialite().getLibelle(), classe.getNiveau().getNumero(), diplome.getAnnee());
            System.out.println(notes.get(0));
            System.out.println(notes.get(1));
            //return generatePDF.genererDiplome(noteEnvois, "LIC 3", 2020, "2020-07-18", "Concepteur Développeur d’Applications pour l’Economie Numérique", "Licence Professionnelle", 3);
            return generatePDF.genererDiplome(notes, classe.getLibelle(), diplome.getAnnee(), diplome.getDateJury(), classe.getSpecialite().getLibelle(), classe.getSpecialite().getFiliere().getLibelle(), classe.getNiveau().getNumero());

        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public byte[] generateFicheAbsences(FicheAbsence ficheAbsence) throws Exception {
        try{
            Classe classe = iClasse.getClasseByCode(ficheAbsence.getCodeClasse());


            DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
            //AnneeAcademique anneeAcademique = iAnneeAcademique.getAnneeAcademiqueByDate(simpleDateFormat.parse(ficheAbsence.getDebutAnneeAcademique()+" 22:00:00"));
            //AnneeAcademique anneeAcademique = ficheAbsence.getAnneeacademique();
            //System.out.println(anneeAcademique);
            return generatePDF.genererFicheAbsence(classe.getLibelle(), classe.getNiveau().getNumero(), classe.getSpecialite().getFiliere().getLibelle(), ficheAbsence.getAnnee(), simpleDateFormat.parse(ficheAbsence.getDateDebut() +" 23:00:00"), simpleDateFormat.parse(ficheAbsence.getDateFin()+" 23:00:0"), ficheAbsence.getSemestre());

        }catch (Exception e) {
            System.out.println(e);
            throw new Exception("Erreur lors de l'impression des fiches d'absences");
        }
    }

    @Override
    public byte[] generatePv(Pv pv) throws Exception {
        try {
            System.out.println(pv);
            Classe classe = iClasse.getClasseByCode(pv.getCodeClasse());
            return generatePDF.genererPv(Integer.valueOf(pv.getAnnee().substring(0, pv.getAnnee().indexOf("-"))),pv.getSemestre(),classe.getLibelle(), classe.getSpecialite().getFiliere().getLibelle(), classe.getSpecialite().getLibelle(), pv.isFormat(), classe.getNiveau().getNumero());

        } catch (Exception e) {
            System.out.println(e);
            throw new Exception("Erreur lors de l'impression des pv");
        }
    }

    @Override
    public byte[] generateListe(Pv pv) throws Exception {
        try {
            System.out.println(pv);
            Classe classe = iClasse.getClasseByCode(pv.getCodeClasse());
            return generatePDF.genererListe(Integer.valueOf(pv.getAnnee().substring(0, pv.getAnnee().indexOf("-"))),pv.getSemestre(),classe.getLibelle(), classe.getSpecialite().getFiliere().getLibelle(), classe.getSpecialite().getLibelle(), classe.getNiveau().getNumero());

        } catch (Exception e) {
            System.out.println(e);
            throw new Exception("Erreur lors de l'impression de la liste de classe");
        }
    }

    @Override
    public byte[] generateNotes() throws Exception {
        try {
            return generatePDF.imprimerNotes(3, "Semestre 1","2020","Semestre 1", "Informatique et systèmes d'information", "CC", 2020, "ISI3165");
        }catch (Exception e) {
            throw new Exception("Erreur lors de l'impression des notes");
        }
    }

    @Override
    public byte[] generateReleveNote(ReleveNote note) throws Exception {
        try {
            Classe clas = iClasse.getClasseByCode(note.getCodeClasse());
            String clas1 = clas.getLibelle();
            Integer niv = clas.getNiveau().getNumero();
            String fil = clas.getSpecialite().getFiliere().getLibelle();
            String speci=clas.getSpecialite().getLibelle();
            List<String> matricules = new ArrayList<>();
            if(note.getMatricules()==null || note.getMatricules().equalsIgnoreCase("")) {
                List<Candidat> candidats = iCandidat.searchCandidatByClasse(clas);
                for (int i = 0; i < candidats.size(); i++) {
                    String etudiant = iEtudiant.getEtudiantByCode((candidats.get(i).getCode())).getMatricule();
                    matricules.add(etudiant);
                }
            }
            else{
                String[] listeEtudiants=note.getMatricules().toString().split(";");
                if (listeEtudiants != null && listeEtudiants.length != 0) {
                    for (int i = 0; i < listeEtudiants.length; i++) {
                        matricules.add(listeEtudiants[i]);
                    }
                }
            }
            return generatePDF.genererReleve(matricules, clas1, note.getAnneeDebut(), note.getTypeReleve(),speci,fil,niv,note.getDecision(),
                    "PDF","IUSJ");
        }catch (Exception e) {
            System.out.println(e);
            throw new Exception("Erreur lors de l'impression des relevés de notes");
        }
    }

    @Override
    public byte[] generatePVSynthese(PVSynthese pvSynthese) throws Exception {
        try {
            Classe clas = iClasse.getClasseByCode(pvSynthese.getCodeClasse());
            String clas1 = clas.getLibelle();
            Integer niv = clas.getNiveau().getNumero();
            String fil = clas.getSpecialite().getFiliere().getLibelle();
            String speci=clas.getSpecialite().getLibelle();
            boolean imprimerAttestations= pvSynthese.isImprimerAttestations();
            String dateJury= pvSynthese.getDateJury();
            List<String> matricules = new ArrayList<>();
            Map<String, Etudiant> etudiants = new HashMap();
            if(pvSynthese.getMatricules()==null || pvSynthese.getMatricules().equalsIgnoreCase("")) {
                List<Candidat> candidats = iCandidat.searchCandidatByClasse(clas);
                for (int i = 0; i < candidats.size(); i++) {
                    Etudiant student = iEtudiant.getEtudiantByCode((candidats.get(i).getCode()));
                    String etudiant = student.getMatricule();
                    etudiants.put(etudiant,student);
                    matricules.add(etudiant);
                }
            }
            else{
                String[] listeEtudiants=pvSynthese.getMatricules().toString().split(";");
                if (listeEtudiants != null && listeEtudiants.length != 0) {
                    for (int i = 0; i < listeEtudiants.length; i++) {
                        matricules.add(listeEtudiants[i]);
                        etudiants.put(listeEtudiants[i],iEtudiant.getStudentByMatricule(listeEtudiants[i]));
                    }
                }
            }
            return generatePDF.genererPVSynthse(matricules, clas1, pvSynthese.getAnneeDebut(),speci,fil,niv,pvSynthese.getCreditsMinAdmission(),
                    pvSynthese.getMgpMinAdmission(),pvSynthese.getNbAnneesCursus(), imprimerAttestations,  dateJury,etudiants);
        }catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erreur lors de l'impression des relevés de notes");
        }
    }

    @Override
    public byte[] generateCertificatScolarite(ReleveNote note) throws Exception {
        try {
            Classe clas = iEtudiant.getStudentByMatricule(note.getMatricules()).getClasse();
            String clas1 = clas.getLibelle();
            Integer niv = clas.getNiveau().getNumero();
            String fil = clas.getSpecialite().getFiliere().getLibelle();
            String speci=clas.getSpecialite().getLibelle();
            List<String> matricules = new ArrayList<>();
            if(note.getMatricules()==null || note.getMatricules().equalsIgnoreCase("")) {
                List<Candidat> candidats = iCandidat.searchCandidatByClasse(clas);
                for (int i = 0; i < candidats.size(); i++) {
                    String etudiant = iEtudiant.getEtudiantByCode((candidats.get(i).getCode())).getMatricule();
                    matricules.add(etudiant);
                }
            }
            else{
                String[] listeEtudiants=note.getMatricules().toString().split(";");
                if (listeEtudiants != null && listeEtudiants.length != 0) {
                    for (int i = 0; i < listeEtudiants.length; i++) {
                        matricules.add(listeEtudiants[i]);
                    }
                }
            }
            return generatePDF.genererCertificat(matricules, clas1, note.getAnneeDebut(), note.getTypeReleve(),speci,fil,niv,note.getDecision());
        }catch (Exception e) {
            System.out.println(e);
            throw new Exception("Erreur lors de l'impression des relevés de notes");
        }
    }
}
