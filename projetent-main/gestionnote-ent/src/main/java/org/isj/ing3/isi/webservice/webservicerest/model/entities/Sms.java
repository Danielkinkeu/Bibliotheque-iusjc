package org.isj.ing3.isi.webservice.webservicerest.model.entities;

/**
 * importation des classes
 */
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Message;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * cette classe crée la table Sms dans la base de données
 * cette classe herite de la classe message
 * @author traitement metier
 */


@NoArgsConstructor
@Data
@Entity
@Table(name = "sms")
public class Sms extends Message implements Serializable {

    @Override
    public String toString() {
        return "Sms{} " + super.toString();
    }

    public Sms(String libelle, String description, String contenu, String destinataire, String emetteur) {
        super(libelle, description, contenu, destinataire, emetteur);
    }

    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }

    @Override
    public String getLibelle(){
        return "Sms";
    }

}
