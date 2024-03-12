package org.isj.ing3.isi.webservice.webservicerest.model.entities;

/**
 * importation des classes
 */


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.isj.ing3.isi.webservice.webservicerest.utils.enumaration.Sexe;
import org.isj.ing3.isi.webservice.webservicerest.utils.enumaration.Statut;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * cette classe crée la table etudiant dans la base de données
 * cette classe herite de la classe CandidatController
 * @author traitement metier
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "etudiant")

public class Etudiant extends Candidat implements Serializable {

    @Column(name = "matricule",unique=true,nullable = false)
    private String matricule;

    @Column(name = "code_authentification",unique=true,nullable = false)
    private String codeAuthentification;

  /*  @OneToMany(mappedBy = "etudiant",cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE})
    private List <Discipline> disciplines = new ArrayList<>();*/

    public Etudiant(String libelle, String description, String nom, String prenom, String email, String telephone, Date dateNaissance, Sexe sexe, Statut statut, String nomDeLaMere, String nomDuPere, String telephoneDeLaMere, String telephoneDuPere, String professionDuPere, String professionDelaMere, String regionOrigine, String ecoleOrigine, Classe classe, String matricule, String codeAuthentification, String lieuNaissance) {
       // super(libelle, description, nom, prenom, email, telephone, dateNaissance, sexe, statut, nomDeLaMere, nomDuPere, telephoneDeLaMere, telephoneDuPere, professionDuPere, professionDelaMere, regionOrigine, ecoleOrigine, classe,lieuNaissance);
        this.matricule = matricule;
        this.codeAuthentification = codeAuthentification;
    }

    @Override
    public String getLibelle(){
        return getMatricule() +"-" + super.getLibelle();
    }

    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getCode(), getMatricule(), getCodeAuthentification());
    }

    @Override
    public String toString() {
        return getNom()+" "+getPrenom()+"-"+getMatricule();
    }

    public String getNomPrenom(){
        return getNom()+" "+getPrenom();
    }
}
