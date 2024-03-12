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
import java.util.Objects;

/**
 * cette classe crée la table annonymat dans la base de données
 * cette classe herite de la classe Securite
 * @author traitement metier
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)

public class Anonymat extends Securite implements Serializable {

    private String numeroAnonymat;


    private Note note;


    private Evaluation evaluation;


    private EstInscrit estInscrit;


    private int numeroTable;

    public Anonymat(String libelle, String description, String numeroAnonymat, Note note, Evaluation evaluation, EstInscrit estInscrit, int numeroTable) {
        super(libelle, description);
        this.numeroAnonymat = numeroAnonymat;
        this.note = note;
        this.evaluation = evaluation;
        this.estInscrit = estInscrit;
        this.numeroTable = numeroTable;
    }

    @Override
    public String getLibelle(){
        return numeroAnonymat +
                "-" + super.getLibelle();
    }

    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNumeroAnonymat(),getNumeroTable(), getNote(), getEstInscrit(), getEvaluation());
    }

    @Override
    public String toString() {
        return ""+ numeroAnonymat+"-"+evaluation.toString();
    }
}
