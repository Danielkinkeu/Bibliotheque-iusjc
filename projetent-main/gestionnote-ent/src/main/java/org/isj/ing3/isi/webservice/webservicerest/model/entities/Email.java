package org.isj.ing3.isi.webservice.webservicerest.model.entities;

/**
 * importation des classes
 */

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * cette classe crée la table email dans la base de données
 * cette classe herite de la classe Message
 * @author traitement metier
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "email")

public class Email extends Message implements Serializable {

    @Column(name = "objet")
    private String objet;

    public Email(String libelle, String description, String contenu, String destinataire, String emetteur, String objet) {
        super(libelle, description, contenu, destinataire, emetteur);
        this.objet = objet;
    }

    @Override
    public String getLibelle(){
        return "Email";
    }

    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getObjet());
    }

    @Override
    public String toString() {
        return getObjet();
    }
}
