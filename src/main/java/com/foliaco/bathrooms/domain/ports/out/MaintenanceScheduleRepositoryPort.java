package com.foliaco.bathrooms.domain.ports.out;

import com.foliaco.bathrooms.domain.model.MaintenanceSchedule;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MaintenanceScheduleRepositoryPort {
    List<MaintenanceSchedule> getAll();
    MaintenanceSchedule save(MaintenanceSchedule maintenanceSchedule);
    Optional<MaintenanceSchedule> findById(Integer id);
    List<MaintenanceSchedule> findByBathroomIdAndBetweenDateTimes(Integer bathroomId,
                                                               LocalDateTime start,
                                                               LocalDateTime end);
    void delete(Integer id);
}
