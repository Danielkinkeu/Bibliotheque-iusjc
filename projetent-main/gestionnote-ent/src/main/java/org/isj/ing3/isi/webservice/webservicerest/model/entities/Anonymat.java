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
import java.util.Objects;

/**
 * cette classe crée la table annonymat dans la base de données
 * cette classe herite de la classe Securite
 * @author traitement metier
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "anonymat",uniqueConstraints={
        @UniqueConstraint(columnNames = {"est_inscrit", "evaluation"})
})

public class Anonymat extends Securite implements Serializable {

    @Column(name = "numero_anonymat")
    private String numeroAnonymat;

    @OneToOne
    @JoinColumn(name = "note")
    private Note note;

    @ManyToOne
    @JoinColumn(name = "evaluation")
    private Evaluation evaluation;

    @OneToOne
    @JoinColumn(name = "est_inscrit")
    private EstInscrit estInscrit;

    @Column(name = "numero_table")
    private int numeroTable;

    public Anonymat(String libelle, String description, String numeroAnonymat, Note note, Evaluation evaluation, EstInscrit estInscrit, int numeroTable) {
        super(libelle, description);
        this.numeroAnonymat = numeroAnonymat;
        this.note = note;
        this.evaluation = evaluation;
        this.estInscrit = estInscrit;
        this.numeroTable = numeroTable;
    }

    @Override
    public String getLibelle(){
        return numeroAnonymat +
                "-" + super.getLibelle();
    }

    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNumeroAnonymat(),getNumeroTable(), getNote(), getEstInscrit(), getEvaluation());
    }

    @Override
    public String toString() {
        return ""+ numeroAnonymat+"-"+evaluation.toString();
    }
}
