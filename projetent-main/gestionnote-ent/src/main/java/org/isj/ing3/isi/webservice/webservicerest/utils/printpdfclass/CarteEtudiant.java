package org.isj.ing3.isi.webservice.webservicerest.utils.printpdfclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarteEtudiant {

    private String nom;
    private String prenom;
    private String date_naissance;
    private String telephone_du_pere;
    private String telephone_de_la_mere;
    private String matricule;
    private String lieu_naissance;
    private String specialite;
    private String filiere;
    private String niveau;
    private Integer annee;

}
