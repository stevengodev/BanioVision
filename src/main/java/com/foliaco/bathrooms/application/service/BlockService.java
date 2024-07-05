package com.foliaco.bathrooms.application.service;

import com.foliaco.bathrooms.domain.model.Block;
import com.foliaco.bathrooms.domain.ports.in.BlockUseCase;
import com.foliaco.bathrooms.domain.ports.out.BlockRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BlockService implements BlockUseCase {

    private final BlockRepositoryPort blockRepositoryPort;

    @Autowired
    public BlockService(@Qualifier("blockRepositoryAdapter") BlockRepositoryPort blockRepositoryPort) {
        this.blockRepositoryPort = blockRepositoryPort;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Block> getAllBlocks() {
        return blockRepositoryPort.getAll();
    }

    @Transactional
    @Override
    public Block createBlock(Block newBlock) {
        return blockRepositoryPort.save(newBlock);
    }

    @Transactional
    @Override
    public Optional<Block> updateBlock(Block block) {
        Optional<Block> foundBlock = blockRepositoryPort.findById(block.getId());
        return foundBlock.isEmpty() ? Optional.empty() : Optional.of(blockRepositoryPort.save(block));
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Block> findBlockById(Integer id) {
        return blockRepositoryPort.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Block> findBlockByName(String blockName) {
        return blockRepositoryPort.findByName(blockName);
    }

    @Transactional
    @Override
    public boolean deleteBlock(Integer id) {
        boolean isDeleted = false;

        if (blockRepositoryPort.findById(id).isPresent()){
            blockRepositoryPort.delete(id);
            isDeleted = true;
        }

        return isDeleted;
    }
}
