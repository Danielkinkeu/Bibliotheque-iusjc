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
 * cette classe crée la table enseignant dans la base de données
 * cette classe herite de la classe Personne
 * @author traitement metier
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)

public class Enseignant extends Personne implements Serializable {


    private String qualification;

   /* @ManyToMany(mappedBy = "enseignants",fetch = FetchType.LAZY,cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE})
    private List <Enseignement> enseignements = new ArrayList<>();
*/
    public Enseignant(String libelle, String description, String nom, String prenom, String email, String telephone, Date dateNaissance, Sexe sexe, Statut statut, String qualification) {
        super(libelle, description, nom, prenom, email, telephone, dateNaissance, sexe, statut);
        this.qualification = qualification;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public void setCreateur(Utilisateur utilisateur){
        super.setCreateur(utilisateur);
    }

    public void setModificateur(Utilisateur utilisateur){
        super.setModificateur(utilisateur);
    }

    @Override
    public String getLibelle(){
        return super.getLibelle() + "-" + qualification;
    }

    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getQualification());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
