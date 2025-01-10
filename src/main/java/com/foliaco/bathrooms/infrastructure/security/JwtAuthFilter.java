package com.foliaco.bathrooms.infrastructure.security;

import com.foliaco.bathrooms.infrastructure.exception.UnauthorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    private List<String> urlsToSkip = List.of("/api/auth/sign-in",
            "/swagger-ui.html", "/swagger-ui", "/api-docs");

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        System.out.println("en esta peticion se rompe");
        System.out.println(request.getRequestURI());

        // Si la URL de la solicitud coincide con alguna de las URLs en la lista urlsToSkip, el filtro no se aplicará a esa solicitud,
        return urlsToSkip.stream().anyMatch(url -> request.getRequestURI().contains(url));
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null) {
            throw new UnauthorizedException();
        }

        String[] authElements = header.split(" ");

        if (authElements.length != 2 || !"Bearer".equals(authElements[0])) {
            throw new UnauthorizedException();
        }

        try {
            Authentication auth = jwtAuthenticationProvider.validateToken(authElements[1]);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (RuntimeException e) {
            SecurityContextHolder.clearContext();
            throw new RuntimeException(e);
        }

        filterChain.doFilter(request, response);

    }
}
