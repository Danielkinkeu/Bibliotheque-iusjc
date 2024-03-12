package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;


import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Classe;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Role;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.RoleRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.IRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zalando.problem.Status;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class RoleServiceImpl implements IRole {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public Long saveRole(Role role) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(role.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = createur;
        role.setCreateur(createur);
        role.setModificateur(modificateur);
        Role roleSave = roleRepository.save(role);
        if (roleSave == null) {
            throw new IsjException("Un problème est survenu lors de l'enregistrement', veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }

        return roleSave.getCode();
    }

    @Override
    public List<Role> listRole() {
       return roleRepository.findAll();
    }

    @Override
    public List<Role> listRoleByUtilisateur(Utilisateur utilisateur) {
        return roleRepository.findByUser(utilisateur);
    }

    @Override
    public int deleteRoleByCode(Long code) {
        roleRepository.deleteById(roleRepository.findById(code).get().getCode());
        return 1;
    }

    @Override
    public Long updateRole(Role role) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(role.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(role.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));

        role.setCreateur(createur);
        role.setModificateur(modificateur);
        Role roleUpdate = roleRepository.save(role);
        if (roleUpdate == null) {
            throw new IsjException("Un problème est survenu lors de la modification', veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return roleUpdate.getCode();
    }

    @Override
    public Role getRoleByCode(Long code) throws IsjException {
        return roleRepository.findById(code).orElseThrow(() -> new IsjException("Role introuvable ", Status.NOT_FOUND));
    }
}
