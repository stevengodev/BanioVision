package com.foliaco.bathrooms.domain.ports.in;

import com.foliaco.bathrooms.domain.dto.MaintenanceSchedule;

import java.util.List;
import java.util.Optional;

public interface MaintenanceScheduleUseCase {

    List<MaintenanceSchedule> getAllMaintenanceSchedules();
    MaintenanceSchedule createMaintenanceSchedule(MaintenanceSchedule newCleaningSchedule);
    Optional<MaintenanceSchedule> updateMaintenanceSchedule(MaintenanceSchedule cleaningSchedule);
    List<MaintenanceSchedule> getTodayMaintenanceSchedulesByBathroomId(Integer bathroomId);
    boolean deleteMaintenanceSchedule(Integer id);

}
