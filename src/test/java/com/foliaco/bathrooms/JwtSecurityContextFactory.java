package com.foliaco.bathrooms;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JwtSecurityContextFactory implements WithSecurityContextFactory<WithMockJwt> {

    @Override
    public SecurityContext createSecurityContext(WithMockJwt customJwt) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        // Crear un usuario simulado con roles
        List<GrantedAuthority> authorities = Arrays.stream(customJwt.roles())
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());

        UserDetails userDetails = new User(customJwt.username(), "password", authorities);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);

        context.setAuthentication(authentication);
        return context;
    }
}
