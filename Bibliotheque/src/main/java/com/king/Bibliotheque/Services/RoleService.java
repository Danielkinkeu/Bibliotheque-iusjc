package com.king.Bibliotheque.Services;

import com.king.Bibliotheque.Exceptions.RoleException;
import com.king.Bibliotheque.Models.Role;
import com.king.Bibliotheque.Exceptions.RoleNotFoundException;
import com.king.Bibliotheque.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class   RoleService {
//    @Autowired
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    //public void addRole( Role role){
      //  if (roleRepository.findByTitle(role.getTitle()) != null) {
        //    throw new RoleException("Title is already taken");
        //}
        //this.roleRepository.save(role);
    //}

    public List<Role> search(){
        return this.roleRepository.findAll();
    }

    public Role getRoleById(int id) {
        Optional<Role> optionalRole = this.roleRepository.findById(id);
        return optionalRole.orElse(null);
    }

    public Role updateRoleDetails(int id, Role updatedRole) {
        Optional<Role> existingRole = roleRepository.findById(id);

        if (existingRole.isPresent()) {
            Role role = existingRole.get();

            // Update role details as needed
            role.setTitle(updatedRole.getTitle());

            // Save the updated role to the database
            return roleRepository.save(role);
        } else {
            throw new RoleNotFoundException("Role not found");
        }
    }
    public void deleteRole(int id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            roleRepository.deleteById(id);
        } else {
            throw new RoleNotFoundException("Role not found");
        }
    }
}
