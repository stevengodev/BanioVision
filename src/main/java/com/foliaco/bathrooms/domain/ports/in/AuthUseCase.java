package com.foliaco.bathrooms.domain.ports.in;

import com.foliaco.bathrooms.domain.dto.AuthUserDto;
import com.foliaco.bathrooms.domain.dto.JwtResponseDto;

public interface AuthUseCase {

    JwtResponseDto signIn(AuthUserDto authUserDto);

    JwtResponseDto signOut(String jwt);

}
