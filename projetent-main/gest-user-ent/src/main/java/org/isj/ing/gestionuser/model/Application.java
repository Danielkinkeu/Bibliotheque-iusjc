package org.isj.ing.gestionuser.model;

//Author NKOT-A-NZOK HONORE ETIENNE
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "application")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apllicationid", nullable = false)
    private Long id;

    @Column(name = "libelle")
    private String libelle;

}