package org.isj.ing3.isi.webservice.webservicerest.model.entities;

/**
 * importation des classes
 */

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.isj.ing3.isi.webservice.webservicerest.utils.enumaration.Sexe;
import org.isj.ing3.isi.webservice.webservicerest.utils.enumaration.Statut;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * cette classe crée la table utilisateur dans la base de données
 * cette classe herite de la classe Personne
 * @author traitement metier
 */

@Entity
@Table(name = "utilisateur")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur extends Personne2 implements Serializable {


    @Column(name = "login", nullable = false, unique=true)
    private String login;

    @Column(name = "mot_de_passe", nullable = false)
    private String motDePasse;

/*    @JsonManagedReference
    @JsonIgnoreProperties(ignoreUnknown = true, value = {"utilisateurs"})
    @ManyToMany(cascade={  CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST},fetch = FetchType.LAZY)
    @JoinTable( joinColumns = {@JoinColumn(name = "code_utilisateur")}, inverseJoinColumns = {@JoinColumn(name = "code_role")})
    private List<Role> roles = new ArrayList<>();*/

    public Utilisateur(String libelle, String description, String nom, String prenom, String email, String telephone, Date dateNaissance, Sexe sexe, Statut statut, String login, String motDePasse) {
        super(libelle, description, nom, prenom, email, telephone, dateNaissance, sexe, statut);
        this.login = login;
        this.motDePasse = motDePasse;
    }

    @Override
    public String getLibelle(){
        return login ;
    }

    @Override
    public String toString() {
        return getLogin();
    }

    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getMotDePasse());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Utilisateur other = (Utilisateur) obj;
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.motDePasse, other.motDePasse)) {
            return false;
        }
        return true;
    }

    public List<Role> getRoles() {
        return new ArrayList<Role>();
    }

    public void setRoles(List<Role> roles) {
    }
}
