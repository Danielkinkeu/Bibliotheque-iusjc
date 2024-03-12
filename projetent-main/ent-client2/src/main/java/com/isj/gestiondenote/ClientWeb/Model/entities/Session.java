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

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)


public class Session extends Securite implements Serializable {

    public enum StatutSession {
        ACTIF,NONACTIF
    }


    private Date dateConnection = new Date();


    private Date dateDeconnection = new Date();


    private Utilisateur utilisateur;

    private StatutSession statut;


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
