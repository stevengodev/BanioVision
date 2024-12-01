package com.foliaco.bathrooms.application.service;

import com.foliaco.bathrooms.domain.dto.BathroomIncidentDto;
import com.foliaco.bathrooms.domain.dto.BathroomIncidentResponseDto;
import com.foliaco.bathrooms.domain.enums.BathroomStatus;
import com.foliaco.bathrooms.domain.enums.Gender;
import com.foliaco.bathrooms.domain.ports.in.BathroomIncidentUseCase;
import com.foliaco.bathrooms.domain.ports.out.BathroomIncidentRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BathroomIncidentService implements BathroomIncidentUseCase {

    private final BathroomIncidentRepositoryPort bathroomIncidentRepositoryPort;

    @Override
    public Optional<BathroomIncidentDto> updateBathroomIncident(BathroomIncidentDto bathroomIncidentDto) {
        Optional<BathroomIncidentDto> foundBathroomIncident = findBathroomIncidentById(bathroomIncidentDto.getIdBathroom(), bathroomIncidentDto.getIdIncident());
        return foundBathroomIncident.isEmpty() ? Optional.empty() : Optional.of(bathroomIncidentRepositoryPort.save( bathroomIncidentDto ));
    }

    @Override
    public BathroomIncidentDto sendIncident(BathroomIncidentDto bathroomIncidentDto) {
        return bathroomIncidentRepositoryPort.save(bathroomIncidentDto);
    }

    @Override
    public Optional<BathroomIncidentDto> findBathroomIncidentById(Integer bathroomId, Integer incidentId) {
        return bathroomIncidentRepositoryPort.findById(bathroomId, incidentId);
    }

    @Override
    public List<BathroomIncidentResponseDto> findBathroomIncidentInLastDays(int days) {
        LocalDate endDate = LocalDate.now();
        LocalDate starDate = endDate.minusDays(days);
        return bathroomIncidentRepositoryPort.getAllBetweenTwoDates(starDate, endDate);
    }

}
