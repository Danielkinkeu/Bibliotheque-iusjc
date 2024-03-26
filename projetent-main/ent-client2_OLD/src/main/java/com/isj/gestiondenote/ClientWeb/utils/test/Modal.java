package com.isj.gestiondenote.ClientWeb.utils.test;

import com.isj.gestiondenote.ClientWeb.Model.modeletat.*;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

public class Modal {

    static Object[] classes = new RestTemplate().getForObject(URL.BASE_URL+"classe/all", Object[].class);
    static Object[] anneeacademiques = new RestTemplate().getForObject(URL.BASE_URL+"annee-accademique/all", Object[].class);
    static Object[] semestres = new RestTemplate().getForObject(URL.BASE_URL + "semestre/all", Object[].class);

    public static void model(Model model){
        RestTemplate restTemplate = new RestTemplate();

        model.addAttribute("carteEtudiant", new CarteEtudiant());
        model.addAttribute("attestationDiplome", new AttestationEtDiplome());
        model.addAttribute("pv", new Pv());
        model.addAttribute("ficheAbsence", new FicheAbsence());
        model.addAttribute("releve", new ReleveNote());
        model.addAttribute("pvsynthese", new PVSynthese());
        model.addAttribute("connection", new Connection());
        model.addAttribute("classes",classes);
        model.addAttribute("anneeacademiques",anneeacademiques);
        model.addAttribute("semestres",semestres);

    }
}
