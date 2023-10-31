package com.routemind.authserver.security.repository;

import com.routemind.authserver.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AuthUser, Long> {
    AuthUser findByEmailOrLogin(String email, String login);
}
