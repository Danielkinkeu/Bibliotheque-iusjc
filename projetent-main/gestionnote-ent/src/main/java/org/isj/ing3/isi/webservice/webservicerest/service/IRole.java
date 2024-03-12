package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Role;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;

import java.util.List;

public interface IRole {
    Long saveRole(Role role) throws IsjException;
    List<Role> listRole();
    List<Role> listRoleByUtilisateur(Utilisateur utilisateur);
    int deleteRoleByCode(Long code) throws IsjException;
    Long updateRole(Role role) throws IsjException;
    Role getRoleByCode(Long code) throws IsjException;


}
