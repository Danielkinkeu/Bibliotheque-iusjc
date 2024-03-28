package com.isj.gestiondenote.ClientWeb.Presentation.Controller;

import com.isj.gestiondenote.ClientWeb.Model.modeletat.ReleveNote;
import com.isj.gestiondenote.ClientWeb.utils.test.Modal;
import com.isj.gestiondenote.ClientWeb.utils.test.ModalWithHttpHeader;
import com.isj.gestiondenote.ClientWeb.utils.test.URL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

@Slf4j
@Controller
public class BiblioController {
    @GetMapping("/gestionbibliotheque")
    public String pageBibliotheque(Model model, HttpSession session){
        ModalWithHttpHeader.model(model, session);
        return "pages/gestion-bibliotheque/AcceuilBiblio";
    }

    @GetMapping("/Listelivres")
    public ModelAndView listelivre(Model model, HttpSession session) {
        ModalWithHttpHeader.model(model, session);
        Modal.model(model);
        String accessToken = (String) session.getAttribute("access_token");
        model.addAttribute("accessToken", accessToken);
        RestTemplate restTemplate = new RestTemplate();
        Object[] book = restTemplate.getForObject(URL.BASE_URL_BIBLIO+"book", Object[].class);
        model.addAttribute("book", book);
        return new ModelAndView("pages/gestion-bibliotheque/book");
    }
    @GetMapping("/Listelivresetu/details/{id}")
    public ModelAndView pageBibliothequedetail(@PathVariable Integer id ,Model model, HttpSession session){
        ModalWithHttpHeader.model(model, session);
        Modal.model(model);
        String accessToken = (String) session.getAttribute("access_token");
        model.addAttribute("accessToken", accessToken);
        RestTemplate restTemplate = new RestTemplate();
        Object[] book = restTemplate.getForObject(URL.BASE_URL_BIBLIO+"book/"+ id, Object[].class);
        model.addAttribute("book", book);
        System.out.println(book);
        return new ModelAndView("pages/gestion-bibliotheque/detail");
    }
    @GetMapping("/Listelivresetu")
    public ModelAndView listelivretu(Model model, HttpSession session) {
        ModalWithHttpHeader.model(model, session);
        Modal.model(model);
        String accessToken = (String) session.getAttribute("access_token");
        model.addAttribute("accessToken", accessToken);
        RestTemplate restTemplate = new RestTemplate();
        Object[] book = restTemplate.getForObject(URL.BASE_URL_BIBLIO+"book", Object[].class);
        model.addAttribute("book", book);
        return new ModelAndView("pages/gestion-bibliotheque/biblioetudiant");
    }

    @GetMapping("/Listecategory")
    public ModelAndView listecategory(Model model, HttpSession session) {
        ModalWithHttpHeader.model(model, session);
        Modal.model(model);
        String accessToken = (String) session.getAttribute("access_token");
        model.addAttribute("accessToken", accessToken);
        RestTemplate restTemplate = new RestTemplate();
        Object[] category = restTemplate.getForObject(URL.BASE_URL_BIBLIO+"category", Object[].class);
        model.addAttribute("category", category);
        return new ModelAndView("pages/gestion-bibliotheque/viewcategory");
    }

    @GetMapping("/deletecategory/{id}")
    public String deletecategory(@PathVariable Integer id, Model model) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.delete(URL.BASE_URL_BIBLIO + "category/delete/" + id );
            System.out.println(restTemplate);
            return "redirect:/Listecategory";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/Listecategory";
    }

    }

    @GetMapping("/Addbook")
    public String addbook(Model model, HttpSession session){
        ModalWithHttpHeader.model(model, session);
        Modal.model(model);
        return "pages/gestion-bibliotheque/addbook";
    }
    @PostMapping("/addCategory")
    public ResponseEntity<?> ajoutCategory(@ModelAttribute Model model, HttpSession session) throws URISyntaxException {
        model.addAttribute(session.getAttribute("title").toString());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", session.getValue("accessToken").toString());

        HttpEntity<Model> httpEntity = new HttpEntity<>(model, headers);
        RestTemplate restTemplate = new RestTemplate();
        byte[] releveEtudiants = restTemplate.postForObject(new URI(URL.BASE_URL_BIBLIO+"addCategory"),httpEntity, byte[].class);
        model.addAttribute("releveEtudiants",releveEtudiants);
        HttpHeaders httpHeaders = new HttpHeaders();

        //httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename="+releve.getMatricules()+""+(new Date()).getTime()+".pdf");
        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF).body(releveEtudiants);

    }
    @GetMapping("/Addplace")
    public String addplace(Model model, HttpSession session){
        ModalWithHttpHeader.model(model, session);
        Modal.model(model);
        return "pages/gestion-bibliotheque/addplace";
    }
}
