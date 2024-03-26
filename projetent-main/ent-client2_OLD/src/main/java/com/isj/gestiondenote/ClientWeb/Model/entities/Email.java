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
 * cette classe crée la table email dans la base de données
 * cette classe herite de la classe Message
 * @author traitement metier
 */

@Data
@NoArgsConstructor
@AllArgsConstructor

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)

public class Email extends Message implements Serializable {


    private String objet;

    public Email(String libelle, String description, String contenu, String destinataire, String emetteur, String objet) {
        super(libelle, description, contenu, destinataire, emetteur);
        this.objet = objet;
    }

    @Override
    public String getLibelle(){
        return "Email";
    }

    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getObjet());
    }

    @Override
    public String toString() {
        return getObjet();
    }
}
