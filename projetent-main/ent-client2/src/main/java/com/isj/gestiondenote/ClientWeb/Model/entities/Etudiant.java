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
 * cette classe crée la table etudiant dans la base de données
 * cette classe herite de la classe CandidatController
 * @author traitement metier
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)


public class Etudiant extends Candidat implements Serializable {


    private String matricule;


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
}
