package com.isj.gestiondenote.ClientWeb.Model.modelIntervention;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PiecejointeEmail implements Serializable {

    private Long idPieceJointeEmail;

    // Champ pour stocker le contenu du fichier (comme décrit précédemment)

    private byte[] contenu;


    private String nom;
    private String type;


    private Email email;


    private EmailMessage emailMessage;

    @Override
    public int hashCode() {
        return Objects.hash(idPieceJointeEmail, contenu, nom, type);
    }

}
