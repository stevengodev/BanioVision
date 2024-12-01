package com.foliaco.bathrooms.domain.ports.in;

import com.foliaco.bathrooms.domain.dto.CleaningScheduleDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CleaningScheduleUseCase {

    List<CleaningScheduleDto> getAllCleaningSchedules();
    CleaningScheduleDto createCleaningSchedule(CleaningScheduleDto newCleaningSchedule);
    Optional<CleaningScheduleDto> updateCleaningSchedule(CleaningScheduleDto cleaningSchedule);
    List<CleaningScheduleDto> getCleaningSchedulesByBathroomIdFromDate(Integer bathroomId, LocalDateTime datetime);
    List<CleaningScheduleDto> getCleaningSchedulesFromDate(LocalDateTime date);
    boolean deleteCleaningSchedule(Integer id);

}
