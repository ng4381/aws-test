package com.example.awstest;

import com.example.awstest.domain.Role;
import com.example.awstest.domain.User;
import com.example.awstest.service.RoleService;
import com.example.awstest.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.example.awstest.util.Constants.*;
import static com.example.awstest.util.Constants.ADMIN_PASSWORD;

@Component
@Slf4j
public class DatabaseLoader implements CommandLineRunner {

    private UserService userService;
    private RoleService roleService;

    public DatabaseLoader(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String adminPassword = "{bcrypt}" + encoder.encode(ADMIN_PASSWORD);
        String userPassword = "{bcrypt}" + encoder.encode(USER_PASSWORD);

        createRoleIfNotExist(ADMIN_ROLE);
        createRoleIfNotExist(USER_ROLE);

        createUserIfNotExist(USER_USERNAME, userPassword, USER_ROLE);
        createUserIfNotExist(ADMIN_USERNAME, adminPassword, ADMIN_ROLE);
    }

    private void createRoleIfNotExist(String name) {
        Optional<Role> roleOptional = roleService.getRoleByName(name);
        if(roleOptional.isEmpty()) {
            log.info("Creating role ... " + name);
            Role role = new Role();
            role.setName(name);
            roleService.createRole(role);
        }
    }

    private void createUserIfNotExist(String userName, String password, String roleName) {
        Optional<User> adminUserOptional = userService.getUserByName(userName);
        if(!adminUserOptional.isPresent()) {
            log.info("Creating user ... " + userName);
            User adminUser = new User();
            adminUser.setName(userName);
            adminUser.addRole(roleService.getRoleByName(roleName).get());
            adminUser.setPassword(password);
            userService.createUser(adminUser);
        }
    }
}