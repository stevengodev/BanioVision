package com.foliaco.bathrooms.infrastructure.controller;

import com.foliaco.bathrooms.domain.dto.BathroomIncidentDto;
import com.foliaco.bathrooms.domain.dto.BathroomIncidentResponseDto;
import com.foliaco.bathrooms.domain.dto.IncidentDto;
import com.foliaco.bathrooms.domain.ports.in.BathroomIncidentUseCase;
import com.foliaco.bathrooms.domain.ports.in.IncidentUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/incidents")
@AllArgsConstructor
public class IncidentController {

    private final IncidentUseCase incidentUseCase;
    private final BathroomIncidentUseCase bathroomIncidentUseCase;

    @GetMapping("/")
    public ResponseEntity<List<IncidentDto>> getAll(){
        return ResponseEntity.ok(incidentUseCase.getAllIncidents());
    }

    @GetMapping("/last-days/{days}")
    public ResponseEntity<List<BathroomIncidentResponseDto>> findBathroomIncidentInLastDays(@PathVariable Integer days){
        return ResponseEntity.ok(bathroomIncidentUseCase.findBathroomIncidentInLastDays(days));
    }

    @PutMapping("/{idIncident}/bathrooms/{idBathroom}")
    public ResponseEntity<BathroomIncidentDto> updateBathroomIncident(@PathVariable Integer idIncident, @PathVariable Integer idBathroom, @RequestBody String status ){
        BathroomIncidentDto bathroomIncidentDto = new BathroomIncidentDto(idBathroom, idIncident, LocalDate.now(), status);
        Optional<BathroomIncidentDto> bathroomIncidentUpdated = bathroomIncidentUseCase.updateBathroomIncident(bathroomIncidentDto);

        return bathroomIncidentUpdated.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

    }

}
