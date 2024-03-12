package com.isj.gestiondenote.ClientWeb.Model.modelIntervention;

import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email implements Serializable {

    private Long idEmail;

    private String content;
    private String subject;
    private String email;


    private Set<PiecejointeEmail> piecejointeEmails = new HashSet<>();
}
