package com.foliaco.bathrooms.infrastructure.repository;

import com.foliaco.bathrooms.domain.enums.IncidentMessage;
import com.foliaco.bathrooms.infrastructure.entity.IncidentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IIncidentRepository extends JpaRepository<IncidentEntity, Integer> {

    Optional<IncidentEntity> findByProblem(IncidentMessage problem);

}
