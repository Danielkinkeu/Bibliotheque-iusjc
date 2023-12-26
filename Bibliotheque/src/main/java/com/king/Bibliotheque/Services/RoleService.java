package com.king.Bibliotheque.Services;

import com.king.Bibliotheque.Models.Role;
import com.king.Bibliotheque.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
//    @Autowired
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void addRole( Role role){
//        Role rol = this.roleRepository.findByTitle()
//        this.roleRepository.findByRole(title:role.getTitle())
        this.roleRepository.save(role);
    }

    public List<Role> search(){
        return this.roleRepository.findAll();
    }

    public Role getRoleById(int id) {
        Optional<Role> optionalRole = this.roleRepository.findById(id);
        return optionalRole.orElse(null);
    }
}
