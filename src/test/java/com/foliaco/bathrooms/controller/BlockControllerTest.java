package com.foliaco.bathrooms.controller;

import com.foliaco.bathrooms.application.service.BlockService;
import com.foliaco.bathrooms.domain.dto.BlockDto;
import com.foliaco.bathrooms.domain.dto.BlockRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = BlockController.class)
class BlockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean(name = "blockService")
    private BlockService blockService;

    @Test
    void getAll_whenGetBlocks_thenReturnAllBlocks() throws Exception {
        when(blockService.getAllBlocks()).thenReturn(List.of(new BlockDto(1, "Bloque A", 2)));

        mockMvc.perform(get("/api/blocks/").contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())

                                .andExpect(jsonPath("$[0].id").value(1))
        .andExpect(jsonPath("$[0].name").value("Bloque A"))
        .andExpect(jsonPath("$[0].numberOfFloors").value(2));

        verify(blockService, times(1)).getAllBlocks();
    }

    @Test
    void givenName_whenFindByName_thenReturnBlock() throws Exception {

        BlockDto blockDto = new BlockDto();
        blockDto.setId(1);
        blockDto.setName("Bloque A");
        blockDto.setNumberOfFloors(2);

        when(blockService.findBlockByName(anyString())).thenReturn(Optional.of(blockDto));

        mockMvc.perform(get("/api/blocks/name/BloqueA").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Bloque A"))
                .andExpect(jsonPath("$.numberOfFloors").value(2))
                .andExpect(status().isOk());

        verify(blockService, times(1)).findBlockByName(anyString());
    }

    @Test
    void givenBlock_whenSaveBlock_thenReturnBlock() throws Exception {
        BlockDto blockDto = new BlockDto();
        blockDto.setId(1);
        blockDto.setName("Bloque A");
        blockDto.setNumberOfFloors(2);

        when(blockService.createBlock(any(BlockRequestDto.class))).thenReturn(blockDto);

        mockMvc.perform(post("/api/blocks/").contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                          "id": 1,
                                          "name": "Bloque A",
                                          "numberOfFloors": 2
                                        }"""))
                .andExpect(jsonPath("$.name").value("Bloque A"))
                .andExpect(jsonPath("$.numberOfFloors").value(2))
                .andExpect(status().isCreated());

        verify(blockService, times(1)).createBlock(any(BlockRequestDto.class));
    }


    @Test
    void givenBlock_whenUpdateBlock_thenReturnBlock() throws Exception {
        BlockDto blockDto = new BlockDto();
        blockDto.setId(1);
        blockDto.setName("Bloque B");
        blockDto.setNumberOfFloors(4);

        when(blockService.updateBlock(any(BlockDto.class))).thenReturn(Optional.of(blockDto));

        mockMvc.perform(put("/api/blocks/").contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                          "id": 1,
                                          "name": "Bloque B",
                                          "numberOfFloors": 4
                                        }"""))
                .andExpect(jsonPath("$.name").value("Bloque B"))
                .andExpect(jsonPath("$.numberOfFloors").value(4))
                .andExpect(status().isOk());

        verify(blockService, times(1)).updateBlock(any(BlockDto.class));
    }

    @Test
    void givenId_whenDeleteBlock_thenReturnOk() throws Exception {
        when(blockService.deleteBlock(anyInt())).thenReturn(true);

        mockMvc.perform(delete("/api/blocks/{id}", 1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(blockService, times(1)).deleteBlock(anyInt());
    }


}