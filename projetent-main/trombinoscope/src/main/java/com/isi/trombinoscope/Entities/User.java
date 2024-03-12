package com.isi.trombinoscope.Entities;

import javax.persistence.*;
import lombok.*;

//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "matricule", nullable = true)
    private String matricule;

    @Column(name = "filiere", nullable = true)
    private String filiere;

    @Column(name = "classe", nullable = true)
    private String classe;
    @Column(name = "image", nullable = true)
    private String image;

}