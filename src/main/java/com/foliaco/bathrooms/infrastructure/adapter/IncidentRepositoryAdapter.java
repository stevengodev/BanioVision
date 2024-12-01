package com.foliaco.bathrooms.infrastructure.adapter;

import com.foliaco.bathrooms.domain.dto.IncidentDto;
import com.foliaco.bathrooms.domain.enums.IncidentMessage;
import com.foliaco.bathrooms.domain.ports.out.IncidentRepositoryPort;
import com.foliaco.bathrooms.infrastructure.entity.IncidentEntity;
import com.foliaco.bathrooms.infrastructure.mapper.IIncidentMapper;
import com.foliaco.bathrooms.infrastructure.repository.IIncidentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class IncidentRepositoryAdapter implements IncidentRepositoryPort {

    private final IIncidentRepository incidentRepository;

    private final IIncidentMapper incidentMapper;

    public IncidentRepositoryAdapter(IIncidentRepository incidentRepository, IIncidentMapper incidentMapper) {
        this.incidentRepository = incidentRepository;
        this.incidentMapper = incidentMapper;
    }

    @Override
    public List<IncidentDto> getAll() {
        return incidentRepository.findAll().stream()
                .map(incidentMapper::toIncidentDto)
                .toList();
    }

    @Override
    public IncidentDto save(IncidentDto incidentDto) {
        IncidentEntity incidentEntity = incidentMapper.toIncidentEntity(incidentDto);
        return incidentMapper.toIncidentDto(incidentRepository.save(incidentEntity));
    }

    @Override
    public Optional<IncidentDto> findById(Integer id) {
        return incidentRepository.findById(id).map(incidentMapper::toIncidentDto);
    }

    @Override
    public Optional<IncidentDto> findByProblem(IncidentMessage problem) {
        return incidentRepository.findByProblem(problem).map(incidentMapper::toIncidentDto);
    }
}
