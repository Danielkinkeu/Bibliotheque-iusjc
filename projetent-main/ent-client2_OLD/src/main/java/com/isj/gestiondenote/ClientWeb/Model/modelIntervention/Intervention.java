package com.isj.gestiondenote.ClientWeb.Model.modelIntervention;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Intervention{


    private Long idIntervention;


    private Date dateCreationInter;


    private Statut statut;


    private String description;

    private Integer nomDepartement;

    private Integer motif;

    private String file;



    private Etudiant etudiant;



    @JsonIgnore
    private Personnel personnel;



    private Departement departement;


    private Categorie categorie;



    private SousIntervention sousIntervention;



    private Set<PieceJointe> piecesJointes = new HashSet<>();

    @Override
    public int hashCode() {
        return Objects.hash(idIntervention, dateCreationInter, statut, description, nomDepartement, motif, file);
    }

}
