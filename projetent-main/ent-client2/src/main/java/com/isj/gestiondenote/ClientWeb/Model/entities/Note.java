package com.isj.gestiondenote.ClientWeb.Model.entities;
/**
 * importation des classes
 */

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * cette classe crée la table note dans la base de données
 * cette classe herite de la classe Securite
 * @author traitement metier
 */

@NoArgsConstructor
@Data

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)

public class Note extends Securite implements Serializable {


    private double valeurNote;


    private int numeroTable;

   /* @OneToMany(mappedBy = "note",cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE})
    private List <HistoriqueNote> historiqueNotes = new ArrayList<>();*/

    private Anonymat anonymat;

    private EstInscrit estInscrit;


    private Evaluation evaluation;

    public Note(String libelle, String description, double valeurNote, int numeroTable, Anonymat anonymat, EstInscrit estInscrit, Evaluation evaluation) {
        super(libelle, description);
        this.valeurNote = valeurNote;
        this.numeroTable = numeroTable;
        this.anonymat = anonymat;
        this.estInscrit = estInscrit;
        this.evaluation = evaluation;
    }
    /*@Override
    public String getLibelle(){
        return evaluation.getLibelle() + "-" + valeurNote + "-" + numeroTable + "-"+
                estInscrit.getLibelle()+"-"+anonymat;
    }*/
    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getValeurNote(), getEvaluation(), getNumeroTable(), getEstInscrit());
    }

    @Override
    public String toString() {
        return valeurNote+"";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        //if (!super.equals(o)) return false;
        Note note = (Note) o;
        return Double.compare(note.valeurNote, valeurNote) == 0 &&
                numeroTable == note.numeroTable &&
                Objects.equals(anonymat, note.anonymat) &&
                Objects.equals(estInscrit,note.estInscrit) &&
                Objects.equals(evaluation, note.evaluation);
    }
}
