package com.foliaco.bathrooms.domain.ports.out;

import com.foliaco.bathrooms.domain.model.CleaningSchedule;
import com.foliaco.bathrooms.infrastructure.entity.CleaningScheduleEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CleaningScheduleRepositoryPort {

    List<CleaningSchedule> getAll();
    CleaningSchedule save(CleaningSchedule cleaningSchedule);
    Optional<CleaningSchedule> findById(Integer id);
    List<CleaningSchedule> findByBathroomId(Integer bathroomId);
    List<CleaningSchedule> findByBathroomIdAndBetweenDateTimes(Integer bathroomId,
                                                               LocalDateTime start,
                                                               LocalDateTime end);
    void delete(Integer id);
}
