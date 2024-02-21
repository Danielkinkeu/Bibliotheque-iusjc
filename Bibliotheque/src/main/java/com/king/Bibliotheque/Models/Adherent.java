package com.king.Bibliotheque.Models;

import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Table(name = "adherent")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Adherent extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String pseudo;
    private String adherentType;
    private Date subscriptionDate;
    private Date membershipExpirationDate;
    private Integer numberLoansAuthorized;
    private Integer maximumLoanDuration;
}
