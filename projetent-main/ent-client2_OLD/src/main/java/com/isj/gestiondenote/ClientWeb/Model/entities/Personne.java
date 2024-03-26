package com.isj.gestiondenote.ClientWeb.Model.entities;
/**
 * importation des classes
 */

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

/**
 * cette classe est superclasse qu'on ne crée pas en bd
 * cette class herite de la classe Securite
 * @author traitement metiér
 */
@Data
@NoArgsConstructor

public class Personne extends Securite {


    public enum Sexe {
        MASCULIN, FEMININ
    }
    public enum Statut {
        ACTIVE, NONACTIVE
    }

    private String nom;


    private String prenom;


    private String email;


    private String telephone;


    private Date dateNaissance;


    private Sexe sexe;


    private Statut statut;

    public Personne(String libelle, String description, String nom, String prenom, String email, String telephone, Date dateNaissance, Sexe sexe, Statut statut) {
        super(libelle, description);
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.dateNaissance = dateNaissance;

    }

    @Override
    public String getLibelle(){
        return nom + "-"+ prenom + "-" + telephone
                + "-" + email + "-" + dateNaissance + "-" + sexe + "-" + statut;
    }

    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNom(), getPrenom(), getEmail(), getTelephone(), getDateNaissance(), getSexe(), getStatut());
    }

    @Override
    public String toString() {
        return "Personne{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", telephone=" + telephone +
                ", dateNaissance=" + dateNaissance +
                ", sexe=" + sexe +
                ", statut=" + statut +
                "} " + super.toString();
    }


}
