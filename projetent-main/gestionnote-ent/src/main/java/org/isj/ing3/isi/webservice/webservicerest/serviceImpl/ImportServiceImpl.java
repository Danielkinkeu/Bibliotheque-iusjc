package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.*;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Evaluation;
import org.isj.ing3.isi.webservice.webservicerest.utils.Bd;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

@Service
public class ImportServiceImpl {
    /**
     * Fonction qui creer un fichier excel pour l'enregistrement des abscences
     *
     * @param classe  le niveau pour lequelle ont veut generer la liste
     * @param filiere la filiere presice
     * @param annee   l'annee correspondante
     * @throws Exception en cas d'erreur
     */
    public void creerFichePresence(String classe, String filiere, String semestre, int annee, String pathOut) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();

        String sql = "{CALL etud_class(?,?,?)}";
        CallableStatement procEtudClass = new Bd().getConnection().prepareCall(sql);
        procEtudClass.setString(1, classe);
        procEtudClass.setString(2, filiere);
        procEtudClass.setInt(3, annee);
        procEtudClass.execute();
        ResultSet rs = procEtudClass.getResultSet();

        //Create Cell Style
        CellStyle cellBold = workbook.createCellStyle();
        CellStyle cellBold_U = workbook.createCellStyle();
        CellStyle cellLight = workbook.createCellStyle();

        Font font = workbook.createFont(); //create a personal font
        font.setFontName("Times New Roman");//set times new roman font type to our cell
        font.setFontHeightInPoints((short) 11);//set font height


        Font font2 = workbook.createFont();
        font2.setFontName("Times New Roman");//set times new roman font type to our cell
        font2.setFontHeightInPoints((short) 11);//set font height


        Font font3 = workbook.createFont();
        font3.setFontName("Times New Roman");//set times new roman font type to our cell
        font3.setFontHeightInPoints((short) 11);//set font height
        font3.setUnderline(Font.U_SINGLE);


        //we set font, horizontal and vertical aligment to our style
        cellBold.setFont(font);
        cellLight.setFont(font2);
        cellBold_U.setFont(font3);

    /*    cellBold.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        cellBold.setVerticalAlignment(VerticalAlignment.CENTER);
        cellBold.setWrapText(true);

        cellLight.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        cellLight.setVerticalAlignment(VerticalAlignment.CENTER);
        cellLight.setWrapText(true);

        cellBold_U.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        cellBold_U.setVerticalAlignment(VerticalAlignment.CENTER);*/
        cellBold_U.setWrapText(true);

        sheet.setColumnWidth(0, 1000);
        sheet.setColumnWidth(1, 2700);
        sheet.setColumnWidth(2, 3500);
        sheet.setColumnWidth(3, 3500);
        sheet.setColumnWidth(4, 3500);

        InputStream imageStream = new FileInputStream("src/main/java/org/isj/metier/input.jpg");

        XSSFDrawing drawing = sheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = new XSSFClientAnchor();
        anchor.setCol1(1);
        anchor.setRow1(2);
        XSSFPicture picture = drawing.createPicture(anchor, workbook.addPicture(imageStream, Workbook.PICTURE_TYPE_JPEG));

        //picture.resize(1.5, 5);

        Row row = sheet.createRow(3);
        row.createCell(2).setCellValue("INSTITUT SAINT JEAN");
        row.getCell(2).setCellStyle(cellBold);
        sheet.addMergedRegion(new CellRangeAddress(3, 4, 2, 5));
        row.createCell(8).setCellValue("Tel: (+237) 657 07 98 07");
        row.getCell(8).setCellStyle(cellLight);
        sheet.addMergedRegion(new CellRangeAddress(3, 3, 8, 11));

        row = sheet.createRow(4);
        row.createCell(8).setCellValue("(+237) 657 07 98 07");
        row.getCell(8).setCellStyle(cellLight);
        sheet.addMergedRegion(new CellRangeAddress(4, 4, 8, 11));

        row = sheet.createRow(5);
        row.createCell(8).setCellValue("B.P: 749 Yaoundé, Cameroun");
        row.getCell(8).setCellStyle(cellLight);
        sheet.addMergedRegion(new CellRangeAddress(5, 5, 8, 11));

        row = sheet.createRow(6);
        row.createCell(8).setCellValue("Email: info@institutsaintjean.org");
        row.getCell(8).setCellStyle(cellLight);
        sheet.addMergedRegion(new CellRangeAddress(6, 6, 8, 11));

        row = sheet.createRow(7);
        row.createCell(8).setCellValue("www.institutsaintjean.org");
        row.getCell(8).setCellStyle(cellLight);
        sheet.addMergedRegion(new CellRangeAddress(7, 7, 8, 11));

        row = sheet.createRow(9);
        row.createCell(3).setCellValue("FICHE DE PRESENCE");
        row.getCell(3).setCellStyle(cellBold);
        sheet.addMergedRegion(new CellRangeAddress(9, 9, 3, 6));

        row = sheet.createRow(10);
        row.createCell(1).setCellValue(semestre);
        row.getCell(1).setCellStyle(cellLight);
        sheet.addMergedRegion(new CellRangeAddress(10, 10, 1, 2));
        row = sheet.createRow(11);
        row.createCell(1).setCellValue("Classe:");
        row.getCell(1).setCellStyle(cellLight);
        row.createCell(2).setCellValue(classe);
        row.getCell(2).setCellStyle(cellBold);
        sheet.addMergedRegion(new CellRangeAddress(11, 11, 2, 3));
        row = sheet.createRow(12);
        row.createCell(1).setCellValue("Date:");
        row.getCell(1).setCellStyle(cellLight);
        sheet.addMergedRegion(new CellRangeAddress(12, 12, 2, 3));

        row = sheet.createRow(14);
        row.setHeight((short) 600);
        row.createCell(0).setCellValue("N°");
        row.getCell(0).setCellStyle(cellBold);
        row.createCell(1).setCellValue("Matricule");
        row.getCell(1).setCellStyle(cellBold);
        row.createCell(2).setCellValue("Noms et prenoms");
        row.getCell(2).setCellStyle(cellBold);
        sheet.addMergedRegion(new CellRangeAddress(14, 14, 2, 4));
        row.createCell(5).setCellValue("7h30-8h30");
        row.getCell(5).setCellStyle(cellBold);
        row.createCell(6).setCellValue("8h30-9h30");
        row.getCell(6).setCellStyle(cellBold);
        row.createCell(7).setCellValue("9h30-10h30");
        row.getCell(7).setCellStyle(cellBold);
        row.createCell(8).setCellValue("10h30-11h30");
        row.getCell(8).setCellStyle(cellBold);
        row.createCell(9).setCellValue("12h30-13h30");
        row.getCell(9).setCellStyle(cellBold);
        row.createCell(10).setCellValue("13h30-14h30");
        row.getCell(10).setCellStyle(cellBold);
        row.createCell(11).setCellValue("14h30-15h30");
        row.getCell(11).setCellStyle(cellBold);
        row.createCell(12).setCellValue("15h30-16h30");
        row.getCell(12).setCellStyle(cellBold);
        row.createCell(13).setCellValue("16h30-17h30");
        row.getCell(13).setCellStyle(cellBold);


        int r = 15, i = 0;
        row = sheet.createRow(15);
        while (rs.next()) {
            row.setHeight((short) 400);
            row.createCell(0).setCellValue(i + 1);
            row.getCell(0).setCellStyle(cellLight);
            row.createCell(1).setCellValue(rs.getString(1));
            row.getCell(1).setCellStyle(cellLight);
            row.createCell(2).setCellValue(rs.getString(2));
            row.getCell(2).setCellStyle(cellLight);
            sheet.addMergedRegion(new CellRangeAddress(r, r, 2, 4));

            i += 1;
            r += 1;
            row = sheet.createRow(r);
        }

        FileOutputStream fos = new FileOutputStream(new File(pathOut));
        workbook.write(fos);
        imageStream.close();
        fos.close();
        //workbook.close();
    }

    /**
     * Fonction qui creer le fichier excel de correspondance anonymat - valeur note
     *

     * @throws IOException en cas d'erreur lors de la sauvegarde du fichier
     */
   /* public void createExcelAnonymatNoteFile(long code_evaluation, String pathOut) throws IOException, MessagingException {

        Evaluation evaluation = new EvaluationFacade().find(code_evaluation);
        Enseignement enseignement = evaluation.getTypeEvaluation().getEnseignement();
        UE ue = enseignement.getUe();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();

        List<Enseignant> listEnseignant = enseignement.getEnseignants();
        String maillist = "";
        for (Enseignant e : listEnseignant) {
            maillist = e.getEmail() + ",";
        }
        if (maillist.isEmpty()) maillist = MAIL_RECEVER;

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


        //Create Cell Style
        CellStyle cellBold = workbook.createCellStyle();
        CellStyle cellBold_U = workbook.createCellStyle();
        CellStyle cellLight = workbook.createCellStyle();

        Font font = workbook.createFont(); //create a personal font
        font.setFontName("Times New Roman");//set times new roman font type to our cell
        font.setFontHeightInPoints((short) 11);//set font height
        font.setBold(true);

        Font font2 = workbook.createFont();
        font2.setFontName("Times New Roman");//set times new roman font type to our cell
        font2.setFontHeightInPoints((short) 11);//set font height


        Font font3 = workbook.createFont();
        font3.setFontName("Times New Roman");//set times new roman font type to our cell
        font3.setFontHeightInPoints((short) 11);//set font height
        font3.setUnderline(Font.U_SINGLE);
        font3.setBold(true);

        //we set font, horizontal and vertical aligment to our style
        cellBold.setFont(font);
        cellLight.setFont(font2);
        cellBold_U.setFont(font3);

        cellBold.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        cellBold.setVerticalAlignment(VerticalAlignment.CENTER);
        cellBold.setWrapText(true);

        cellLight.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        cellLight.setVerticalAlignment(VerticalAlignment.CENTER);
        cellLight.setWrapText(true);

        cellBold_U.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        cellBold_U.setVerticalAlignment(VerticalAlignment.CENTER);
        cellBold_U.setWrapText(true);


        sheet.setColumnWidth(0, 4000);
        sheet.setColumnWidth(1, 3500);
        sheet.setColumnWidth(2, 3500);
        sheet.setColumnWidth(3, 5500);

        Row row = sheet.createRow(0);
        row.setHeight((short) 600);
        row.createCell(0).setCellValue(evaluation.getTypeEvaluation().getLibelle());
        row.getCell(0).setCellStyle(cellBold);
        row.createCell(1).setCellValue(ue.getLibelle());
        row.getCell(1).setCellStyle(cellBold_U);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 4)); //MERGE CELL 4 to 8 at ligne 0


        row = sheet.createRow(1);
        row.createCell(0).setCellValue(formatter.format(evaluation.getDateEval()));
        row.getCell(0).setCellStyle(cellBold);
        row.createCell(1).setCellValue(ue.getCodeUE());
        row.getCell(1).setCellStyle(cellLight);
        row.createCell(2).setCellValue(ue.getNiveau().toString());
        row.getCell(2).setCellStyle(cellLight);
        String filiereSpecialite = ue.getSpecialite().getLibelle();
        row.createCell(3).setCellValue(filiereSpecialite);
        row.getCell(3).setCellStyle(cellLight);

        List<Anonymat> listeAnonymat = retrouverListeAnonymat(evaluation);

        for (int i = 0; i < listeAnonymat.size(); i++) {
            Anonymat anonymat = listeAnonymat.get(i);
            row = sheet.createRow(5 + i);
            row.setHeight((short) 600);
            row.createCell(0).setCellValue(anonymat.getNumeroAnonymat());
            row.getCell(0).setCellStyle(cellLight);
        }

        row = sheet.createRow(4);
        row.setHeight((short) 600);
        row.createCell(0).setCellValue("numero anonymat");
        row.getCell(0).setCellStyle(cellBold);
        row.createCell(1).setCellValue("valeur note");
        row.getCell(1).setCellStyle(cellBold);

        //Save Excel file
        FileOutputStream fout = new FileOutputStream(new File(pathOut));
        workbook.write(fout);
        fout.close();
        workbook.close();

        String titre = evaluation.getTypeEvaluation().getLibelle() + " " + ue.getLibelle() + " du " + formatter.format(evaluation.getDateEval()) + " " + ue.getNiveau().toString();
        SendEmail sendEmail = new SendEmail();
        try {
            sendEmail.sendAttachFile(maillist, "Fiche de Note ", "<h3>" + titre + "</h3>", pathOut);
        } catch (Exception e) {
            e.getMessage();
        }

    }
*/

    public void enregistrerNoteExcel(byte[] bytes) throws IOException, InvalidFormatException {
        Path path = Paths.get("src/main/resources/import/note.xlsx");
        FileInputStream fileInputStream = new FileInputStream(String.valueOf(path));

        Workbook workbook = new XSSFWorkbook(fileInputStream);

        DataFormatter dataFormatter = new DataFormatter();

        String matricule = null;
        String reponse = null;
        Evaluation evaluation = null;
        int numeroTable = 0;
        double valeurNote = -1;//si note non definit alors note vaut -1 et on ne l'afficheras pas.
        Etudiant etudiant = null;
        Candidat candidat = null;
        TypeEvaluation typeEvaluation = null;
        Enseignement enseignement = null;
        EstInscrit estInscrit = null;
        String resultat = null, code = null;
        int numeroLigne = -1;

        Iterator<Sheet> sheetIterator = null;

        while (sheetIterator.hasNext()) {
            Sheet sheet = sheetIterator.next();

            Iterator<Row> rowIterator = sheet.rowIterator();

            while (rowIterator.hasNext()) {

                Row row = rowIterator.next();
                numeroLigne = row.getRowNum();
                String description = dataFormatter.formatCellValue(sheet.getRow(0).getCell(2));
                String libelle = dataFormatter.formatCellValue(sheet.getRow(0).getCell(3));
                if (numeroLigne == 0) {
                    reponse = dataFormatter.formatCellValue(row.getCell(1)).trim();
                } else if (numeroLigne == 1) {
                    //evaluation = new EvaluationFacade().find(Long.valueOf(dataFormatter.formatCellValue(row.getCell(1)).trim()));
                    typeEvaluation = evaluation.getTypeEvaluation();
                    enseignement = typeEvaluation.getEnseignement();
                } else if (numeroLigne >= 5) {

                   // try {
                       /* if (!isCellEmpty(row.getCell(3))) {
                            numeroTable = Integer.parseInt(dataFormatter.formatCellValue(row.getCell(4)).trim());
                            valeurNote = Double.parseDouble(dataFormatter.formatCellValue(row.getCell(3)).trim());
                            //Désormais si une note est vide on tombera directement dans le catch
                            //et elle ne sera pas enregistrée
                            if (reponse.equals("oui")) {
                                matricule = dataFormatter.formatCellValue(row.getCell(1)).trim();
                             *//*   etudiant = new Isj().retrouverEtudiantMatricule(matricule);
                                estInscrit = new Isj().retrouverEstInscrit(etudiant, enseignement);*//*
                                //resultat = new NoteFacade().enregistrer("", "", valeurNote, numeroTable, null, estInscrit, evaluation);
                            } else if (reponse.equals("non")) {
                                code = dataFormatter.formatCellValue(row.getCell(1));
                             *//*   candidat = new CandidatFacade().find(Long.valueOf(code));
                                estInscrit = new Isj().retrouverEstInscrit(candidat, enseignement);*//*
                            }*/

                            //On vérifie si la note n'existe pas déjà avant de l'enregistrer
                            //Isj isj=new Isj();
                           // Note NoteExiste=isj.retrouverNote(estInscrit,evaluation);
                           /* if(NoteExiste==null)
                                resultat = new NoteFacade().enregistrer(libelle, description, valeurNote, numeroTable, null, estInscrit, evaluation);
*/
                     /*   }
                    } catch (Exception e) {
                        e.printStackTrace();
                        //valeurNote = 0;
                        resultat = e.getMessage();
                    }*/


                }
            }
        }
    }

}
