package com.webservice.microservice.test.oauth.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webservice.microservice.test.oauth.dto.RoleDto;
import com.webservice.microservice.test.oauth.dto.UserRoleDto;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

public class UserPrinciple implements UserDetails {
    private String id;

    private String username;

    @JsonIgnore
    private String password;

    private Collection authorities;

    private static Set<String> roles = new HashSet<>();

    public UserPrinciple(String name, String password, Collection authorities) {
        this.username = name;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrinciple build(UserRoleDto user) {
        List<RoleDto> roleUser = user.getCodeRole();

        //Collections.addAll(roles, roleUser.toArray());
        //SimpleGrantedAuthority authorities = new SimpleGrantedAuthority(role.getIntitule());
        List<SimpleGrantedAuthority> authorities = roleUser.stream().map(role ->
                new SimpleGrantedAuthority(role.getLibelle())).collect(Collectors.toList());

        return new UserPrinciple(
                user.getCodeUserid().getEmail(),
                user.getCodeUserid().getPassword(),
                authorities
        );
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Collection getAuthorities() {
        return authorities;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }
}
