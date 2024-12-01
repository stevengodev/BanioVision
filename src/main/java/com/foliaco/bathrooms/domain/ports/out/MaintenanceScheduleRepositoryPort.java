package com.foliaco.bathrooms.domain.ports.out;

import com.foliaco.bathrooms.domain.dto.MaintenanceScheduleDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MaintenanceScheduleRepositoryPort {
    List<MaintenanceScheduleDto> getAll();
    MaintenanceScheduleDto save(MaintenanceScheduleDto maintenanceSchedule);
    Optional<MaintenanceScheduleDto> findById(Integer id);
    List<MaintenanceScheduleDto> findByBathroomIdAndStartDateTimeAfter(Integer bathroomId,
                                                                     LocalDateTime start);
    List<MaintenanceScheduleDto> findAllByStartDateTimeAfter(LocalDateTime start);
    void delete(Integer id);
}
