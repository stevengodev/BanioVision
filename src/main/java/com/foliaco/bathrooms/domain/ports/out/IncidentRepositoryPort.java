package com.foliaco.bathrooms.domain.ports.out;

import com.foliaco.bathrooms.domain.dto.IncidentDto;
import com.foliaco.bathrooms.domain.enums.IncidentMessage;

import java.util.List;
import java.util.Optional;

public interface IncidentRepositoryPort {
    List<IncidentDto> getAll();
    IncidentDto save(IncidentDto incidentDto);
    Optional<IncidentDto> findById(Integer id);
    Optional<IncidentDto> findByProblem(IncidentMessage problem);
}
