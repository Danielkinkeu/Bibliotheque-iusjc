package org.institutsaintjean.intervention.gestionDesInterventions.Controllers;


import org.institutsaintjean.intervention.gestionDesInterventions.Entities.Categorie;
import org.institutsaintjean.intervention.gestionDesInterventions.Entities.SousIntervention;
import org.institutsaintjean.intervention.gestionDesInterventions.Repositories.CategorieRepository;
import org.institutsaintjean.intervention.gestionDesInterventions.Repositories.SousInterventionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categorie")
public class CategorieController {

    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private SousInterventionRepository sousInterventionRepository;

    @GetMapping("/Liste")
    public List<Categorie> ListeCategories (){
        return categorieRepository.findAll();
    }

    @GetMapping("/sousIntervention/{idCategorie}")
    public List<SousIntervention> ListSousInterventionParCategorie(@PathVariable Long idCategorie) {
        return sousInterventionRepository.findByCategorieIdCategorie(idCategorie);

    }

}
