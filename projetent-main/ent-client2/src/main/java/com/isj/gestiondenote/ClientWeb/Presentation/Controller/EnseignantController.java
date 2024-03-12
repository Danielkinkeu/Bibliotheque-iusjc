package com.isj.gestiondenote.ClientWeb.Presentation.Controller;

import com.isj.gestiondenote.ClientWeb.Model.entities.Enseignant;
import com.isj.gestiondenote.ClientWeb.Model.entities.Utilisateur;
import com.isj.gestiondenote.ClientWeb.utils.test.Modal;
import com.isj.gestiondenote.ClientWeb.utils.test.URL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Controller
public class EnseignantController {

    @GetMapping("/listProfessor")
    public String pagelisteProfessor(Model model) {
        Modal.model(model);

        RestTemplate restTemplate = new RestTemplate();
        Object[] enseignants = restTemplate.getForObject(URL.BASE_URL + "enseignant/all", Object[].class);
        model.addAttribute("enseignants", enseignants);
        model.addAttribute("enseignant", new Enseignant());

        return "";
    }

    @PostMapping("/saveProfessor")
    public String enregistrerprofessor(@ModelAttribute Enseignant enseignant, Model model) throws URISyntaxException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();


        Utilisateur utilisateur = restTemplate.getForObject(URL.BASE_URL + "utilisateur/1/data", Utilisateur.class);
        System.out.println(utilisateur);
        enseignant.setCreateur(utilisateur);
        enseignant.setModificateur(utilisateur);

        HttpEntity<Enseignant> httpEntity = new HttpEntity<>(enseignant, headers);
        System.out.println(enseignant);

        restTemplate.postForObject(new URI(URL.BASE_URL + "enseignant/save"), httpEntity, Object[].class);

        return "";
    }

    @GetMapping("/deleteprof")
    public String pageSupprimer(@RequestParam(name = "code") String code, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(URL.BASE_URL + "enseignant/" + code + "/delete", String.class);
        return "";
    }

    @GetMapping("/listEnseignement")
    public String pagelisteEnseignement(Model model) {
        Modal.model(model);

        RestTemplate restTemplate = new RestTemplate();
        Object[] enseignements = restTemplate.getForObject(URL.BASE_URL + "enseignement/all", Object[].class);
        model.addAttribute("enseignements", enseignements);

        return "";
    }

    @GetMapping("/deleteenseignement")
    public String pageSupprimerEnseignement(@RequestParam(name = "code") String code, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(URL.BASE_URL + "enseignement/" + code + "/delete", String.class);
        return "";
    }

    @GetMapping("/listModule")
    public String pagelisteModule(Model model) {
        Modal.model(model);

        RestTemplate restTemplate = new RestTemplate();
        Object[] modules = restTemplate.getForObject(URL.BASE_URL + "module/all", Object[].class);
        Object[] etudiants = restTemplate.getForObject(URL.BASE_URL + "etudiant/all", Object[].class);
        model.addAttribute("etudiants", etudiants);
        model.addAttribute("modules", modules);

        return "Professor/Module";
    }

    @GetMapping("/deletemodule")
    public String pageSupprimerModule(@RequestParam(name = "code") String code, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(URL.BASE_URL + "module/" + code + "/delete", String.class);
        return "redirect:listModule";
    }

    @GetMapping("/ueDispense")
    public String pageListeUE(Model model) {
        return "redirect:/listUE";
    }
}