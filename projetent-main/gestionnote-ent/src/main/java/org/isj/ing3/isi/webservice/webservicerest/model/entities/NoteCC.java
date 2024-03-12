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
 * cette classe crée la table note dans la base de données
 * cette classe herite de la classe Securite
 * @author traitement metier
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "notecc",uniqueConstraints={
        @UniqueConstraint(columnNames = {"candidat","type_note_cc"})})

public class NoteCC extends Securite implements Serializable {

    @ManyToOne
    @JoinColumn(name = "candidat", nullable = false)
    private Candidat candidat;

    @ManyToOne
    @JoinColumn(name = "type_note_cc", nullable = false)
    private TypeNoteCC typeNoteCC;

    @Column(name = "valeur_note", nullable = false)
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
