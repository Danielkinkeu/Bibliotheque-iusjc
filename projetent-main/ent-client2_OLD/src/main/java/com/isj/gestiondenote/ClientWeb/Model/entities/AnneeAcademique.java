package com.isj.gestiondenote.ClientWeb.Model.entities;
/**
 * importation des classes
 */

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * cette classe crée la table annee_academique dans la base de données
 * cette classe herite de la classe Securite
 * @author traitement metier
 */

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AnneeAcademique extends Securite implements Serializable {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDebut;

    private Date dateCloture;



    /*@OneToMany(mappedBy = "anneeAcademique",cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE})
    private List <Semestre> semestres = new ArrayList<>();*/

    public AnneeAcademique(String libelle, String description, Date dateDebut, Date dateCloture) {
        super(libelle, description);
        this.dateDebut = dateDebut;
        this.dateCloture = dateCloture;

    }


    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDateDebut(), getDateCloture());
    }

    @Override
    public String toString() {

        //return getDescription();
        //return getLibelle();
        return (getDateDebut().getYear()+1900)+"/"+(getDateCloture().getYear()+1900);
    }
}
