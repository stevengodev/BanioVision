package com.foliaco.bathrooms.infrastructure.repository;

import com.foliaco.bathrooms.infrastructure.entity.MaintenanceScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface IMaintenanceScheduleRepository extends JpaRepository<MaintenanceScheduleEntity, Integer> {

    @Query("SELECT ms FROM MaintenanceScheduleEntity ms WHERE ms.bathroomId = :bathroomId")
    List<MaintenanceScheduleEntity> findByBathroomId(Integer bathroomId);

    @Query("SELECT ms FROM MaintenanceScheduleEntity ms WHERE ms.bathroomId = :banoId " +
            "AND ms.startDateTime BETWEEN :startOfDay AND :endOfDay")
    List<MaintenanceScheduleEntity> findByBathroomIdAndBetweenDateTimes(@Param("banoId") Integer banoId,
                                                                        @Param("startOfDay") LocalDateTime startOfDay,
                                                                        @Param("endOfDay") LocalDateTime endOfDay);

}
