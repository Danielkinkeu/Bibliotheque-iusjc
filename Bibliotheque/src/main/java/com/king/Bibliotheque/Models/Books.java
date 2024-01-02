package com.king.Bibliotheque.Models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "book")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String isbn;
    private String title;
    private String authorName;
    private String edition;
    private Date publicationDate;
    private String file;

    @OneToMany
    List<BookCopy> copyList;

}
