package org.institutsaintjean.intervention.gestionDesInterventions.Controllers;


import org.institutsaintjean.intervention.gestionDesInterventions.Entities.Departement;
import org.institutsaintjean.intervention.gestionDesInterventions.Entities.SousIntervention;
import org.institutsaintjean.intervention.gestionDesInterventions.Repositories.DepartementRepository;
import org.institutsaintjean.intervention.gestionDesInterventions.Repositories.SousInterventionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/departement")
public class DepartementController {

    @Autowired
    DepartementRepository departementRepository;

    @Autowired
    SousInterventionRepository sousInterventionRepository;



    @GetMapping("/Liste")
    public List<Departement> ListeDepartement() {
        return departementRepository.findAll();
    }



    @GetMapping("/sousIntervention/{idDepartement}")
    public List<SousIntervention> ListSousInterventionParDepartement(@PathVariable Long idDepartement) {
            return sousInterventionRepository.findByDepartementIdDepartement(idDepartement);

    }


}
