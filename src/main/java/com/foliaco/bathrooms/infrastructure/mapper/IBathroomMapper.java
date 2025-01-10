package com.foliaco.bathrooms.infrastructure.mapper;

import com.foliaco.bathrooms.domain.dto.BathroomDto;
import com.foliaco.bathrooms.domain.dto.BathroomRequestDto;
import com.foliaco.bathrooms.domain.dto.BathroomResponseDto;
import com.foliaco.bathrooms.infrastructure.entity.BathroomEntity;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IBathroomMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "blockId", target = "blockId")
    @Mapping(source = "floor", target = "floor")
    BathroomDto toBathroom(BathroomEntity bathroomEntity);

    @InheritInverseConfiguration
    @Mapping(target = "blockEntity", ignore = true)
    @Mapping(target = "cleaningScheduleEntities", ignore = true)
    BathroomEntity toBathroomEntity(BathroomDto bathroom);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cleaningScheduleEntities", ignore = true)
    @Mapping(target = "blockEntity", ignore = true)
    @Mapping(target = "bathroomIncidentEntities", ignore = true)
    BathroomEntity toBathroomEntity(BathroomRequestDto bathroom);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "blockEntity.name", target = "nameBlock")
    @Mapping(source = "floor", target = "floor")
    BathroomResponseDto toBathroomResponse(BathroomEntity bathroomEntity);

}
