package com.foliaco.bathrooms.application.service;

import com.foliaco.bathrooms.domain.dto.MaintenanceScheduleDto;
import com.foliaco.bathrooms.domain.ports.in.MaintenanceScheduleUseCase;
import com.foliaco.bathrooms.domain.ports.out.MaintenanceScheduleRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MaintenanceScheduleService implements MaintenanceScheduleUseCase {

    private final MaintenanceScheduleRepositoryPort maintenanceScheduleRepositoryPort;

    @Override
    public List<MaintenanceScheduleDto> getAllMaintenanceSchedules() {
        return maintenanceScheduleRepositoryPort.getAll();
    }

    @Override
    public MaintenanceScheduleDto createMaintenanceSchedule(MaintenanceScheduleDto newCleaningSchedule) {
        return maintenanceScheduleRepositoryPort.save(newCleaningSchedule);
    }

    @Override
    public Optional<MaintenanceScheduleDto> updateMaintenanceSchedule(MaintenanceScheduleDto cleaningSchedule) {

        Optional<MaintenanceScheduleDto> maintenanceScheduleFound = maintenanceScheduleRepositoryPort.findById(
                cleaningSchedule.getId()
        );

        return maintenanceScheduleFound.isPresent()
                ? Optional.of(maintenanceScheduleRepositoryPort.save(cleaningSchedule))
                : Optional.empty();
    }

    @Override
    public List<MaintenanceScheduleDto> getMaintenanceSchedulesByBathroomIdAndFromDate(Integer bathroomId, LocalDateTime datetime) {
        return maintenanceScheduleRepositoryPort.findByBathroomIdAndStartDateTimeAfter(bathroomId, datetime);
    }

    @Override
    public List<MaintenanceScheduleDto> getMaintenanceSchedulesFromDate(LocalDateTime date) {
        return maintenanceScheduleRepositoryPort.findAllByStartDateTimeAfter(date);
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
