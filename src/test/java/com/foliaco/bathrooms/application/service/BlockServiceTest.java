package com.foliaco.bathrooms.application.service;

import com.foliaco.bathrooms.domain.dto.BlockDto;
import com.foliaco.bathrooms.domain.dto.BlockRequestDto;
import com.foliaco.bathrooms.domain.ports.out.BlockRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BlockServiceTest {

    @Mock
    BlockRepositoryPort repository;

    @InjectMocks
    private BlockService blockService;

    @Test
    void whenGetAllBlocks_thenReturnAllBlocks() {

        List<BlockDto> blocks = new ArrayList<>();
        blocks.add(new BlockDto(1, "Bloque A", 2));
        blocks.add(new BlockDto(2, "Bloque B", 3));
        blocks.add(new BlockDto(3, "Bloque C", 3));

        when(repository.getAll()).thenReturn(blocks);

        List<BlockDto> allBlocks = blockService.getAllBlocks();

        assertEquals(3, blocks.size());
        assertEquals("Bloque A", blocks.get(0).getName());
        assertEquals(2, blocks.get(0).getNumberOfFloors());
        assertEquals(blocks, allBlocks);

    }

    @Test
    void givenBlock_whenCreateBlock_thenReturnNewBlock() {

        BlockDto newBlock = new BlockDto(1, "Bloque A", 2);

        when(repository.save( any(BlockDto.class) )).thenReturn(newBlock);

        BlockDto blockDto = blockService.createBlock(new BlockRequestDto("Bloque A", 2));

        assertEquals(newBlock.getName(), blockDto.getName());
        assertEquals(newBlock.getNumberOfFloors(), blockDto.getNumberOfFloors());
        assertNotNull(blockDto);

    }

    @Test
    void givenBlock_whenUpdateBlock_thenReturnUpdatedBlock() {

        BlockDto block = new BlockDto(1, "Bloque A", 2);
        BlockDto updatedBlock = new BlockDto(1, "Bloque 2", 4);

        when(repository.findById(block.getId())).thenReturn(Optional.of(block));
        when(repository.save(any(BlockDto.class))).thenReturn(updatedBlock);

        Optional<BlockDto> updated = blockService.updateBlock(block);

        assertTrue(updated.isPresent());
        assertEquals(updatedBlock.getName(), updated.get().getName());
        assertEquals(updatedBlock.getNumberOfFloors(), updated.get().getNumberOfFloors());

    }

    @Test
    void givenBlockId_whenFindBlockById_thenReturnBlock() {

        BlockDto block = new BlockDto(1, "Bloque A", 2);

        when(repository.findById( any(Integer.class) )).thenReturn(Optional.of(block));

        Optional<BlockDto> found = blockService.findBlockById(block.getId());

        assertTrue(found.isPresent());
        assertEquals(block.getId(), found.get().getId());
        assertEquals(block.getName(), found.get().getName());
        assertEquals(block.getNumberOfFloors(), found.get().getNumberOfFloors());
    }

    @Test
    void givenName_whenFindBlockByName_thenReturnBlock() {
        BlockDto block = new BlockDto(1, "Bloque A", 2);

        when(repository.findByName(any(String.class) )).thenReturn(Optional.of(block));

        Optional<BlockDto> found = blockService.findBlockByName(block.getName());

        assertTrue(found.isPresent());
        assertEquals(block.getId(), found.get().getId());
        assertEquals(block.getName(), found.get().getName());
        assertEquals(block.getNumberOfFloors(), found.get().getNumberOfFloors());
    }

    @Test
    void givenBlockId_whenDeleteBlock_thenReturnTrue() {

        BlockDto block = new BlockDto(1, "Bloque A", 2);

        when(repository.findById(1)).thenReturn(Optional.of(block));
        doNothing().when(repository).delete(block.getId());

        boolean isDeleted = blockService.deleteBlock(1);

        assertTrue(isDeleted);
        verify(repository, times(1)).findById(1);
        verify(repository, times(1)).delete(1);

    }
}