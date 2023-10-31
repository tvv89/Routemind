package com.routemind.authserver.security.service;

import com.routemind.authserver.model.AuthUser;
import com.routemind.authserver.security.repository.UserRepository;
import com.routemind.authserver.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * CustomUserDetailService is a service class that implements the Spring Security UserDetailsService interface.
 * It provides custom logic for loading user details by username and is used for authentication and authorization
 * purposes within the application.
 *
 * This service class retrieves user information from a UserRepository and constructs a UserDetails object based on
 * the retrieved AuthUser entity. If the user is not found, it throws a UsernameNotFoundException.
 *
 * @author Volodymyr Tymchuk
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Loads user details by username for authentication and authorization.
     *
     * @param username The username (email or login) of the user to load.
     * @return UserDetails representing the user, or throws a UsernameNotFoundException if the user is not found.
     * @throws UsernameNotFoundException If the specified username is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final AuthUser authUser = userRepository.findByEmailOrLogin(username, username);
        if (authUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return User.withUsername(authUser.getEmail())
                .password(authUser.getPassword())
                .authorities(CommonUtils.buildSimpleGrantedAuthorities(authUser.getRoles()))
                .build();
    }
}