package com.isj.gestiondenote.ClientWeb.Presentation.Controller;

import com.isj.gestiondenote.ClientWeb.utils.test.Modal;
import com.isj.gestiondenote.ClientWeb.utils.test.ModalWithHttpHeader;
import com.isj.gestiondenote.ClientWeb.utils.test.URL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

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
   
    @GetMapping("/Addbook")
    public String addbook(Model model, HttpSession session){
        ModalWithHttpHeader.model(model, session);
        Modal.model(model);
        return "pages/gestion-bibliotheque/addbook";
    }
    @GetMapping("/Addcategory")
    public String addcategory(Model model, HttpSession session){
        ModalWithHttpHeader.model(model, session);
        Modal.model(model);
        return "pages/gestion-bibliotheque/addcategory";
    }
    @GetMapping("/Addplace")
    public String addplace(Model model, HttpSession session){
        ModalWithHttpHeader.model(model, session);
        Modal.model(model);
        return "pages/gestion-bibliotheque/addplace";
    }
}
