package com.foliaco.bathrooms.application.service;

import com.foliaco.bathrooms.domain.dto.AuthUserDto;
import com.foliaco.bathrooms.domain.dto.JwtResponseDto;
import com.foliaco.bathrooms.domain.dto.UserDto;
import com.foliaco.bathrooms.domain.ports.in.AuthUseCase;
import com.foliaco.bathrooms.domain.ports.out.UserRepositoryPort;
import com.foliaco.bathrooms.infrastructure.exception.NotFoundException;
import com.foliaco.bathrooms.infrastructure.exception.PasswordIncorrectException;
import com.foliaco.bathrooms.infrastructure.security.JwtAuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService implements AuthUseCase {

    private final PasswordEncoder passwordEncoder;

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public JwtResponseDto signIn(AuthUserDto authUserDto) {
        Optional<UserDto> userDto = userRepositoryPort.getByEmail(authUserDto.getEmail());


        if (userDto.isEmpty()) {
            throw new NotFoundException("Este usuario no existe");
        }

        if (!passwordEncoder.matches(authUserDto.getPassword(), userDto.get().getPassword())) {
            throw new PasswordIncorrectException();
        }

        return new JwtResponseDto(jwtAuthenticationProvider.createToken(userDto.get()));
    }

    @Override
    public JwtResponseDto signOut(String jwt) {
        String[] authElements = jwt.split(" ");
        return new JwtResponseDto(jwtAuthenticationProvider.deleteToken(authElements[1]));    }
}
