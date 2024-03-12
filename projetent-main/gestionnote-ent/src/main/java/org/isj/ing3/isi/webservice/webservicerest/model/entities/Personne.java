package org.isj.ing3.isi.webservice.webservicerest.model.entities;
/**
 * importation des classes
 */

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.isj.ing3.isi.webservice.webservicerest.utils.enumaration.Sexe;
import org.isj.ing3.isi.webservice.webservicerest.utils.enumaration.Statut;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * cette classe est superclasse qu'on ne crée pas en bd
 * cette class herite de la classe Securite
 * @author traitement metiér
 */
@Data
@NoArgsConstructor
@MappedSuperclass

public class Personne extends Securite {

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email", nullable = true, unique = false)
    private String email;

    @Column(name = "telephone",nullable = true, unique = false, length=13)
    private String telephone;

    @Column(name = "date_naissance", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateNaissance;

    @Column(name = "sexe",nullable = false)
    @Enumerated(EnumType.STRING)
    private Sexe sexe;

    @Column(name = "statut",nullable = false)
    @Enumerated(EnumType.STRING)
    private Statut statut;

    public Personne(String libelle, String description, String nom, String prenom, String email, String telephone, Date dateNaissance, Sexe sexe, Statut statut) {
        super(libelle, description);
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.statut = statut;
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
