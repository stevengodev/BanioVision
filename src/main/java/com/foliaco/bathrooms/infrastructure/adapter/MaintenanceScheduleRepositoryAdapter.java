package com.foliaco.bathrooms.infrastructure.adapter;

import com.foliaco.bathrooms.domain.dto.MaintenanceSchedule;
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
    public List<MaintenanceSchedule> getAll() {
        return maintenanceScheduleMapper.toMaintenanceScheduleList(maintenanceScheduleRepository.findAll());
    }

    @Override
    public MaintenanceSchedule save(MaintenanceSchedule maintenanceSchedule) {
        MaintenanceScheduleEntity maintenanceScheduleEntity = maintenanceScheduleMapper
                .toMaintenanceScheduleEntity(maintenanceSchedule);

        return maintenanceScheduleMapper.toMaintenanceSchedule(maintenanceScheduleRepository
                .save( maintenanceScheduleEntity ));
    }

    @Override
    public Optional<MaintenanceSchedule> findById(Integer id) {
        return maintenanceScheduleRepository.findById(id)
                .map(maintenanceScheduleMapper::toMaintenanceSchedule);
    }

    @Override
    public List<MaintenanceSchedule> findByBathroomIdAndBetweenDateTimes(Integer bathroomId,
                                                                         LocalDateTime start,
                                                                         LocalDateTime end) {

        return maintenanceScheduleMapper.toMaintenanceScheduleList(
                maintenanceScheduleRepository.findByBathroomIdAndBetweenDateTimes(bathroomId, start, end)
        );
    }

    @Override
    public void delete(Integer id) {
        maintenanceScheduleRepository.deleteById(id);
    }
}
