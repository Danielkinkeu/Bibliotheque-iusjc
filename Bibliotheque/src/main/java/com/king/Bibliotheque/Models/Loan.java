package com.king.Bibliotheque.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "loan")
@NoArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "adherent_Id", referencedColumnName = "id")
    private Adherent adherent;

    @ManyToOne
    @JoinColumn (name = "book_Id", referencedColumnName = "id")
    private Books book;

    private LocalDate startDate;

    private Date endDate;

    private Date returnDate;

}
