package org.isj.ing3.isi.webservice.webservicerest.utils.etats;


import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.multipdf.Overlay;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.util.Matrix;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.*;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Note;
import org.isj.ing3.isi.webservice.webservicerest.service.IEtudiant;
import org.isj.ing3.isi.webservice.webservicerest.serviceImpl.EtudiantServiceImpl;
import org.isj.ing3.isi.webservice.webservicerest.utils.Bd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.awt.*;
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

/**
 * @author USER
 */
@Service
public class GeneratePDF {

    @Autowired
    EtudiantServiceImpl etudiantImpl;

    @Autowired
    IEtudiant iEtudiant;


    public File genererCartesEtudiants(String fil, String speci, int an, int niv) throws Exception {


        // - Chargement et compilation du rapport
        //JasperDesign jasperDesign = JRXmlLoader.load(GeneratePDF.class.getClassLoader().getResourceAsStream("src/main/ressources/etats/carte_etudiant.jrxml"));
        JasperReport jasperReport = JasperCompileManager.compileReport(new ClassPathResource("etats" + FileSystems.getDefault().getSeparator() + "carte_etudiant.jrxml").getInputStream());
        File fichierCourant = null;

        try {
            long t1 = System.currentTimeMillis();
            // - Paramètres à envoyer au rapport

            Connection connection = Bd.getConnection();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMMMM yyyy");
                    /*List<CarteEtudiant> carteEtudiants = new ArrayList<CarteEtudiant>();
                    carteEtudiants.add(etudiant);*/
            Map parameters = new HashMap();
            parameters.put("filiere", fil);
            parameters.put("specialite", speci);
            parameters.put("niveau", niv);
            parameters.put("annee", an);

            // - Execution du rapport
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
            //JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(carteEtudiants);

            // - Execution du rapport
            //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, jrBeanCollectionDataSource);

            // - Création du rapport au format PDF
            //String fileName= "K:/ISI/IS4/projet-tutore/web-service/webservicerest/src/main/resources/etatsImprimes/carteEtudiants/cartes-" + fil + "-" + speci + "-" + System.currentTimeMillis() + ".pdf";
            String fileName = (GeneratePDF.class.getClassLoader().getResource(".").getPath() + "etatsImprimes" + FileSystems.getDefault().getSeparator() + "carteEtudiants" + FileSystems.getDefault().getSeparator() + "cartes-" + fil + "-" + speci + "-" + System.currentTimeMillis() + ".pdf");

            File f = new File(fileName);
            System.out.println(f.getPath());
            f.createNewFile();
            JasperExportManager.exportReportToPdfFile(jasperPrint, f.getAbsolutePath());
            byte[] data = JasperExportManager.exportReportToPdf(jasperPrint);
            fichierCourant = f;
            long t2 = System.currentTimeMillis();
            System.out.println("Fin d'impression des cartes en..." + (t2 - t1) / 60000 + " min");
            //openFile(f);
        } catch (Exception e) {
            e.printStackTrace();
        }

        File result = superposerPDFCarteEtudiant(new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "fond_carte_etudiant.pdf").getFile(), fichierCourant);
        //return Files.readAllBytes(result.toPath());
        return result;
        //openFile(result);
    }

/*
    public byte[] genererReleve(List<String> matricules, String classe, int annee, String typeReleve,
                                String speci, String filiere, int niveau, String decision) throws Exception {

        System.out.println(matricules.toString() + "-" + classe + "-" + typeReleve + "-" + speci + "-" + filiere + "-" + niveau + "-" + decision);

        String listeMatricules = "";
        if (matricules != null && matricules.size() > 0) {
            for (int i = 0; i < matricules.size(); i++) {
                listeMatricules += (matricules.get(i) + ";");
            }
        }
        System.out.println("Matricules:" + listeMatricules);

        //JasperDesign jasperDesign = JRXmlLoader.load(GeneratePDF.class.getClassLoader().getResourceAsStream("src/main/resources/etats/ReleveFinal.jrxml"));
        //JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperReport jasperReport = JasperCompileManager.compileReport(new ClassPathResource("etats" + FileSystems.getDefault().getSeparator() + "ReleveFinal.jrxml").getInputStream());


        if (matricules != null && matricules.size() > 0) {

            // - Chargement et compilation du rapport
            File fichierCourant = null;
            Connection connection = Bd.getConnection();
            for (int i = 0; i < matricules.size(); i++) {
                String matricule = matricules.get(i);
                try {
                    long t = System.currentTimeMillis();
                    System.out.println("Debut d'impresion du Relevé " + (i + 1) + " " + matricule);

                    //Etudiant etudiant = new Isj().retrouverEtudiantMatricule(matricule);
                    Etudiant etudiant = new Etudiant();
                    etudiant = iEtudiant.getStudentByMatricule(matricule);
                    long t1 = System.currentTimeMillis();

                    // - Paramètres à envoyer au rapport
                    Map parameters = new HashMap();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMMMM yyyy");
                    parameters.put("filiere", filiere);
                    parameters.put("specialites", filiere + " - " + speci);
                    parameters.put("nom_etudiant", etudiant.getNom() + " " + etudiant.getPrenom());
                    parameters.put("date_naissance", dateFormat.format(etudiant.getDateNaissance()));
                    parameters.put("niveau", niveau);
                    parameters.put("classe", classe);
                    parameters.put("annee_academique", annee);
                    parameters.put("sexe", etudiant.getSexe().toString());
                    parameters.put("matricule", matricule);
                    parameters.put("image_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "logo_isj.jpeg").getFile().getAbsolutePath());
                    parameters.put("entete_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "entete_isj.jpg").getFile().getAbsolutePath());
                    parameters.put("image_uy1", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "logo_uy1.jpg").getFile().getAbsolutePath());
                    parameters.put("image_ensp", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "logo_ensp.jpg").getFile().getAbsolutePath());
                    parameters.put("speci", speci);
                    parameters.put("numero_releve", i);
                    System.out.println(etudiant.toString());
                    Long numero = (etudiant.getCode() + annee);
                    System.out.println("Numero = " + numero);
                    parameters.put("numero", numero);
                    parameters.put("type_releve", typeReleve);
                    parameters.put("semestr", typeReleve);
                    parameters.put("listeMatricules", listeMatricules);
                    parameters.put("decision", decision);
                    // - Execution du rapport
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

                    // - Création du rapport au format PDF
                    String fileName;
                    if (matricules.size() == 1)
                         //fileName= "K:/ISI/IS4/projet-tutore/web-service/webservicerest/src/main/resources/etatsImprimes/releves/Releve-" + matricule + "-" + classe + "-" + System.currentTimeMillis() + ".pdf";
                        //fileName=new File("/etatsImprimes"+FileSystems.getDefault().getSeparator()+"releves"+FileSystems.getDefault().getSeparator()+"Releve-" + matricule + "-" + classe + "-" + System.currentTimeMillis() + ".pdf").getAbsolutePath();
                        fileName = (GeneratePDF.class.getClassLoader().getResource(".").getPath() + "etatsImprimes" + FileSystems.getDefault().getSeparator() + "releves" + FileSystems.getDefault().getSeparator() + "Releve-" + matricule + "-" + classe + "-" + System.currentTimeMillis() + ".pdf");
                    else
                        //fileName = "K:/ISI/IS4/projet-tutore/web-service/webservicerest/src/main/resources/etatsImprimes/releves/Releves" + "-" + classe + "-" + annee + "-" + System.currentTimeMillis() + ".pdf";
                        fileName = (GeneratePDF.class.getClassLoader().getResource(".").getPath() + "etatsImprimes" + FileSystems.getDefault().getSeparator() + "releves" + FileSystems.getDefault().getSeparator() + "-" + classe + "-" + annee + "-" + System.currentTimeMillis() + ".pdf");

                    System.out.println("fileName = " + fileName);
                    File f = new File(fileName);
                    f.createNewFile();
                    JasperExportManager.exportReportToPdfFile(jasperPrint, f.getAbsolutePath());
                    if (i == 0) {
                        fichierCourant = f;
                    } else {
                        PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
                        pdfMergerUtility.addSource(fichierCourant);
                        pdfMergerUtility.addSource(f);

                        pdfMergerUtility.setDestinationFileName(fichierCourant.getAbsolutePath());
                        pdfMergerUtility.mergeDocuments();
                        f.delete();
                    }
                    long t2 = System.currentTimeMillis();
                    System.out.println("Fin d'impression du releve en..." + (t2 - t1) / 60000 + " min");
                    //openFile(f);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            File result = superposerPDF(new ClassPathResource("images/image_fond.pdf").getFile(), fichierCourant);
            return Files.readAllBytes(result.toPath());
            //openFile(result);
        }
        return new byte[0];
    }
*/
    public byte[] genererReleve(List<String> matricules, String classe, int annee, String typeReleve,
                                String speci, String filiere, int niveau, String decision,
                                String typeEtat, String ecoleEtat) throws Exception {

        System.out.println(annee+" - "+matricules.toString() + "-" + classe + "-" + typeReleve + "-" + speci + "-" + filiere + "-" + niveau + "-" + decision + "-" + typeEtat + "-" + ecoleEtat);
        File result = null;

        String listeMatricules = "";
        if (matricules != null && matricules.size() > 0) {
            for (int i = 0; i < matricules.size(); i++) {
                listeMatricules += (matricules.get(i) + ";");
            }
        }
        System.out.println("Matricules:" + listeMatricules);

        //JasperDesign jasperDesign = JRXmlLoader.load(GeneratePDF.class.getClassLoader().getResourceAsStream("src/main/resources/etats/ReleveFinal.jrxml"));
        //JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String, Object> parameters = new HashMap();

        JasperReport jasperReport;
        if (ecoleEtat.equalsIgnoreCase("IUSJ"))
            jasperReport = JasperCompileManager.compileReport(new ClassPathResource("etats" + FileSystems.getDefault().getSeparator() + "ReleveFinal.jrxml").getInputStream());
        else {
            jasperReport = JasperCompileManager.compileReport(new ClassPathResource("etats" + FileSystems.getDefault().getSeparator() + "ReleveFinalUTT.jrxml").getInputStream());
            parameters.put("nb_annees_cursus", 3);
            parameters.put("afficher_moyenne", false);
            parameters.put("afficher_decision", true);
            parameters.put("titre", "Institut Saint Jean - PV de jury de diplôme DU_IRIS-" + getBranche(speci) + "/" + speci + "_" + new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        }

        if (matricules != null && matricules.size() > 0) {

            // - Chargement et compilation du rapport
            File fichierCourant = null;
            File f = null;
            SimpleXlsxReportConfiguration configuration = null;
            File outputFile = null;
            Connection connection = Bd.getConnection();
            List<JasperPrint> jprintlist = new ArrayList<JasperPrint>();

            for (int i = 0; i < matricules.size(); i++) {
                long t1 = System.currentTimeMillis();
                String matricule = matricules.get(i);
                try {
                    System.out.println("Debut d'impresion du Relevé " + (i + 1) + " " + matricule);

                    //Etudiant etudiant = new Isj().retrouverEtudiantMatricule(matricule);
                    Etudiant etudiant = new Etudiant();
                    etudiant = iEtudiant.getStudentByMatricule(matricule);

                    // - Paramètres à envoyer au rapport
                    SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd MMMM yyyy",  java.util.Locale.FRANCE);

                    parameters.put("filiere", filiere);
                    parameters.put("specialites", (speci));
                    parameters.put("nom_etudiant", (etudiant.getNomPrenom()));
                    parameters.put("date_naissance", dateFormat.format(etudiant.getDateNaissance()));
                    parameters.put("date_jour", dateFormat.format(new java.util.Date()));
                    parameters.put("niveau", niveau);
                    parameters.put("classe", classe);
                    parameters.put("annee_academique", annee);
                    parameters.put("sexe", etudiant.getSexe().toString());
                    parameters.put("matricule", matricule);
                    parameters.put("image_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "logo_isj.jpeg").getFile().getAbsolutePath());
                    parameters.put("entete_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "entete_isj.jpg").getFile().getAbsolutePath());
                    parameters.put("image_uy1", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "logo_uy1.jpg").getFile().getAbsolutePath());
                    parameters.put("image_ensp", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "logo_ensp.jpg").getFile().getAbsolutePath());
                    parameters.put("speci", speci);
                    parameters.put("numero_releve", i);
                    System.out.println(etudiant.toString());
                    Long numero = (etudiant.getCode() + annee);
                    System.out.println("Numero = " + numero);
                    parameters.put("numero", numero);
                    parameters.put("type_releve", typeReleve);
                    parameters.put("semestr", typeReleve);
                    parameters.put("listeMatricules", listeMatricules);
                    parameters.put("decision", decision);
                    parameters.put("lieu_naissance", etudiant.getLieuNaissance());

                    // - Execution du rapport
                    Set<Map.Entry<String, Object>> parametersSet = parameters.entrySet();
                    for (Map.Entry<String, Object> x : parametersSet)
                        System.out.println(x.getKey() + "=" + x.getValue().toString());
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

                    // - Création du rapport au format PDF
                    String fileName, filename0;
                    if (matricules.size() == 1) {
                        filename0 = new File("/etatsImprimes" + FileSystems.getDefault().getSeparator() + "releves" + FileSystems.getDefault().getSeparator() + "Releve-" + matricule + "-" + classe + "-" + System.currentTimeMillis() + ".pdf").getAbsolutePath();
                        fileName = (GeneratePDF.class.getClassLoader().getResource(".").getPath() + "etatsImprimes" + FileSystems.getDefault().getSeparator() + "releves" + FileSystems.getDefault().getSeparator() + "Releve-" + matricule + "-" + classe + "-" + System.currentTimeMillis() + ".pdf");
                    }else {
                        filename0 = new File("/etatsImprimes" + FileSystems.getDefault().getSeparator() + "releves" + FileSystems.getDefault().getSeparator() + "Releve-" + matricule + "-" + classe + "-" + System.currentTimeMillis() + ".pdf").getAbsolutePath();
                        fileName = (GeneratePDF.class.getClassLoader().getResource(".").getPath() + "etatsImprimes" + FileSystems.getDefault().getSeparator() + "releves" + FileSystems.getDefault().getSeparator() + "Releves" + "-" + classe + "-" + annee + "-" + System.currentTimeMillis() + ".pdf");
                    }
                        System.out.println("fileName0 = " + filename0);
                        System.out.println("fileName = " + fileName);

                        fileName=filename0;//Lorsque l'application s'execute directement sur Intellij

                    if (typeEtat.equalsIgnoreCase("pdf")) {
                        // - Création du rapport au format PDF
                        //f = new File (GeneratePDF.class.getClassLoader().getResource(".").getPath()+"etatsImprimes"+FileSystems.getDefault().getSeparator()+"pv"+FileSystems.getDefault().getSeparator()+"" + "PV-" + filiere + "-" + specialites + "-" + niveau + "-" + semestre + "-" + System.currentTimeMillis() + ".pdf");
                        File fi = new File(fileName);
                        fi.createNewFile();
                        JasperExportManager.exportReportToPdfFile(jasperPrint, fi.getAbsolutePath());
                        if (i == 0) {
                            fichierCourant = fi;
                        } else {
                            PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
                            pdfMergerUtility.addSource(fichierCourant);
                            pdfMergerUtility.addSource(fi);

                            pdfMergerUtility.setDestinationFileName(fichierCourant.getAbsolutePath());
                            pdfMergerUtility.mergeDocuments();
                            fi.delete();
                        }

                    } else {
                        jprintlist.add(jasperPrint);

                        if (i == (matricules.size() - 1)) {
                            outputFile = new File(GeneratePDF.class.getClassLoader().getResource(".").getPath() + "etatsImprimes" + FileSystems.getDefault().getSeparator() + "releves" + FileSystems.getDefault().getSeparator() + "" + "releves-" + filiere + "-" + speci + "-" + niveau + "-" + ecoleEtat + "-" + System.currentTimeMillis() + ".xlsx");
                            outputFile.createNewFile();
                            JRXlsxExporter exporter = new JRXlsxExporter();
                            /*configuration = new SimpleXlsxReportConfiguration();
                            configuration.setOnePagePerSheet(false);
                            configuration.setIgnoreGraphics(false);*/
                            exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
                            exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT_LIST, jprintlist);
                            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outputFile.getAbsolutePath());

                            exporter.exportReport();
                        }
/*                            //Exportation de l'état au format EXCEL
                            f = new File(GeneratePDF.class.getClassLoader().getResource(".").getPath() + "etatsImprimes" + FileSystems.getDefault().getSeparator() + "releves" + FileSystems.getDefault().getSeparator() + "" + "releves-" + filiere + "-" + speci + "-" + niveau + "-" + ecoleEtat + "-" + System.currentTimeMillis() + ".xlsx");
                            f.createNewFile();
                            configuration = new SimpleXlsxReportConfiguration();
                            configuration.setOnePagePerSheet(false);
                            configuration.setIgnoreGraphics(false);
                            outputFile = new File(f.getAbsolutePath());
                        }

                        File newFile=new File(GeneratePDF.class.getClassLoader().getResource(".").getPath() + "etatsImprimes" + FileSystems.getDefault().getSeparator() + i +"-"+ matricule +"-"+ System.currentTimeMillis() + ".xlsx");

                        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                             OutputStream fileOutputStream = new FileOutputStream(newFile)) {
                                Exporter exporter = new JRXlsxExporter();
                                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
                                exporter.setConfiguration(configuration);
                                exporter.exportReport();
                                byteArrayOutputStream.writeTo(fileOutputStream);
                                byteArrayOutputStream.close();
                                //File tempFile=new File(GeneratePDF.class.getClassLoader().getResource(".").getPath() + "etatsImprimes" + FileSystems.getDefault().getSeparator() + "temp"+i + System.currentTimeMillis() + ".xlsx");
                                //Files.copy(outputFile.toPath(),tempFile.toPath());
                                if(i==0) {
                                    outputFile = newFile;
                                }
                                else {
                                    //outputFile = new File(MergeExcelFiles.merge(new String[]{outputFile.getAbsolutePath(), newFile.getAbsolutePath()}, outputFile.getAbsolutePath()));
                                    com.aspose.cells.Workbook excelWorkbook0 = new com.aspose.cells.Workbook(newFile.getAbsolutePath());
    // Create another Workbook.
                                    com.aspose.cells.Workbook excelWorkbook1 = new com.aspose.cells.Workbook(outputFile.getAbsolutePath());
    // Copy the first sheet of the first book into second book.
                                    excelWorkbook1.getWorksheets().add();
                                    int numberOfSheets=excelWorkbook1.getWorksheets().getCount();
                                    excelWorkbook1.getWorksheets().get(numberOfSheets-1).copy(excelWorkbook0.getWorksheets().get(0));
    // Save the file.
                                    excelWorkbook1.save(outputFile.getAbsolutePath(), FileFormatType.XLSX);
                                    try {
                                        Files.deleteIfExists(newFile.toPath());
                                    }catch (Exception e){
                                        //e.printStackTrace();
                                    }
                                }
                            }*/

                    }
                    //openFile(f);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                long t2 = System.currentTimeMillis();
                System.out.println("Fin d'impression du releve en..." + (t2 - t1) / 60000 + " min");
            }

            if (typeEtat.equalsIgnoreCase("pdf"))
                result = superposerPDF(new ClassPathResource("images/image_fond.pdf").getFile(), fichierCourant);
            else
                result = outputFile;

            return Files.readAllBytes(result.toPath());
            //openFile(result);
        }
        return new byte[0];
    }

    public byte[] genererPVSynthse(List<String> matricules, String classe, int annee,
                                   String speci, String filiere, int niveau, float creditsMinAdmission,
                                   float mgpMinAdmission, int nbAnneesCursus,
                                   boolean imprimerAttestations, String dateJury,Map<String, Etudiant> etudiants) throws Exception {

        System.out.println(matricules.toString() + "-" + classe + "-" + creditsMinAdmission + "-" + speci + "-" + filiere + "-" + niveau + "-" + mgpMinAdmission);

        String listeMatricules = "";
        if (matricules != null && matricules.size() > 0) {
            for (int i = 0; i < matricules.size(); i++) {
                listeMatricules += (matricules.get(i) + ";");
            }
        }
        System.out.println("Matricules:" + listeMatricules);

        //JasperDesign jasperDesign = JRXmlLoader.load(GeneratePDF.class.getClassLoader().getResourceAsStream("src/main/resources/etats/ReleveFinal.jrxml"));
        //JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperReport jasperReport = JasperCompileManager.compileReport(new ClassPathResource("etats" + FileSystems.getDefault().getSeparator() + "PvRecapitulatif.jrxml").getInputStream());


        if (matricules != null && matricules.size() > 0) {

            // - Chargement et compilation du rapport
            File fichierCourant = null;
            //Etudiant etudiant = iEtudiant.getStudentByMatricule(matricules.get(0));
            Connection connection = Bd.getConnection();
             //etudiant = iEtudiant.getStudentByMatricule(matricules.get(1));
            //for (int i = 0; i < matricules.size(); i++) {
            //String matricule = matricules.get(i);
            // int i=0;
            try {
                long t = System.currentTimeMillis();
                System.out.println("Debut d'impresion du PV de synthèse " + (0 + 1) + " " + matricules);

                //Etudiant etudiant = new Isj().retrouverEtudiantMatricule(matricule);
                //Etudiant etudiant = new Etudiant();
                //etudiant = iEtudiant.getStudentByMatricule(matricule);
                long t1 = System.currentTimeMillis();

                // - Paramètres à envoyer au rapport
                Map parameters = new HashMap();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMMMM yyyy");
                parameters.put("filiere", filiere);
                parameters.put("specialites", speci);
                //parameters.put("nom_etudiant", etudiant.getNom() + " " + etudiant.getPrenom());
                //parameters.put("date_naissance", dateFormat.format(etudiant.getDateNaissance()));
                parameters.put("niveau", niveau);
                parameters.put("classe", classe);
                parameters.put("annee_academique", annee);
                //parameters.put("sexe", etudiant.getSexe().toString());
                //parameters.put("matricule", listeMatricules);
                parameters.put("image_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "logo_isj.jpeg").getFile().getAbsolutePath());
                parameters.put("entete_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "entete_isj.jpg").getFile().getAbsolutePath());
                //parameters.put("image_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "logo_isj.jpeg").getFile().getAbsolutePath());parameters.put("entete_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "entete_isj.jpg").getFile().getAbsolutePath());
                parameters.put("image_uy1", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "logo_uy1.jpg").getFile().getAbsolutePath());
                parameters.put("image_ensp", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "logo_ensp.jpg").getFile().getAbsolutePath());
                parameters.put("speci", speci);
                parameters.put("numero_releve", 0);
                //System.out.println(etudiant.toString());
                //Long numero = (etudiant.getCode() + annee);
                //System.out.println("Numero = " + numero);
                parameters.put("numero", 0);
                parameters.put("credits_min_admission", creditsMinAdmission);
                parameters.put("mgp_min_admission", mgpMinAdmission);
                parameters.put("listeMatricules", listeMatricules);
                parameters.put("nb_annees_cursus", nbAnneesCursus);
                parameters.put("semestr", "Annuel");
                // - Execution du rapport
                //etudiant = iEtudiant.getStudentByMatricule(matricules.get(1));
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
                //etudiant = iEtudiant.getStudentByMatricule(matricules.get(1));
//jasperPrint.
                // - Création du rapport au format PDF
                String fileName;
                if (matricules.size() == 1)
                    //fileName=new File("/etatsImprimes"+FileSystems.getDefault().getSeparator()+"releves"+FileSystems.getDefault().getSeparator()+"Releve-" + matricule + "-" + classe + "-" + System.currentTimeMillis() + ".pdf").getAbsolutePath();
                    //fileName = (GeneratePDF.class.getClassLoader().getResource(".").getPath() + "etatsImprimes/PVSyntheses/PVSynthese-" + "-" + classe + "-" + System.currentTimeMillis() + ".pdf");
                    //fileName = "K:/ISI/IS4/projet-tutore/web-service/webservicerest/src/main/resources/etatsImprimes/PVSyntheses/PVSynthese-" + "-" + classe + "-" + System.currentTimeMillis() + ".pdf";
                    fileName = "C:/etatsImprimes/PVSyntheses/PVSynthese-" + "-" + classe + "-" + System.currentTimeMillis() + ".pdf";

                else
                    //fileName = "K:/ISI/IS4/projet-tutore/web-service/webservicerest/src/main/resources/etatsImprimes/PVSyntheses/PVSyntheses" + "-" + classe + "-" + annee + "-" + System.currentTimeMillis() + ".pdf";
                    fileName = (GeneratePDF.class.getClassLoader().getResource(".").getPath() + "etatsImprimes/PVSyntheses/PVSyntheses" + "-" + classe + "-" + annee + "-" + System.currentTimeMillis() + ".pdf");

                System.out.println("fileName = " + fileName);
                //fileName="C:\\Users\\moupojoe\\Documents\\ISJ\\Gestions de Notes - Web\\webservicerest\\target\\classes\\etatsImprimes\\PVSyntheses\\PVSyntheses-INGI 4-2021-1662116633392.pdf";
                //System.out.println("fileName = "+fileName);
                File f = new File(fileName);
                f.createNewFile();
                JasperExportManager.exportReportToPdfFile(jasperPrint, f.getAbsolutePath());

                fichierCourant = f;

                long t2 = System.currentTimeMillis();
                System.out.println("Fin d'impression du PV de synthese en..." + (t2 - t1) / 60000 + " min");
                //openFile(f);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //}

            File result = superposerPDF(new ClassPathResource("images/image_fond.pdf").getFile(), fichierCourant);

            if (imprimerAttestations) {
                //Generation des attestations de réussite
                String requete = "select ec.matricule as matricule, ec.credits as creditc, ec.mgp as mgpc from  etudiant_cursus ec \n" +
                        "    order by ec.credits desc, ec.mgp desc;";
                // create the java statement
                Statement st = connection.createStatement();
                // execute the query, and get a java resultset
                ResultSet rs = st.executeQuery(requete);
                // iterate through the java resultset
                List<Note> notesAdmis = new ArrayList<>();
                while (rs.next()) {
                    String matricule = rs.getString("matricule");
                    float creditc = rs.getFloat("creditc");
                    float mgpc = rs.getFloat("mgpc");
                    if (mgpc >= mgpMinAdmission && creditc >= creditsMinAdmission)
                        notesAdmis.add(new Note(
                                matricule,
                                "",
                                mgpc,
                                0,
                                null,
                                null,
                                null));
                }
                if (notesAdmis.size() > 0) {
                    //etudiant = iEtudiant.getStudentByMatricule(matricules.get(1));
                    byte[] attestations = genererAttestation(notesAdmis, classe, annee, dateJury,
                            speci, filiere, niveau,etudiants, notesAdmis.get(0).getLibelle());
                    Etudiant etudiant = iEtudiant.getStudentByMatricule(matricules.get(1));
                    PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
                    pdfMergerUtility.addSource(result);
                    File fichierAttestations = new File(GeneratePDF.class.getClassLoader().getResource(".").getPath() + "etatsImprimes" + FileSystems.getDefault().getSeparator() + "attestations"+ FileSystems.getDefault().getSeparator() + "attestations" + (new Date()).getTime() + ".pdf");
                    FileUtils.writeByteArrayToFile(fichierAttestations, attestations);
                    pdfMergerUtility.addSource(fichierAttestations);
                    pdfMergerUtility.setDestinationFileName(result.getAbsolutePath());
                    pdfMergerUtility.mergeDocuments();
                    //f.delete();
                }
            }

            return Files.readAllBytes(result.toPath());
            //openFile(result);
        }
        return new byte[0];
    }

    public byte[] genererCertificat(List<String> matricules, String classe, int annee, String typeReleve,
                                    String speci, String filiere, int niveau, String decision) throws Exception {

        //[1920i010]-ING 2-null-Tronc Commun-Ingenieur-2-null
        System.out.println(matricules.toString() + "-" + classe + "-" + typeReleve + "-" + speci + "-" + filiere + "-" + niveau + "-" + decision);

        String listeMatricules = "";
        if (matricules != null && matricules.size() > 0) {
            for (int i = 0; i < matricules.size(); i++) {
                listeMatricules += (matricules.get(i) + ";");
            }
        }
       // System.out.println("Matricules:" + listeMatricules);
        //System.out.println("path" + th);
        //JasperDesign jasperDesign = JRXmlLoader.load(GeneratePDF.class.getClassLoader().getResourceAsStream("src/main/resources/etats/ReleveFinal.jrxml"));
        //JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        //JasperReport jasperReport = JasperCompileManager.compileReport(this.getClass().getResourceAsStream("etats/CertificatScolarite.jrxml"));
        JasperReport jasperReport = JasperCompileManager.compileReport(new ClassPathResource("etats" + FileSystems.getDefault().getSeparator() + "CertificatScolarite.jrxml").getInputStream());
        System.out.println("coco");

        if (matricules != null && matricules.size() > 0) {

            // - Chargement et compilation du rapport
            File fichierCourant = null;
            Connection connection = Bd.getConnection();
            for (int i = 0; i < matricules.size(); i++) {
                String matricule = matricules.get(i);
                try {
                    long t = System.currentTimeMillis();
                    System.out.println("Debut d'impresion du certificat " + (i + 1) + " " + matricule);

                    //Etudiant etudiant = new Isj().retrouverEtudiantMatricule(matricule);
                    Etudiant etudiant = new Etudiant();
                    etudiant = iEtudiant.getStudentByMatricule(matricule);
                    long t1 = System.currentTimeMillis();

                    // - Paramètres à envoyer au rapport
                    Map parameters = new HashMap();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMMMM yyyy");
                    parameters.put("filiere", filiere);
                    parameters.put("specialites", filiere + " - " + speci);
                    parameters.put("nom_etudiant", etudiant.getNom() + " " + etudiant.getPrenom());
                    parameters.put("date_naissance", dateFormat.format(etudiant.getDateNaissance()));
                    parameters.put("niveau", niveau);
                    parameters.put("classe", classe);
                    parameters.put("annee_academique", annee);
                    parameters.put("sexe", etudiant.getSexe().toString());
                    parameters.put("matricule", matricule);
                    parameters.put("image_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "logo_isj.jpeg").getFile().getAbsolutePath());
                    parameters.put("entete_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "entete_isj.jpg").getFile().getAbsolutePath());
                    parameters.put("image_uy1", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "logo_uy1.jpg").getFile().getAbsolutePath());
                    parameters.put("image_ensp", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "logo_ensp.jpg").getFile().getAbsolutePath());
                    //parameters.put("image_isj", this.getClass().getResourceAsStream("images/logo_isj.jpeg"));
                    //parameters.put("entete_isj", new ClassPathResource("images/entete_isj.jpg").getInputStream());
                    //parameters.put("image_uy1", new ClassPathResource("images/logo_uy1.jpg").getInputStream());
                    //parameters.put("image_ensp", new ClassPathResource("images/logo_ensp.jpg").getInputStream());
                    parameters.put("speci", speci);
                    parameters.put("numero_releve", i);
                    System.out.println(etudiant.toString());
                    Long numero = (etudiant.getCode() + annee);
                    System.out.println("Numero = " + numero);
                    parameters.put("numero", numero);
                    parameters.put("type_releve", typeReleve);
                    parameters.put("semestr", typeReleve);
                    parameters.put("listeMatricules", listeMatricules);
                    parameters.put("decision", decision);
                    parameters.put("branche", getBranche(speci));

                    // - Execution du rapport
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

                    // - Création du rapport au format PDF
                    String fileName;
                    if (matricules.size() == 1)
                        //fileName=new File("/etatsImprimes"+FileSystems.getDefault().getSeparator()+"releves"+FileSystems.getDefault().getSeparator()+"Releve-" + matricule + "-" + classe + "-" + System.currentTimeMillis() + ".pdf").getAbsolutePath();
                        fileName = (GeneratePDF.class.getClassLoader().getResource(".").getPath() + "etatsImprimes" + FileSystems.getDefault().getSeparator() + "certificats" + FileSystems.getDefault().getSeparator() + "Certificat-" + matricule + "-" + classe + "-" + System.currentTimeMillis() + ".pdf");
                        //fileName = "K:/ISI/IS4/projet-tutore/web-service/webservicerest/src/main/resources/etatsImprimes/certificats/Releve-" + matricule + "-" + classe + "-" + System.currentTimeMillis() + ".pdf";
                    else
                        fileName = (GeneratePDF.class.getClassLoader().getResource(".").getPath() + "etatsImprimes" + FileSystems.getDefault().getSeparator() + "certificats" + FileSystems.getDefault().getSeparator() + "Certificats" + "-" + classe + "-" + annee + "-" + System.currentTimeMillis() + ".pdf");
                        //fileName = "K:/ISI/IS4/projet-tutore/web-service/webservicerest/src/main/resources/etatsImprimes/certificats/Releve-"+ classe + "-" + annee + "-" + System.currentTimeMillis() + ".pdf";
                    System.out.println("fileName = " + fileName);
                    File f = new File(fileName);
                    f.createNewFile();
                    JasperExportManager.exportReportToPdfFile(jasperPrint, f.getAbsolutePath());
                    if (i == 0) {
                        fichierCourant = f;
                    } else {
                        PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
                        pdfMergerUtility.addSource(fichierCourant);
                        pdfMergerUtility.addSource(f);

                        pdfMergerUtility.setDestinationFileName(fichierCourant.getAbsolutePath());
                        pdfMergerUtility.mergeDocuments();
                        f.delete();
                    }
                    long t2 = System.currentTimeMillis();
                    System.out.println("Fin d'impression du certificat en..." + (t2 - t1) / 60000 + " min");
                    //openFile(f);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //File file = new File(new ClassPathResource("images/image_fond.pdf").getInputStream().toString());
            System.out.println("path" + GeneratePDF.class.getClassLoader().getResource(".").getPath());
            File result = superposerPDF(new ClassPathResource("images/image_fond.pdf").getFile(), fichierCourant);
            //File result = superposerPDF(new ClassPathResource(GeneratePDF.class.getClassLoader().getResource(".").getPath() + "images/logo_isj_background.jpg").getFile(), fichierCourant);
            return Files.readAllBytes(result.toPath());
            //openFile(result);
        }
        return new byte[0];
    }


    public static String getMatriculeFromNote(Note note) {

        if (note != null && note.getEstInscrit() != null && note.getEstInscrit().getCandidatInscrit() != null) {
            Candidat candidat = note.getEstInscrit().getCandidatInscrit();
            System.out.println(candidat.toString());
            return candidat.toString().substring(candidat.toString().lastIndexOf("-") + 1);
        }
        else if(note!=null && note.getLibelle()!=null && !note.getLibelle().equalsIgnoreCase("")) {
            return note.getLibelle();
        }
        return "";
    }

    static double n;

    @Transactional
    public byte[] genererAttestation(List<Note> notes, String classe, int annee, String dateJury,
                                     String speci, String filiere, int niveau, Map<String, Etudiant> etudiants, String... mat) throws Exception {

            //Map parameters = new HashMap();
//        parameters.put("image_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "logo_isj.jpeg").getFile().getAbsolutePath());
//        parameters.put("entete_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "entete_isj.jpg").getFile().getAbsolutePath());

        //System.out.println(notes);
//        System.out.println(classe);
//        System.out.println(annee);
//        System.out.println(dateJury);
//        System.out.println(speci);
//        System.out.println(filiere);
//        System.out.println(niveau);
        //System.out.println(mat[0]);
        Connection connection = Bd.getConnection();
        String listeMatricules = "";
        if (notes != null && notes.size() > 0) {
            for (int i = 0; i < notes.size(); i++) {
//                parameters.put("image_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "logo_isj.jpeg").getFile().getAbsolutePath());
//                parameters.put("entete_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "entete_isj.jpg").getFile().getAbsolutePath());
                listeMatricules += (getMatriculeFromNote(notes.get(i)) + ";");
//                parameters.put("image_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "logo_isj.jpeg").getFile().getAbsolutePath());
//                parameters.put("entete_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "entete_isj.jpg").getFile().getAbsolutePath());
            }
        } else listeMatricules = (mat[0] + ";");
        System.out.println("Matricules:" + listeMatricules);
//        parameters.put("image_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "logo_isj.jpeg").getFile().getAbsolutePath());
//        parameters.put("entete_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "entete_isj.jpg").getFile().getAbsolutePath());

        //Etudiant etudiant = iEtudiant.getStudentByMatricule(notes.get(0).getLibelle());

        JasperReport jasperReport = JasperCompileManager.compileReport(new ClassPathResource("etats" + FileSystems.getDefault().getSeparator() + "attestationReussite.jrxml").getInputStream());
        //JasperReport jasperReport = (JasperReport) JRLoader.loadObject(new ClassPathResource("etats" + FileSystems.getDefault().getSeparator() + "attestationReussite.jasper").getFile());
        //CompiledClasses h;
        net.sf.jasperreports.engine.design.CompiledClasses m;
        //etudiant = iEtudiant.getStudentByMatricule(notes.get(0).getLibelle());
//        parameters.put("image_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "logo_isj.jpeg").getFile().getAbsolutePath());
//        parameters.put("entete_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "entete_isj.jpg").getFile().getAbsolutePath());

        if ((notes != null && notes.size() > 0) || !listeMatricules.equalsIgnoreCase("")) {
            //etudiant = iEtudiant.getStudentByMatricule(notes.get(0).getLibelle());
            if(connection==null)
                connection = Bd.getConnection();
            //etudiant = iEtudiant.getStudentByMatricule(notes.get(0).getLibelle());
//            parameters.put("image_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "logo_isj.jpeg").getFile().getAbsolutePath());
//            parameters.put("entete_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "entete_isj.jpg").getFile().getAbsolutePath());

            // - Chargement et compilation du rapport
            File fichierCourant = null;
            String[] matricules = listeMatricules.split(";");
            for (int i = 0; i < matricules.length; i++) {
                String matricule = matricules[i];
                try {
                    long t = System.currentTimeMillis();
                    System.out.println("Debut d'impression de l'attestation " + (i + 1) + " " + matricule + " - Note:" + (notes != null ? notes.get(i).getValeurNote() : n));
//                    parameters.put("image_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "logo_isj.jpeg").getFile().getAbsolutePath());
//                    parameters.put("entete_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "entete_isj.jpg").getFile().getAbsolutePath());

                    //Etudiant etudiant = new Isj().retrouverEtudiantMatricule(matricule);
                    Etudiant etudiant = iEtudiant.getStudentByMatricule(matricule);
                    //Etudiant etudiant=etudiants.get(matricule);
                    System.out.println(etudiant);
                    long t1 = System.currentTimeMillis();

                    // - Paramètres à envoyer au rapport
                    Map parameters = new HashMap();
                    parameters.put("image_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "logo_isj.jpeg").getFile().getAbsolutePath());
                    parameters.put("entete_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "entete_isj.jpg").getFile().getAbsolutePath());

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMMMM yyyy");
                    parameters.put("filiere", filiere);
                    parameters.put("specialites", filiere + " - " + speci);
                    parameters.put("nom_etudiant", etudiant.getNom() + " " + etudiant.getPrenom());
                    parameters.put("date_naissance", dateFormat.format(etudiant.getDateNaissance()));
                    parameters.put("niveau", niveau);
                    parameters.put("classe", classe);
                    parameters.put("annee_academique", annee);
                    parameters.put("sexe", etudiant.getSexe());
                    parameters.put("matricule", matricule);
                    parameters.put("image_uy1", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "logo_uy1.jpg").getFile().getAbsolutePath());
                    parameters.put("image_ensp", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "logo_ensp.jpg").getFile().getAbsolutePath());
                    parameters.put("speci", speci);
                    parameters.put("numero_releve", i);
                    System.out.println(etudiant.toString());
                    Long numero = (etudiant.getCode() + annee);
                    System.out.println("Numero = " + numero);
                    parameters.put("numero", numero);
                    parameters.put("date_jury", dateJury);
                    //parameters.put("listeMatricules", "");
                    parameters.put("type_releve", "Annuel");
                    parameters.put("semestr", "Annuel");
                    parameters.put("note_cycle_licence", notes != null ? notes.get(i).getValeurNote() : n);
                    parameters.put("branche", getBranche(speci));
                    System.out.println(matricules);
                    // - Execution du rapport
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

                    // - Création du rapport au format PDF
                    String fileName;
                    if (matricules.length == 1)
                       //K:/ISI/IS4/projet-tutore/web-service/webservicerest/src/main/resources/etatsImprimes/
                        fileName = (GeneratePDF.class.getClassLoader().getResource(".").getPath() + "etatsImprimes" + FileSystems.getDefault().getSeparator() + "attestations" + FileSystems.getDefault().getSeparator() + "attestation" + matricule + "-" + classe + "-" + System.currentTimeMillis() + ".pdf");
                        //fileName = "K:/ISI/IS4/projet-tutore/web-service/webservicerest/src/main/resources/etatsImprimes/attestations/attestation" + matricule + "-" + classe + "-" + System.currentTimeMillis() + ".pdf";
                    else

                        //fileName = "K:/ISI/IS4/projet-tutore/web-service/webservicerest/src/main/resources/etatsImprimes/attestations/attestation" + "-" + classe + "-" + annee + "-" + System.currentTimeMillis() + ".pdf";
                        fileName = (GeneratePDF.class.getClassLoader().getResource(".").getPath() + "etatsImprimes" + FileSystems.getDefault().getSeparator() + "attestations" + FileSystems.getDefault().getSeparator() + "attestations" + "-" + classe + "-" + annee + "-" + System.currentTimeMillis() + ".pdf");
                    File f = new File(fileName);
                    f.createNewFile();
                    JasperExportManager.exportReportToPdfFile(jasperPrint, f.getAbsolutePath());
                    if (i == 0) {
                        fichierCourant = f;
                    } else {
                        PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
                        pdfMergerUtility.addSource(fichierCourant);
                        pdfMergerUtility.addSource(f);

                        pdfMergerUtility.setDestinationFileName(String.valueOf(fichierCourant));
                        pdfMergerUtility.mergeDocuments();
                        f.delete();
                    }
                    long t2 = System.currentTimeMillis();
                    System.out.println("Fin d'impression de l'attestation en..." + (t2 - t1) / 60000 + " min");
                    //openFile(f);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            File result = superposerPDF(new ClassPathResource("images/image_fond.pdf").getFile(), fichierCourant);

            return Files.readAllBytes(result.toPath());
        }
        return new byte[0];
    }

    @Transactional
    public byte[] genererDiplome(List<Note> notes, String classe, int annee, String dateJury,
                                 String speci, String filiere, int niveau, String... mat) throws Exception {

        System.out.println(notes);
        System.out.println(classe);
        System.out.println(annee);
        System.out.println(dateJury);
        System.out.println(speci);
        System.out.println(filiere);
        System.out.println(niveau);
        //System.out.println(mat[0]);

        String listeMatricules = "";
        if (notes != null && notes.size() > 0) {
            for (int i = 0; i < notes.size(); i++) {
                listeMatricules += (getMatriculeFromNote(notes.get(i)) + ";");
            }
        } else listeMatricules = (mat[0] + ";");
        System.out.println("Matricules:" + listeMatricules);


        JasperReport jasperReport = JasperCompileManager.compileReport(new ClassPathResource("etats" + FileSystems.getDefault().getSeparator() + "diplome.jrxml").getInputStream());
        if ((notes != null && notes.size() > 0) || !listeMatricules.equalsIgnoreCase("")) {

            // Connection connection = AbstractFacade.getConnection();
            Connection connection = Bd.getConnection();
            // - Chargement et compilation du rapport
            File fichierCourant = null;
            String[] matricules = listeMatricules.split(";");
            for (int i = 0; i < matricules.length; i++) {
                String matricule = matricules[i];
                try {
                    long t = System.currentTimeMillis();
                    System.out.println("Debut d'impresion du diplome " + (i + 1) + " " + matricule + " - Note:" + (notes != null ? notes.get(i).getValeurNote() : n));

                    //Etudiant etudiant = new Isj().retrouverEtudiantMatricule(matricule);
                    Etudiant etudiant = etudiantImpl.getStudentByMatricule(matricule);
                    long t1 = System.currentTimeMillis();

                    // - Paramètres à envoyer au rapport
                    Map parameters = new HashMap();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMMMM yyyy");
                    parameters.put("filiere", filiere);
                    parameters.put("specialites", filiere + " - " + speci);
                    parameters.put("nom_etudiant", etudiant.getNom() + " " + etudiant.getPrenom());
                    parameters.put("date_naissance", dateFormat.format(etudiant.getDateNaissance()));
                    parameters.put("niveau", niveau);
                    parameters.put("classe", classe);
                    parameters.put("annee_academique", annee);
                    parameters.put("sexe", etudiant.getSexe());
                    parameters.put("matricule", matricule);
                    parameters.put("image_isj", new File("images" + FileSystems.getDefault().getSeparator() + "logo_isj.jpeg").getAbsolutePath());
                    parameters.put("image_uy1", new File("images" + FileSystems.getDefault().getSeparator() + "logo_uy1.jpg").getAbsolutePath());
                    parameters.put("image_ensp", new File("images" + FileSystems.getDefault().getSeparator() + "logo_ensp.jpg").getAbsolutePath());
                    parameters.put("speci", speci);
                    parameters.put("numero_releve", i);
                    System.out.println(etudiant.toString());
                    Long numero = (etudiant.getCode() + annee);
                    System.out.println("Numero = " + numero);
                    parameters.put("numero", numero);
                    parameters.put("date_jury", dateJury);
                    parameters.put("listeMatricules", "");
                    parameters.put("type_releve", "Annuel");
                    parameters.put("semestr", "Annuel");
                    parameters.put("note_cycle_licence", notes != null ? notes.get(i).getValeurNote() : n);
                    parameters.put("branche", getBranche(speci));
                    // - Execution du rapport
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

                    // - Création du rapport au format PDF
                    String fileName;
                    if (matricules.length == 1)

                        //fileName = "K:/ISI/IS4/projet-tutore/web-service/webservicerest/src/main/resources/etatsImprimes/diplomes" + FileSystems.getDefault().getSeparator() + "Diplome-" + matricule + "-" + classe + "-" + System.currentTimeMillis() + ".pdf";
                        fileName = (GeneratePDF.class.getClassLoader().getResource(".").getPath() + "etatsImprimes" + FileSystems.getDefault().getSeparator() + "diplomes" + FileSystems.getDefault().getSeparator() + "Diplome-" + matricule + "-" + classe + "-" + System.currentTimeMillis() + ".pdf");
                    else
                        fileName = (GeneratePDF.class.getClassLoader().getResource(".").getPath() + "etatsImprimes" + FileSystems.getDefault().getSeparator() + "diplomes" + FileSystems.getDefault().getSeparator() + "Diplomes" + "-" + classe + "-" + annee + "-" + System.currentTimeMillis() + ".pdf");
                        //fileName = "K:/ISI/IS4/projet-tutore/web-service/webservicerest/src/main/resources/etatsImprimes/diplomes" + FileSystems.getDefault().getSeparator() + "Diplomes" + "-" + classe + "-" + annee + "-" + System.currentTimeMillis() + ".pdf";
                    File f = new File(fileName);
                    f.createNewFile();
                    JasperExportManager.exportReportToPdfFile(jasperPrint, f.getAbsolutePath());
                    if (i == 0) {
                        fichierCourant = f;
                    } else {
                        PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
                        pdfMergerUtility.addSource(fichierCourant);
                        pdfMergerUtility.addSource(f);
                        //String destinationFinale = pv.getAbsolutePath().substring(0, pv.getAbsolutePath().indexOf(".") - 1) + "-synthese.pdf";
                        //File destinationFinaleFile = new File(destinationFinale);
                        //destinationFinaleFile.createNewFile();
                        pdfMergerUtility.setDestinationFileName(fichierCourant.getAbsolutePath());
                        pdfMergerUtility.mergeDocuments();
                        f.delete();
                    }
                    long t2 = System.currentTimeMillis();
                    System.out.println("Fin d'impression de du diplome en..." + (t2 - t1) / 60000 + " min");
                    //openFile(f);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            File result = superposerPDF(new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "logo_paysage.pdf").getFile(), fichierCourant);
            //openFile(result);;
            return Files.readAllBytes(result.toPath());
        }
        return new byte[0];
    }


    public static String getBranche(String specilatite) {

        if (specilatite.equalsIgnoreCase("Ingénierie Logicielle") ||
                specilatite.equalsIgnoreCase("Management des Systèmes d'Information") ||
                specilatite.equalsIgnoreCase("Data Science") ||
                specilatite.equalsIgnoreCase("Informatique et systèmes d'information") ||
                specilatite.equalsIgnoreCase("Sécurité des Systèmes d'Information"))
            return "Informatique et Systèmes d'Information";
        else if (specilatite.equalsIgnoreCase("Sécurité des Systèmes et des Communications") ||
                specilatite.equalsIgnoreCase("Technologies Mobiles, Systèmes et Services Réseaux"))
            return "Systèmes, Réseaux et Télécommunications";

        else return specilatite;
    }

    public static File superposerPDFCarteEtudiant(File base, File fichier) throws IOException {

        PDDocument overlayDoc = PDDocument.load(base);
        Overlay overlayObj = new Overlay();

        PDDocument originalDoc = PDDocument.load(fichier);
        overlayObj.setOverlayPosition(Overlay.Position.BACKGROUND);
        overlayObj.setInputPDF(originalDoc);
        overlayObj.setAllPagesOverlayPDF(overlayDoc);      //alternatives?
        Map<Integer, String> ovmap = new HashMap<Integer, String>();
        overlayObj.overlay(ovmap);
        //File result=new File("result.pdf");
        originalDoc.save(fichier);

        overlayDoc.close();
        originalDoc.close();

        return fichier;
    }

    public static File superposerPDF(File base, File fichier) throws IOException {

        PDDocument overlayDoc = PDDocument.load(base);
        Overlay overlayObj = new Overlay();

        PDDocument originalDoc = PDDocument.load(fichier);
        overlayObj.setOverlayPosition(Overlay.Position.BACKGROUND);
        overlayObj.setInputPDF(originalDoc);
        overlayObj.setAllPagesOverlayPDF(overlayDoc);      //alternatives?
        Map<Integer, String> ovmap = new HashMap<Integer, String>();
        overlayObj.overlay(ovmap);
        //File result=new File("result.pdf");
        originalDoc.save(fichier);

        overlayDoc.close();
        originalDoc.close();

        return fichier;
    }

    public byte[] genererFicheAbsence(String libelleClasse, Integer niveau, String filiere, Integer
            annee_academique, Date datedebut, Date datefin, String semestre) throws Exception {

        Connection connection = null;

        try {
            long t1 = System.currentTimeMillis();
            String fileName;
            connection = Bd.getConnection();

            // - Chargement et compilation du rapport

            JasperReport jasperReport = JasperCompileManager.compileReport(new ClassPathResource("etats" + FileSystems.getDefault().getSeparator() + "FicheAbsences.jrxml").getInputStream());
            //JasperReport jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream("src/main/resources/etats/FicheAbsences.jasper"));
            // - Paramètres à envoyer au rapport
            Map parameters = new HashMap();
            parameters.put("filiere", filiere);
            parameters.put("niveau", niveau);
            parameters.put("annee_academique", annee_academique);
            parameters.put("semestre", semestre);
            parameters.put("date_debut", new java.sql.Date(datedebut.getTime()));
            parameters.put("date_fin", new java.sql.Date(datefin.getTime()));
            parameters.put("classe", libelleClasse);
            parameters.put("logo_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "logo_isj.jpeg").getFile().getAbsolutePath());
            System.out.println(parameters);
            // - Execution du rapport
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
            // - Création du rapport au format PDF
            //String destination = new ClassPathResource("etatsImprimes"+FileSystems.getDefault().getSeparator()+"absences"+FileSystems.getDefault().getSeparator()+"" + "Fiche " + filiere + "-" + niveau + "-" + semestre + "-" + System.currentTimeMillis() + ".pdf").getFile().getAbsolutePath();
            fileName = (GeneratePDF.class.getClassLoader().getResource(".").getPath() + "etatsImprimes/absences/Fiche d'absences-" + "-" + filiere + "-" + niveau + "-" + semestre + "-" + System.currentTimeMillis() + ".pdf");
            //String destination = ("K:/ISI/IS4/projet-tutore/web-service/webservicerest/src/main/resources/etatsImprimes/absences/Fiche " + filiere + "-" + niveau + "-" + semestre + "-" + System.currentTimeMillis() + ".pdf");
            System.out.println(fileName);
            //File f = new File(destination);
            File f = new File(fileName);
            f.createNewFile();
            //System.out.println("Generation de l'etat..." + destination);
            System.out.println("Generation de l'etat..." + fileName);
            JasperExportManager.exportReportToPdfFile(jasperPrint, f.getAbsolutePath());
            System.out.println("Etat Genere...");

            long t2 = System.currentTimeMillis();

            System.out.println("Fin d'impression de la fiche en..." + (t2 - t1) / 60000 + " min");
            //openFile(f);
            //connection.close();
            return Files.readAllBytes(f.toPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public byte[] genererPv(Integer annee_academique, String semestre, String classe, String filiere, String
            specialites, boolean formatPDF, int niv) throws Exception {
        Integer niveau = niv;

        try {
            Connection connection = null;

            connection = Bd.getConnection();
            File pv = imprimerPV(niveau, filiere, annee_academique, semestre, connection, classe, specialites, formatPDF);
            File recapitulatif = imprimerRecapitulatif(niveau, filiere, annee_academique, semestre, connection, classe, specialites, formatPDF);

            if (formatPDF) {
                PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
                pdfMergerUtility.addSource(pv);
                pdfMergerUtility.addSource(recapitulatif);
                String destinationFinale = pv.getAbsolutePath().substring(0, pv.getAbsolutePath().indexOf(".") - 1) + annee_academique + "-synthese.pdf";
                File destinationFinaleFile = new File(destinationFinale);
                destinationFinaleFile.createNewFile();
                pdfMergerUtility.setDestinationFileName(destinationFinale);
                pdfMergerUtility.mergeDocuments();
                ajouterNumerosPages(destinationFinaleFile);
                return Files.readAllBytes(destinationFinaleFile.toPath());
                //openFile(destinationFinaleFile);
            } else {
                String destinationFinale = pv.getAbsolutePath().substring(0, pv.getAbsolutePath().indexOf(".") - 1) + annee_academique + "-synthese.xlsx";
                File destinationFinaleFile = new File(destinationFinale);
                destinationFinaleFile.createNewFile();
                String[] files = new String[]{pv.getAbsolutePath(), recapitulatif.getAbsolutePath()};
                XSSFWorkbook workbook = new XSSFWorkbook();
                //XSSFSheet sheet = createSheetWithHeader(workbook,"PVSynthese");
                XSSFSheet sheet = workbook.createSheet();

                DataFormat format = workbook.createDataFormat();
                CellStyle styleNumeric = workbook.createCellStyle();
                styleNumeric.setDataFormat(format.getFormat("##.##"));

                try {
                    for (int f = 0; f < files.length; f++) {
                        String file = files[f];
                        FileInputStream inputStream = new FileInputStream(file);
                        XSSFWorkbook tempWorkbook = new XSSFWorkbook(inputStream);

                        int numOfSheets = tempWorkbook.getNumberOfSheets();

                        for (int i = 0; i < numOfSheets; i++) {
                            XSSFSheet tempSheet = tempWorkbook.getSheetAt(i);

                            int indexLastDataInserted = sheet.getLastRowNum();
                            int firstDataRow = getFirstDataRow(tempSheet);

                            Iterator<Row> itRow = tempSheet.rowIterator();

                            while (itRow.hasNext()) {
                                Row tempRow = itRow.next();
                                indexLastDataInserted = indexLastDataInserted + 1;
                                if (tempRow.getRowNum() >= firstDataRow) {
                                    XSSFRow row = sheet.createRow(indexLastDataInserted);

                                    Iterator<Cell> itCell = tempRow.cellIterator();

                                    while (itCell.hasNext()) {
                                        Cell tempCell = itCell.next();
                                        XSSFCell cell = row.createCell(tempCell.getColumnIndex());
                                        //At this point you will have to set the value of the cell depending on the type of data it is
                                        switch (tempCell.getCellType()) {
                                            case 0:
                                                cell.setCellType(0);
                                                cell.setCellValue(tempCell.getNumericCellValue());
                                                break;
                                            case 1:
                                                cell.setCellValue(tempCell.getStringCellValue());
                                                break;
                                            /**
                                             * Add your other types, here is your problem!!!!!
                                             */
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (IOException ex1) {
                    System.out.println("Error reading file");
                    ex1.printStackTrace();
                }

                try {
                    FileOutputStream outputStream = new FileOutputStream(destinationFinaleFile);
                    workbook.write(outputStream);
                    outputStream.close();
                    return Files.readAllBytes(destinationFinaleFile.toPath());
                    //workbook.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //openFile(destinationFinaleFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public byte[] genererListe(Integer annee_academique, String semestre, String classe, String filiere, String
            specialites, int niv) throws Exception {
        Integer niveau = niv;

        try {
            Connection connection = null;

            connection = Bd.getConnection();
            File listeclasse = imprimerListe(niveau, filiere, annee_academique, semestre, connection, classe, specialites);
            //File recapitulatif = imprimerRecapitulatif(niveau, filiere, annee_academique, semestre, connection, classe, specialites, formatPDF);

            PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
            pdfMergerUtility.addSource(listeclasse);

            String destinationFinale = listeclasse.getAbsolutePath().substring(0, listeclasse.getAbsolutePath().indexOf(".") - 1) + annee_academique + "-synthese.pdf";
            File destinationFinaleFile = new File(destinationFinale);
            destinationFinaleFile.createNewFile();
            pdfMergerUtility.setDestinationFileName(destinationFinale);
            pdfMergerUtility.mergeDocuments();
            ajouterNumerosPages(destinationFinaleFile);
            return Files.readAllBytes(destinationFinaleFile.toPath());
            //openFile(destinationFinaleFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    /**
     * If the tab has a filter, it returns the row index of the filter + 1, otherwise it returns 0
     *
     * @param tempSheet
     * @return index of first data row
     */
    public static Integer getFirstDataRow(XSSFSheet tempSheet) {
        Integer result = 0;
        Boolean isAutoFilter = tempSheet.getCTWorksheet().isSetAutoFilter();

        if (isAutoFilter) {
            String autoFilterRef = tempSheet.getCTWorksheet().getAutoFilter().getRef();

            result = new CellReference(autoFilterRef.substring(0, autoFilterRef.indexOf(":"))).getRow() + 1;
        }
        return result;
    }

    public static XSSFSheet createSheetWithHeader(XSSFWorkbook workbook, String sheetName) {
        XSSFSheet sheet = workbook.createSheet(sheetName);

        /*//Implement the header
    [...]*/

        return sheet;
    }

    public static void ajouterNumerosPages(File destinationFinaleFile) throws IOException {

        PDDocument document = PDDocument.load(destinationFinaleFile);
// get all number of pages.
        int numberOfPages = document.getNumberOfPages();

        for (int i = 0; i < numberOfPages; i++) {
            PDPage fpage = document.getPage(i);

            // calculate to center of the page
            int rotation = fpage.getRotation();
            boolean rotate = rotation == 90 || rotation == 270;
            float pageWidth = rotate ? fpage.getMediaBox().getHeight() : fpage.getMediaBox().getWidth();
            float pageHeight = rotate ? fpage.getMediaBox().getWidth() : fpage.getMediaBox().getHeight();
            float centerX = rotate ? pageHeight / 2f : (pageWidth) / 2f;
            float centerY = rotate ? (pageWidth) / 2f : pageHeight / 2f;

// content stream to write content in pdf page.
            PDPageContentStream contentStream = new PDPageContentStream(document, fpage, PDPageContentStream.AppendMode.APPEND, true);
            contentStream.beginText();
            if (rotate) {
                // rotate the text according to the page rotation
                contentStream.setTextMatrix(Matrix.getRotateInstance(Math.PI / 2, centerX, centerY));
            } else {
                contentStream.setTextMatrix(Matrix.getTranslateInstance(centerX, centerY));
            }
            PDFont font = PDType1Font.HELVETICA_BOLD;
            float fontSize = 10.0f;
            contentStream.setFont(font, fontSize);
            // set text color to red
            contentStream.setNonStrokingColor(0, 0, 0);
            // contentStream.showText("Page "+(i+1)+"/"+numberOfPages);
            contentStream.endText();


            contentStream.close();
        }
        document.save(destinationFinaleFile);
    }

    public static void main(String[] args) {
        System.out.println(new ClassPathResource("etats" + FileSystems.getDefault().getSeparator() + "PvFinal.jrxml"));
    }

    private static File imprimerPV(Integer niveau, String filiere, Integer annee_academique, String
            semestre, Connection connection, String classe, String specialites, boolean formatPDF) throws JRException, IOException {

        long t1 = System.currentTimeMillis();
        System.out.println("Début d'impression du PV..." + t1);

        //JasperReport jasperReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/etats/PvFinal.jrxml"));
        JasperReport jasperReport = JasperCompileManager.compileReport(new ClassPathResource("etats" + FileSystems.getDefault().getSeparator() + "PvFinal.jrxml").getInputStream());

        // - Paramètres à envoyer au rapport
        Map parameters = new HashMap();
        parameters.put("filiere", filiere);
        parameters.put("niveau", niveau);
        parameters.put("annee_academique", annee_academique);
        parameters.put("semestre", semestre);
        parameters.put("specialites", specialites);
        //parameters.put("image_isj", new ClassPathResource("images"+FileSystems.getDefault().getSeparator()+"logo_isj.jpeg").getFile().getAbsolutePath() );
        parameters.put("image_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "logo_isj.jpeg").getFile().getAbsolutePath());
        parameters.put("entete_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "entete_isj.jpg").getFile().getAbsolutePath());

        //Pour le PV synthese
        parameters.put("annee", annee_academique);
        parameters.put("classe", classe);

        // - Execution du rapport
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
        File f; String filename, filename0;
        if(formatPDF) {
            filename0 = new File ("/etatsImprimes" + FileSystems.getDefault().getSeparator() + "pv" + FileSystems.getDefault().getSeparator() + "" + "PV-" + filiere + "-" + specialites + "-" + niveau + "-" + semestre + "-" + System.currentTimeMillis() + ".pdf").getAbsolutePath();
            filename = GeneratePDF.class.getClassLoader().getResource(".").getPath() + "etatsImprimes" + FileSystems.getDefault().getSeparator() + "pv" + FileSystems.getDefault().getSeparator() + "" + "PV-" + filiere + "-" + specialites + "-" + niveau + "-" + semestre + "-" + System.currentTimeMillis() + ".pdf";
        }else {
            filename0 =new File ("/etatsImprimes" + FileSystems.getDefault().getSeparator() + "pv" + FileSystems.getDefault().getSeparator() + "" + "PV-" + filiere + "-" + specialites + "-" + niveau + "-" + semestre + "-" + System.currentTimeMillis() + ".xlsx").getAbsolutePath();
            filename = GeneratePDF.class.getClassLoader().getResource(".").getPath() + "etatsImprimes" + FileSystems.getDefault().getSeparator() + "pv" + FileSystems.getDefault().getSeparator() + "" + "PV-" + filiere + "-" + specialites + "-" + niveau + "-" + semestre + "-" + System.currentTimeMillis() + ".xlsx";
        }
        filename = filename0;//Lorsque l'exécution se fait directement sur Intellij
        System.out.println("filename pv: "+filename);

        if (formatPDF) {
            // - Création du rapport au format PDF
            f = new File (filename);
            //String filename = "K:/ISI/IS4/projet-tutore/web-service/webservicerest/src/main/resources/etatsImprimes/pv/"+ "" + "PV-" + filiere + "-" + specialites + "-" + niveau + "-" + semestre + "-" + System.currentTimeMillis() + ".pdf";
            //f = new File(GeneratePDF.class.getClassLoader().getResource(".").getPath() + "etatsImprimes" + FileSystems.getDefault().getSeparator() + "pv" + FileSystems.getDefault().getSeparator() + "" + "PV-" + filiere + "-" + specialites + "-" + niveau + "-" + semestre + "-" + System.currentTimeMillis() + ".pdf");
            //f = new File(filename);
            f.createNewFile();
            JasperExportManager.exportReportToPdfFile(jasperPrint, f.getAbsolutePath());
        } else {
            f = new File (filename);

            //Exportation de l'état au format EXCEL
            //f = new File("K:/ISI/IS4/projet-tutore/web-service/webservicerest/src/main/resources/etatsImprimes/pv/PV-" + filiere + "-" + specialites + "-" + niveau + "-" + semestre + "-" + System.currentTimeMillis() + ".xlsx");
            f.createNewFile();
            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
            configuration.setOnePagePerSheet(false);
            configuration.setIgnoreGraphics(false);

            File outputFile = new File(f.getAbsolutePath());
            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                 OutputStream fileOutputStream = new FileOutputStream(outputFile)) {
                Exporter exporter = new JRXlsxExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
                exporter.setConfiguration(configuration);
                exporter.exportReport();
                byteArrayOutputStream.writeTo(fileOutputStream);
            }
        }

        long t2 = System.currentTimeMillis();
        System.out.println("Fin d'impression du PV en..." + ((t2 - t1) / 1000) + " sec");

        return f;

    }

    private static File imprimerListe(Integer niveau, String filiere, Integer annee_academique, String
            semestre, Connection connection, String classe, String specialites) throws JRException, IOException {

        long t1 = System.currentTimeMillis();
        System.out.println("Début d'impression de la liste de classe..." + t1);

        //JasperReport jasperReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/etats/PvFinal.jrxml"));
        JasperReport jasperReport = JasperCompileManager.compileReport(new ClassPathResource("etats" + FileSystems.getDefault().getSeparator() + "listedeclasse.jrxml").getInputStream());

        // - Paramètres à envoyer au rapport
        Map parameters = new HashMap();
        parameters.put("filiere", filiere);
        parameters.put("niveau", niveau);

        parameters.put("annee_academique", annee_academique);
        parameters.put("annee", annee_academique);
        parameters.put("classe", classe);
        parameters.put("semestre", semestre);
        parameters.put("specialites", specialites);
        //parameters.put("image_isj", new ClassPathResource("images"+FileSystems.getDefault().getSeparator()+"logo_isj.jpeg").getFile().getAbsolutePath() );
        parameters.put("image_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "logo_isj.jpeg").getFile().getAbsolutePath());
        parameters.put("entete_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "entete_isj.jpg").getFile().getAbsolutePath());

        // - Execution du rapport
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
        File f; String filename, filename0;
        //filename0 = new File ("/etatsImprimes" + FileSystems.getDefault().getSeparator() + "listeclasses" + FileSystems.getDefault().getSeparator() + "" + "Listedeclasse-" + filiere + "-" + specialites + "-" + niveau + "-" + semestre + "-" + System.currentTimeMillis() + ".pdf").getAbsolutePath();
        filename = GeneratePDF.class.getClassLoader().getResource(".").getPath() + "etatsImprimes" + FileSystems.getDefault().getSeparator() + "listeclasses" + FileSystems.getDefault().getSeparator() + "" + "Listedeclasse-" + filiere + "-" + classe + "-" + niveau + "-" + System.currentTimeMillis() + ".pdf";

        //filename = filename0;//Lorsque l'exécution se fait directement sur Intellij
        System.out.println("filename pv: "+filename);
        // - Création du rapport au format PDF
        f = new File (filename);
        //String filename = "K:/ISI/IS4/projet-tutore/web-service/webservicerest/src/main/resources/etatsImprimes/pv/"+ "" + "PV-" + filiere + "-" + specialites + "-" + niveau + "-" + semestre + "-" + System.currentTimeMillis() + ".pdf";
        //f = new File(GeneratePDF.class.getClassLoader().getResource(".").getPath() + "etatsImprimes" + FileSystems.getDefault().getSeparator() + "pv" + FileSystems.getDefault().getSeparator() + "" + "PV-" + filiere + "-" + specialites + "-" + niveau + "-" + semestre + "-" + System.currentTimeMillis() + ".pdf");
        //f = new File(filename);
        f.createNewFile();
        JasperExportManager.exportReportToPdfFile(jasperPrint, f.getAbsolutePath());

        long t2 = System.currentTimeMillis();
        System.out.println("Fin d'impression de la liste de classe en..." + ((t2 - t1) / 1000) + " sec");

        return f;

    }

    public byte[] imprimerNotes(int niveau, String filiere, String annee_academique, String semestre,
                                String specialite, String typeNote, int anneeDebut, String codeUE) throws JRException, IOException, SQLException {

        long t1 = System.currentTimeMillis();
        System.out.println("Début d'impression de notes..." + t1);
        Connection connection = Bd.getConnection();
        //if(connection==null)
        //connection = AbstractFacade.getConnection();        // - Chargement et compilation du rapport
        //A partir du Jar

        JasperReport jasperReport = JasperCompileManager.compileReport(new ClassPathResource("etats" + FileSystems.getDefault().getSeparator() + "NotesUe.jrxml").getInputStream());
        // - Paramètres à envoyer au rapport
        Map parameters = new HashMap();
        parameters.put("filiere", filiere);
        parameters.put("specialite", specialite);
        parameters.put("niveau", niveau);
        parameters.put("annee_academique", annee_academique);
        parameters.put("typeEval", typeNote);
        parameters.put("image_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "logo_isj.jpeg").getFile().getAbsolutePath());
        parameters.put("entete_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "entete_isj.jpg").getFile().getAbsolutePath());
        parameters.put("annee_debut", anneeDebut);
        parameters.put("semestre", semestre);
        parameters.put("ue", codeUE);

        // - Execution du rapport
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

        // - Création du rapport au format PDF
        File f = new File(GeneratePDF.class.getClassLoader().getResource(".").getPath()+"etatsImprimes"+FileSystems.getDefault().getSeparator()+ "notes"+FileSystems.getDefault().getSeparator()+"Notes-" + filiere + "-" + specialite + "-" + niveau + "-" + semestre + "-" + System.currentTimeMillis() + ".pdf");
        f.createNewFile();
        JasperExportManager.exportReportToPdfFile(jasperPrint, f.getAbsolutePath());
        long t2 = System.currentTimeMillis();
        System.out.println("Fin d'impression du PV en..." + ((t2 - t1) / 1000) + " sec");
        return Files.readAllBytes(f.toPath());
        //openFile(f);
    }

    private static File imprimerRecapitulatif(Integer niveau, String filiere, Integer annee_academique, String
            semestre, Connection connection, String classe, String specialites, boolean formatPDF) throws JRException, IOException {

        long t1 = System.currentTimeMillis();
        System.out.println("Début d'impression du recapitulatif..." + t1);
        // - Chargement et compilation du rapport
        //A partir du Jar
        //A partir de l'IDE
        //Pour le PV classique

        
        JasperReport jasperReport = JasperCompileManager.compileReport(new ClassPathResource("etats" + FileSystems.getDefault().getSeparator() + "PV_Synthese.jrxml").getInputStream());
        org.eclipse.jdt.internal.compiler.Compiler j;

        // - Paramètres à envoyer au rapport
        Map parameters = new HashMap();
        parameters.put("filiere", filiere);
        parameters.put("niveau", niveau);
        parameters.put("annee_academique", annee_academique);
        parameters.put("semestre", semestre);
        parameters.put("specialites", specialites);
        parameters.put("image_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "logo_isj.jpeg").getFile().getAbsolutePath());
        parameters.put("entete_isj", new ClassPathResource("images" + FileSystems.getDefault().getSeparator() + "entete_isj.jpg").getFile().getAbsolutePath());

        //Pour le PV synthese
        parameters.put("annee", annee_academique);
        parameters.put("classe", classe);


   System.out.println("connection");
        System.out.println(connection);
                System.out.println(jasperReport);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
        File f; String filename, filename0;
        if(formatPDF) {
            filename0 = new File ("/etatsImprimes"+FileSystems.getDefault().getSeparator()+ "pv"+FileSystems.getDefault().getSeparator()+"recapitulatif-" + filiere + "-" + niveau + "-" + semestre + "-" + System.currentTimeMillis() + ".pdf").getAbsolutePath();
            filename = GeneratePDF.class.getClassLoader().getResource(".").getPath()+"etatsImprimes"+FileSystems.getDefault().getSeparator()+ "pv"+FileSystems.getDefault().getSeparator()+"recapitulatif-" + filiere + "-" + niveau + "-" + semestre + "-" + System.currentTimeMillis() + ".pdf";
        }else {
            filename0 = new File ("/etatsImprimes"+FileSystems.getDefault().getSeparator()+ "pv"+FileSystems.getDefault().getSeparator()+"recapitulatif-" + filiere + "-" + specialites + "-" + niveau + "-" + semestre + "-" + System.currentTimeMillis() + ".xlsx").getAbsolutePath();
            filename = GeneratePDF.class.getClassLoader().getResource(".").getPath()+"etatsImprimes"+FileSystems.getDefault().getSeparator()+ "pv"+FileSystems.getDefault().getSeparator()+"recapitulatif-" + filiere + "-" + specialites + "-" + niveau + "-" + semestre + "-" + System.currentTimeMillis() + ".xlsx";
        }
        filename = filename0;//Lorsque l'exécution se fait directement sur Intellij
        System.out.println("filename recapitulatif: "+filename);
        if (formatPDF) {
            // - Création du rapport au format PDF
            f = new File(filename);
            f.createNewFile();
            JasperExportManager.exportReportToPdfFile(jasperPrint, f.getAbsolutePath());

        } else {
            f = new File(filename);
            f.createNewFile();
            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
            configuration.setOnePagePerSheet(false);
            configuration.setIgnoreGraphics(false);

            File outputFile = new File(f.getAbsolutePath());
            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                 OutputStream fileOutputStream = new FileOutputStream(outputFile)) {
                Exporter exporter = new JRXlsxExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
                exporter.setConfiguration(configuration);
                exporter.exportReport();
                byteArrayOutputStream.writeTo(fileOutputStream);
            }
        }
        long t2 = System.currentTimeMillis();
        System.out.println("Fin d'impression du recapitulatif en..." + ((t2 - t1) / 1000) + " sec");
        return f;
    }


}
