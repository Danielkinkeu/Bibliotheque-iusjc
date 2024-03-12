package org.isj.ing3.isi.webservice.webservicerest.utils.etats.dynamicreports;

import ar.com.fdvs.dj.core.DJJRDesignHelper;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.ImageBanner;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.builders.GroupBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.*;
import ar.com.fdvs.dj.domain.entities.DJGroup;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.domain.entities.columns.PropertyColumn;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Etudiant;
import org.isj.ing3.isi.webservice.webservicerest.serviceImpl.EtudiantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;


import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static ar.com.fdvs.dj.domain.constants.Page.Page_Letter_Portrait;

public class SimpleDynamicReport {

    @Autowired
    static EtudiantServiceImpl etudiantImpl;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    static SimpleDateFormat formatImpression = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public void printDynamicReport(
            String requete,
            List<String> colonnesAImprimer,
            String titre,
            String sousTitre,
            boolean printBackgroundOnOddRows,
            boolean useFullPageWidth,
            Page orientation) {

        try {
            FastReportBuilder drb = new FastReportBuilder();
            drb.setTitle(titre)
                    .setSubtitle(sousTitre)
                    .setPrintBackgroundOnOddRows(printBackgroundOnOddRows)
                    .setUseFullPageWidth(useFullPageWidth)
                    .setPageSizeAndOrientation(orientation)
                    .setWhenNoDataBlankPage()
                    .addFirstPageImageBanner(new File("images"+File.separator+"logo_isj.jpeg").getPath(), 100, 100, ImageBanner.Alignment.Center);

            /*ResultSet resultSet = new CandidatFacade().getConnection().createStatement().executeQuery(requete);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                if (colonnesAImprimer.contains(resultSetMetaData.getColumnLabel(i))) {
                    ColumnBuilder columnBuilder = new ColumnBuilder();
                    columnBuilder.setCommonProperties(resultSetMetaData.getColumnLabel(i),
                            resultSetMetaData.getColumnLabel(i),
                            resultSetMetaData.getColumnClassName(i),
                            (orientation.equals(Page_A4_Landscape()) ? 280 : 200) / colonnesAImprimer.size(),
                            false);
                    columnBuilder.setStyle(new StyleBuilder(true).setHorizontalAlign(HorizontalAlign.CENTER).build());
                    drb.addColumn(columnBuilder.build());
                }
            }*/
            /*resultSet.beforeFirst();
            JRDataSource ds = new JRResultSetDataSource(resultSet);
            JasperPrint jp = DynamicJasperHelper.generateJasperPrint(drb.build(), new ClassicLayoutManager(), ds);*/

                    DJJRDesignHelper f;
            //JasperViewer.viewReport(jp,false);    //finally display the report report
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printDynamicReport(
            Etudiant etudiant,
            int annee,
            String requete,
            List<String> colonnesAImprimer,
            String titre,
            String sousTitre,
            boolean printBackgroundOnOddRows,
            boolean useFullPageWidth,
            Page orientation) {

        //System.out.println("requete = [" + requete + "], colonnesAImprimer = [" + colonnesAImprimer + "], titre = [" + titre + "], sousTitre = [" + sousTitre + "], printBackgroundOnOddRows = [" + printBackgroundOnOddRows + "], useFullPageWidth = [" + useFullPageWidth + "], orientation = [" + orientation + "]");
        //System.out.println(orientation.equals(Page.Page_A4_Landscape()));
        try {

            FastReportBuilder drb = new FastReportBuilder(); //Créee un FastReportBuilder et définit les options de base pour le rapport
            drb.setTitle(titre)
                    .setSubtitle(sousTitre) //définit le titre du rapport
                    .setPrintBackgroundOnOddRows(printBackgroundOnOddRows) //définit les lignes et retourn un booleen
                    .setUseFullPageWidth(useFullPageWidth) // définit la longeur des éléments dans du rapport
                    .setPageSizeAndOrientation(orientation).setWhenNoDataBlankPage()

                    //.addAutoText(AutoText.AUTOTEXT_PAGE_X, AutoText.POSITION_HEADER,AutoText.ALIGNMENT_LEFT,200,40)
                    //.addAutoText("Texte automatique inférieure au compteur de pages" , AutoText.POSITION_HEADER,AutoText.ALIGNMENT_LEFT)
                    .addFirstPageImageBanner(getClass().getClassLoader().getResource("images"+File.separator+"logo_isj.jpeg").getPath(), 200, 138, ImageBanner.Alignment.Center);    //définit l'image qui sera afficher au dessus du rapport

            Style atStylelist = new StyleBuilder(true).setFont(Font.COMIC_SANS_SMALL).setTextColor(Color.red).build();
            Style atStylelist2 = new StyleBuilder(true).setFont(new Font(9, Font._FONT_TIMES_NEW_ROMAN, false, true, false)).setTextColor(Color.BLACK).build();

            drb.setGlobalHeaderVariableHeight(new Integer(25));
            drb.setGlobalFooterVariableHeight(new Integer(25));
            drb.addAutoText("REPUBLIQUE DU CAMEROUN", AutoText.POSITION_HEADER, AutoText.ALIGNMENT_LEFT, 200);
            drb.addAutoText("REPUBLIQUE DU CAMEROUN", AutoText.POSITION_HEADER, AutoText.ALIGNMENT_RIGHT, 200);
            drb.addAutoText("Yaoundé", AutoText.POSITION_FOOTER, AutoText.ALIGNMENT_RIGHT);
            drb.addAutoText("Le Directeur de l'ENSP", AutoText.POSITION_FOOTER, AutoText.ALIGNMENT_RIGHT, 200);
            //drb.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_FOOTER, AutoText.ALIGNMENT_RIGHT,30,30);
            drb.addAutoText("Le Directeur de l'ISJ", AutoText.POSITION_FOOTER, AutoText.ALIGNMENT_LEFT, 200);
            drb.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_FOOTER, AutoText.ALIGNMENT_CENTER, 200, 100);

            drb.addAutoText("PAIX-TRAVAIL-PATRIE", AutoText.POSITION_HEADER, AutoText.ALIGNMENT_LEFT, 200);
            drb.addAutoText("PAIX-TRAVAIL-PATRIE", AutoText.POSITION_HEADER, AutoText.ALIGNMENT_RIGHT, 200);
            //drb.addImageBanner("ENS.jpg", 200, 138, ImageBanner.Alignment.Left);
            drb.addAutoText("UNIVERSITE DE YAOUNDE I", AutoText.POSITION_HEADER, AutoText.ALIGNMENT_RIGHT, 200);
            drb.addAutoText("ECOLE NATIONALE SUPERIEURE POLYTECHNIQUE", AutoText.POSITION_HEADER, AutoText.ALIGNMENT_RIGHT, 200);
            drb.addAutoText("MINISTERE DE L'ENSEIGNEMENT SUPERIEURE", AutoText.POSITION_HEADER, AutoText.ALIGNMENT_LEFT, 200);
            drb.addAutoText("INSTITUT SAINT JEAN", AutoText.POSITION_HEADER, AutoText.ALIGNMENT_LEFT, 200);

            drb.addAutoText(" ", AutoText.POSITION_HEADER, AutoText.ALIGNMENT_LEFT, 200);
            drb.addAutoText(" ", AutoText.POSITION_HEADER, AutoText.ALIGNMENT_RIGHT, 200);

            drb.addAutoText("Matricule: " + etudiant.getMatricule(), AutoText.POSITION_HEADER, AutoText.ALIGNMENT_RIGHT, 200);
            drb.addAutoText("NIVEAU: " + etudiant.getClasse().getNiveau().getNumero(), AutoText.POSITION_HEADER, AutoText.ALIGNMENT_LEFT, 200);
            drb.addAutoText("NOM ET PRENOM: " + etudiant.getNom() + " " + etudiant.getPrenom(), AutoText.POSITION_HEADER, AutoText.ALIGNMENT_LEFT, 200);

            drb.addAutoText("ANNEE ACADEMIQUE: " + annee + "/" + (annee + 1), AutoText.POSITION_HEADER, AutoText.ALIGNMENT_LEFT, 200);
            drb.addAutoText("NE(E) LE: " + format.format(etudiant.getDateNaissance()) + " A " + etudiant.getRegionOrigine(), AutoText.POSITION_HEADER, AutoText.ALIGNMENT_LEFT, 200);
            drb.addAutoText("Sexe: " + etudiant.getSexe().toString(), AutoText.POSITION_HEADER, AutoText.ALIGNMENT_RIGHT, 200);

            // drb.addImageBanner(System.getProperty("user.dir") +"images/isj.jpg", new Integer(100), new Integer(30), ImageBanner.ALIGN_RIGHT);
            //     drb.setReportLocale(new Locale("FR","CAM"));
            //drb.addAutoText("Autotext at top-center", AutoText.POSITION_HEADER, AutoText.ALIGNMENT_CENTER,200,atStylelist);
            // Vector<ImageBanner> vImageBanner = new Vector<ImageBanner>();
            // vImageBanner.add(new ImageBanner("images/isj.jpg",120,50,ImageBanner.ALIGN_LEFT));
            Style titleStyle = new Style();
            titleStyle.setFont(new Font(8, Font._FONT_VERDANA, true));
            Style importeStyle = new Style();
            importeStyle.setHorizontalAlign(HorizontalAlign.CENTER);
            Style oddRowStyle = new Style();
            oddRowStyle.setBorder(Border.NO_BORDER());
            oddRowStyle.setBackgroundColor(Color.LIGHT_GRAY);
            oddRowStyle.setTransparency(Transparency.OPAQUE);
            Style headerVariables = new Style("headerVariables");
            headerVariables.setFont(Font.ARIAL_BIG_BOLD);
            headerVariables.setBorderBottom(Border.THIN());
            headerVariables.setHorizontalAlign(HorizontalAlign.CENTER);
            headerVariables.setVerticalAlign(VerticalAlign.TOP);
            headerVariables.setStretchWithOverflow(true);

            Style groupVariables = new Style("groupVariables");
            groupVariables.setFont(Font.ARIAL_MEDIUM_BOLD);
            groupVariables.setTextColor(Color.BLUE);
            groupVariables.setBorderBottom(Border.THIN());
            groupVariables.setHorizontalAlign(HorizontalAlign.LEFT);
            groupVariables.setVerticalAlign(VerticalAlign.BOTTOM);
            Style groupTitleStyle = new Style();
            groupTitleStyle.setFont(Font.ARIAL_BIG);

            //DynamicReport dr = drb.build();
            //ResultSet resultSet = new CandidatFacade().getConnection().createStatement().executeQuery(requete);
           /* ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                System.out.println(resultSetMetaData.getColumnLabel(i) + " - " +
                        resultSetMetaData.getColumnName(i) + " - " +
                        resultSetMetaData.getColumnClassName(i));
                if (colonnesAImprimer.contains(resultSetMetaData.getColumnLabel(i))) {
                    ColumnBuilder columnBuilder = new ColumnBuilder();
                    columnBuilder.setCommonProperties(resultSetMetaData.getColumnLabel(i),
                            resultSetMetaData.getColumnLabel(i),
                            resultSetMetaData.getColumnClassName(i),
                            (orientation.equals(Page_A4_Landscape()) ? 280 : 200) / colonnesAImprimer.size(),
                            false);
                    columnBuilder.setStyle(new StyleBuilder(true).setHorizontalAlign(HorizontalAlign.CENTER).build());
                    drb.addColumn(columnBuilder.build());
                }
            }*/

            AbstractColumn columnmodule = ColumnBuilder.getNew()
                    .setColumnProperty("module", String.class.getName()).setTitle("module").setWidth(new Integer(100))
                    .setStyle(groupVariables).setHeaderStyle(groupVariables)
                    // .setColumnProperty("module", String.class.getName(),String
                    .build();
            AbstractColumn columncode_ue = ColumnBuilder.getNew()
                    .setColumnProperty("code_ue", String.class.getName()).setTitle("code_ue").setWidth(new Integer(100))
                    .setStyle(groupVariables).setHeaderStyle(headerVariables)

                    .build();
/*           AbstractColumn columnmatiere= ColumnBuilder.getNew()
                    .setColumnProperty("matiere", String.class.getName()).setTitle(
                            "matiere").setWidth(new Integer(85)).build();
            AbstractColumn columnevaluation= ColumnBuilder.getNew()
                    .setColumnProperty("evaluation", String.class.getName()).setTitle(
                            "evaluation").setWidth(new Integer(85)).build();*/
            AbstractColumn columnvaleur_note = ColumnBuilder.getNew()
                    .setColumnProperty("moyenne", Long.class.getName()).setTitle(
                            "moyenne").setWidth(new Integer(85))
                    .build();


            GroupBuilder gb1 = new GroupBuilder(); // define the criteria ki3u[h'
            // column to group by (columnmodule)
            DJGroup g1 = gb1.setCriteriaColumn((PropertyColumn) columnmodule)
                    .addFooterVariable(columnvaleur_note, DJCalculation.SUM, groupVariables) // tell the group place a variable footer of the column "columnAmount" with the SUM of allvalues of the columnAmount in this group.
                    .setFooterVariablesHeight(new Integer(20))
                    .setFooterHeight(new Integer(50), true)
                    .setHeaderVariablesHeight(new Integer(35))
                    .build();
            drb.addGroup(g1);// add group g1

            /*resultSet.beforeFirst();
            JRDataSource ds = new JRResultSetDataSource(resultSet);
            JasperPrint jp = DynamicJasperHelper.generateJasperPrint(drb.build(), new ClassicLayoutManager(), ds);
            JasperViewer.viewReport(jp, false);*/    //finally display the report
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //net.sf.jasperreports.engine.design.JRDesignTextField f;

    public static void genererReleve(String matricule, int niveau, int annee) throws IsjException {

        //Etudiant etudiant = new Isj().retrouverEtudiantMatricule(matricule);
        Etudiant etudiant = etudiantImpl.getStudentByMatricule(matricule);
        new SimpleDynamicReport().printDynamicReport(
                etudiant,
                annee,
                "call releve_note(\"" + matricule + "\"," + niveau + "," + annee + ")",
                Arrays.asList("module", "code_ue", "ue", "credits", "decision", "moyenne", "grade"),
                "RELEVE DE NOTES ANNUEL",
                "Etat imprimé à " + formatImpression.format( new Date()),
                true,
                true,
                Page_Letter_Portrait());
    }

    public static void main(String[] args) throws IsjException {

        genererReleve("ISJ0004", 2, 2019);
        /*new SimpleDynamicReport().printDynamicReport(

                        "select DISTINCT module.libelle as module,code_ue,ue.libelle as matiere,type_evaluation.libelle as evaluation,valeur_note,\n" +
                                "(case when (valeur_note >=10) \n" +
                                " THEN\n" +
                                "      \"CA\" \n" +
                                "\tWHEN (valeur_note =9) THEN\n" +
                                "      \"CANT\" \n" +
                                "\tWHEN (valeur_note <9) THEN\n" +
                                "      \"NC\" \n" +
                                " END)\n" +
                                " as decision,(case when (valeur_note >=16 and valeur_note<=20) \n" +
                                " THEN\n" +
                                "      \"A\" \n" +
                                "\twhen (valeur_note >=15 and valeur_note<16) THEN\n" +
                                "      \"A-\" \n" +
                                "\twhen (valeur_note >=14 and valeur_note<15) THEN\n" +
                                "      \"B+\" \n" +
                                "\twhen (valeur_note >=13 and valeur_note<14) THEN\n" +
                                "      \"B\" \n" +
                                "\twhen (valeur_note >=12 and valeur_note<13) THEN\n" +
                                "      \"B-\" \n" +
                                "\twhen (valeur_note >=11 and valeur_note<12) THEN\n" +
                                "      \"C+\" \n" +
                                "\twhen (valeur_note >=10 and valeur_note<11) THEN\n" +
                                "      \"C\" \n" +
                                "\twhen (valeur_note >=9 and valeur_note<10) THEN\n" +
                                "      \"C-\" \n" +
                                "\twhen (valeur_note >=8 and valeur_note<9) THEN\n" +
                                "      \"D+\" \n" +
                                "\twhen (valeur_note >=7 and valeur_note<8) THEN\n" +
                                "      \"D\" \n" +
                                "\twhen (valeur_note >=6 and valeur_note<7) THEN\n" +
                                "      \"E\" \n" +
                                "\twhen (valeur_note >=0 and valeur_note<6) THEN\n" +
                                "      \"F\" \n" +
                                " END)\n" +
                                " as grade\n" +
                                "FROM etudiant,niveau,est_inscrit,filiere,type_evaluation,note,classe,candidat,evaluation,specialite,ue,enseignement,module\n" +
                                "WHERE note.est_inscrit=est_inscrit.`code`\n" +
                                "AND est_inscrit.candidat_inscrit=candidat.`code`\n" +
                                "AND candidat.`code`=etudiant.code_authentification\n" +
                                "AND note.evaluation=evaluation.`code`\n" +
                                "AND evaluation.type_evaluation=type_evaluation.`code`\n" +
                                "AND candidat.classe=classe.`code`\n" +
                                "AND classe.specialite=specialite.`code`\n" +
                                "AND specialite.filiere=filiere.`code`\n" +
                                "AND type_evaluation.enseignement=enseignement.`code`\n" +
                                "AND enseignement.ue=ue.`code`\n" +
                                "AND module.`code`=ue.module\n" +
                                "ORDER BY module asc",
                Arrays.asList("module","code_ue","matiere","evaluation","decision","valeur_note","grade"),
                "RELEVE DE NOTES ANNUEL",
                "Etat imprimé à " + formatImpression.format( new Date()),
                true,
                true,
                Page_Letter_Landscape());*/
    }
}
