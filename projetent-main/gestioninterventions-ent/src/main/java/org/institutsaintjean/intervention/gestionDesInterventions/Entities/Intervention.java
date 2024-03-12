package org.institutsaintjean.intervention.gestionDesInterventions.Entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.institutsaintjean.intervention.gestionDesInterventions.Enumerations.Statut;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idIntervention")
public class Intervention implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idIntervention")
    @JsonProperty("idIntervention")
    private Long idIntervention;

    @Column(name = "dateCreationIntervention")
    private Date dateCreationInter;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private Statut statut;

    @Column(name = "description")
    private String description;

    private Integer nomDepartement;

    private Integer motif;

    private String file;
    
    private boolean isTaken = false;

    private boolean isCanceled = false;


    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    @JsonManagedReference
    private Etudiant etudiant;


    @ManyToOne
    @JoinColumn(name = "personnel_id")
    @JsonManagedReference
    private Personnel personnel;


    @ManyToOne
    @JoinColumn(name = "departement_id")
    @JsonManagedReference
    private Departement departement;

    @ManyToOne
    @JoinColumn(name = "idCategorie")
    @JsonManagedReference
    private Categorie categorie;


    @ManyToOne
    @JoinColumn(name = "ID_SOUS_INTERVENTION")
    @JsonManagedReference
    private SousIntervention sousIntervention;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "intervention")
    private Set<PieceJointe> piecesJointes = new HashSet<>();

    @Override
    public int hashCode() {
        return Objects.hash(idIntervention, dateCreationInter, statut, description, nomDepartement, motif, file);
    }

}
