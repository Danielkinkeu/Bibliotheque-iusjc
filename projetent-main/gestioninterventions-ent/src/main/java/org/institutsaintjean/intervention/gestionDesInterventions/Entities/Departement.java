package org.institutsaintjean.intervention.gestionDesInterventions.Entities;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class Departement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DEPARTEMENt")
    private Long idDepartement;

    @Column(name = "intituleDepartement")
    private String intituleDepartement;

    @JsonBackReference
    @OneToMany(mappedBy = "departement")
    private Set<Intervention> interventions;

    @JsonBackReference
    @OneToMany(mappedBy = "departement")
    private Set<Personnel> personnels = new HashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "departement", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SousIntervention> sousInterventions = new HashSet<>();





}
