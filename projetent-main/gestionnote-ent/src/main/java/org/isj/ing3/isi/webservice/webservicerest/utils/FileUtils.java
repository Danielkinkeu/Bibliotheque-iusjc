/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.isj.ing3.isi.webservice.webservicerest.utils;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author nkot
 */
public class FileUtils {

    public static boolean copieFichier(File source, File destination, Boolean deleteSource) {

        boolean resultat = false;

        if (!destination.exists()) {
            try {
                destination.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
// Declaration des flux
        FileInputStream sourceFile = null;
        FileOutputStream destinationFile = null;

        try {
// Création du fichier :
            destination.createNewFile();
// Ouverture des flux
            sourceFile = new FileInputStream(source);
            destinationFile = new FileOutputStream(destination);
// Lecture par segment de 0.5Mo
            byte buffer[] = new byte[512 * 1024];
            int nbLecture;
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, nbLecture);
            }

// Copie réussie
            resultat = true;
        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
// Quoi qu'il arrive, on ferme les flux
            try {
                sourceFile.close();
            } catch (Exception e) {
            }
            try {
                destinationFile.close();
            } catch (Exception e) {
            }
        }

        if (deleteSource) {
            source.delete();
        }
        return (resultat);

    }

    public static void ajouterElementFichier(String fichier, String element) throws Exception {

        if (!new File(fichier).exists()) {
            System.err.println("Le fichier " + fichier + " n'existe pas !");
            return;
        }

        FileOutputStream destinationFile = null;
        try {
            destinationFile = new FileOutputStream(fichier, true);
            destinationFile.write((element + "\n").getBytes());
        } catch (FileNotFoundException f) {
            f.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                destinationFile.close();
            } catch (Exception e) {
            }
        }
    }

    public static void supprimerLigneFichier(String fichier, String ligne) {

        try {

            File file = new File(fichier);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";
            String oldtext = "";

            while ((line = reader.readLine()) != null) {
                if (!line.equals(ligne)) {
                    oldtext += line + "\r\n";
                }
            }
            reader.close();

            FileWriter writer = new FileWriter(fichier);
            writer.write(oldtext);
            writer.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static Set listeElements(String fichier) {

        Set<String> liste = new HashSet<>();
        try {
            File file = new File(fichier);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";

            while ((line = reader.readLine()) != null) {
                liste.add(line);
            }
            reader.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return liste;
    }

    public static String LectureFichier(String fichier) throws IOException {

        File file = new File(fichier);
        return lectureFichier(file);
    }

    public static String lectureFichier(File fichier) {
        BufferedReader reader = null;
        String oldtext = "";
        try {
            reader = new BufferedReader(new FileReader(fichier));
            String line = "";
//            System.out.println("Debut de lecture ");
            while ((line = reader.readLine()) != null) {
//                System.out.println("Ligne lue : " + line);
                oldtext += line + "\r\n";
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return oldtext;
    }

    public static void replace(String fichier, String source, String destination) {
        try {
            File file = new File(fichier);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";
            String oldtext = "";
            while ((line = reader.readLine()) != null) {
                oldtext += line + "\r\n";
            }
            reader.close();
            // replace a word in a file
//            System.out.println(Matcher.quoteReplacement(destination)+"\n\n\n"+oldtext);
//            String newtext = oldtext.replace(source, Matcher.quoteReplacement(destination));
            String newtext = oldtext.replace(source, destination);

            FileWriter writer = new FileWriter(fichier);
            writer.write(newtext);
            writer.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void ecritureFichier(String fichier, String texte) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(fichier);
            fw.write(texte);
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
