package com.foliaco.bathrooms.controller;

import com.foliaco.bathrooms.application.service.CleaningScheduleService;
import com.foliaco.bathrooms.domain.dto.CleaningScheduleDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cleaning-schedules")
public class CleaningScheduleController {

    private final CleaningScheduleService cleaningScheduleService;

    public CleaningScheduleController(CleaningScheduleService cleaningScheduleService) {
        this.cleaningScheduleService = cleaningScheduleService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CleaningScheduleDto>> getAll(){
        return ResponseEntity.ok(cleaningScheduleService.getAllCleaningSchedules());
    }

    @GetMapping("/bathroom/{id}")
    public ResponseEntity<List<CleaningScheduleDto>> getTodayCleaningSchedulesByBathroomId(@PathVariable Integer id){
        return ResponseEntity.ok(cleaningScheduleService.getTodayCleaningSchedulesByBathroomId(id));
    }

    @PostMapping("/")
    public ResponseEntity<CleaningScheduleDto> save(@RequestBody CleaningScheduleDto newCleaningSchedule){
        return ResponseEntity.status(HttpStatus.CREATED).body(cleaningScheduleService.createCleaningSchedule(newCleaningSchedule));
    }

    @PutMapping("/")
    public ResponseEntity<CleaningScheduleDto> update(@RequestBody CleaningScheduleDto cleaningSchedule){

        Optional<CleaningScheduleDto> updateCleaningSchedule = cleaningScheduleService.updateCleaningSchedule(cleaningSchedule);

        return updateCleaningSchedule.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id){
        return cleaningScheduleService.deleteCleaningSchedule(id) ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
