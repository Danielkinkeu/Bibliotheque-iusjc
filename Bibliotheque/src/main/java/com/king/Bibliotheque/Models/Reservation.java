package com.king.Bibliotheque.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "reservation")
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "adherent_Id")
    private Adherent adherent;

    @ManyToOne
    @JoinColumn(name = "book_Id")
    private Books book;

    private LocalDate startDate;

    private Date endDate;
}
