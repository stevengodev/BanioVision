package com.foliaco.bathrooms.domain.ports.out;

import com.foliaco.bathrooms.domain.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {
    List<UserDto> getAll();

    Optional<UserDto> getByEmail(String email);

    UserDto save(UserDto newUserDto);

    void delete(String  email);
}
