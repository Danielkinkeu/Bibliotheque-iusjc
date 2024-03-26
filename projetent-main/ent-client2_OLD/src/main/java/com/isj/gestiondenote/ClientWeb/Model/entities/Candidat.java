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
 * cette classe crée la table candidat dans la base de données
 * cette classe herite de la classe Personne
 * @author traitement metier
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)


public class Candidat extends Personne implements Serializable {


    private String nomDeLaMere;


    private String nomDuPere;


    private String telephoneDeLaMere;


    private String telephoneDuPere;


    private String professionDuPere;


    private String professionDelaMere;


    private String regionOrigine;


    private String ecoleOrigine;


    private String lieuNaissance;


    private Classe classe;

   /* @OneToMany(mappedBy = "candidat",cascade = {CascadeType.PERSIST})
    private List <EnvoiMessage> envoiMessages = new ArrayList<>();

    @OneToMany(mappedBy = "candidatInscrit",cascade = {CascadeType.PERSIST})
    private List <EstInscrit> estInscrits = new ArrayList<>();*/

    public Candidat(String libelle, String description, String nom, String prenom, String email, String telephone, Date dateNaissance, Sexe sexe, Statut statut, String nomDeLaMere, String nomDuPere, String telephoneDeLaMere, String telephoneDuPere, String professionDuPere, String professionDelaMere, String regionOrigine, String ecoleOrigine, Classe classe,
                    String lieuNaissance) {
        super(libelle, description, nom, prenom, email, telephone, dateNaissance, sexe, statut);
        this.nomDeLaMere = nomDeLaMere;
        this.nomDuPere = nomDuPere;
        this.telephoneDeLaMere = telephoneDeLaMere;
        this.telephoneDuPere = telephoneDuPere;
        this.professionDuPere = professionDuPere;
        this.professionDelaMere = professionDelaMere;
        this.regionOrigine = regionOrigine;
        this.ecoleOrigine = ecoleOrigine;
        this.classe = classe;
        this.lieuNaissance=lieuNaissance;
    }

    @Override
    public String getLibelle(){
        return super.getLibelle()+ "-" + classe.getLibelle() + "-" + nomDeLaMere + "-"
                + telephoneDeLaMere + "-"+ professionDelaMere + "-" + nomDuPere + "-"
                + telephoneDuPere + "-" + professionDuPere + "-" +regionOrigine+ "-"+ecoleOrigine;
    }
    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNomDeLaMere(), getNomDuPere(), getTelephoneDeLaMere(), getTelephoneDuPere(), getProfessionDuPere(), getProfessionDelaMere(), getRegionOrigine(), getEcoleOrigine(), getClasse(), getNom());
    }

    @Override
    public String toString() {
        System.out.println("NOm:"+getNom());
        System.out.println("Prenom:"+getPrenom());
        return getNom() +" "+ getPrenom();
    }
}

