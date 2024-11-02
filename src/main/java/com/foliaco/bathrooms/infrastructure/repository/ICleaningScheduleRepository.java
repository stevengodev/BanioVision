package com.foliaco.bathrooms.infrastructure.repository;

import com.foliaco.bathrooms.infrastructure.entity.CleaningScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ICleaningScheduleRepository extends JpaRepository<CleaningScheduleEntity, Integer> {

    @Query("SELECT cs FROM CleaningScheduleEntity cs WHERE cs.bathroomId = :bathroomId")
    List<CleaningScheduleEntity> findByBathroomId(Integer bathroomId);

    @Query("SELECT cs FROM CleaningScheduleEntity cs WHERE cs.bathroomId = :banoId " +
            "AND cs.startDateTime BETWEEN :startOfDay AND :endOfDay")
    List<CleaningScheduleEntity> findByBathroomIdAndBetweenDateTimes(@Param("banoId") Integer banoId,
                                                       @Param("startOfDay") LocalDateTime startOfDay,
                                                       @Param("endOfDay") LocalDateTime endOfDay);

}
