package org.isj.ing.gestionuser.service;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing.gestionuser.dto.DroitDto;
import org.isj.ing.gestionuser.exception.ErrorInfo;
import org.isj.ing.gestionuser.exception.IsjException;
import org.isj.ing.gestionuser.mapper.DroitMapper;
import org.isj.ing.gestionuser.model.Droit;
import org.isj.ing.gestionuser.repository.DroitRepository;
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
public class DroitService {
    @Autowired
    private DroitRepository repository;
    @Autowired
    private DroitMapper droitMapper;


    public DroitDto save(DroitDto droitDto) {
        Droit entity = droitMapper.toEntity(droitDto);
        return droitMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public DroitDto findById(Long id) throws IsjException {
        return droitMapper.toDto(repository.findById(id).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND)));
    }

    public Page<DroitDto> findByCondition(DroitDto droitDto, Pageable pageable) {
        Page<Droit> entityPage = repository.findAll(pageable);
        List<Droit> entities = entityPage.getContent();
        return new PageImpl<>(droitMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public DroitDto update(DroitDto droitDto, Long id) throws IsjException {
        DroitDto data = findById(id);
        Droit entity = droitMapper.toEntity(droitDto);
        BeanUtils.copyProperties(data, entity);
        return save(droitMapper.toDto(entity));
    }
}