package com.webservice.microservice.test.oauth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_role", indexes = {
        @Index(name = "code_role", columnList = "code_role")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    @EmbeddedId
    private UserRoleId id;

    @MapsId("codeUserid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "code_userid", nullable = false)
    private User codeuserid;

    @MapsId("codeRole")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "code_role", nullable = false)
    private Role codeRole;

}