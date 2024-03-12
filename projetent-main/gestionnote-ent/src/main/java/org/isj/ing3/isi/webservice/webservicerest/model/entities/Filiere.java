package org.isj.ing3.isi.webservice.webservicerest.model.entities;
/**
 * importation des classes
 */

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * cette classe crée la table filiere dans la base de données
 * cette classe herite de la classe Securite
 * @author traitement metier
 */

@NoArgsConstructor
@Entity
@Data
@Table(name = "filiere")

public class Filiere extends Securite implements Serializable {

   /* @OneToMany(mappedBy = "filiere",cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE})
    private List<Specialite> specialites = new ArrayList<>();*/

    public Filiere(String libelle, String description) {
        super(libelle, description);
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
    public String toString() {
        return getLibelle();
    }
}
