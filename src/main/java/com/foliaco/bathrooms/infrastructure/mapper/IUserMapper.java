package com.foliaco.bathrooms.infrastructure.mapper;

import com.foliaco.bathrooms.domain.dto.UserDto;
import com.foliaco.bathrooms.infrastructure.entity.UserEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IUserMapper {

    @Mapping(source = "names", target = "names")
    @Mapping(source = "lastNames", target = "lastNames")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "role", target = "role")
    UserDto toUserDto(UserEntity userEntity);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    UserEntity toUserEntity(UserDto userDto);

    List<UserDto> toUserDtoList(List<UserEntity> userEntities);
}
