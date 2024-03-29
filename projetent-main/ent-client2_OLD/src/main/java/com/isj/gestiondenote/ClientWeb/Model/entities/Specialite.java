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
 * cette classe crée la table Specialite dans la base de données
 * cette classe herite de la classe Securite
 * @author traitement metier
 */

@AllArgsConstructor
@NoArgsConstructor
@Data

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)


public class Specialite extends Securite implements Serializable {

/*    @OneToMany(mappedBy = "specialite",cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE})
    private List <UE> ues = new ArrayList<>();

    @OneToMany(mappedBy = "specialite",cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE})
    private List <Classe> classes = new ArrayList<>();*/


    private Filiere filiere;

    public Specialite(String libelle, String description, Filiere filiere) {
        super(libelle, description);
        this.filiere = filiere;
    }

    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),getFiliere());
    }

    @Override
    public String toString() {
        return getLibelle()+" - "+getFiliere();
    }


}
