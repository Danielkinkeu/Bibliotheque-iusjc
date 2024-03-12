package com.webservice.microservice.test.oauth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "role_droit", indexes = {
        @Index(name = "code_droitid", columnList = "code_droitid")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDroit {
    @EmbeddedId
    private RoleDroitId id;

    @MapsId("codeRoleid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "code_roleid", nullable = false)
    private Role coderoleid;

    @MapsId("codeDroitid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "code_droitid", nullable = false)
    private Droit codeDroitid;

}