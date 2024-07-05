package com.foliaco.bathrooms.domain.ports.in;

import com.foliaco.bathrooms.domain.model.Block;

import java.util.List;
import java.util.Optional;

public interface BlockUseCase {

    List<Block> getAllBlocks();
    Block createBlock(Block newBlock);
    Optional<Block> updateBlock(Block block);
    Optional<Block> findBlockById(Integer id);
    Optional<Block> findBlockByName(String blockName);
    boolean deleteBlock(Integer id);
}
