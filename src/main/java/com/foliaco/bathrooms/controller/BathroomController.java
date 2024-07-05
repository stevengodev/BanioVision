package com.foliaco.bathrooms.controller;

import com.foliaco.bathrooms.application.service.BathroomService;
import com.foliaco.bathrooms.domain.model.Bathroom;
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
    public ResponseEntity<List<Bathroom>> getAll(){
        return ResponseEntity.ok(bathroomService.getAllBathrooms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bathroom> getById(@PathVariable("id") Integer id ) {

        Optional<Bathroom> bathroom = bathroomService.getBathroomById(id);

        return bathroom.map(ResponseEntity::ok).
                orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .build());
    }

    @PostMapping("/")
    public ResponseEntity<Bathroom> save(@RequestBody Bathroom newBathroom){
        return ResponseEntity.status(HttpStatus.CREATED).body(bathroomService.createBathroom(newBathroom));
    }

    @PutMapping("/")
    public ResponseEntity<Bathroom> update(@RequestBody Bathroom bathroom){

        Optional<Bathroom> updatedBathroom = bathroomService.updateBathroom(bathroom);

        return updatedBathroom.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id){
        return bathroomService.deleteBathroom(id) ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}