package com.foliaco.bathrooms.infrastructure.controller;

import com.foliaco.bathrooms.domain.dto.BlockDto;
import com.foliaco.bathrooms.domain.dto.BlockRequestDto;
import com.foliaco.bathrooms.domain.ports.in.BlockUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blocks")
@AllArgsConstructor
public class BlockController {

    private final BlockUseCase blockUseCase;

    @GetMapping("/")
    public ResponseEntity<List<BlockDto>> getAll(){
        return ResponseEntity.ok(blockUseCase.getAllBlocks());
    }

    @PostMapping("/")
    public ResponseEntity<BlockDto> save(@RequestBody BlockRequestDto newBlock){
        return ResponseEntity.status(HttpStatus.CREATED).body(blockUseCase.createBlock(newBlock));
    }

    @PutMapping("/")
    public ResponseEntity<BlockDto> update(@RequestBody BlockDto block){

        Optional<BlockDto> updatedBlock = blockUseCase.updateBlock(block);

        return updatedBlock.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id){
        return blockUseCase.deleteBlock(id) ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<BlockDto> findBlockByName(@PathVariable("name") String name){

        Optional<BlockDto> foundBlock = blockUseCase.findBlockByName(name);
        return foundBlock.map(block -> ResponseEntity.status(HttpStatus.OK).body(block)).
                orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }
}
