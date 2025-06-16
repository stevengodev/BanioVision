package com.foliaco.bathrooms.application.service;

import com.foliaco.bathrooms.domain.dto.AuthUserDto;
import com.foliaco.bathrooms.domain.dto.UserDto;
import com.foliaco.bathrooms.domain.ports.out.UserRepositoryPort;
import com.foliaco.bathrooms.infrastructure.exception.NotFoundException;
import com.foliaco.bathrooms.infrastructure.exception.PasswordIncorrectException;
import com.foliaco.bathrooms.infrastructure.security.JwtAuthenticationProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Mock
    private UserRepositoryPort userRepositoryPort;

    @InjectMocks
    private AuthService authService;

    @Test
    void givenEmailAndPassword_WhenCredentialsAreValid_ShouldReturnJwt(){

        AuthUserDto authUserDto = new AuthUserDto();
        authUserDto.setEmail("jhondoe@gmail.com");
        authUserDto.setPassword("password");

        UserDto userDto = new UserDto();
        userDto.setNames("jhon");
        userDto.setLastNames("doe");
        userDto.setEmail("jhon@gmail.com");
        userDto.setPassword("password");

        when( userRepositoryPort.getByEmail( authUserDto.getEmail() ) ).thenReturn( Optional.of(userDto));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(jwtAuthenticationProvider.createToken(any( UserDto.class ))).thenReturn( "jwtvalue");

        assertNotNull(authService.signIn(authUserDto).getJwt());
        assertEquals("jwtvalue", authService.signIn(authUserDto).getJwt());
        verify(userRepositoryPort, times(2)).getByEmail( authUserDto.getEmail() );

    }

    @Test
    void givenEmailExists_WhenCredentialsAreNotValid_ShouldThrowException(){
        AuthUserDto authUserDto = new AuthUserDto();
        authUserDto.setEmail("jhondoe@gmail.com");
        authUserDto.setPassword("password");

        when( userRepositoryPort.getByEmail( authUserDto.getEmail() ) ).thenReturn( Optional.empty());

        assertThrows(NotFoundException.class, () -> authService.signIn(authUserDto));
        verify(userRepositoryPort, times(1)).getByEmail( authUserDto.getEmail() );

    }

    @Test
    void givenPasswordIncorrect_WhenCredentialsAreNotValid_ShouldThrowException(){
        AuthUserDto authUserDto = new AuthUserDto();
        authUserDto.setEmail("jhondoe@gmail.com");
        authUserDto.setPassword("password");

        UserDto userDto = new UserDto();
        userDto.setNames("jhon");
        userDto.setLastNames("doe");
        userDto.setEmail("jhon@gmail.com");
        userDto.setPassword("password");

        when( userRepositoryPort.getByEmail( authUserDto.getEmail() ) ).thenReturn( Optional.of(userDto));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        assertThrows(PasswordIncorrectException.class, () -> authService.signIn(authUserDto));
        verify(passwordEncoder, times(1)).matches( anyString(), anyString() );

    }

    @Test
    void givenJwt_WhenDeleteJwt_ShouldReturnDeletedToken(){

        String bearerToken = "Bearer token";

        when(jwtAuthenticationProvider.deleteToken("token")).thenReturn("deletedtoken");

        String deletedJwt = authService.signOut(bearerToken).getJwt();

        assertEquals("deletedtoken", deletedJwt);
        verify(jwtAuthenticationProvider).deleteToken("token");

    }


}
