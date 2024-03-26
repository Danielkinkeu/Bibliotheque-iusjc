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
 * cette classe crée la table note dans la base de données
 * cette classe herite de la classe Securite
 * @author traitement metier
 */

@AllArgsConstructor
@NoArgsConstructor
@Data

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)

public class TypeNoteCC extends Securite implements Serializable {


    private Enseignement enseignement;

    private int numeroCC;

    private int pourcentageCC;
/*
    @OneToMany(mappedBy = "typeNoteCC",cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE})
    private List<NoteCC> noteCCS = new ArrayList<>();*/



    public TypeNoteCC(String libelle, String description, Enseignement enseignement, int numeroCC, int pourcentageCC) {
        super(libelle, description);
        this.enseignement = enseignement;
        this.numeroCC = numeroCC;
        this.pourcentageCC = pourcentageCC;
    }

    public Enseignement getEnseignement() {
        return enseignement;
    }

    public void setEnseignement(Enseignement enseignement) {
        this.enseignement = enseignement;
    }

    public int getNumeroCC() {
        return numeroCC;
    }

    public void setNumeroCC(int numeroCC) {
        this.numeroCC = numeroCC;
    }

    public int getPourcentageCC() {
        return pourcentageCC;
    }

    public void setPourcentageCC(int pourcentageCC) {
        this.pourcentageCC = pourcentageCC;
    }


    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),getEnseignement(),getNumeroCC(),getPourcentageCC());
    }

    @Override
    public String toString() {
        return enseignement.toString()+"-"+getNumeroCC()+"-"+getPourcentageCC();
    }

}
