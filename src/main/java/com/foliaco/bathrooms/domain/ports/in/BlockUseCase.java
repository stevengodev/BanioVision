package com.foliaco.bathrooms.domain.ports.in;

import com.foliaco.bathrooms.domain.dto.BlockDto;

import java.util.List;
import java.util.Optional;

public interface BlockUseCase {

    List<BlockDto> getAllBlocks();
    BlockDto createBlock(BlockDto newBlock);
    Optional<BlockDto> updateBlock(BlockDto block);
    Optional<BlockDto> findBlockById(Integer id);
    Optional<BlockDto> findBlockByName(String blockName);
    boolean deleteBlock(Integer id);
}
