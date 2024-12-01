package com.foliaco.bathrooms.domain.ports.in;

import com.foliaco.bathrooms.domain.dto.MaintenanceScheduleDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MaintenanceScheduleUseCase {

    List<MaintenanceScheduleDto> getAllMaintenanceSchedules();
    MaintenanceScheduleDto createMaintenanceSchedule(MaintenanceScheduleDto newCleaningSchedule);
    Optional<MaintenanceScheduleDto> updateMaintenanceSchedule(MaintenanceScheduleDto cleaningSchedule);
    List<MaintenanceScheduleDto> getMaintenanceSchedulesByBathroomIdAndFromDate(Integer bathroomId, LocalDateTime datetime);
    List<MaintenanceScheduleDto> getMaintenanceSchedulesFromDate(LocalDateTime date);
    boolean deleteMaintenanceSchedule(Integer id);

}
