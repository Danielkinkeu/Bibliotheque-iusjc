package com.king.Bibliotheque.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

//@Entity // This tells Hibernate to make a table out of this class
@Getter
@Setter
@AllArgsConstructor
@Table(name = "user")
@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;
    private String subName;
    private String email;
    private Date birthDate;
    private Number phone;
    private String address;

}
