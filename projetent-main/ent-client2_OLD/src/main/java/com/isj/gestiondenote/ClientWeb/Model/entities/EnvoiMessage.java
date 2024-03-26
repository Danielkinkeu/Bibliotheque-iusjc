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
 * cette classe crée la table envoi_message dans la base de données
 * cette classe herite de la classe Securite
 * @author traitement metier
 */

@Data
@NoArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)

public class EnvoiMessage extends Securite implements Serializable {


    private Date dateEnvoi;


    public enum Statut{
        SUCCES,ECHEC
    }


    private Statut statut;


    private Message message;


    private Candidat candidat;

    /*@Override
    public String getLibelle(){
        return candidat.getLibelle() +"-" + dateEnvoi +"-" + message.getLibelle();
    }*/

    public EnvoiMessage(String libelle, String description, Date dateEnvoi, Statut statut, Message message, Candidat candidat) {
        super(libelle, description);
        this.dateEnvoi = dateEnvoi;
        this.statut = statut;
        this.message = message;
        this.candidat = candidat;
    }



    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDateEnvoi(), getStatut(), getMessage(), getCandidat());
    }

    @Override
    public String toString() {
        return "EnvoiMessageFacade{" +
                "dateEnvoi=" + dateEnvoi +
                ", statut=" + statut +
                ", message=" + message.toString() +
                ", candidat=" + candidat.toString() +
                "}" ;
    }
}
