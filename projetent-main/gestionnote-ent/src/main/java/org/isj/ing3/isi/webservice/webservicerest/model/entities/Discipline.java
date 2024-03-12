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
import java.util.Date;
import java.util.Objects;

/**
 * cette classe crée la table discipline dans la base de donnée
 * cette classe herite de la classe Securite
 * @author traitement metier
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "discipline",uniqueConstraints={
        @UniqueConstraint(columnNames = {"etudiant", "semestre"})
})

public class Discipline extends Securite implements Serializable {

    @ManyToOne
    @JoinColumn(name="etudiant")
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "semestre")
    private Semestre semestre;

    @Column(name="nb_heures_absences")
    private int nbHeures;

    @Column(name="nb_retards")
    private int nbRetards;

    @Column(name="date_retard")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRetard;

    @Column(name="heure_justifie")
    private int heureJustifie;



    public Discipline(String libelle, String description, Etudiant etudiant, Semestre semestre, int nbHeures, int nbRetards, Date dateRetard, int heureJustifie) {
        super(libelle, description);
        this.etudiant = etudiant;
        this.semestre = semestre;
        this.nbHeures = nbHeures;
        this.nbRetards = nbRetards;
        this.dateRetard = dateRetard;
        this.heureJustifie = heureJustifie;
    }

    @Override
    public String getLibelle(){
        if(etudiant!=null)
        return etudiant.getNom()+"-"+ etudiant.getPrenom()+"-"+nbHeures+ "-" +nbRetards+ "-" +dateRetard;
        return "";
    }

    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getEtudiant(), getSemestre(), getNbHeures(), getNbRetards(), getDateRetard(), getHeureJustifie());
    }

    @Override
    public String toString() {
        return "Discipline{" +
                "etudiant=" + etudiant.toString() +
                ", semestre=" + semestre.toString() +
                ", nbHeures=" + nbHeures +
                ", nbRetards=" + nbRetards +
                ", dateRetards=" + dateRetard +
                ", heureJustifie=" + heureJustifie +
                "} " ;
    }
}
