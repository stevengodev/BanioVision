package com.foliaco.bathrooms.controller;

import com.foliaco.bathrooms.domain.dto.*;
import com.foliaco.bathrooms.domain.enums.IncidentMessage;
import com.foliaco.bathrooms.domain.ports.in.BathroomIncidentUseCase;
import com.foliaco.bathrooms.domain.ports.in.BathroomUseCase;
import com.foliaco.bathrooms.domain.ports.in.IncidentUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("api/bathrooms")
@AllArgsConstructor
public class BathroomController {

    private final BathroomUseCase bathroomUseCase;
    private final BathroomIncidentUseCase bathroomIncidentUseCase;
    private final IncidentUseCase incidentUseCase;

    @GetMapping("/")
    public ResponseEntity<List<BathroomResponseDto>> getAll(){
        return ResponseEntity.ok(bathroomUseCase.getAllBathrooms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BathroomDto> getById(@PathVariable("id") Integer id ) {

        Optional<BathroomDto> bathroom = bathroomUseCase.getBathroomById(id);

        return bathroom.map(ResponseEntity::ok).
                orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .build());
    }

    @PostMapping("/")
    public ResponseEntity<BathroomDto> save(@RequestBody BathroomRequestDto bathroomRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(bathroomUseCase.createBathroom(bathroomRequest));
    }

    @PutMapping("/")
    public ResponseEntity<BathroomDto> update(@RequestBody BathroomDto bathroom){

        Optional<BathroomDto> updatedBathroom = bathroomUseCase.updateBathroom(bathroom);

        return updatedBathroom.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id){
        return bathroomUseCase.deleteBathroom(id) ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{idBathroom}/report-incident")
    public ResponseEntity<BathroomIncidentDto> reportIncident(@PathVariable Integer idBathroom,
                                                              @RequestBody IncidentDto incidentDto){

        BathroomIncidentDto bathroomIncidentDto = new BathroomIncidentDto();

        if (incidentDto.getProblem() == IncidentMessage.OTRO && incidentDto.getComment() != null ){
            //Crear un nuevo incidente
            IncidentDto incident = incidentUseCase.createIncident(incidentDto);
            bathroomIncidentDto.setIdIncident(incident.getId());
        }

        bathroomIncidentDto.setIdIncident( incidentDto.getId() );
        bathroomIncidentDto.setIdBathroom(idBathroom);
        bathroomIncidentDto.setDate(LocalDate.now());
        bathroomIncidentDto.setStatus("PENDIENTE");

        BathroomIncidentDto bathroomIncidentSaved = bathroomIncidentUseCase.sendIncident(bathroomIncidentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(bathroomIncidentSaved);
    }


}