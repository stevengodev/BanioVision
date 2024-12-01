package com.foliaco.bathrooms.infrastructure.mapper;

import com.foliaco.bathrooms.domain.dto.IncidentDto;
import com.foliaco.bathrooms.infrastructure.entity.IncidentEntity;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IIncidentMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "problem", target = "problem"),
            @Mapping(source = "comment", target = "comment")
    })
    IncidentDto toIncidentDto(IncidentEntity incidentEntity);

    @InheritInverseConfiguration
    IncidentEntity toIncidentEntity(IncidentDto incident);

}
