package com.foliaco.bathrooms.infrastructure.mapper;

import com.foliaco.bathrooms.domain.dto.MaintenanceScheduleDto;
import com.foliaco.bathrooms.infrastructure.entity.MaintenanceScheduleEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IMaintenanceScheduleMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "startDateTime", target = "startDateTime"),
            @Mapping(source = "endDateTime", target = "endDateTime"),
            @Mapping(source = "bathroomId", target = "bathroomId")
    })
    MaintenanceScheduleDto toMaintenanceSchedule(MaintenanceScheduleEntity maintenanceScheduleEntity);

    @InheritInverseConfiguration
    @Mapping(target = "bathroomEntity", ignore = true)
    MaintenanceScheduleEntity toMaintenanceScheduleEntity(MaintenanceScheduleDto maintenanceSchedule);

    List<MaintenanceScheduleDto> toMaintenanceScheduleList(List<MaintenanceScheduleEntity> maintenanceScheduleEntities);


}
