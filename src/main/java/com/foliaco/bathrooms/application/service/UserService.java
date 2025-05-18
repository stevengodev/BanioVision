package com.foliaco.bathrooms.application.service;

import com.foliaco.bathrooms.application.validator.EmailValidator;
import com.foliaco.bathrooms.domain.dto.BlockDto;
import com.foliaco.bathrooms.domain.dto.UserDto;
import com.foliaco.bathrooms.domain.enums.Role;
import com.foliaco.bathrooms.domain.ports.in.UserUseCase;
import com.foliaco.bathrooms.domain.ports.out.UserRepositoryPort;
import com.foliaco.bathrooms.infrastructure.exception.EmailValidationException;
import com.foliaco.bathrooms.infrastructure.exception.UserExistsException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> getAllUsers() {
        return userRepositoryPort.getAll();
    }

    @Override
    public Optional<UserDto> getUserByEmail(String email) {
        return userRepositoryPort.getByEmail(email);
    }

    @Override
    public UserDto createUser(UserDto newUser) {

        if (!EmailValidator.isValidEmail(newUser.getEmail())) {
            throw new EmailValidationException();
        }

        if (userRepositoryPort.getByEmail(newUser.getEmail()).isPresent()){
            throw new UserExistsException();
        }

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        newUser.setRole(Role.USER);
        userRepositoryPort.save(newUser);

        return newUser;
    }

    @Override
    public Optional<UserDto> updateUser(UserDto userDto) {
        Optional<UserDto> foundUser = userRepositoryPort.getByEmail(userDto.getEmail());
        return foundUser.isEmpty() ? Optional.empty() : Optional.of(userRepositoryPort.save(userDto));
    }

}
