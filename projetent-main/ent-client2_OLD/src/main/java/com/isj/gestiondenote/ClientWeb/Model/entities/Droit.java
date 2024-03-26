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
 * cette classe crée la table droit dans la base de données
 * cette classe hérite de la classe Securite
 * @author traitement metier
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)

public class Droit extends Securite implements Serializable {


    private String categorie;


    private boolean lecture;

    private boolean ecriture;

    private boolean modification;

    private boolean suppression;

    private Role role;

    public Droit(String libelle, String description, String categorie, boolean lecture, boolean ecriture, boolean modification, boolean suppression, Role role) {
        super(libelle, description);
        this.categorie = categorie;
        this.lecture = lecture;
        this.ecriture = ecriture;
        this.modification = modification;
        this.suppression = suppression;
        this.role = role;
    }

    @Override
    public String getLibelle(){
        return categorie+"-"+ suppression+"-"+modification+"-"+lecture
                +"-"+lecture+"-"+ecriture+ "-" + role.getLibelle();
    }

    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCategorie(), isLecture(), isEcriture(), isModification(), isSuppression(), getRole());
    }

    @Override
    public String toString() {
        return "DroitFacade{" +
                "categorie='" + categorie + '\'' +
                ", lecture=" + lecture +
                ", ecriture=" + ecriture +
                ", modification=" + modification +
                ", suppression=" + suppression +
                ", role=" + role.getCode() +
                "} " ;
                //+ super.toString();
    }
}
