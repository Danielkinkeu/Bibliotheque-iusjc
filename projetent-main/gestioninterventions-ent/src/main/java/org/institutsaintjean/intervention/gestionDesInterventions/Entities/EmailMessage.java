package org.institutsaintjean.intervention.gestionDesInterventions.Entities;

import javax.persistence.*;
import lombok.*;

import java.io.File;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class EmailMessage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EMAIL")
    private Long idEmail;

    private String content;

    private String subject;

    private String email;

    private File pieceJointe;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "emailMessage")
    private Set<PiecejointeEmail> piecejointeEmails = new HashSet<>();
}
