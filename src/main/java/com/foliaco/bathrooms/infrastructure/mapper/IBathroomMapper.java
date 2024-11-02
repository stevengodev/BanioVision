package com.foliaco.bathrooms.infrastructure.mapper;

import com.foliaco.bathrooms.domain.dto.BathroomDto;
import com.foliaco.bathrooms.infrastructure.entity.BathroomEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IBathroomMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "gender", target = "gender"),
            @Mapping(source = "blockId", target = "blockId"),
            @Mapping(source = "floor", target = "floor")
    })
    BathroomDto toBathroom(BathroomEntity bathroomEntity);

    @InheritInverseConfiguration
    @Mapping(target = "blockEntity", ignore = true)
    @Mapping(target = "cleaningScheduleEntities", ignore = true)
    BathroomEntity toBathroomEntity(BathroomDto bathroom);

    List<BathroomDto> toBathrooms(List<BathroomEntity> bathroomEntities);
}
