package com.foliaco.bathrooms.domain.ports.in;

import com.foliaco.bathrooms.domain.dto.BathroomIncidentDto;
import com.foliaco.bathrooms.domain.dto.BathroomIncidentResponseDto;

import java.util.List;
import java.util.Optional;

public interface BathroomIncidentUseCase {

    Optional<BathroomIncidentDto> updateBathroomIncident(BathroomIncidentDto bathroomIncidentDto);
    BathroomIncidentDto sendIncident(BathroomIncidentDto bathroomIncidentDto);
    Optional<BathroomIncidentDto> findBathroomIncidentById(Integer bathroomId, Integer incidentId);
    List<BathroomIncidentResponseDto> findBathroomIncidentInLastDays(int days);

}
