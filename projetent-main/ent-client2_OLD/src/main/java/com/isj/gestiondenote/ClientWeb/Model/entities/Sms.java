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
 * cette classe crée la table Sms dans la base de données
 * cette classe herite de la classe message
 * @author traitement metier
 */


@NoArgsConstructor
@Data

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)


public class Sms extends Message implements Serializable {

    @Override
    public String toString() {
        return "Sms{} " + super.toString();
    }

    public Sms(String libelle, String description, String contenu, String destinataire, String emetteur) {
        super(libelle, description, contenu, destinataire, emetteur);
    }

    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }

    @Override
    public String getLibelle(){
        return "Sms";
    }

}
