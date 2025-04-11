package com.example.garbandgo.service;
import com.example.garbandgo.entities.Role;
import com.example.garbandgo.repositories.RoleRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getRolesRepository() {
        return roleRepository.findAll();
    }

    public void saveRoles(Role role) {
        roleRepository.save(role);
    }
}