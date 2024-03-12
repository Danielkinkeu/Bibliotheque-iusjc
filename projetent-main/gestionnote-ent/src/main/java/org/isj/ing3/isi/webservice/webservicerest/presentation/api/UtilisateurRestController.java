package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Role;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.service.IUtilisateur;
import org.isj.ing3.isi.webservice.webservicerest.utils.enumaration.Statut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateur")
@Slf4j
public class UtilisateurRestController {

    @Autowired
    IUtilisateur iUtilisateur;

    @PostMapping("/save")
    public void enregistrer(@RequestBody Utilisateur create) throws IsjException {
        iUtilisateur.saveUtilisateur(create);
    }


    @GetMapping("/{code}/data")
    public ResponseEntity<Utilisateur> getUtilisateurByCode(@PathVariable("code") Long code) throws IsjException {

        return ResponseEntity.ok(iUtilisateur.getUtilisateurByCode(code));
    }


    @GetMapping("/all")
    public ResponseEntity<List<Utilisateur>> getAllUtilisateur() {
        return ResponseEntity.ok(iUtilisateur.listUtilisateur());
    }

    @GetMapping("/allUsers")
    public List<Utilisateur> getAllUtilisateurs() {
        return iUtilisateur.listUtilisateur();
    }

    @GetMapping("/{code}/delete")
    public int deteleUtilisateur(@PathVariable("code") Long code){
        return iUtilisateur.deleteUtilisateur(code);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Utilisateur utilisateur) throws IsjException {
        try {
            return ResponseEntity.ok(iUtilisateur.login(utilisateur.getLogin(), utilisateur.getMotDePasse(), Statut.ACTIVE));
        }catch (IsjException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/deconnect")
    public ResponseEntity<?> deconnectUser(@RequestBody Utilisateur utilisateur) throws IsjException {
        try {
            iUtilisateur.deconnect(utilisateur);
            return ResponseEntity.ok("deconnexion réussie");
        }catch (IsjException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/rolesuser")
    public List<Role> rolesUser(@RequestBody Utilisateur utilisateur) throws IsjException {
        try {
            List<Role> roles = utilisateur.getRoles();
            return roles;
        }catch (Exception e) {
            return null;
        }
    }

}
