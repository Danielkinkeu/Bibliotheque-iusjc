package org.isj.ing3.isi.webservice.webservicerest.model.entities;

/**
 * importation des classes
 */

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * cette classe crée la table Semestre dans la base de données
 * cette classe herite de la classe Securite
 * @author traitement metier
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "semestre",uniqueConstraints={
        @UniqueConstraint(columnNames = {"annee_academique", "date_debut"})})
public class Semestre extends Securite implements Serializable {

    @Column(name = "date_debut")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebut;

    @Column(name= "date_cloture")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCloture;
/*
    @OneToMany(mappedBy = "semestre",cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE})
    private List <Discipline> disciplines = new ArrayList<>();*/

    @ManyToOne
    @JoinColumn(name = "annee_academique")
    private AnneeAcademique annee_academique;

/*    @OneToMany(mappedBy = "semestre",cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE})
    private List <Enseignement> enseignements;*/

    public Semestre(String libelle, String description, Date dateDebut, Date dateCloture, AnneeAcademique annee_academique) {
        super(libelle, description);
        this.dateDebut = dateDebut;
        this.dateCloture = dateCloture;
        this.annee_academique = annee_academique;
    }

    @Override
    public String getLibelle(){
        return super.getLibelle();
    }

    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),getDateDebut(), getDateCloture(),getAnnee_academique());
    }

    @Override
    public String toString() {
        return super.getDescription();
    }
}
