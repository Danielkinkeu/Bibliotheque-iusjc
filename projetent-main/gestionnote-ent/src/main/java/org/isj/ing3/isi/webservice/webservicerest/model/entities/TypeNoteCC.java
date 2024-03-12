package org.isj.ing3.isi.webservice.webservicerest.model.entities;
/**
 * importation des classes
 */

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Enseignement;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.NoteCC;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Securite;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
@Table(name = "typenotecc",uniqueConstraints={
        @UniqueConstraint(columnNames = {"numero_cc", "enseignement"})})

public class TypeNoteCC extends Securite implements Serializable {

    @ManyToOne
    @JoinColumn(name="enseignement", nullable = false)
    private Enseignement enseignement;

    @Column(name = "numero_cc", nullable = false)
    private int numeroCC;

    @Column(name = "pourcentage_cc", nullable = false)
    private int pourcentageCC;
/*
    @OneToMany(mappedBy = "typeNoteCC",cascade = {CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE})
    private List<NoteCC> noteCCS = new ArrayList<>();*/



    public TypeNoteCC(String libelle, String description, Enseignement enseignement, int numeroCC, int pourcentageCC) {
        super(libelle, description);
        this.enseignement = enseignement;
        this.numeroCC = numeroCC;
        this.pourcentageCC = pourcentageCC;
    }

    public Enseignement getEnseignement() {
        return enseignement;
    }

    public void setEnseignement(Enseignement enseignement) {
        this.enseignement = enseignement;
    }

    public int getNumeroCC() {
        return numeroCC;
    }

    public void setNumeroCC(int numeroCC) {
        this.numeroCC = numeroCC;
    }

    public int getPourcentageCC() {
        return pourcentageCC;
    }

    public void setPourcentageCC(int pourcentageCC) {
        this.pourcentageCC = pourcentageCC;
    }


    @Override
    public void setSignature() {
        setSignature(String.valueOf(hashCode()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),getEnseignement(),getNumeroCC(),getPourcentageCC());
    }

    @Override
    public String toString() {
        return enseignement.toString()+"-"+getNumeroCC()+"-"+getPourcentageCC();
    }

}
