package com.isj.gestiondenote.ClientWeb.Model.entities;

/**
 * importation des classes
 */


import lombok.Data;

import java.util.Date;
/**
 * cette classe est une superclasse à partir de laquelle héritent toutes les classes du projet
 *
 * @author traitement metier
 */
@Data

public class Securite {

    public enum StatutVie {
        ATTENTE, ACTIVE, CLOTUREE
    }


    private Long code= Long.valueOf(0);



    private String libelle;


    private String description;

    private String signature;


    private StatutVie statutVie;


    private Utilisateur createur;

    private Date dateCreation = new Date();

    //@JsonBackReference

    private Utilisateur modificateur;

    private Date dateModification = new Date();

    public Securite(String libelle, String description) {
        this.libelle = libelle;
        this.description = description;
    }

    public Securite() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Securite securite = (Securite) o;
        return code.equals(securite.code);
    }

  /*  @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).
                append(createur).
                append(dateCreation).
                append(code).
                append(libelle).
                append(description).
                append(statutVie).
                append(modificateur).
                append(dateModification).
                toHashCode();
    }*/
/*@Override
    public int hashCode() {
        return Objects.hash(getCode(),getDateCreation());
    }*/


    public void setSignature() {

    }


/*
    public String toString() {
        return "Securite{" +
                "code=" + code +
                ", libelle='" + libelle + '\'' +
                ", description='" + description + '\'' +
                ", signature='" + signature + '\'' +
                ", statutVie=" + statutVie +
                ", createur=" + createur +
                ", dateCreation=" + dateCreation +
                ", modificateur=" + modificateur +
                ", dateModification=" + dateModification +
                '}';
    }*/
}
