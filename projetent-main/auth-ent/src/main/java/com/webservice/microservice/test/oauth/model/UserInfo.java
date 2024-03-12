package com.webservice.microservice.test.oauth.model;

import javax.persistence.*;

@Entity
@Table(name="user_info")
public class UserInfo {
    @Id
    @SequenceGenerator(name="users_id_seq",
            sequenceName="users_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="users_id_seq")
    @Column(name = "id", updatable=false)
    private Long id;

    @Column
    private String username;

    @Column
    private String passwordhash;


    @Column
    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordhash() {
        return passwordhash;
    }

    public void setPasswordhash(String passwordhash) {
        this.passwordhash = passwordhash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
