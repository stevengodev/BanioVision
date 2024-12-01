package com.foliaco.bathrooms.domain.ports.out;

import com.foliaco.bathrooms.domain.dto.CleaningScheduleDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CleaningScheduleRepositoryPort {

    List<CleaningScheduleDto> getAll();
    CleaningScheduleDto save(CleaningScheduleDto cleaningSchedule);
    Optional<CleaningScheduleDto> findById(Integer id);
    List<CleaningScheduleDto> findByBathroomId(Integer bathroomId);

    void delete(Integer id);

    List<CleaningScheduleDto> findByBathroomIdAndStartDateTimeAfter(Integer bathroomId,
                                                                      LocalDateTime start);
    List<CleaningScheduleDto> findAllByStartDateTimeAfter(LocalDateTime start);
}
