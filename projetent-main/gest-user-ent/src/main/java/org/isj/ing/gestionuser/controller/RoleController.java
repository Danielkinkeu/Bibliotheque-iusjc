package org.isj.ing.gestionuser.controller;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing.gestionuser.dto.*;
import org.isj.ing.gestionuser.exception.*;
import org.isj.ing.gestionuser.exception.*;
import org.isj.ing.gestionuser.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/api/role")
@RestController
@Slf4j
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated RoleDto roleDto) {
        roleService.save(roleDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> findById(@PathVariable("id") Long id) throws IsjException {
        RoleDto role = roleService.findById(id);
        return ResponseEntity.ok(role);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws IsjException {
        Optional.ofNullable(roleService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data！");
            return new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND);
        });
        roleService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<RoleDto>> pageQuery(RoleDto roleDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<RoleDto> rolePage = roleService.findByCondition(roleDto, pageable);
        return ResponseEntity.ok(rolePage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated RoleDto roleDto, @PathVariable("id") Long id) throws IsjException {
        roleService.update(roleDto, id);
        return ResponseEntity.ok().build();
    }
}