package org.isj.ing3.isi.webservice.webservicerest.model.entities;

/**
 * importation des classes
 */

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * cette classe crée la table envoi_message dans la base de données
 * cette classe herite de la classe Securite
 * @author traitement metier
 */

@Entity
@Data
@NoArgsConstructor
@Table(name = "envoi_message")

public class EnvoiMessage extends Securite implements Serializable {

    @Column(name = "date_envoi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnvoi;


    public enum Statut{
        SUCCES,ECHEC
    }

    @Column(name="statut")
    @Enumerated(EnumType.STRING)
    private Statut statut;

    @OneToOne
    @JoinColumn(name="message")
    private Message message;

    @ManyToOne
    @JoinColumn(name = "candidat")
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
