package com.foliaco.bathrooms.infrastructure.controller;

import com.foliaco.bathrooms.domain.dto.MaintenanceScheduleDto;
import com.foliaco.bathrooms.domain.ports.in.MaintenanceScheduleUseCase;
import com.foliaco.bathrooms.infrastructure.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/maintenance-schedules")
@AllArgsConstructor
public class MaintenanceScheduleController {

    private final MaintenanceScheduleUseCase maintenanceScheduleUseCase;

    @GetMapping("/")
    public ResponseEntity<List<MaintenanceScheduleDto>> getAll(){
        return ResponseEntity.ok(maintenanceScheduleUseCase.getAllMaintenanceSchedules());
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<MaintenanceScheduleDto>> getAllUpcoming(){
        LocalDateTime now = LocalDateTime.now();
        return ResponseEntity.ok(maintenanceScheduleUseCase.getMaintenanceSchedulesFromDate(now));
    }

    @GetMapping("/bathroom/{id}")
    public ResponseEntity<List<MaintenanceScheduleDto>> getMaintenanceSchedulesByBathroomIdFromDate(@PathVariable Integer id){
        LocalDateTime now = LocalDateTime.now();
        List<MaintenanceScheduleDto> maintenanceSchedules = maintenanceScheduleUseCase.getMaintenanceSchedulesByBathroomIdAndFromDate(id, now);
        return ResponseEntity.ok(maintenanceSchedules);
    }

    @PostMapping("/")
    public ResponseEntity<MaintenanceScheduleDto> save(@RequestBody MaintenanceScheduleDto newMaintenanceSchedule){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(maintenanceScheduleUseCase.createMaintenanceSchedule(newMaintenanceSchedule));
    }

    @PutMapping("/")
    public ResponseEntity<MaintenanceScheduleDto> update(@RequestBody MaintenanceScheduleDto maintenanceSchedule){

        Optional<MaintenanceScheduleDto> updateMaintenanceSchedule = maintenanceScheduleUseCase.updateMaintenanceSchedule(
                maintenanceSchedule
        );

        return updateMaintenanceSchedule.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id){
        return maintenanceScheduleUseCase.deleteMaintenanceSchedule(id) ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
