package com.foliaco.bathrooms.controller;

import com.foliaco.bathrooms.application.service.CleaningScheduleService;
import com.foliaco.bathrooms.domain.model.CleaningSchedule;
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
    public ResponseEntity<List<CleaningSchedule>> getAll(){
        return ResponseEntity.ok(cleaningScheduleService.getAllCleaningSchedules());
    }

    @GetMapping("/bathroom/{id}")
    public ResponseEntity<List<CleaningSchedule>> getTodayCleaningSchedulesByBathroomId(@PathVariable Integer id){
        return ResponseEntity.ok(cleaningScheduleService.getTodayCleaningSchedulesByBathroomId(id));
    }

    @PostMapping("/")
    public ResponseEntity<CleaningSchedule> save(@RequestBody CleaningSchedule newCleaningSchedule){
        return ResponseEntity.status(HttpStatus.CREATED).body(cleaningScheduleService.createCleaningSchedule(newCleaningSchedule));
    }

    @PutMapping("/")
    public ResponseEntity<CleaningSchedule> update(@RequestBody CleaningSchedule cleaningSchedule){

        Optional<CleaningSchedule> updateCleaningSchedule = cleaningScheduleService.updateCleaningSchedule(cleaningSchedule);

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
