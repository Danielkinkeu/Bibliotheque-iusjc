package org.isj.ing3.isi.webservice.webservicerest.model.entities;

/**
 * importation des classes
 */


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.isj.ing3.isi.webservice.webservicerest.utils.enumaration.StatutVie;

import javax.persistence.*;
import java.util.Date;

/**
 * cette classe est une superclasse à partir de laquelle héritent toutes les classes du projet
 *
 * @author traitement metier
 */
@Data
@MappedSuperclass

public class Securite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "code")
    private Long code= Long.valueOf(0);


    @Column(name = "libelle")
    private String libelle;

    @Column(name = "description")
    private String description;

    @Column(name = "signature", unique = true)
    private String signature;

    @Column(name = "statut_vie", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatutVie statutVie;

    @JoinColumn(name = "createur", nullable = false)
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private Utilisateur createur;

    @Column(name = "date_creation", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation = new Date();

    //@JsonBackReference
    @JoinColumn(name = "modificateur", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Utilisateur modificateur;

    @Column(name = "date_modification", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
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
