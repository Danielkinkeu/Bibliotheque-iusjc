package org.institutsaintjean.intervention.gestionDesInterventions.Entities;

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
public class Categorie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CATEGORIE")
    private Long idCategorie;

    @Column(name = "intituleCategorie")
    private String intituleCategorie;

    @JsonBackReference
    @OneToMany(mappedBy = "categorie")
    private Set<Intervention> interventions;

    @JsonBackReference
    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SousIntervention> sousInterventions = new HashSet<>();
}
