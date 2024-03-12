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
 * cette classe crée la table enseignement dans la base de données
 * cette classe herite de la classe Securite
 * @author traitement metier
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)


public class Enseignement extends Securite implements Serializable {


    private double credit;


    private int heuresDeCours;


    private String programmeDeCours;


    private Semestre semestre;


    private UE ue;

   /* @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST})
    @JoinTable(joinColumns = {@JoinColumn(name = "code_enseignement")}, inverseJoinColumns = {@JoinColumn(name = "code_enseignant")})
    private List <Enseignant> enseignants = new ArrayList<>();


    @OneToMany(mappedBy = "enseignement")
    private List<EstInscrit> estInscrits = new ArrayList<>();


    @OneToMany(mappedBy = "enseignement")
    private List<TypeEvaluation> typeEvaluations = new ArrayList<>();*/

    public Enseignement(String libelle, String description, int heuresDeCours, String programmeDeCours, Semestre semestre, UE ue, double credit) {
        super(libelle, description);
        this.heuresDeCours = heuresDeCours;
        this.programmeDeCours = programmeDeCours;
        this.semestre = semestre;
        this.ue = ue;
        this.credit=credit;
    }

/*    public List<TypeEvaluation> getTypeEvaluations() {
        return typeEvaluations;
    }

    public void setTypeEvaluations(List<TypeEvaluation> typeEvaluations) {
        this.typeEvaluations = typeEvaluations;
    }*/


    /*public List<Enseignant> getEnseignants() {
        return enseignants;
    }*/

/*    public void setEnseignants(List<Enseignant> enseignants) {
        this.enseignants = enseignants;
    }

    public List<EstInscrit> getEstInscrits() {
        return estInscrits;
    }

    public void setEstInscrits(List<EstInscrit> estInscrits) {
        this.estInscrits = estInscrits;
    }*/
/*
    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }*/

    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getHeuresDeCours(), getProgrammeDeCours(), getUe());
    }

    @Override
    public String toString() {
        try {
        return getUe().getCodeUE()+"-"+getLibelle()+"-"+getSemestre().getAnnee_academique().getLibelle()+"-"+credit;
        }catch (Exception e) {
            e.printStackTrace();
            try {
                return getLibelle()+"-"+getSemestre().getLibelle();
            }catch (Exception e2) {
                e2.printStackTrace();
                try {
                    return getLibelle();
                }catch (Exception e3) {
                    e3.printStackTrace();
                }
        }
    }
        return "Enseignement inconnu";
}
}
