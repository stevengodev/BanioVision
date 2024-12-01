package com.foliaco.bathrooms.infrastructure.adapter;

import com.foliaco.bathrooms.domain.dto.MaintenanceScheduleDto;
import com.foliaco.bathrooms.domain.ports.out.MaintenanceScheduleRepositoryPort;
import com.foliaco.bathrooms.infrastructure.entity.MaintenanceScheduleEntity;
import com.foliaco.bathrooms.infrastructure.mapper.IMaintenanceScheduleMapper;
import com.foliaco.bathrooms.infrastructure.repository.IMaintenanceScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class MaintenanceScheduleRepositoryAdapter implements MaintenanceScheduleRepositoryPort {

    private final IMaintenanceScheduleMapper maintenanceScheduleMapper;

    private final IMaintenanceScheduleRepository maintenanceScheduleRepository;

    @Autowired
    public MaintenanceScheduleRepositoryAdapter(IMaintenanceScheduleMapper maintenanceScheduleMapper,
                                                IMaintenanceScheduleRepository maintenanceScheduleRepository) {
        this.maintenanceScheduleMapper = maintenanceScheduleMapper;
        this.maintenanceScheduleRepository = maintenanceScheduleRepository;
    }

    @Override
    public List<MaintenanceScheduleDto> getAll() {
        return maintenanceScheduleMapper.toMaintenanceScheduleList(maintenanceScheduleRepository.findAll());
    }

    @Override
    public MaintenanceScheduleDto save(MaintenanceScheduleDto maintenanceSchedule) {
        MaintenanceScheduleEntity maintenanceScheduleEntity = maintenanceScheduleMapper
                .toMaintenanceScheduleEntity(maintenanceSchedule);

        return maintenanceScheduleMapper.toMaintenanceSchedule(maintenanceScheduleRepository
                .save( maintenanceScheduleEntity ));
    }

    @Override
    public Optional<MaintenanceScheduleDto> findById(Integer id) {
        return maintenanceScheduleRepository.findById(id)
                .map(maintenanceScheduleMapper::toMaintenanceSchedule);
    }

    @Override
    public List<MaintenanceScheduleDto> findByBathroomIdByStartDateTimeAfter(Integer bathroomId,
                                                                            LocalDateTime start) {

        return maintenanceScheduleMapper.toMaintenanceScheduleList(
                maintenanceScheduleRepository.findByBathroomIdAndStartDateTimeAfter(bathroomId, start)
        );
    }

    @Override
    public List<MaintenanceScheduleDto> findAllByStartDateTimeAfter(LocalDateTime start) {
        return maintenanceScheduleMapper.toMaintenanceScheduleList(
                maintenanceScheduleRepository.findMaintenanceScheduleEntitiesByStartDateTimeAfter(start)
        );
    }

    @Override
    public void delete(Integer id) {
        maintenanceScheduleRepository.deleteById(id);
    }
}
