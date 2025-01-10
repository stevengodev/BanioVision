package com.foliaco.bathrooms.infrastructure.adapter;

import com.foliaco.bathrooms.domain.dto.UserDto;
import com.foliaco.bathrooms.domain.ports.out.UserRepositoryPort;
import com.foliaco.bathrooms.infrastructure.entity.UserEntity;
import com.foliaco.bathrooms.infrastructure.mapper.IUserMapper;
import com.foliaco.bathrooms.infrastructure.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final IUserRepository userRepository;

    private final IUserMapper userMapper;

    @Override
    public List<UserDto> getAll() {
        return userMapper.toUserDtoList(userRepository.findAll());
    }

    @Override
    public Optional<UserDto> getByEmail(String email) {
        return userRepository.findByEmail(email).map(userMapper::toUserDto);
    }

    @Override
    public UserDto save(UserDto newUserDto) {
        UserEntity userEntity = userMapper.toUserEntity( newUserDto );
        return userMapper.toUserDto( userRepository.save(userEntity) );
    }

    @Override
    public void delete(String email) {
        userRepository.deleteByEmail(email);
    }
}
