package com.foliaco.bathrooms.infrastructure.mapper;

import com.foliaco.bathrooms.domain.dto.BathroomIncidentDto;
import com.foliaco.bathrooms.domain.dto.BathroomIncidentResponseDto;
import com.foliaco.bathrooms.infrastructure.entity.BathroomIncidentEntity;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IBathroomIncidentMapper {

    @Mappings({
            @Mapping(source = "id.bathroomId", target = "idBathroom"),
            @Mapping(source = "id.incidentId", target = "idIncident"),
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "status", target = "status")
    })
    BathroomIncidentDto toBathroomIncidentDto(BathroomIncidentEntity bathroomIncidentEntity);

    @InheritInverseConfiguration
    @Mapping(target = "incidentEntity", ignore = true)
    @Mapping(target = "bathroomEntity", ignore = true)
    BathroomIncidentEntity toBathroomIncidentEntity(BathroomIncidentDto bathroomIncidentDto);

    @Mappings({
            @Mapping(source = "bathroomEntity.gender", target = "genderBathroom"),
            @Mapping(source = "bathroomEntity.blockEntity.name", target = "nameBlock"),
            @Mapping(source = "bathroomEntity.floor", target = "floor"),
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "incidentEntity.problem", target = "problem"),
            @Mapping(source = "incidentEntity.comment", target = "comment")
    })
    BathroomIncidentResponseDto toBathroomIncidentResponseDto(BathroomIncidentEntity bathroomIncidentEntity);

}
