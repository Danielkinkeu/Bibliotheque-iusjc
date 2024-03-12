package org.institutsaintjean.intervention.gestionDesInterventions.Entities;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "piecejointe")
public class PieceJointe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPieceJointe;

    // Champ pour stocker le contenu du fichier (comme décrit précédemment)
    @Lob
    private byte[] contenu;


    private String nom;
    private String type;

    @ManyToOne
    @JoinColumn(name = "intervention_id")
    @JsonBackReference
    private Intervention intervention;


    @Override
    public int hashCode() {
        return Objects.hash(idPieceJointe, contenu, nom, type);
    }

}
