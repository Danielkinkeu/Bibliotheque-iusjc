package org.isj.ing3.isi.webservice.webservicerest.model.entities;

/**
 * importation des classes
 */

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * cette classe crée la table historique_note dans la base de données
 * cette classe herite de la classe Securite
 * @author traitement metier
 */

@NoArgsConstructor
@Data
@Entity
@Table(name = "historique_note")

public class HistoriqueNote extends Securite implements Serializable {

    @Column(name = "valeur_note", nullable = false)
    private double valeurNote;

    @ManyToOne
    @JoinColumn(name = "note")
    private Note note;

    public HistoriqueNote(String libelle, String description, double valeurNote, Note note) {
        super(libelle, description);
        this.valeurNote = valeurNote;
        this.note = note;
    }

    @Override
    public String getLibelle(){
        return note.getLibelle() + "-" + valeurNote;
    }

    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getNote(), getValeurNote());
    }

    @Override
    public String toString() {
        return "HistoriqueNote{" +
                ", note=" + note.toString() +
                ", valeurNote=" + valeurNote +
                "} " + super.toString();
    }
}
