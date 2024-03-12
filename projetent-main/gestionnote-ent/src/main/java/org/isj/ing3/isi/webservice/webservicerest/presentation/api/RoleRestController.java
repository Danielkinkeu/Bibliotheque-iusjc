package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Role;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.service.IRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleRestController {
    @Autowired
    private IRole iRole;

    @PostMapping("/save")
    public String enregistrer(@RequestBody Role create) throws IsjException {
        try {
            iRole.saveRole(create);
        }catch (IsjException exception) {
            return exception.getMessage();
        }

        return "enregistrement reussi";
    }

    @GetMapping("/all")
    public ResponseEntity<List<Role>> getAllRole() {
        return ResponseEntity.ok(iRole.listRole());
    }

    @PostMapping("/allByUtilisateur")
    public List<Role> getAllRoleByUtilisateur(@RequestBody Utilisateur utilisateur) {
        return iRole.listRoleByUtilisateur(utilisateur);
    }

    @GetMapping("/{code}/delete")
    public ResponseEntity<?> deteleRole(@PathVariable("code") Long code) throws IsjException {
        try {
            return ResponseEntity.ok(iRole.deleteRoleByCode(code));
        }catch (IsjException exception) {
            return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping("/update")
    public String modifier(@RequestBody Role create) throws IsjException {
        try {
            iRole.updateRole(create);
        }catch (IsjException exception) {
            return exception.getMessage();
        }

        return "modification reussi";
    }
}
