package com.isj.gestiondenote.ClientWeb.Presentation.Controller;

import com.isj.gestiondenote.ClientWeb.Model.modeletat.*;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Enumeration;


@Slf4j
@Controller
public class ScolariteController {

    @PostMapping("/imprimerDiplomeOuAttestation")
    public ResponseEntity<?> imprimerAttestationOuDiplome(@ModelAttribute AttestationEtDiplome attestationEtDiplome, Model model, HttpSession session) throws URISyntaxException {
        attestationEtDiplome.setCodeUser("fhjhh");
        int anneDebut = Integer.valueOf(attestationEtDiplome.getDateJury().substring(0, attestationEtDiplome.getDateJury().indexOf("-")));
        System.out.println(anneDebut);

        attestationEtDiplome.setAnnee(anneDebut);
        System.out.println(attestationEtDiplome);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", session.getValue("accessToken").toString());
        HttpEntity<AttestationEtDiplome> httpEntity = new HttpEntity<>(attestationEtDiplome, headers);
        RestTemplate restTemplate = new RestTemplate();
        byte[] carteEtudiants = {4, 2};
        if(attestationEtDiplome.getTypeEtat().equals("diplome")) {
            carteEtudiants = restTemplate.postForObject(new URI(URL.BASE_URL+"print/generatediplome"),httpEntity, byte[].class);
        }
        if(attestationEtDiplome.getTypeEtat().equals("attestation")) {
            System.out.println(attestationEtDiplome);
            carteEtudiants = restTemplate.postForObject(new URI(URL.BASE_URL+"print/generateatestation"),httpEntity, byte[].class);
        }

        model.addAttribute("carteEtudiants",carteEtudiants);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename="+attestationEtDiplome.getCodeClasse()+""+attestationEtDiplome.getAnnee()+".pdf");
        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF).body(carteEtudiants);

    }

    @PostMapping("/imprimerpv")
    public ResponseEntity<?> imprimerPv(@ModelAttribute Pv pv, Model model, HttpSession session) throws URISyntaxException {
        pv.setCodeUser("fhjhh");
        if(pv.getTypeEtat().equals("excel")) {
            pv.setFormat(false);
        } else {
            pv.setFormat(true);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", session.getValue("accessToken").toString());
        HttpEntity<Pv> httpEntity = new HttpEntity<>(pv, headers);
        RestTemplate restTemplate = new RestTemplate();
        byte[] carteEtudiants = restTemplate.postForObject(new URI(URL.BASE_URL+"print/generatePv"),httpEntity, byte[].class);
        model.addAttribute("carteEtudiants",carteEtudiants);
        HttpHeaders httpHeaders = new HttpHeaders();

        if(pv.getTypeEtat().equals("excel")) {
            httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename="+pv.getCodeClasse()+""+pv.getAnnee()+".xlsx");
            return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_OCTET_STREAM).body(carteEtudiants);
        } else {
            httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename="+pv.getCodeClasse()+""+pv.getAnnee()+".pdf");
            return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF).body(carteEtudiants);
        }

    }

    @PostMapping("/imprimerFicheAbsence")
    public ResponseEntity<?> imprimerFicheAbsence(@ModelAttribute FicheAbsence ficheAbsence, Model model, HttpSession session) throws URISyntaxException {
        ficheAbsence.setCodeUser("fhjhh");
        int anneDebut = Integer.valueOf(ficheAbsence.getDateDebut().substring(0, ficheAbsence.getDateDebut().indexOf("-")));
        System.out.println(anneDebut);

        ficheAbsence.setAnnee(anneDebut);
        System.out.println(ficheAbsence);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", session.getValue("accessToken").toString());
        HttpEntity<FicheAbsence> httpEntity = new HttpEntity<>(ficheAbsence, headers);
        RestTemplate restTemplate = new RestTemplate();
        byte[] carteEtudiants = {4, 2};
        carteEtudiants = restTemplate.postForObject(new URI(URL.BASE_URL+"print/generateficheabsence"),httpEntity, byte[].class);

        model.addAttribute("carteEtudiants",carteEtudiants);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename="+ficheAbsence.getCodeClasse()+"-"+ficheAbsence.getAnnee()+".pdf");
        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF).body(carteEtudiants);

    }

    @PostMapping("/Imprimercarte")
    public ResponseEntity<?> ImprimerCartEtudiant(@ModelAttribute CarteEtudiant carteEtudiant, Model model, HttpSession session) throws URISyntaxException {
        //carteEtudiant.setCodeUser("fhjhh");
        //carteEtudiant.setCodeUser(session.getAttribute("matricule").toString());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", session.getValue("accessToken").toString());
        HttpEntity<CarteEtudiant> httpEntity = new HttpEntity<>(carteEtudiant, headers);
        RestTemplate restTemplate = new RestTemplate();
        byte[] carteEtudiants = restTemplate.postForObject(new URI(URL.BASE_URL+"print/generatecarteetudiant"),httpEntity, byte[].class);
        model.addAttribute("carteEtudiants",carteEtudiants);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename="+carteEtudiant.getCodeClasse()+""+carteEtudiant.getAnnee()+".pdf");
        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF).body(carteEtudiants);
    }

    @PostMapping("/imprimerreleve")
    public ResponseEntity<?> imprimerReleve(@ModelAttribute ReleveNote releve, Model model, HttpSession session) throws URISyntaxException {
        System.out.println( "releve = " + releve.toString());
        System.out.println( "model = " + model.toString());
        System.out.println( ", session attributes = " + session.getAttributeNames().toString());
        Enumeration<String> attributesNames = session.getAttributeNames();
        while(attributesNames.hasMoreElements()) {
            String element=attributesNames.nextElement();
            System.out.println("attribute = " + element + " - " + session.getAttribute(element));
        }
        releve.setDecision("");
        System.out.println(releve.getDecision());
        releve.setAnneeDebut(Integer.parseInt(releve.getDateString().substring(0,4)));
        releve.setMatricules(session.getAttribute("matricule").toString());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", session.getValue("accessToken").toString());
        HttpEntity<ReleveNote> httpEntity = new HttpEntity<>(releve, headers);
        RestTemplate restTemplate = new RestTemplate();
        byte[] releveEtudiants = restTemplate.postForObject(new URI(URL.BASE_URL+"print/generateReleve"),httpEntity, byte[].class);
        model.addAttribute("releveEtudiants",releveEtudiants);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename="+releve.getCodeClasse()+""+(new Date()).getTime()+".pdf");
        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF).body(releveEtudiants);

    }
    @PostMapping("/imprimercertificat")
    public ResponseEntity<?> imprimerCertificat(@ModelAttribute ReleveNote releve, Model model, HttpSession session) throws URISyntaxException {
        releve.setMatricules(session.getAttribute("matricule").toString());
        releve.setAnneeDebut(Integer.parseInt(releve.getDateString().substring(0,4)));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", session.getValue("accessToken").toString());

        HttpEntity<ReleveNote> httpEntity = new HttpEntity<>(releve, headers);
        RestTemplate restTemplate = new RestTemplate();
        byte[] releveEtudiants = restTemplate.postForObject(new URI(URL.BASE_URL+"print/generateCertificat"),httpEntity, byte[].class);
        model.addAttribute("releveEtudiants",releveEtudiants);
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename="+releve.getMatricules()+""+(new Date()).getTime()+".pdf");
        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF).body(releveEtudiants);

    }

    @PostMapping("/imprimerPVSynthese")
    public ResponseEntity<?> imprimerPVSynthese(@ModelAttribute PVSynthese pvSynthese, Model model, HttpSession session) throws URISyntaxException {
        pvSynthese.setMatricules(session.getAttribute("matricule").toString());
        pvSynthese.setAnneeDebut(Integer.parseInt(pvSynthese.getDateString().substring(0,4)));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", session.getValue("accessToken").toString());
        HttpEntity<PVSynthese> httpEntity = new HttpEntity<>(pvSynthese, headers);
        RestTemplate restTemplate = new RestTemplate();
        byte[] releveEtudiants = restTemplate.postForObject(new URI(URL.BASE_URL+"print/generatePVSynthese"),httpEntity, byte[].class);
        model.addAttribute("releveEtudiants",releveEtudiants);
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename="+pvSynthese.getCodeClasse()+""+(new Date()).getTime()+".pdf");
        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF).body(releveEtudiants);

    }


}
