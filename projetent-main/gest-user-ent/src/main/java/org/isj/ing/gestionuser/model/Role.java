package org.isj.ing.gestionuser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleid", nullable = false)
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "description")
    private String description;

}