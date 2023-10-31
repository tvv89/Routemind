package com.routemind.authserver.util;

import com.routemind.authserver.model.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Utility class for common operations.
 *
 * This utility class provides common methods that are used across the application, including building SimpleGrantedAuthorities.
 * It is used to convert user roles into Spring Security SimpleGrantedAuthority objects.
 *
 * @author Volodymyr Tymchuk
 * @version 1.0
 */
public class CommonUtils {

	/**
	 * Builds a list of SimpleGrantedAuthority objects from user roles.
	 *
	 * This method converts a set of user roles into a list of SimpleGrantedAuthority objects,
	 * which are used for Spring Security authorization.
	 *
	 * @param userRoles The set of user roles to convert.
	 * @return List of SimpleGrantedAuthority objects representing user roles.
	 */
	public static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final Set<Role> userRoles) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (Role userRole : userRoles) {
			authorities.add(new SimpleGrantedAuthority(userRole.getName().toUpperCase()));
		}
		return authorities;
	}
}