package org.isj.ing3.isi.webservice.webservicerest.model.entities;

/**
 * importation des classes
 */

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * cette classe crée la table Role dans la base de données
 * cette classe herite de la classe Securite
 * @author traitement metier
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@XmlRootElement
@Table(name = "role")

public class Role extends Securite implements Serializable {

    @OneToMany(cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    private List <Droit> droits = new ArrayList<>();

    @JsonBackReference
    @JsonIgnoreProperties(ignoreUnknown = true, value = {"roles"})
    //@ManyToMany(mappedBy = "roles",cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    @ManyToMany(cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinTable(name="utilisateur_roles", joinColumns = {@JoinColumn(name = "code_role")}, inverseJoinColumns = {@JoinColumn(name = "code_utilisateur")})
    private List <Utilisateur> utilisateurs = new ArrayList<>();

    public Role(String libelle, String description) {

        super(libelle, description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Role role = (Role) o;
        return getDroits().equals(role.getDroits()) &&
                getUtilisateurs().equals(role.getUtilisateurs());
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
