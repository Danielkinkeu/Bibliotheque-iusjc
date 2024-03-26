package com.isj.gestiondenote.ClientWeb.Presentation.Controller;

import com.isj.gestiondenote.ClientWeb.Model.entities.Classe;
import com.isj.gestiondenote.ClientWeb.Model.entities.Specialite;
import com.isj.gestiondenote.ClientWeb.Model.entities.Niveau;
import com.isj.gestiondenote.ClientWeb.Model.entities.Filiere;
import com.isj.gestiondenote.ClientWeb.utils.test.Modal;
import com.isj.gestiondenote.ClientWeb.utils.test.URL;
//import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Controller
public class ClasseController {
    @GetMapping("/listFiliere")
    public String pagelisteFiliere(Model model) {
        Modal.model(model);

        RestTemplate restTemplate = new RestTemplate();
        //restTemplate.
        Object[] filieres = restTemplate.getForObject(URL.BASE_URL+ "filiere/all", Object[].class);
        model.addAttribute("filieres", filieres);

       return "";
    }

    @GetMapping("/supprimerFiliere")
    public String supprimerFiliere(@RequestParam(name = "code") String code, Model model){
        RestTemplate restTemplate = new RestTemplate();
        Object res = restTemplate.getForObject(URL.BASE_URL + "filiere/" + code + "/delete", Object.class );
       return "";
    }

    @PostMapping("/editerFiliere")
    public String editerFiliere(@ModelAttribute Filiere filiere, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(URL.BASE_URL + "filiere/update", filiere, Filiere.class);
        return "";
    }

    @GetMapping("/listClasse")
    public String pagelisteClasse(Model model) {
        Modal.model(model);
        return "";
    }

    @GetMapping("/supprimerClasse")
    public String supprimerClasse(@RequestParam(name = "code") String code, Model model){
        RestTemplate restTemplate = new RestTemplate();
        Object res = restTemplate.getForObject(URL.BASE_URL + "classe/" + code + "/delete", Object.class );
        return "";
    }

    @PostMapping("/editerClasse")
    public String editerClasse(@ModelAttribute Classe classe, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(URL.BASE_URL + "classe/update", classe, Classe.class);
        return "";
    }

    @GetMapping("/listNiveau")
    public String pagelisteNiveau(Model model) {
        Modal.model(model);

        RestTemplate restTemplate = new RestTemplate();
        Object[] niveaux = restTemplate.getForObject(URL.BASE_URL+"niveau/all", Object[].class);
        model.addAttribute("niveaux", niveaux);
        return "";
    }

    @GetMapping("/supprimerNiveau")
    public String supprimerNiveau(@RequestParam(name = "code") String code, Model model){
        RestTemplate restTemplate = new RestTemplate();
        Object res = restTemplate.getForObject(URL.BASE_URL + "niveau/" + code + "/delete", Object.class );
        return "";
    }

    @PostMapping("/editerNiveau")
    public String editerNiveau(@ModelAttribute Niveau niveau, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(URL.BASE_URL + "niveau/update", niveau, Niveau.class);
        return "";
    }

    @GetMapping("/listSpecialite")
    public String pagelisteSpecialite(Model model) {
        Modal.model(model);

        RestTemplate restTemplate = new RestTemplate();
        Object[] specialites = restTemplate.getForObject(URL.BASE_URL + "specialite/all", Object[].class);
        Object[] filieres = restTemplate.getForObject(URL.BASE_URL + "filiere/all", Object[].class);
        model.addAttribute("specialites", specialites);
        model.addAttribute("filieres",filieres);
        return "";
    }

    @GetMapping("/supprimerSpecialite")
    public String supprimerSpecialite(@RequestParam(name = "code") String code, Model model){
        RestTemplate restTemplate = new RestTemplate();
        Object res = restTemplate.getForObject(URL.BASE_URL + "specialite/" + code + "/delete", Object.class );
        return "";
    }

    @PostMapping("/editerSpecialite")
    public String editerSpecialite(@ModelAttribute Specialite specialite, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(URL.BASE_URL + "specialite/update", specialite, Specialite.class);
        return "";
    }
}
