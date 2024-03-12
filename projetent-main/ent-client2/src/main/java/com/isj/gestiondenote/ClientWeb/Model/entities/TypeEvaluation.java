package com.isj.gestiondenote.ClientWeb.Model.entities;


/**
 * importation des classes
 */

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * cette classe crée la table TypeEvaluation dans la base de données
 * cette classe herite de la classe Securite
 * @author traitement metier
 */

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
@Data
@NoArgsConstructor

public class TypeEvaluation extends Securite implements Serializable {


    private float pourcentage;

    private Enseignement enseignement;
/*
    @OneToMany(mappedBy = "typeEvaluation",cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE})
    private List <Evaluation> evaluations = new ArrayList<>();*/

    public TypeEvaluation(String libelle, String description, float pourcentage, Enseignement enseignement) {
        super(libelle, description);
        this.pourcentage = pourcentage;
        this.enseignement = enseignement;
    }



    /*@Override
    public String getLibelle(){
        return enseignement.getLibelle() + "-" + pourcentage;
    }*/
    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPourcentage(), getEnseignement());
    }

    @Override
    public String toString() {
        return getLibelle()+" - "+getPourcentage()+"% - "+getEnseignement();
    }
}

