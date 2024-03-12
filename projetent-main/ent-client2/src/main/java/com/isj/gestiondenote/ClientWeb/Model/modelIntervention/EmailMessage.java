package com.isj.gestiondenote.ClientWeb.Model.modelIntervention;

import lombok.*;

import java.io.File;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailMessage implements Serializable {

    private Long idEmail;

    private String content;

    private String subject;

    private String email;

    private File pieceJointe;


    private Set<PiecejointeEmail> piecejointeEmails = new HashSet<>();
}
