package com.isj.gestiondenote.ClientWeb.Model.modelIntervention;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PieceJointe implements Serializable {

    private Long idPieceJointe;
    private byte[] contenu;
    private String nom;
    private String type;
    @JsonIgnore
    private Intervention intervention;

    @Override
    public int hashCode() {
        return Objects.hash(idPieceJointe, contenu, nom, type);
    }

}
