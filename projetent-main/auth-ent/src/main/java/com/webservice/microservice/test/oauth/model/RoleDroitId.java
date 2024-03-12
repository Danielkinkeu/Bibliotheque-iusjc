package com.webservice.microservice.test.oauth.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RoleDroitId implements Serializable {
    private static final long serialVersionUID = 3184077342321722335L;
    @Column(name = "code_roleid", nullable = false)
    private Long codeRoleid;
    @Column(name = "code_droitid", nullable = false)
    private Long codeDroitid;

    public Long getCodeDroitid() {
        return codeDroitid;
    }

    public void setCodeDroitid(Long codeDroitid) {
        this.codeDroitid = codeDroitid;
    }

    public Long getCodeRoleid() {
        return codeRoleid;
    }

    public void setCodeRoleid(Long codeRoleid) {
        this.codeRoleid = codeRoleid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeDroitid, codeRoleid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RoleDroitId entity = (RoleDroitId) o;
        return Objects.equals(this.codeDroitid, entity.codeDroitid) &&
                Objects.equals(this.codeRoleid, entity.codeRoleid);
    }
}