package com.foliaco.bathrooms.domain.ports.in;


import com.foliaco.bathrooms.domain.dto.IncidentDto;
import com.foliaco.bathrooms.domain.enums.IncidentMessage;

import java.util.List;
import java.util.Optional;

public interface IncidentUseCase {

    List<IncidentDto> getAllIncidents();
    IncidentDto createIncident(IncidentDto newIncident);
    Optional<IncidentDto> updateIncident(IncidentDto incidentDto);
    Optional<IncidentDto> findIncidentById(Integer id);
    Optional<IncidentDto> findIncidentByProblem(IncidentMessage problem);
}
