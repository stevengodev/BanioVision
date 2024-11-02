package com.foliaco.bathrooms.application.service;

import com.foliaco.bathrooms.domain.dto.MaintenanceSchedule;
import com.foliaco.bathrooms.domain.ports.in.MaintenanceScheduleUseCase;
import com.foliaco.bathrooms.domain.ports.out.MaintenanceScheduleRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceScheduleService implements MaintenanceScheduleUseCase {

    private final MaintenanceScheduleRepositoryPort maintenanceScheduleRepositoryPort;

    @Autowired
    public MaintenanceScheduleService(MaintenanceScheduleRepositoryPort maintenanceScheduleRepositoryPort) {
        this.maintenanceScheduleRepositoryPort = maintenanceScheduleRepositoryPort;
    }

    @Override
    public List<MaintenanceSchedule> getAllMaintenanceSchedules() {
        return maintenanceScheduleRepositoryPort.getAll();
    }

    @Override
    public MaintenanceSchedule createMaintenanceSchedule(MaintenanceSchedule newCleaningSchedule) {
        return maintenanceScheduleRepositoryPort.save(newCleaningSchedule);
    }

    @Override
    public Optional<MaintenanceSchedule> updateMaintenanceSchedule(MaintenanceSchedule cleaningSchedule) {

        Optional<MaintenanceSchedule> maintenanceScheduleFound = maintenanceScheduleRepositoryPort.findById(
                cleaningSchedule.getId()
        );

        return maintenanceScheduleFound.isPresent()
                ? Optional.of(maintenanceScheduleRepositoryPort.save(cleaningSchedule))
                : Optional.empty();
    }

    @Override
    public List<MaintenanceSchedule> getTodayMaintenanceSchedulesByBathroomId(Integer bathroomId) {

        LocalDateTime startOfDay = LocalDateTime.now().with(LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.now().with(LocalTime.MAX);

        return maintenanceScheduleRepositoryPort.findByBathroomIdAndBetweenDateTimes(bathroomId, startOfDay, endOfDay);
    }

    @Override
    public boolean deleteMaintenanceSchedule(Integer id) {

        boolean isDeleted = false;

        if (maintenanceScheduleRepositoryPort.findById(id).isPresent()){
            maintenanceScheduleRepositoryPort.delete(id);
            isDeleted = true;
        }

        return isDeleted;
    }
}
