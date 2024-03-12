package com.isj.gestiondenote.ClientWeb.Presentation.Controller;

import com.isj.gestiondenote.ClientWeb.utils.test.Modal;
import com.isj.gestiondenote.ClientWeb.utils.test.URL;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class SemestreController {

    @GetMapping("/listevaluation")
    public String pageListeEvaluation(Model model){
        Modal.model(model);
        RestTemplate restTemplate = new RestTemplate();
        Object[] evaluations = restTemplate.getForObject(URL.BASE_URL + "evaluation/all", Object[].class);
        Object[] etudiants = restTemplate.getForObject(URL.BASE_URL + "etudiant/all", Object[].class);
        model.addAttribute("etudiants", etudiants);
        model.addAttribute("evaluations", evaluations);

        return  "Semestre/evaluation";
    }
    @GetMapping("/deleteEvaluation")
    public String pageSupprimerEvaluation(@RequestParam(name = "code") String code, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(URL.BASE_URL + "evaluation/" + code + "/delete", String.class);
        return "redirect:listevaluation";
    }

    @GetMapping("/listhistorique")
    public String pageListeHistorique(Model model){
        Modal.model(model);

        RestTemplate restTemplate = new RestTemplate();
        Object[] historiques = restTemplate.getForObject(URL.BASE_URL + "historiqueNote/all", Object[].class);
        Object[] etudiants = restTemplate.getForObject(URL.BASE_URL + "etudiant/all", Object[].class);
        model.addAttribute("etudiants", etudiants);
        model.addAttribute("historiques", historiques);

        return  "Semestre/historique";
    }
    @GetMapping("/deleteHistorique")
    public String pageSupprimerHistorique(@RequestParam(name = "code") String code, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(URL.BASE_URL + "historiqueNote/" + code + "/delete", String.class);
        return "redirect:listhistorique";
    }

    @GetMapping("/listsemestre")
    public String pageListeSemestre(Model model){
        Modal.model(model);

        RestTemplate restTemplate = new RestTemplate();
        Object[] etudiants = restTemplate.getForObject(URL.BASE_URL + "etudiant/all", Object[].class);
        model.addAttribute("etudiants", etudiants);

        return  "Semestre/semestre";

    }
    @GetMapping("/deleteSemestre")
    public String pageSupprimersemestre(@RequestParam(name = "code") String code, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(URL.BASE_URL + "semestre/" + code + "/delete", String.class);
        return "redirect:listsemestre";
    }


    @GetMapping("/listtypeevalution")
    public String pageListeTypeEvaluation(Model model){
        Modal.model(model);

        RestTemplate restTemplate = new RestTemplate();
        Object[] typeevaluations = restTemplate.getForObject(URL.BASE_URL + "type-evaluation/all", Object[].class);

        return  "Semestre/typeevaluation";
    }

    @GetMapping("/deletelisttypeevaluation")
    public String pageTypeevalution(@RequestParam(name = "code") String code, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(URL.BASE_URL + "type-evaluation/" + code + "/delete", String.class);
        return "redirect:listtypeevalution";
    }

    @GetMapping("/listanneeacademique")
    public String pageListeAnneeacademique(Model model){
        Modal.model(model);
        return  "Semestre/anneeacademique";
    }

    @GetMapping("/deleteanneeacademique")
    public String pagedeleteanneeacademique(@RequestParam(name = "code") String code, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(URL.BASE_URL + "annee-accademique/" + code + "/delete", String.class);
        return "redirect:listanneeacademique";
    }
}
