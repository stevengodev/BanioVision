package com.foliaco.bathrooms.controller;

import com.foliaco.bathrooms.application.service.BathroomService;
import com.foliaco.bathrooms.domain.dto.BathroomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("api/bathrooms")
public class BathroomController {

    private final BathroomService bathroomService;

    @Autowired
    public BathroomController(@Qualifier("bathroomService") BathroomService bathroomService) {
        this.bathroomService = bathroomService;
    }

    @GetMapping("/")
    public ResponseEntity<List<BathroomDto>> getAll(){
        return ResponseEntity.ok(bathroomService.getAllBathrooms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BathroomDto> getById(@PathVariable("id") Integer id ) {

        Optional<BathroomDto> bathroom = bathroomService.getBathroomById(id);

        return bathroom.map(ResponseEntity::ok).
                orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .build());
    }

    @PostMapping("/")
    public ResponseEntity<BathroomDto> save(@RequestBody BathroomDto newBathroom){
        return ResponseEntity.status(HttpStatus.CREATED).body(bathroomService.createBathroom(newBathroom));
    }

    @PutMapping("/")
    public ResponseEntity<BathroomDto> update(@RequestBody BathroomDto bathroom){

        Optional<BathroomDto> updatedBathroom = bathroomService.updateBathroom(bathroom);

        return updatedBathroom.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id){
        return bathroomService.deleteBathroom(id) ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}