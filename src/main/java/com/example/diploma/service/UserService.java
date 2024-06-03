package com.example.diploma.service;

import com.example.diploma.dto.UserDto;
import com.example.diploma.entity.Role;
import com.example.diploma.entity.User;
import com.example.diploma.mapper.UserMapper;
import com.example.diploma.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Failed to find user with id: %s".formatted(id)));
    }

    public void addRole(Integer userId, Integer roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Failed to find user with id: %s".formatted(userId)));
        Role role = roleService.getRoleById(roleId);
        user.addRole(role);
        userRepository.save(user);
    }

    public void removeRole(Integer userId, Integer roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Failed to find user with id: %s".formatted(userId)));
        Role role = roleService.getRoleById(roleId);
        user.removeRole(role);
        userRepository.save(user);
    }

}
