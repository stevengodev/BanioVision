package com.foliaco.bathrooms.application.service;

import com.foliaco.bathrooms.domain.dto.CleaningScheduleDto;
import com.foliaco.bathrooms.domain.ports.in.CleaningScheduleUseCase;
import com.foliaco.bathrooms.domain.ports.out.CleaningScheduleRepositoryPort;
import com.foliaco.bathrooms.infrastructure.adapter.CleaningScheduleRepositoryAdapter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CleaningScheduleService implements CleaningScheduleUseCase {

    private final CleaningScheduleRepositoryPort cleaningScheduleRepositoryPort;

    @Override
    public List<CleaningScheduleDto> getAllCleaningSchedules() {
        return cleaningScheduleRepositoryPort.getAll();
    }

    @Override
    public CleaningScheduleDto createCleaningSchedule(CleaningScheduleDto newCleaningSchedule) {
        return cleaningScheduleRepositoryPort.save(newCleaningSchedule);
    }

    @Override
    public Optional<CleaningScheduleDto> updateCleaningSchedule(CleaningScheduleDto cleaningSchedule) {
        Optional<CleaningScheduleDto> foundCleaningSchedule = cleaningScheduleRepositoryPort
                .findById(cleaningSchedule.getId());

        return foundCleaningSchedule.isEmpty()
                ? Optional.empty()
                : Optional.of(cleaningScheduleRepositoryPort.save(cleaningSchedule));
    }


    @Override
    public List<CleaningScheduleDto> getTodayCleaningSchedulesByBathroomId(Integer bathroomId) {
        LocalDateTime startOfDay = LocalDateTime.now().with(LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.now().with(LocalTime.MAX);
        return cleaningScheduleRepositoryPort.findByBathroomIdAndBetweenDateTimes(bathroomId, startOfDay, endOfDay);
    }


    @Override
    public boolean deleteCleaningSchedule(Integer id) {
        boolean isDeleted = false;

        if (cleaningScheduleRepositoryPort.findById(id).isPresent()){
            cleaningScheduleRepositoryPort.delete(id);
            isDeleted = true;
        }

        return isDeleted;
    }
}

