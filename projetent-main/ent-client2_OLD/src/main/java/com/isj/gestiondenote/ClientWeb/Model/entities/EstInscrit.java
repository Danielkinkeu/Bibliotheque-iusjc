package com.isj.gestiondenote.ClientWeb.Model.entities;

/**
 * importation des classes
 */

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * cette classe crée la table est_inscrit dans la base de données
 * cette classe herite de la classe Securite
 * @author traitement metier
 */


@Data

@AllArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
public class EstInscrit extends Securite implements Serializable {

    public EstInscrit() {

    }
    public EstInscrit(Candidat candidat) {
        this.candidatInscrit=candidat;
    }

    public enum Statut {
       VALIDE, NONVALIDE
   }


    private Statut statut;

    private Candidat candidatInscrit;

    /*@OneToOne(mappedBy = "estInscrit")
    private Note note;

    @OneToOne(mappedBy = "estInscrit")
    private Anonymat anonymat;*/


    private Enseignement enseignement;


    public EstInscrit(String libelle, String description, Statut statut, Candidat candidatInscrit, Enseignement enseignement) {
        super(libelle, description);
        this.statut = statut;
        this.candidatInscrit = candidatInscrit;
        this.enseignement = enseignement;
    }



    /*public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }*/



    /*@Override
    public String getLibelle(){
        return candidatInscrit.getLibelle() +"-" +enseignement.getLibelle()+ "-"+statut
                +"-"+ note.getLibelle();
                 {
        return Objects.hash(super.hashCode(), getNiveau(), getSpecialite());
    }*/
    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCode(), getEnseignement(), getCandidatInscrit());
    }

    @Override
    public String toString() {
        //System.out.println("candidatInscrit:"+candidatInscrit.toString());
        //System.out.println("enseignement:"+enseignement.toString());
        return (candidatInscrit!=null?(candidatInscrit.getNom()+" "+ candidatInscrit.getPrenom()):"") +(enseignement!=null? ("-"+enseignement.toString()):"");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstInscrit that = (EstInscrit) o;
        return Objects.equals(candidatInscrit, that.candidatInscrit) &&
                Objects.equals(enseignement, that.enseignement);
    }
}
