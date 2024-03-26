package com.isj.gestiondenote.ClientWeb.Presentation.Controller;

import com.isj.gestiondenote.ClientWeb.Model.entities.Enseignant;
import com.isj.gestiondenote.ClientWeb.Model.entities.Session;
import com.isj.gestiondenote.ClientWeb.Model.modelIntervention.Departement;
import com.isj.gestiondenote.ClientWeb.Model.modelIntervention.Intervention;
import com.isj.gestiondenote.ClientWeb.Model.modelIntervention.PieceJointe;
import com.isj.gestiondenote.ClientWeb.utils.test.Modal;
import com.isj.gestiondenote.ClientWeb.utils.test.ModalWithHttpHeader;
import com.isj.gestiondenote.ClientWeb.utils.test.URL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class InterventionController {
    @PostMapping("/creerIntervention")
    public String creerIntervention(Model model, HttpSession session) {
        Modal.model(model);
        RestTemplate restTemplate = new RestTemplate();

        System.out.println(model);
        System.out.println("yaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

//        Object[] intervention = restTemplate.getForObject(URL.BASE_URL_INT + "soumettre/"+session.getAttribute("matricule")+"/"+ " fff ", Object[].class);
//        model.addAttribute("intervention", intervention);
//        model.addAttribute("intervention", new Object());

        return "redirect:/listeIntervention";
    }
    @GetMapping("/listeIntervention")
    public ModelAndView listeIntervention(Model model, HttpSession session) {
        ModalWithHttpHeader.model(model, session);
        Modal.model(model);
        String accessToken = (String) session.getAttribute("accessToken");
        model.addAttribute("accessToken", accessToken);
        System.out.println(model);
        System.out.println("yaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        RestTemplate restTemplate = new RestTemplate();

        Object[] interventions = restTemplate.getForObject(URL.BASE_URL_INT + "Liste/etudiant/"+session.getAttribute("matricule"), Object[].class);
        model.addAttribute("interventions", interventions);

        System.out.println(model);
        return new ModelAndView("pages/gestion-interventions/liste-des-interventions");

    }
    @GetMapping("/oneIntervention/{intervention}")
    public ModelAndView oneIntervention(Model model, HttpSession session,@PathVariable Object intervention) {
        ModalWithHttpHeader.model(model, session);
        Modal.model(model);
        String accessToken = (String) session.getAttribute("accessToken");
        model.addAttribute("accessToken", accessToken);
        System.out.println(intervention);
        RestTemplate restTemplate = new RestTemplate();

        return null;

    }

    @GetMapping("/listeInterventionParDepartement")
    public ModelAndView listeInterventionParDepartement(Model model, HttpSession session) {
        ModalWithHttpHeader.model(model, session);
        Modal.model(model);
        String accessToken = (String) session.getAttribute("accessToken");
        model.addAttribute("accessToken", accessToken);
        System.out.println(model);
        System.out.println("yooooooooooooooooooooooooooo");
        RestTemplate restTemplate = new RestTemplate();

        String matricule = (String) session.getAttribute("matricule");
        model.addAttribute("matricule", matricule);
        Object[] interventions = restTemplate.getForObject(URL.BASE_URL_INT + "Liste/Departement/"+session.getAttribute("matricule"), Object[].class);
        model.addAttribute("interventions", interventions);

        System.out.println(model);
        System.out.println(session.getAttribute("matricule"));
        return new ModelAndView("pages/gestion-interventions/liste-des-interventions");

    }
//    http://localhost:9090/api/interventions/prendre-en-charge/{{interventionId}}/{{personnelId}}
@GetMapping("/prendreEnCharge/{idIntervention}")
    public String prendreEnCharge(Model model, HttpSession session,@PathVariable Long idIntervention){
    ModalWithHttpHeader.model(model, session);
    Modal.model(model);
    String accessToken = (String) session.getAttribute("accessToken");
    model.addAttribute("accessToken", accessToken);
    System.out.println(model);
    System.out.println("yeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.put(URL.BASE_URL_INT + "prendre-en-charge/"+idIntervention+"/"+session.getAttribute("matricule"), null);
    return "redirect:/listeInterventionParDepartement";
}

    @GetMapping("/terminer/{idIntervention}")
    public String terminer(Model model, HttpSession session,@PathVariable Long idIntervention){
        ModalWithHttpHeader.model(model, session);
        Modal.model(model);
        String accessToken = (String) session.getAttribute("accessToken");
        model.addAttribute("accessToken", accessToken);
        System.out.println(model);
        System.out.println("rediiiiiiiiiiiiiiiiiiiiiiiiireeeeeeeeeeeeect");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(URL.BASE_URL_INT + "Termine/"+idIntervention, null);
        return "redirect:/listeInterventionParDepartement";
    }

    @GetMapping("/cancel/{idIntervention}")
    public String cancelIntervention(Model model, HttpSession session,@PathVariable Long idIntervention){
        ModalWithHttpHeader.model(model, session);
        Modal.model(model);
        String accessToken = (String) session.getAttribute("accessToken");
        model.addAttribute("accessToken", accessToken);
        System.out.println(model);
        System.out.println("rediiiiiiiiiiiiiiiiiiiiiiiiireeeeeeeeeeeeect");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(URL.BASE_URL_INT + "cancel/"+idIntervention, null,void.class);
        return "redirect:/listeIntervention";
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadFile(Model model, HttpSession session,@PathVariable Long id) {
        ModalWithHttpHeader.model(model, session);
        Modal.model(model);
        RestTemplate restTemplate = new RestTemplate();
//        System.out.println(restTemplate.getForObject(URL.BASE_URL_INT + "pieceJointe/"+id, Object.class));

        PieceJointe fichier = restTemplate.getForObject(URL.BASE_URL_INT + "pieceJointe/"+id, PieceJointe.class);;
//                fichierRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Fichier non trouvé"));
        System.out.println(fichier);
        System.out.println("ddddddddddddoooooooooooooooooooooooowwwwwnload");
//
        return  ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fichier.getNom() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new ByteArrayResource(fichier.getContenu()));
    }
}
