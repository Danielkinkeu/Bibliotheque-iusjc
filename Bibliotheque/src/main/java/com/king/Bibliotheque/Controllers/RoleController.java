package com.king.Bibliotheque.Controllers;

import com.king.Bibliotheque.Models.Role;
import com.king.Bibliotheque.Services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
@RestController
@RequestMapping(path = "role")
public class RoleController {
    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void addRole(@RequestBody Role role){
        this.roleService.addRole(role);
    }
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Role> getRole(){
        return this.roleService.search();
    }

    @GetMapping(path = "{id}", produces = APPLICATION_JSON_VALUE)
    public Role getRoleById(@PathVariable int id){
        return this.roleService.getRoleById(id);
    }
}
