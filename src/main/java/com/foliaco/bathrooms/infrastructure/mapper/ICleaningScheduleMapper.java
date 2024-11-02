package com.foliaco.bathrooms.infrastructure.mapper;

import com.foliaco.bathrooms.domain.dto.CleaningScheduleDto;
import com.foliaco.bathrooms.infrastructure.entity.CleaningScheduleEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ICleaningScheduleMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "startDateTime", target = "startDateTime"),
            @Mapping(source = "endDateTime", target = "endDateTime"),
            @Mapping(source = "bathroomId", target = "bathroomId"),
    })
    CleaningScheduleDto toCleaningSchedule(CleaningScheduleEntity cleaningScheduleEntity);

    @InheritInverseConfiguration
    @Mapping(target = "bathroomEntity", ignore = true)
    CleaningScheduleEntity toCleaningScheduleEntity(CleaningScheduleDto cleaningSchedule);

    List<CleaningScheduleDto> toCleaningScheduleList(List<CleaningScheduleEntity> cleaningScheduleEntities);

}
