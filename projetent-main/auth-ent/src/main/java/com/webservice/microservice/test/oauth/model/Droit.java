package com.webservice.microservice.test.oauth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "droit", indexes = {
        @Index(name = "codeapplicationid", columnList = "codeapplicationid")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Droit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "droitid", nullable = false)
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "description")
    private String description;

    @Column(name = "ecriture")
    private Integer ecriture;

    @Column(name = "lecture")
    private Integer lecture;

    @Column(name = "modification")
    private Integer modification;

    @Column(name = "suppression")
    private Integer suppression;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codeapplicationid")
    private Application codeapplicationid;

}