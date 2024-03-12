package com.isj.gestiondenote.ClientWeb.Model.entities;
/**
 * importation des classes
 */

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * cette classe crée la table discipline dans la base de donnée
 * cette classe herite de la classe Securite
 * @author traitement metier
 */

@Data
@NoArgsConstructor
@AllArgsConstructor

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)

public class Discipline extends Securite implements Serializable {


    private Etudiant etudiant;


    private Semestre semestre;


    private int nbHeures;


    private int nbRetards;


    private Date dateRetard;


    private int heureJustifie;



    public Discipline(String libelle, String description, Etudiant etudiant, Semestre semestre, int nbHeures, int nbRetards, Date dateRetard, int heureJustifie) {
        super(libelle, description);
        this.etudiant = etudiant;
        this.semestre = semestre;
        this.nbHeures = nbHeures;
        this.nbRetards = nbRetards;
        this.dateRetard = dateRetard;
        this.heureJustifie = heureJustifie;
    }

    @Override
    public String getLibelle(){
        if(etudiant!=null)
        return etudiant.getNom()+"-"+ etudiant.getPrenom()+"-"+nbHeures+ "-" +nbRetards+ "-" +dateRetard;
        return "";
    }

    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getEtudiant(), getSemestre(), getNbHeures(), getNbRetards(), getDateRetard(), getHeureJustifie());
    }

    @Override
    public String toString() {
        return "Discipline{" +
                "etudiant=" + etudiant.toString() +
                ", semestre=" + semestre.toString() +
                ", nbHeures=" + nbHeures +
                ", nbRetards=" + nbRetards +
                ", dateRetards=" + dateRetard +
                ", heureJustifie=" + heureJustifie +
                "} " ;
    }
}
