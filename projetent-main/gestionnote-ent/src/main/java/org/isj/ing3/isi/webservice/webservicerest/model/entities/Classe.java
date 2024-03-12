package org.isj.ing3.isi.webservice.webservicerest.model.entities;

/**
 * importation des classes
 */

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * cette classe crée la table classe dans la base de données
 * cette classe hérite de la classe Securite
 * @author traitement metier
 */

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "classe",uniqueConstraints={
        @UniqueConstraint(columnNames = {"niveau", "specialite"})
})

public class Classe extends Securite implements Serializable {

  /*  @OneToMany(mappedBy = "classe", fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST})
    private List<Candidat> candidats = new ArrayList<>();*/

    @ManyToOne
    @JoinColumn(name = "niveau")
    private Niveau niveau;

    @ManyToOne
    @JoinColumn(name = "specialite")
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
