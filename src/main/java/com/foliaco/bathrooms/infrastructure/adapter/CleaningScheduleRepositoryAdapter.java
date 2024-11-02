package com.foliaco.bathrooms.infrastructure.adapter;

import com.foliaco.bathrooms.domain.dto.CleaningScheduleDto;
import com.foliaco.bathrooms.domain.ports.out.CleaningScheduleRepositoryPort;
import com.foliaco.bathrooms.infrastructure.entity.CleaningScheduleEntity;
import com.foliaco.bathrooms.infrastructure.mapper.ICleaningScheduleMapper;
import com.foliaco.bathrooms.infrastructure.repository.ICleaningScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class CleaningScheduleRepositoryAdapter implements CleaningScheduleRepositoryPort {

    private final ICleaningScheduleRepository cleaningScheduleRepository;
    private final ICleaningScheduleMapper cleaningScheduleMapper;

    @Autowired
    public CleaningScheduleRepositoryAdapter(ICleaningScheduleRepository cleaningScheduleRepository,
                                             ICleaningScheduleMapper cleaningScheduleMapper) {
        this.cleaningScheduleRepository = cleaningScheduleRepository;
        this.cleaningScheduleMapper = cleaningScheduleMapper;
    }


    @Override
    public List<CleaningScheduleDto> getAll() {
        return cleaningScheduleMapper.toCleaningScheduleList(cleaningScheduleRepository.findAll());
    }

    @Override
    public CleaningScheduleDto save(CleaningScheduleDto newCleaningSchedule) {
        CleaningScheduleEntity cleaningScheduleEntity = cleaningScheduleMapper
                                                        .toCleaningScheduleEntity(newCleaningSchedule);
        return cleaningScheduleMapper.toCleaningSchedule(cleaningScheduleRepository
                .save( cleaningScheduleEntity ));
    }

    @Override
    public Optional<CleaningScheduleDto> findById(Integer id) {
        return cleaningScheduleRepository.findById(id)
                .map(cleaningScheduleMapper::toCleaningSchedule);
    }

    @Override
    public List<CleaningScheduleDto> findByBathroomId(Integer bathroomId) {
        return cleaningScheduleMapper.toCleaningScheduleList(
                cleaningScheduleRepository.findByBathroomId(bathroomId)
        );
    }

    @Override
    public List<CleaningScheduleDto> findByBathroomIdAndBetweenDateTimes(Integer bathroomId, LocalDateTime start, LocalDateTime end) {
        return cleaningScheduleMapper.toCleaningScheduleList(
                cleaningScheduleRepository.findByBathroomIdAndBetweenDateTimes(bathroomId, start, end)
        );
    }

    @Override
    public void delete(Integer id) {
        cleaningScheduleRepository.deleteById(id);
    }
}
