package org.institutsaintjean.intervention.gestionDesInterventions.Entities;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "utilisateur")
public class Personnel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private long idPersonnel;

    @Column(name = "nomPersonnel")
    private String nomPersonnel;

    @Column(name = "prenomPersonnel")
    private String prenomPersonnel;

    @Column(name = "email")
    private String emailPersonnel;




    private String login;

    @JsonBackReference
    @OneToMany(mappedBy = "personnel")
    private Set<Intervention> interventions = new HashSet<>();

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;


}
