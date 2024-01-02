package com.king.Bibliotheque.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@Table(name = "validation")
@Entity
@NoArgsConstructor
public class Validation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Instant creation;
    private Instant activation;
    private Instant expiration;
    private String code;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;

}
