package org.isj.ing3.isi.webservice.webservicerest.model.entities;

import java.util.Calendar;
import java.util.Date;

/**
 * cette classe crée la table annee_academique dans la base de données
 * cette classe herite de la classe Securite
 * @author traitement metier
 */

public class PasswordResetTokenPojo {

    private Long id;

    private String token;

    private Utilisateur user;

    private long expiryDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public long getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(long expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isExpired() {
        return new Date().after(new Date(this.expiryDate));
    }
}