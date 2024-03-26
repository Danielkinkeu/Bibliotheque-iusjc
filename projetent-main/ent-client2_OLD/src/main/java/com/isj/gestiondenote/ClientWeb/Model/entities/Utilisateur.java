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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * cette classe crée la table utilisateur dans la base de données
 * cette classe herite de la classe Personne
 * @author traitement metier
 */

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur extends Personne implements Serializable {

    public enum Sexe {
        MASCULIN, FEMININ
    }
    public enum Statut {
        ACTIVE, NONACTIVE
    }

    private String login;


    private String motDePasse;


    private List<Role> roles = new ArrayList<>();

    public Utilisateur(String libelle, String description, String nom, String prenom, String email, String telephone, Date dateNaissance, Sexe sexe, Statut statut, String login, String motDePasse) {
        //super(libelle, description, nom, prenom, email, telephone, dateNaissance, sexe, statut);
        this.login = login;
        this.motDePasse = motDePasse;
    }

    public Utilisateur(Long code) {
        super();
        this.setCode(code);
        this.login = login;
        this.motDePasse = motDePasse;
    }

    @Override
    public String getLibelle(){
        return login + "-" + super.getLibelle();
    }

    @Override
    public String toString() {
        return getLogin();
    }

    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getMotDePasse());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Utilisateur other = (Utilisateur) obj;
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.motDePasse, other.motDePasse)) {
            return false;
        }
        return true;
    }
}
