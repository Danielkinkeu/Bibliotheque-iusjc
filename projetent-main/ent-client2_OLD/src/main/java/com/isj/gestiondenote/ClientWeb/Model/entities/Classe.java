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
 * cette classe crée la table classe dans la base de données
 * cette classe hérite de la classe Securite
 * @author traitement metier
 */

@Data

@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)


public class Classe extends Securite implements Serializable {

    /*@OneToMany(mappedBy = "classe", fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST})
    private List<Candidat> candidats = new ArrayList<>();*/


    private Niveau niveau;


    private Specialite specialite;

    public Classe(String libelle, String description, Niveau niveau, Specialite specialite) {
        super(libelle, description);
        this.niveau = niveau;
        this.specialite = specialite;
    }

    /**
     * creation d'une methode getLibelle
     */
    @Override
    public String getLibelle(){
        return super.getLibelle();
    }

    @Override
    public String getDescription(){
        return niveau.getDescription()+" - "+specialite.getLibelle();
    }

    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    /**
     * creation d'une methode hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNiveau(), getSpecialite());
    }

    /**
     * creation d'une methode toString
     */
    @Override
    public String toString() {
        return getLibelle()+" - "+specialite.getLibelle();
    }
}
