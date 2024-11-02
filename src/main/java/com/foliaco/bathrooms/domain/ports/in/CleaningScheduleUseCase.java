package com.foliaco.bathrooms.domain.ports.in;

import com.foliaco.bathrooms.domain.dto.CleaningScheduleDto;

import java.util.List;
import java.util.Optional;

public interface CleaningScheduleUseCase {

    List<CleaningScheduleDto> getAllCleaningSchedules();
    CleaningScheduleDto createCleaningSchedule(CleaningScheduleDto newCleaningSchedule);
    Optional<CleaningScheduleDto> updateCleaningSchedule(CleaningScheduleDto cleaningSchedule);
    List<CleaningScheduleDto> getTodayCleaningSchedulesByBathroomId(Integer bathroomId);
    boolean deleteCleaningSchedule(Integer id);

}
