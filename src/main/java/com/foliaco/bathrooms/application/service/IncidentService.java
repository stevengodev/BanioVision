package com.foliaco.bathrooms.application.service;

import com.foliaco.bathrooms.domain.dto.IncidentDto;
import com.foliaco.bathrooms.domain.enums.IncidentMessage;
import com.foliaco.bathrooms.domain.ports.in.IncidentUseCase;
import com.foliaco.bathrooms.domain.ports.out.IncidentRepositoryPort;
import com.foliaco.bathrooms.infrastructure.mapper.IIncidentMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class IncidentService implements IncidentUseCase {

    private final IncidentRepositoryPort incidentRepositoryPort;
    private final IIncidentMapper incidentMapper;

    @Override
    public List<IncidentDto> getAllIncidents() {
        return incidentRepositoryPort.getAll();
    }

    @Override
    public IncidentDto createIncident(IncidentDto newIncident) {
        return incidentRepositoryPort.save(newIncident);
    }

    @Override
    public Optional<IncidentDto> updateIncident(IncidentDto incidentDto) {
        Optional<IncidentDto> foundIncident = findIncidentById(incidentDto.getId());
        return foundIncident.isEmpty() ? Optional.empty() : Optional.of(incidentRepositoryPort.save(incidentDto));
    }

    @Override
    public Optional<IncidentDto> findIncidentById(Integer id) {
        return incidentRepositoryPort.findById(id);
    }

    @Override
    public Optional<IncidentDto> findIncidentByProblem(IncidentMessage problem) {
        return incidentRepositoryPort.findByProblem(problem);
    }

}
