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
 * cette classe crée la table UE dans la base de données
 * cette classe herite de la classe securite
 * @author traitement metier
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name ="ue")
public class  UE extends Securite implements Serializable {
    public enum Statut {
        ACTIVE, NONACTIVE
    }

    @Column(name = "code_ue",unique = true)
    private String codeUE;

    @Column(name = "statut",nullable = false)
    @Enumerated(EnumType.STRING)
    private Statut statut;

    @Column(name= "credits")
    private double credits;

    @ManyToOne
    @JoinColumn(name = "module")
    private Module module;

    @ManyToOne
    @JoinColumn(name = "niveau")
    private Niveau niveau;

    @ManyToOne
    @JoinColumn(name = "specialite")
    private Specialite specialite;
/*
    @OneToMany(mappedBy = "ue",cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE})
    private List <Enseignement> enseignements=new ArrayList<>();*/

    public UE(String libelle, String description, String codeUE, Statut statut, double credits, Module module, Niveau niveau, Specialite specialite) {
        super(libelle, description);
        this.codeUE = codeUE;
        this.statut = statut;
        this.credits = credits;
        this.module = module;
        this.niveau = niveau;
        this.specialite = specialite;
    }


    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    public String getCodeUE() {
        return codeUE;
    }

    public void setCodeUE(String code_ue) {
        this.codeUE = code_ue;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public Specialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }

    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCodeUE(), getStatut(),getCredits(), getModule(), getNiveau(), getSpecialite());
    }


    @Override
    public String toString() {
        return codeUE+" - "+getLibelle() +" - "+niveau+" - "+this.specialite;


    }
}
