package org.isj.ing.gestionuser.controller;

import lombok.extern.slf4j.Slf4j;

import org.isj.ing.gestionuser.dto.*;
import org.isj.ing.gestionuser.exception.*;
import org.isj.ing.gestionuser.exception.*;
import org.isj.ing.gestionuser.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/user")
@RestController
@Slf4j
public class UserController {
    @Autowired
    private  UserService userService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated UserRoleDto userDto) {
        userService.save(userDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/savetest")
    public ResponseEntity<Void> saveUserTest() {
        UserDto userDto = new UserDto();
        userDto.setEmail("etiennenkot2@gmail.com");
        userDto.setPassword("ent2022");
        userDto.setName("nkot");
        UserRoleDto userRoleDto = new UserRoleDto();
        userRoleDto.setCodeUserid(userDto);
        List<RoleDto> roles = new ArrayList<>();
        RoleDto roleDto = new RoleDto();
        roleDto.setId(102755L);
        roles.add(roleDto);
        userRoleDto.setCodeRole(roles);
        userService.save(userRoleDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable("id") Long id) throws IsjException {
        UserDto user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws IsjException {
        Optional.ofNullable(userService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data！");
            return new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND);
        });
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<UserDto>> pageQuery(UserDto userDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<UserDto> userPage = userService.findByCondition(userDto, pageable);
        return ResponseEntity.ok(userPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated UserRoleDto userDto, @PathVariable("id") Long id) throws IsjException {
        userService.update(userDto);
        return ResponseEntity.ok().build();
    }
}