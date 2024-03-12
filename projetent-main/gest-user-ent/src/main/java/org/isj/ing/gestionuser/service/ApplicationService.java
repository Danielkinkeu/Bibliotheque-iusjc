package org.isj.ing.gestionuser.service;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing.gestionuser.dto.ApplicationDto;
import org.isj.ing.gestionuser.exception.ErrorInfo;
import org.isj.ing.gestionuser.exception.IsjException;
import org.isj.ing.gestionuser.mapper.ApplicationMapper;
import org.isj.ing.gestionuser.model.Application;
import org.isj.ing.gestionuser.repository.ApplicationRepository;
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
public class ApplicationService {
    @Autowired
    private ApplicationRepository repository;
    @Autowired
    private ApplicationMapper applicationMapper;


    public ApplicationDto save(ApplicationDto applicationDto) {
        Application entity = applicationMapper.toEntity(applicationDto);
        return applicationMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public ApplicationDto findById(Long id) throws IsjException {
        return applicationMapper.toDto(repository.findById(id).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND)));
    }

    public Page<ApplicationDto> findByCondition(ApplicationDto applicationDto, Pageable pageable) {
        Page<Application> entityPage = repository.findAll(pageable);
        List<Application> entities = entityPage.getContent();
        return new PageImpl<>(applicationMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public ApplicationDto update(ApplicationDto applicationDto, Long id) throws IsjException {
        ApplicationDto data = findById(id);
        Application entity = applicationMapper.toEntity(applicationDto);
        BeanUtils.copyProperties(data, entity);
        return save(applicationMapper.toDto(entity));
    }
}