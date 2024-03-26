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
 * cette classe crée la table historique_note dans la base de données
 * cette classe herite de la classe Securite
 * @author traitement metier
 */

@NoArgsConstructor
@Data

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)

public class HistoriqueNote extends Securite implements Serializable {


    private double valeurNote;


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
