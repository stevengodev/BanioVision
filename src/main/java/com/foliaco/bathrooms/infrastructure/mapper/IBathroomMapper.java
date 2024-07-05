package com.foliaco.bathrooms.infrastructure.mapper;

import com.foliaco.bathrooms.domain.model.Bathroom;
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
    Bathroom toBathroom(BathroomEntity bathroomEntity);

    @InheritInverseConfiguration
    @Mapping(target = "blockEntity", ignore = true)
    @Mapping(target = "cleaningScheduleEntities", ignore = true)
    BathroomEntity toBathroomEntity(Bathroom bathroom);

    List<Bathroom> toBathrooms(List<BathroomEntity> bathroomEntities);
}
