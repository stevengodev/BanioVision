package com.foliaco.bathrooms.controller;

import com.foliaco.bathrooms.domain.dto.CleaningScheduleDto;
import com.foliaco.bathrooms.domain.ports.in.CleaningScheduleUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cleaning-schedules")
@AllArgsConstructor
public class CleaningScheduleController {

    private final CleaningScheduleUseCase cleaningScheduleUseCase;

    @GetMapping("/")
    public ResponseEntity<List<CleaningScheduleDto>> getAll(){
        return ResponseEntity.ok(cleaningScheduleUseCase.getAllCleaningSchedules());
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<CleaningScheduleDto>> getAllUpcoming(){
        LocalDateTime now = LocalDateTime.now();
        return ResponseEntity.ok(cleaningScheduleUseCase.getCleaningSchedulesFromDate(now));
    }

    @GetMapping("/bathroom/{id}")
    public ResponseEntity<List<CleaningScheduleDto>> getCleaningSchedulesByBathroomIdFromDate(@PathVariable Integer id){
        LocalDateTime now = LocalDateTime.now();
        return ResponseEntity.ok(cleaningScheduleUseCase.getCleaningSchedulesByBathroomIdFromDate(id, now));
    }

    @PostMapping("/")
    public ResponseEntity<CleaningScheduleDto> save(@RequestBody CleaningScheduleDto newCleaningSchedule){
        return ResponseEntity.status(HttpStatus.CREATED).body(cleaningScheduleUseCase.createCleaningSchedule(newCleaningSchedule));
    }

    @PutMapping("/")
    public ResponseEntity<CleaningScheduleDto> update(@RequestBody CleaningScheduleDto cleaningSchedule){

        Optional<CleaningScheduleDto> updateCleaningSchedule = cleaningScheduleUseCase.updateCleaningSchedule(cleaningSchedule);

        return updateCleaningSchedule.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id){
        return cleaningScheduleUseCase.deleteCleaningSchedule(id) ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
