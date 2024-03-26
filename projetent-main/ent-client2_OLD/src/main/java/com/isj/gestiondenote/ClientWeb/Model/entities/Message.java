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
 * cette classe crée la table message dans la base de données
 * cette classe herite de la classe Securite
 * @author traitement metier
 */

@NoArgsConstructor
@Data

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)

public class Message extends Securite implements Serializable{


    private String contenu;

    private String destinataire;


    private String emetteur;

    private EnvoiMessage envoiMessage;

    public Message(String libelle, String description, String contenu, String destinataire, String emetteur) {
        super(libelle, description);
        this.contenu = contenu;
        this.destinataire = destinataire;
        this.emetteur = emetteur;
    }


    /*
    @Override
    public String getLibelle(){
        return "Message";
    }
    */
    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getContenu(), getDestinataire(), getEmetteur());
    }

    @Override
    public String toString() {
        return "Message{" +
                "contenu='" + contenu + '\'' +
                ", destinataire='" + destinataire + '\'' +
                ", emetteur='" + emetteur + '\'' +
                "} ";
    }
}
