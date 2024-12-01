package com.foliaco.bathrooms.infrastructure.repository;

import com.foliaco.bathrooms.infrastructure.entity.BathroomIncidentEntity;
import com.foliaco.bathrooms.infrastructure.entity.BathroomIncidentPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IBathroomIncidentRepository extends JpaRepository<BathroomIncidentEntity, BathroomIncidentPK> {

    List<BathroomIncidentEntity> getAllByDateBetween(LocalDate startDate, LocalDate endDate);

}
