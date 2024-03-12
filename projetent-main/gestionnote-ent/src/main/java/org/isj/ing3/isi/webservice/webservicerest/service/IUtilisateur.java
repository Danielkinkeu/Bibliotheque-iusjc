package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Droit;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.utils.enumaration.Statut;

import java.util.List;
import java.util.Map;

public interface IUtilisateur {

    Utilisateur saveUtilisateur(Utilisateur utilisateur ) throws IsjException;
    Utilisateur updateUtilisateur(Utilisateur utilisateur ) throws IsjException;
    List<Utilisateur> listUtilisateur();
    int deleteUtilisateur(Long code);
    Utilisateur getUtilisateurByCode(Long code) throws IsjException;
    Utilisateur login(String login, String pw, Statut statut) throws IsjException;
    void deconnect(Utilisateur utilisateur) throws IsjException;
    Utilisateur ajouterDroitAUnUser(Utilisateur utilisateur, Long codeDroit) throws IsjException;
    Map<String, Droit> droitsFinaux(Utilisateur u) throws IsjException;
    boolean peutLire(Class c, Long code) throws IsjException;
    boolean peutEcrire(Class c, Long code) throws IsjException;
    boolean peutModifier(Class c, Long code) throws IsjException;
    boolean peutSupprimer(Class c, Long code) throws IsjException;

    Utilisateur findByEmail(String email);
}
