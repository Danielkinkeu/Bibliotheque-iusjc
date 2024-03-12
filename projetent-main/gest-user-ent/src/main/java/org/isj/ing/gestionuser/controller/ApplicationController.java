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

@RequestMapping("/api/application")
@RestController
@Slf4j
public class ApplicationController {
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated ApplicationDto applicationDto) {
        applicationService.save(applicationDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationDto> findById(@PathVariable("id") Long id) throws IsjException {
        ApplicationDto application = applicationService.findById(id);
        return ResponseEntity.ok(application);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws IsjException {
        Optional.ofNullable(applicationService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data！");
            return new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND);
        });
        applicationService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<ApplicationDto>> pageQuery(ApplicationDto applicationDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ApplicationDto> applicationPage = applicationService.findByCondition(applicationDto, pageable);
        return ResponseEntity.ok(applicationPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated ApplicationDto applicationDto, @PathVariable("id") Long id) throws IsjException {
        applicationService.update(applicationDto, id);
        return ResponseEntity.ok().build();
    }
}