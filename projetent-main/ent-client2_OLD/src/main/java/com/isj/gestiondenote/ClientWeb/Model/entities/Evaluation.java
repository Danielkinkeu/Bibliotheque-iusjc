package com.isj.gestiondenote.ClientWeb.Model.entities;

/**
 * importation des classes
 */

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * cette classe crée la table evaluation dans la base de données
 * cette classe herite de la classe Securite
 * @author traitement metier
 */
@Data
@NoArgsConstructor

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)

public class Evaluation extends Securite implements Serializable {


    private Date dateEvaluation;

    public enum Statut {
        ACTIVE, NONACTIVE
    }



    public Statut statut;

    private TypeEvaluation typeEvaluation;

/*    @OneToMany(mappedBy = "evaluation",cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE})
    private List <Anonymat> anonymats = new ArrayList<>();

    @OneToMany(mappedBy = "evaluation",cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE})
    private List <Note> notes = new ArrayList<>();*/

    public Evaluation(String libelle, String description, Date dateEvaluation, Statut statut, TypeEvaluation typeEvaluation) {
        super(libelle, description);
        this.dateEvaluation = dateEvaluation;
        this.statut = statut;
        this.typeEvaluation = typeEvaluation;
    }

    public Date getDateEval() {
        return dateEvaluation;
    }



    /*@Override
    public String getLibelle(){
        return dateEvaluation +"-"+ typeEvaluation.getLibelle()+"-"+statut;
    }*/
    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDateEval(), getStatut(), getTypeEvaluation());
    }

    @Override
    public String toString() {
        return getTypeEvaluation().getLibelle()+" - "+getTypeEvaluation().getEnseignement().getUe();
    }
}
