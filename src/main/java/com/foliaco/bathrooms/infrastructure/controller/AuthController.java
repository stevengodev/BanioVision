package com.foliaco.bathrooms.infrastructure.controller;

import com.foliaco.bathrooms.domain.dto.AuthUserDto;
import com.foliaco.bathrooms.domain.dto.JwtResponseDto;
import com.foliaco.bathrooms.domain.dto.UserDto;
import com.foliaco.bathrooms.domain.ports.in.AuthUseCase;
import com.foliaco.bathrooms.domain.ports.in.UserUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/auth")
public class AuthController {

    private final AuthUseCase iAuthUseCase;

    private final UserUseCase userUseCase;

    @PostMapping("/register")
    public ResponseEntity<UserDto> save(@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userUseCase.createUser(userDto));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JwtResponseDto> signIn(@RequestBody AuthUserDto authUserDto) {
        return ResponseEntity.ok(iAuthUseCase.signIn(authUserDto));
    }

    @PostMapping("/sign-out")
    public ResponseEntity<JwtResponseDto> signOut(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String jwt) {
        return ResponseEntity.ok(iAuthUseCase.signOut(jwt));
    }

}
