package org.isj.ing.gestionuser.service;

import lombok.extern.slf4j.Slf4j;
import org.isj.ing.gestionuser.dto.UserDto;
import org.isj.ing.gestionuser.dto.UserRoleDto;
import org.isj.ing.gestionuser.exception.ErrorInfo;
import org.isj.ing.gestionuser.exception.IsjException;
import org.isj.ing.gestionuser.mapper.RoleMapper;
import org.isj.ing.gestionuser.mapper.UserMapper;
import org.isj.ing.gestionuser.model.Role;
import org.isj.ing.gestionuser.model.User;
import org.isj.ing.gestionuser.model.UserRole;
import org.isj.ing.gestionuser.model.UserRoleId;
import org.isj.ing.gestionuser.repository.UserRepository;
import org.isj.ing.gestionuser.repository.UserRoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMapper roleMapper;



    public UserDto save(UserRoleDto userRoleDto) {
        User user = userMapper.toEntity(userRoleDto.getCodeUserid());
        user.setPassword((new BCryptPasswordEncoder()).encode(userRoleDto.getCodeUserid().getPassword()));
        List<Role> roles = new ArrayList<Role>();
        userRoleDto.getCodeRole().forEach((role) -> {
            try {
                roles.add(roleMapper.toEntity(roleService.findById(role.getId())));
            } catch (IsjException e) {
                e.printStackTrace();
            }
        });
        User entity = repository.save(user);
        roles.forEach((role -> {
            UserRole userRole = new UserRole();
            UserRoleId userRoleId = new UserRoleId();
            userRoleId.setCodeRole(role.getId());
            userRoleId.setCodeUserid(entity.getId());
            userRole.setCodeRole(role);
            userRole.setCodeUserid(entity);
            userRole.setId(userRoleId);
            userRoleRepository.save(userRole);
        }));
        return userMapper.toDto(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }


    public UserDto findById(Long id) throws IsjException {
        return userMapper.toDto(repository.findById(id).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND)));
    }

    public Page<UserDto> findByCondition(UserDto userDto, Pageable pageable) {
        Page<User> entityPage = repository.findAll(pageable);
        List<User> entities = entityPage.getContent();
        return new PageImpl<>(userMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public UserDto update(UserRoleDto userRoleDto) throws IsjException {
        UserDto data = findById(userRoleDto.getCodeUserid().getId());
        User entity = userMapper.toEntity(userRoleDto.getCodeUserid());
        BeanUtils.copyProperties(data, entity);
        return save(userRoleDto);
    }
}