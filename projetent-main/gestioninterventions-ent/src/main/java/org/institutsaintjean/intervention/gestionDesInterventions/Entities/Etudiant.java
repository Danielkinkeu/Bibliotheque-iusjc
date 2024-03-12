package org.institutsaintjean.intervention.gestionDesInterventions.Entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name="etudiant")
public class Etudiant  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="code")
    private long code;

    @Column(name="matricule")
    private String matricule;

    @Column(name="email")
    private String emailEtudiant;

    @Column(name="nomEtudiant")
    private String nomEtudiant;

    @Column(name="prenomEtudiant")
    private String prenomEtudiant;

    @Column(name = "code_authentification")
    private String codeAuthentification;





    private String login;

    @JsonBackReference
    @OneToMany(mappedBy = "etudiant")
    private Set<Intervention> interventions = new HashSet<>();




}
