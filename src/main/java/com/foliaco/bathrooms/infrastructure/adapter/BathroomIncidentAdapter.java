package com.foliaco.bathrooms.infrastructure.adapter;

import com.foliaco.bathrooms.domain.dto.BathroomIncidentDto;
import com.foliaco.bathrooms.domain.dto.BathroomIncidentResponseDto;
import com.foliaco.bathrooms.domain.ports.out.BathroomIncidentRepositoryPort;
import com.foliaco.bathrooms.infrastructure.entity.BathroomIncidentEntity;
import com.foliaco.bathrooms.infrastructure.entity.BathroomIncidentPK;
import com.foliaco.bathrooms.infrastructure.mapper.IBathroomIncidentMapper;
import com.foliaco.bathrooms.infrastructure.repository.IBathroomIncidentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class BathroomIncidentAdapter implements BathroomIncidentRepositoryPort {

    private final IBathroomIncidentRepository bathroomIncidentRepository;

    private final IBathroomIncidentMapper bathroomIncidentMapper;

    @Override
    public List<BathroomIncidentDto> getAll() {

        return bathroomIncidentRepository.findAll().stream()
                .map(bathroomIncidentMapper::toBathroomIncidentDto)
                .toList();
    }

    @Override
    public BathroomIncidentDto save(BathroomIncidentDto bathroomIncident) {
        BathroomIncidentEntity bathroomIncidentEntity = bathroomIncidentMapper.toBathroomIncidentEntity(bathroomIncident);
        return bathroomIncidentMapper.toBathroomIncidentDto( bathroomIncidentRepository.save(bathroomIncidentEntity) ) ;
    }

    @Override
    public Optional<BathroomIncidentDto> findById(Integer bathroomId, Integer incidentId) {
        BathroomIncidentPK bathroomIncidentId = new BathroomIncidentPK();
        bathroomIncidentId.setBathroomId(bathroomId);
        bathroomIncidentId.setIncidentId(incidentId);
        return bathroomIncidentRepository.findById(bathroomIncidentId)
                .map(bathroomIncidentMapper::toBathroomIncidentDto);

    }

    @Override
    public List<BathroomIncidentResponseDto> getAllBetweenTwoDates(LocalDate startDate, LocalDate endDate) {
        return bathroomIncidentRepository.getAllByDateBetween(startDate, endDate)
                .stream().map(bathroomIncidentMapper::toBathroomIncidentResponseDto)
                .toList();
    }


}
