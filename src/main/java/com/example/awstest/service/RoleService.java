package com.example.awstest.service;

import com.example.awstest.domain.Role;
import com.example.awstest.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void createRole(Role role) {
        roleRepository.save(role);
    }

    public Optional<Role> getRoleByName(String name) {
        return roleRepository.findByName(name);
    }
}