package com.webservice.microservice.test.oauth.service;

import com.webservice.microservice.test.oauth.dto.*;
import com.webservice.microservice.test.oauth.mapper.DroitMapper;
import com.webservice.microservice.test.oauth.mapper.RoleMapper;
import com.webservice.microservice.test.oauth.mapper.UserMapper;
import com.webservice.microservice.test.oauth.model.Droit;
import com.webservice.microservice.test.oauth.model.User;
import com.webservice.microservice.test.oauth.model.UserRole;
import com.webservice.microservice.test.oauth.repository.RoleDroitRepository;
import com.webservice.microservice.test.oauth.repository.UserRepository;
import com.webservice.microservice.test.oauth.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleDroitRepository roleDroitRepository;

    @Autowired(required = false)
    private UserRoleRepository userRoleRepository;

    @Autowired(required = false)
    private RoleMapper roleMapper;

/*
    @Autowired
    DroitMapper droitMapper;*/


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        UserDetails userDetails;
        UserDto userDto =  new UserDto();
        UserRoleDto userRoleDto = new UserRoleDto();
        User user = userRepository.findByEmail( username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));
        List<UserRole> userRoles = userRoleRepository.findByCodeuserid(user).stream().collect(Collectors.toList());
        List<Droit> droits = new ArrayList<Droit>();
        System.out.println(userRoles.size());
        List<RoleDto> roles = new ArrayList<RoleDto>();
        userRoles.forEach((userRole) -> {

            RoleDto roleD = new RoleDto();
            roleD.setDescription(userRole.getCodeRole().getDescription());
            roleD.setId(userRole.getCodeRole().getId());
            roleD.setLibelle(userRole.getCodeRole().getLibelle());
            droits.addAll(roleDroitRepository.findByCoderoleid(userRole.getCodeRole()).stream().map(roleDroit -> {
                return roleDroit.getCodeDroitid();
            }).collect(Collectors.toList()));
            roles.add(roleD);
        });
        //problème de reconnaissance de mapper
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setName(user.getName());
        userDto.setId(user.getId());

        //
        List<DroitDtoPrint> droitDtos = droits.stream().map(droit -> {
            DroitDtoPrint droitDtoPrint = new DroitDtoPrint();
            droitDtoPrint.setDescription(droit.getDescription());
            droitDtoPrint.setEcriture(droit.getEcriture());
            droitDtoPrint.setLecture(droit.getLecture());
            droitDtoPrint.setLibelle(droit.getLibelle());
            droitDtoPrint.setModification(droit.getModification());
            droitDtoPrint.setSuppression(droit.getSuppression());
            return droitDtoPrint;
        }).collect(Collectors.toList());

        userRoleDto.setCodeRole(roles);
        userRoleDto.setCodeUserid(userDto);
        userRoleDto.setDroitDtos(droitDtos);
        return  UserPrinciple.build(userRoleDto);
    }

    public User findByEmail(String email) {
       return userRepository.findByEmail(email).get();
    }

    public List<RoleDto> getRole(String email) {
        User user = userRepository.findByEmail( email).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> username or email : " + email));
        List<UserRole> userRoles = userRoleRepository.findByCodeuserid(user).stream().collect(Collectors.toList());
        List<RoleDto> roles = new ArrayList<RoleDto>();
        userRoles.forEach((userRole) -> {
            RoleDto roleD = new RoleDto();
            roleD.setDescription(userRole.getCodeRole().getDescription());
            roleD.setId(userRole.getCodeRole().getId());
            roleD.setLibelle(userRole.getCodeRole().getLibelle());
            roles.add(roleD);
        });
        return roles;
    }
    public List<DroitDtoPrint> getDroit(String username) {
        UserRoleDto userRoleDto = new UserRoleDto();
        User user = userRepository.findByEmail( username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));
        List<UserRole> userRoles = userRoleRepository.findByCodeuserid(user).stream().collect(Collectors.toList());
        List<Droit> droits = new ArrayList<Droit>();
        System.out.println(userRoles.size());
        List<RoleDto> roles = new ArrayList<RoleDto>();
        userRoles.forEach((userRole) -> {

            RoleDto roleD = new RoleDto();
            roleD.setDescription(userRole.getCodeRole().getDescription());
            roleD.setId(userRole.getCodeRole().getId());
            roleD.setLibelle(userRole.getCodeRole().getLibelle());
            droits.addAll(roleDroitRepository.findByCoderoleid(userRole.getCodeRole()).stream().map(roleDroit -> {
                return roleDroit.getCodeDroitid();
            }).collect(Collectors.toList()));
            roles.add(roleD);
        });

        //
        List<DroitDtoPrint> droitDtos = droits.stream().map(droit -> {
            DroitDtoPrint droitDtoPrint = new DroitDtoPrint();
            droitDtoPrint.setDescription(droit.getDescription());
            droitDtoPrint.setEcriture(droit.getEcriture());
            droitDtoPrint.setLecture(droit.getLecture());
            droitDtoPrint.setLibelle(droit.getLibelle());
            droitDtoPrint.setModification(droit.getModification());
            droitDtoPrint.setSuppression(droit.getSuppression());
            return droitDtoPrint;
        }).collect(Collectors.toList());
        return droitDtos;
    }
}
