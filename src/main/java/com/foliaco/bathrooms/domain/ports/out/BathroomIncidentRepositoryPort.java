package com.foliaco.bathrooms.domain.ports.out;

import com.foliaco.bathrooms.domain.dto.BathroomIncidentDto;
import com.foliaco.bathrooms.domain.dto.BathroomIncidentResponseDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BathroomIncidentRepositoryPort {

    List<BathroomIncidentDto> getAll();
    BathroomIncidentDto save(BathroomIncidentDto bathroomIncident);
    Optional<BathroomIncidentDto> findById(Integer bathroomId, Integer incidentId);
    List<BathroomIncidentResponseDto> getAllBetweenTwoDates(LocalDate startDate, LocalDate endDate);
}
