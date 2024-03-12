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
 * cette classe crée la table note dans la base de données
 * cette classe herite de la classe Securite
 * @author traitement metier
 */

@AllArgsConstructor
@NoArgsConstructor
@Data

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)

public class NoteCC extends Securite implements Serializable {

    private Candidat candidat;

    private TypeNoteCC typeNoteCC;

    private double valeurNote;



    public NoteCC(String libelle, String description, Candidat candidat, TypeNoteCC typeNoteCC, double valeurNote) {
        super(libelle, description);
        this.candidat = candidat;
        this.typeNoteCC = typeNoteCC;
        this.valeurNote = valeurNote;
    }

    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getValeurNote(), getCandidat(),getTypeNoteCC());
    }

    @Override
    public String toString() {
        return typeNoteCC.toString()+"-"+valeurNote+"";
    }

}
