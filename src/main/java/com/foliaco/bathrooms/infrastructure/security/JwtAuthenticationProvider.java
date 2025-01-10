package com.foliaco.bathrooms.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.foliaco.bathrooms.domain.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

@Component
public class JwtAuthenticationProvider {

    @Value("${jwt.secret.key}")
    private String secretKey;

    private HashMap<String, UserDto> listToken = new HashMap<>();

    public String createToken(UserDto userJwt) {

        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1 hora en milisegundos

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        String tokenCreated = JWT.create()
                .withClaim("names", userJwt.getNames())
                .withClaim("lastNames", userJwt.getLastNames())
                .withClaim("email", userJwt.getEmail())
                .withClaim("role",userJwt.getRole().name())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(algorithm);

        listToken.put(tokenCreated, userJwt);
        return tokenCreated;
    }

    public Authentication validateToken(String token) throws AuthenticationException {

        JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token);

        UserDto exists = listToken.get(token);
        if (exists == null) {
            throw new BadCredentialsException("Usuario no registrado.");
        }

        HashSet<SimpleGrantedAuthority> rolesAndAuthorities = new HashSet<>();
        rolesAndAuthorities.add(new SimpleGrantedAuthority("ROLE_" + exists.getRole()));

        return new UsernamePasswordAuthenticationToken(exists, token, rolesAndAuthorities);
    }

    public String deleteToken(String jwt) {

        if (!listToken.containsKey(jwt)) {
            return "No existe el token";
        }

        listToken.remove(jwt);
        return "Sesión cerrada exitosamente";
    }

}
