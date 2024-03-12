package org.institutsaintjean.intervention.gestionDesInterventions.Controllers;

import org.institutsaintjean.intervention.gestionDesInterventions.Entities.*;
import org.institutsaintjean.intervention.gestionDesInterventions.Repositories.EtudiantRepository;
import org.institutsaintjean.intervention.gestionDesInterventions.Repositories.PersonnelRepository;
import org.institutsaintjean.intervention.gestionDesInterventions.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import java.util.Enumeration;

@CrossOrigin("*")
@RestController
public class UserController {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private PersonnelRepository personnelRepository;

    @GetMapping("/Users/role/{idRole}")
    public Role RoleEnFonctionDeId(@PathVariable long idRole) {
        return roleRepository.findRoleByIdRole(idRole);
    }

    @GetMapping("/Users/id/{email}/{role}")
    public long RechercherId(@PathVariable String email, @PathVariable String role) {
        if ("student".equals(role)) {
            Etudiant etudiant = etudiantRepository.findByEmailEtudiant(email);
            if (etudiant != null) {
                return etudiant.getCode();
            }
        } else if ("user".equals(role)) {
            Personnel personnel = personnelRepository.findByEmailPersonnel(email);
            if (personnel != null) {
                return personnel.getIdPersonnel();
            }
        }
        return 0;
    }


}
