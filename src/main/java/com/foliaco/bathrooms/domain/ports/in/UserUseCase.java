package com.foliaco.bathrooms.domain.ports.in;

import com.foliaco.bathrooms.domain.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserUseCase {

    List<UserDto> getAllUsers();

    Optional<UserDto> getUserByEmail(String email);

    UserDto createUser(UserDto newUser);

    Optional<UserDto> updateUser(UserDto userDto);

}
