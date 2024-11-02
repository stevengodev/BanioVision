package com.foliaco.bathrooms.controller;

import com.foliaco.bathrooms.application.service.BlockService;
import com.foliaco.bathrooms.domain.dto.BlockDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blocks")
public class BlockController {

    private final BlockService blockService;

    @Autowired
    public BlockController(@Qualifier("blockService") BlockService blockService) {
        this.blockService = blockService;
    }

    @GetMapping("/")
    public ResponseEntity<List<BlockDto>> getAll(){
        return ResponseEntity.ok(blockService.getAllBlocks());
    }

    @PostMapping("/")
    public ResponseEntity<BlockDto> save(@RequestBody BlockDto newBlock){
        return ResponseEntity.status(HttpStatus.CREATED).body(blockService.createBlock(newBlock));
    }

    @PutMapping("/")
    public ResponseEntity<BlockDto> update(@RequestBody BlockDto block){

        Optional<BlockDto> updatedBlock = blockService.updateBlock(block);

        return updatedBlock.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id){
        return blockService.deleteBlock(id) ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<BlockDto> findBlockByName(@PathVariable("name") String name){

        Optional<BlockDto> foundBlock = blockService.findBlockByName(name);
        return foundBlock.map(block -> ResponseEntity.status(HttpStatus.OK).body(block)).
                orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }
}
