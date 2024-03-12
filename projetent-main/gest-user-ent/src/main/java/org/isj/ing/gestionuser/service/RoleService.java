package org.isj.ing.gestionuser.service;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing.gestionuser.dto.RoleDto;
import org.isj.ing.gestionuser.exception.ErrorInfo;
import org.isj.ing.gestionuser.exception.IsjException;
import org.isj.ing.gestionuser.mapper.RoleMapper;
import org.isj.ing.gestionuser.model.Role;
import org.isj.ing.gestionuser.repository.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
public class RoleService {
    @Autowired
    private RoleRepository repository;
    @Autowired
    private RoleMapper roleMapper;

    public RoleDto save(RoleDto roleDto) {
        Role entity = roleMapper.toEntity(roleDto);
        return roleMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public RoleDto findById(Long id) throws IsjException {
        return roleMapper.toDto(repository.findById(id).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND)));
    }

    public Page<RoleDto> findByCondition(RoleDto roleDto, Pageable pageable) {
        Page<Role> entityPage = repository.findAll(pageable);
        List<Role> entities = entityPage.getContent();
        return new PageImpl<>(roleMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public RoleDto update(RoleDto roleDto, Long id) throws IsjException {
        RoleDto data = findById(id);
        Role entity = roleMapper.toEntity(roleDto);
        BeanUtils.copyProperties(data, entity);
        return save(roleMapper.toDto(entity));
    }
}