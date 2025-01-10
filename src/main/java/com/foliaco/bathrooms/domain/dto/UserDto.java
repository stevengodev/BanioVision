package com.foliaco.bathrooms.domain.dto;

import com.foliaco.bathrooms.domain.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {
    private String names;
    private String lastNames;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
