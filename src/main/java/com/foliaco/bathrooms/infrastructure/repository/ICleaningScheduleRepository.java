package com.foliaco.bathrooms.infrastructure.repository;

import com.foliaco.bathrooms.infrastructure.entity.CleaningScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ICleaningScheduleRepository extends JpaRepository<CleaningScheduleEntity, Integer> {

    @Query("SELECT cs FROM CleaningScheduleEntity cs WHERE cs.bathroomId = :bathroomId")
    List<CleaningScheduleEntity> findByBathroomId(Integer bathroomId);

    List<CleaningScheduleEntity> findByBathroomIdAndStartDateTimeAfter(Integer id, LocalDateTime date);

    List<CleaningScheduleEntity> findMaintenanceScheduleEntitiesByStartDateTimeAfter(LocalDateTime startOfDay);


}
