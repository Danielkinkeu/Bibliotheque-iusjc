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
import javax.tools.Diagnostic;
import java.io.Serializable;
import java.util.Objects;

/**
 * cette classe crée la table droit dans la base de données
 * cette classe hérite de la classe Securite
 * @author traitement metier
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "droit")

public class Droit extends Securite implements Serializable {

    @Column(name = "categorie", nullable = false)
    private String categorie;

    @Column(name = "lecture")
    private boolean lecture;

    @Column(name = "ecriture")
    private boolean ecriture;

    @Column(name = "modification")
    private boolean modification;

    @Column(name = "suppression", nullable = true)
    private boolean suppression;

/*    @ManyToOne
    @JoinColumn(name = "role")
    private Role role;*/

    public Droit(String libelle, String description, String categorie, boolean lecture, boolean ecriture, boolean modification, boolean suppression, Role role) {
        super(libelle, description);
        this.categorie = categorie;
        this.lecture = lecture;
        this.ecriture = ecriture;
        this.modification = modification;
        this.suppression = suppression;
        //this.role = role;
    }

    @Override
    public String getLibelle(){
        return categorie+"-"+ suppression+"-"+modification+"-"+lecture
                +"-"+lecture+"-"+ecriture;
    }

    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCategorie(), isLecture(), isEcriture(), isModification(), isSuppression());
    }

    @Override
    public String toString() {
        return "DroitFacade{" +
                "categorie='" + categorie + '\'' +
                ", lecture=" + lecture +
                ", ecriture=" + ecriture +
                ", modification=" + modification +
                ", suppression=" + suppression +
                "} " ;
                //+ super.toString();
    }

    public Role getRole() {
        return null;
    }

    public void setRole(Role role) {
    }
}
