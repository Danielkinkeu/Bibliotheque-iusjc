package org.isj.ing3.isi.webservice.webservicerest.model.entities;

/**
 * importation des classes
 */

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.isj.ing3.isi.webservice.webservicerest.utils.enumaration.StatutSession;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * cette classe crée la table Session dans la base de données
 * cette classe herite de la classe Securite
 * @author traitement metier
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "session")

public class Session extends Securite implements Serializable {


    @Column(name = "date_connection")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateConnection = new Date();

    @Column(name = "date_deconnection")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDeconnection = new Date();

    @ManyToOne
    @JoinColumn(name = "utilisateur")
    private Utilisateur utilisateur;

    @Column(name = "statut",nullable = false)
    @Enumerated(EnumType.STRING)
    private StatutSession statut;

    @Column(name="machine_cliente")
    private String machineCliente;

    public Session(String libelle, String description, Date dateConnection, Date dateDeconnection, Utilisateur utilisateur, StatutSession statut, String machineCliente) {
        super(libelle, description);
        this.dateConnection = dateConnection;
        this.dateDeconnection = dateDeconnection;
        this.utilisateur = utilisateur;
        this.statut = statut;
        this.machineCliente = machineCliente;
    }

    public Date getDateConnection() {
        return dateConnection;
    }

    public void setDateConnection(Date dateConnection) {
        this.dateConnection = dateConnection;
    }

    public Date getDateDeconnection() {
        return dateDeconnection;
    }

    public void setDateDeconnection(Date dateDeconnection) {
        this.dateDeconnection = dateDeconnection;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public StatutSession getStatut() {
        return statut;
    }

    public void setStatut(StatutSession statut) {
        this.statut = statut;
    }

    public String getMachineCliente() {
        return machineCliente;
    }

    public void setMachineCliente(String machineCliente) {
        this.machineCliente = machineCliente;
    }

    @Override
    public String getLibelle(){
        return dateConnection + "-" + dateDeconnection + "-" + utilisateur.getLibelle() + "-"+ machineCliente +"-"+statut;
    }

    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDateConnection(), getDateDeconnection(), getUtilisateur(), getStatut(), getMachineCliente());
    }

    @Override
    public String toString() {
        return "Session{" +
                "dateConnection=" + dateConnection +
                ", dateDeconnection=" + dateDeconnection +
                ", utilisateur=" + utilisateur.toString() +
                ", statut=" + statut +
                ", machineCliente='" + machineCliente + '\'' +
                "} " + super.toString();
    }
}
