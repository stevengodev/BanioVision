package com.foliaco.bathrooms.controller;

import com.foliaco.bathrooms.application.service.MaintenanceScheduleService;
import com.foliaco.bathrooms.domain.dto.MaintenanceSchedule;
import com.foliaco.bathrooms.infrastructure.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/maintenance-schedules")
public class MaintenanceScheduleController {

    private final MaintenanceScheduleService maintenanceScheduleService;

    @Autowired
    public MaintenanceScheduleController(MaintenanceScheduleService maintenanceScheduleService) {
        this.maintenanceScheduleService = maintenanceScheduleService;
    }

    @GetMapping("/")
    public ResponseEntity<List<MaintenanceSchedule>> getAll(){
        return ResponseEntity.ok(maintenanceScheduleService.getAllMaintenanceSchedules());
    }

    @GetMapping("/bathroom/{id}")
    public ResponseEntity<List<MaintenanceSchedule>> getTodayMaintenanceSchedulesByBathroomId(@PathVariable Integer id){

        List<MaintenanceSchedule> maintenanceSchedules = maintenanceScheduleService.getTodayMaintenanceSchedulesByBathroomId(id);

        if (maintenanceSchedules.isEmpty()){
             throw new NotFoundException("Horarios de mantenimiento para hoy no encontrados");
        }

        return ResponseEntity.ok(maintenanceSchedules);

    }

    @PostMapping("/")
    public ResponseEntity<MaintenanceSchedule> save(@RequestBody MaintenanceSchedule newMaintenanceSchedule){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(maintenanceScheduleService.createMaintenanceSchedule(newMaintenanceSchedule));
    }

    @PutMapping("/")
    public ResponseEntity<MaintenanceSchedule> update(@RequestBody MaintenanceSchedule maintenanceSchedule){

        Optional<MaintenanceSchedule> updateMaintenanceSchedule = maintenanceScheduleService.updateMaintenanceSchedule(
                maintenanceSchedule
        );

        return updateMaintenanceSchedule.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id){
        return maintenanceScheduleService.deleteMaintenanceSchedule(id) ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
