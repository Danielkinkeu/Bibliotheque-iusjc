package org.isj.ing.gestionuser.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserRoleId implements Serializable {
    private static final long serialVersionUID = -4774109266338626662L;
    @Column(name = "code_userid", nullable = false)
    private Long codeUserid;
    @Column(name = "code_role", nullable = false)
    private Long codeRole;

    public Long getCodeRole() {
        return codeRole;
    }

    public void setCodeRole(Long codeRole) {
        this.codeRole = codeRole;
    }

    public Long getCodeUserid() {
        return codeUserid;
    }

    public void setCodeUserid(Long codeUserid) {
        this.codeUserid = codeUserid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeRole, codeUserid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserRoleId entity = (UserRoleId) o;
        return Objects.equals(this.codeRole, entity.codeRole) &&
                Objects.equals(this.codeUserid, entity.codeUserid);
    }
}