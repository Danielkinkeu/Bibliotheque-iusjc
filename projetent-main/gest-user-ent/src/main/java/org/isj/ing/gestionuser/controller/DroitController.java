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

@RequestMapping("/api/droit")
@RestController
@Slf4j
public class DroitController {
    private final DroitService droitService;

    public DroitController(DroitService droitService) {
        this.droitService = droitService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Validated DroitDto droitDto) {
        droitService.save(droitDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DroitDto> findById(@PathVariable("id") Long id) throws IsjException {
        DroitDto droit = droitService.findById(id);
        return ResponseEntity.ok(droit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws IsjException {
        Optional.ofNullable(droitService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data！");
            return new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND);
        });
        droitService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<DroitDto>> pageQuery(DroitDto droitDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<DroitDto> droitPage = droitService.findByCondition(droitDto, pageable);
        return ResponseEntity.ok(droitPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated DroitDto droitDto, @PathVariable("id") Long id) throws IsjException {
        droitService.update(droitDto, id);
        return ResponseEntity.ok().build();
    }
}