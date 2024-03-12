package com.webservice.microservice.test.oauth.reponse;

import com.webservice.microservice.test.oauth.dto.DroitDtoPrint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;


import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private String username;
    private Collection<? extends GrantedAuthority> authorities;
    private List<DroitDtoPrint> droitDtoPrint;
    private String matricule;
    private String filiere;
    private String classe;
    private String name;
    private Date expired = new Date((new Date()).getTime() + (new Date(3600*18000)).getTime());

    public JwtResponse(String accessToken, String tokenType, String username, Collection<? extends GrantedAuthority> authorities, List<DroitDtoPrint> droitDtoPrint, String matricule, String filiere, String classe, String name) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.username = username;
        this.authorities = authorities;
        this.droitDtoPrint = droitDtoPrint;
        this.matricule = matricule;
        this.filiere = filiere;
        this.classe = classe;
        this.name = name;
    }
}
