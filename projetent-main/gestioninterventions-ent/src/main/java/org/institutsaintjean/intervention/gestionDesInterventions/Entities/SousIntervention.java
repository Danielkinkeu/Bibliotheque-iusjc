package org.institutsaintjean.intervention.gestionDesInterventions.Entities;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SousIntervention implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SOUS_INTERVENTION")
    private Long idSousIntervention;

    @Column(name = "description")
    private String intituleSousIntervention;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "ID_DEPARTEMENT")
    private Departement departement;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "ID_CATEGORIE")
    private Categorie categorie;

}
