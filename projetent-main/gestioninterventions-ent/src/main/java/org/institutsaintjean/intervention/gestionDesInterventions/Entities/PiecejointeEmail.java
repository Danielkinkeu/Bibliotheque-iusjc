package org.institutsaintjean.intervention.gestionDesInterventions.Entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "PiecesjointesEmail")
public class PiecejointeEmail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPieceJointeEmail;

    // Champ pour stocker le contenu du fichier (comme décrit précédemment)
    @Lob
    private byte[] contenu;


    private String nom;
    private String type;

    @ManyToOne
    @JoinColumn(name = "email_id")
    @JsonBackReference
    private Email email;

    @ManyToOne
    @JoinColumn(name = "emailMessage_id")
    @JsonBackReference
    private EmailMessage emailMessage;

    @Override
    public int hashCode() {
        return Objects.hash(idPieceJointeEmail, contenu, nom, type);
    }

}
