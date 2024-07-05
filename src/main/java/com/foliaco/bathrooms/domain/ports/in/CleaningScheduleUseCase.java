package com.foliaco.bathrooms.domain.ports.in;

import com.foliaco.bathrooms.domain.model.CleaningSchedule;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CleaningScheduleUseCase {

    List<CleaningSchedule> getAllCleaningSchedules();
    CleaningSchedule createCleaningSchedule(CleaningSchedule newCleaningSchedule);
    Optional<CleaningSchedule> updateCleaningSchedule(CleaningSchedule cleaningSchedule);
    List<CleaningSchedule> getTodayCleaningSchedulesByBathroomId(Integer bathroomId);
    boolean deleteCleaningSchedule(Integer id);

}
