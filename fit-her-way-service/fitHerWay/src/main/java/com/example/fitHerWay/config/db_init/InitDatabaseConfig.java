package com.example.fitHerWay.config.db_init;

import com.example.fitHerWay.constant.SystemMessage;
import com.example.fitHerWay.role.entity.Role;
import com.example.fitHerWay.role.repository.RoleRepository;
import com.example.fitHerWay.users.entity.User;
import com.example.fitHerWay.users.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class InitDatabaseConfig {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private static final String PASSWORD = "Admin@123";

    @PostConstruct
    public void initDatabase() {
        log.info("Initializing database");
        if(roleRepository.findAll().isEmpty()) {
            log.info("Creating roles");
            List<Role> roles = List.of(
                    new Role(null, "ADMIN", "Super Admin Role"),
                    new Role(null, "USER", "Admin Role")
            );
            roleRepository.saveAll(roles);
            log.info("Roles created");
        }

        if(userRepository.findAll().isEmpty()) {
            log.info("Creating super admin role user");
            User user = new User();
            user.setUserName("superadmin");
            user.setEmail("admin@gmail.com");
            user.setPhone("9840757252");
            user.setAddress("Kathmandu, Nepal");
            user.setPassword(new BCryptPasswordEncoder().encode(PASSWORD));
            Role role = roleRepository.findByName(Role.ROLE_ADMIN).orElseThrow(
                    () -> new RuntimeException(SystemMessage.ROLE_NOT_FOUND)
            );
            user.setRole(List.of(role));
            user.setIsActive(true);
            user.setIsDeleted(false);
            user.setIsVerified(true);
            userRepository.save(user);
            log.info("Super admin role user created");
        }

    }
}

