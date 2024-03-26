package com.isj.gestiondenote.ClientWeb.Presentation.Controller;

import com.isj.gestiondenote.ClientWeb.Model.dto.UserDto;
import com.isj.gestiondenote.ClientWeb.Model.dto.*;
import com.isj.gestiondenote.ClientWeb.Model.entities.*;
import com.isj.gestiondenote.ClientWeb.Model.modeletat.*;
import com.isj.gestiondenote.ClientWeb.utils.test.*;
import com.isj.gestiondenote.ClientWeb.utils.test.ModalWithHttpHeader;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@Controller
public class UserController {
    @RequestMapping(value = {"/"})
    public ModelAndView pageConnexionForm(Model model){
        final ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("user", new UserDto());
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping("/connexion")
    public String pageConnexion(@ModelAttribute(name = "utilisateur") Utilisateur utilisateur, Model model){
        return "redirect:dashboard";
    }

    @GetMapping("/deconnexion")
    public String deconnexion(Model model){
        return "redirect:/";
    }

    @GetMapping("/dashboard")
    public String pageAccueil(Model model){
  //      Modal.model(model);

        return "Accueil";
    }

    @PostMapping("/login")
    public String login(Model model, @ModelAttribute UserDto userDto , HttpSession session) throws URISyntaxException {
        System.out.println("yes");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserDto> httpEntity = new HttpEntity<>(userDto, headers);
        RestTemplate restTemplate = new RestTemplate();
        UserResponseDto userResponseDto= new UserResponseDto();
        userResponseDto = restTemplate.postForObject(new URI(URL.BASE_URL_AUTH+"/user/login"),httpEntity, UserResponseDto.class);
        System.out.println("Utilisateur:"+userResponseDto.toString());
        session.setAttribute("email",userResponseDto.getUsername());
        session.setAttribute("matricule",userResponseDto.getMatricule());
        session.setAttribute("filiere",userResponseDto.getFiliere());
        session.setAttribute("classe",userResponseDto.getClasse());
        session.setAttribute("name",userResponseDto.getName());
        session.setAttribute("accessToken",userResponseDto.getAccessToken());
        session.setAttribute("authorithies",userResponseDto.getAuthorities());
        List<AuthorityDto> authorities = userResponseDto.getAuthorities();
        session.setAttribute("authorithy", authorities.get(0).getAuthority());
        System.out.println(userResponseDto.getAccessToken());
        return "redirect:dashboard";
    }

    @GetMapping("/logout")
        public String disconnectUser(Model model,HttpSession session){
            session.invalidate();
            return "redirect:/";
        }


    @GetMapping("/gestionnote")
    public String pageProfile(Model model, HttpSession session){
        ModalWithHttpHeader.model(model, session);
        return "layout/gestionNote/gestNote";
    }
    @GetMapping("/gestionbibliotheque")
    public String pageBibliotheque(Model model, HttpSession session){
        ModalWithHttpHeader.model(model, session);
        return "pages/gestion-bibliotheque/AcceuilBiblio";
    }

    @GetMapping("/profil")
    public String pageProfileEtudiant(Model model,HttpSession session){
        ModalWithHttpHeader.model(model, session);
        return "layout/gestionNote/monProfil";
    }

    @GetMapping("/parcours")
    public String PageParcoursEtudiant(Model model,HttpSession session){
        ModalWithHttpHeader.model(model, session);
        return "layout/gestionNote/academique";
    }

    @GetMapping("/gestionintervention")
    public ModelAndView pageintervention(Model model, HttpSession session){
        ModalWithHttpHeader.model(model, session);
        String accessToken = (String) session.getAttribute("accessToken");
        String matricule = (String) session.getAttribute("matricule");
        model.addAttribute("accessToken", accessToken);
        model.addAttribute("matricule", matricule);
        System.out.println(model);
        return new ModelAndView("pages/gestion-interventions/dashboard-interventions");

    }
    @GetMapping("/trombinoscope")
    public ModelAndView trombinoscope(Model model, HttpSession session){
        ModalWithHttpHeader.model(model, session);
        String accessToken = (String) session.getAttribute("accessToken");
        String matricule = (String) session.getAttribute("matricule");
        model.addAttribute("accessToken", accessToken);
        model.addAttribute("matricule", matricule);
        System.out.println(model);
        return new ModelAndView("pages/gestion-trombinoscope/dashboard-trombinoscope");

    }
    @GetMapping("/trombinoscope/information/etudiant")
    public ModelAndView trombinoscopeInformationEtudiant(Model model, HttpSession session){
        ModalWithHttpHeader.model(model, session);
        String accessToken = (String) session.getAttribute("accessToken");
        String matricule = (String) session.getAttribute("matricule");
        model.addAttribute("accessToken", accessToken);
        model.addAttribute("matricule", matricule);


        System.out.println("yooooooooooooooooooooooooooo");
        RestTemplate restTemplate = new RestTemplate();
        Object[] infos = restTemplate.getForObject(URL.BASE_URL_TRO + "Trombo/AllUser", Object[].class);
        model.addAttribute("infos", infos);

//        System.out.println(infos);
        System.out.println(model);
        System.out.println("yooooooooooooooooooooooooooo");


        return new ModelAndView("pages/gestion-trombinoscope/informations-étudiants");

    }
    @GetMapping("/trombinoscope/information/personnel")
    public ModelAndView trombinoscopeInformationPersonnel(Model model, HttpSession session){
        ModalWithHttpHeader.model(model, session);
        String accessToken = (String) session.getAttribute("accessToken");
        String matricule = (String) session.getAttribute("matricule");
        model.addAttribute("accessToken", accessToken);
        model.addAttribute("matricule", matricule);
        System.out.println(model);
        return new ModelAndView("pages/gestion-trombinoscope/informations-personnel");

    }
///template/pages/gestion-trombinoscope/informations-étudiants.html
///template/pages/gestion-trombinoscope/informations-personnel.html

//    @GetMapping("/listeintervention")
//    public ModelAndView listeintervention(Model model, HttpSession session){
//        ModalWithHttpHeader.model(model, session);
//        String accessToken = (String) session.getAttribute("accessToken");
//        model.addAttribute("accessToken", accessToken);
//        System.out.println(model);
//        return new ModelAndView("pages/gestion-interventions/liste-des-interventions");
//
//    }

}
