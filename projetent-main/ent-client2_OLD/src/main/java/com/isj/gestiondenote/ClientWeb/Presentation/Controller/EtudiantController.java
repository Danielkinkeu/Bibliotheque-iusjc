package com.isj.gestiondenote.ClientWeb.Presentation.Controller;

import com.isj.gestiondenote.ClientWeb.Model.entities.Discipline;
import com.isj.gestiondenote.ClientWeb.Model.entities.Etudiant;
import com.isj.gestiondenote.ClientWeb.Model.entities.UE;
import com.isj.gestiondenote.ClientWeb.utils.test.Modal;
import com.isj.gestiondenote.ClientWeb.utils.test.RequestInterceptor;
import com.isj.gestiondenote.ClientWeb.utils.test.URL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class EtudiantController {

    @GetMapping("/listEtudiant")
    public String pagelisteEtudiant(Model model, HttpSession session) {
        Modal.model(model);

        HttpHeaders headers = new HttpHeaders();
        RequestInterceptor.addHeaders(headers, session);
        HttpEntity<String> httpEntity = new HttpEntity<>("", headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object[]> etudiants = restTemplate.exchange(URL.BASE_URL+"etudiant/all", HttpMethod.GET, httpEntity, Object[].class);
        model.addAttribute("etudiants", etudiants);

        return "Students/listeEtudiants";
    }

    @GetMapping("/supprimerEtudiant")
    public String supprimerEtudiant(@RequestParam(name = "code") String code, Model model){
        RestTemplate restTemplate = new RestTemplate();
        Object res = restTemplate.getForObject(URL.BASE_URL + "etudiant/" + code + "/delete", Object.class );
        return "redirect:listEtudiant";
    }

    @PostMapping("/editeretudiant")
    public String editeretudiant(@ModelAttribute Etudiant etudiant, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(URL.BASE_URL + "etudiant/update", etudiant, Etudiant.class);
        return "redirect:listEtudiant";
    }


    @GetMapping("/listUE")
    public String pagelisteUE(Model model, HttpSession session) {
        Modal.model(model);
        HttpHeaders headers = new HttpHeaders();
        RequestInterceptor.addHeaders(headers, session);
        HttpEntity<String> httpEntity = new HttpEntity<>("", headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object[]> ues = restTemplate.exchange(URL.BASE_URL + "ue/all", HttpMethod.GET, httpEntity, Object[].class);
        ResponseEntity<Object[]> modules = restTemplate.exchange(URL.BASE_URL + "module/all", HttpMethod.GET, httpEntity, Object[].class);
        ResponseEntity<Object[]> niveaux = restTemplate.exchange(URL.BASE_URL+"niveau/all", HttpMethod.GET, httpEntity, Object[].class);
        ResponseEntity<Object[]> specialites = restTemplate.exchange(URL.BASE_URL+"specialite/all", HttpMethod.GET, httpEntity, Object[].class);
        model.addAttribute("ues", ues);
        model.addAttribute("modules", modules);
        model.addAttribute("niveaux", niveaux);
        model.addAttribute("specialites", specialites);
        model.addAttribute("ue", new UE());

        return "Students/listeUE";
    }

    @GetMapping("/supprimerUE")
    public String supprimerUE(@RequestParam(name = "code") String code, Model model){
        RestTemplate restTemplate = new RestTemplate();
        Object res = restTemplate.getForObject(URL.BASE_URL + "ue/" + code + "/delete", Object.class );
        return "redirect:listUE";
    }

    @PostMapping("/editerUE")
    public String editerUE(@ModelAttribute Etudiant etudiant, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(URL.BASE_URL + "ue/update", etudiant, Etudiant.class);
        return "redirect:listUE";
    }

    @GetMapping("/listCandidat")
    public String pagelisteCandidat(Model model) {
        Modal.model(model);

        RestTemplate restTemplate = new RestTemplate();
        Object[] candidats = restTemplate.getForObject(URL.BASE_URL+"candidat/all", Object[].class);
        model.addAttribute("candidats",candidats);

        return "Students/listeCandidats";
    }

    @GetMapping("/supprimerCandidat")
    public String supprimerCandidat(@RequestParam(name = "code") String code, Model model){
        RestTemplate restTemplate = new RestTemplate();
        Object res = restTemplate.getForObject(URL.BASE_URL + "candidat/" + code + "/delete", Object.class );
        return "redirect:listCandidat";
    }

    @PostMapping("/editerCandidat")
    public String editerCandidat(@ModelAttribute Etudiant etudiant, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(URL.BASE_URL + "candidat/update", etudiant, Etudiant.class);
        return "redirect:listCandidat";
    }

    @GetMapping("/discipline")
    public String pagelisteDiscipline(Model model) {
        Modal.model(model);

        RestTemplate restTemplate = new RestTemplate();
        Object[] disciplines = restTemplate.getForObject(URL.BASE_URL+"candidat/all", Object[].class);
        Object[] etudiants = restTemplate.getForObject(URL.BASE_URL+"etudiant/all", Object[].class);
        model.addAttribute("disciplines",disciplines);
        model.addAttribute("etudiants",etudiants);
        model.addAttribute("discipline", new Discipline());

        return "Students/discipline";
    }

    @GetMapping("/supprimerDiscipline")
    public String supprimerDiscipline(@RequestParam(name = "code") String code, Model model){
        RestTemplate restTemplate = new RestTemplate();
        Object res = restTemplate.getForObject(URL.BASE_URL + "candidat/" + code + "/delete", Object.class );
        return "redirect:discipline";
    }

    @PostMapping("/editerDiscipline")
    public String editerDiscipline(@ModelAttribute Etudiant etudiant, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(URL.BASE_URL + "candidat/update", etudiant, Etudiant.class);
        return "redirect:discipline";
    }


    @GetMapping("/anonymat")
    public String pagelisteAnonymat(Model model) {
        Modal.model(model);

        RestTemplate restTemplate = new RestTemplate();
        Object[] anonymats = restTemplate.getForObject(URL.BASE_URL + "anonymat/all", Object[].class);
        model.addAttribute("anonymats",anonymats);

        return "Students/anonymat";
    }

    @GetMapping("/supprimerAnonymat")
    public String supprimerAnonymat(@RequestParam(name = "code") String code, Model model){
        RestTemplate restTemplate = new RestTemplate();
        Object res = restTemplate.getForObject(URL.BASE_URL + "candidat/" + code + "/delete", Object.class );
        return "redirect:anonymat";
    }

    @PostMapping("/editerAnonymat")
    public String editerAnonymat(@ModelAttribute Etudiant etudiant, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(URL.BASE_URL + "candidat/update", etudiant, Etudiant.class);
        return "redirect:anonymat";
    }

}
