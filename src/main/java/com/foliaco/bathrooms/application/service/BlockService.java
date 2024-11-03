package com.foliaco.bathrooms.application.service;

import com.foliaco.bathrooms.domain.dto.BlockDto;
import com.foliaco.bathrooms.domain.dto.BlockRequestDto;
import com.foliaco.bathrooms.domain.ports.in.BlockUseCase;
import com.foliaco.bathrooms.domain.ports.out.BlockRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cglib.core.Block;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("blockService")
public class BlockService implements BlockUseCase {

    private final BlockRepositoryPort blockRepositoryPort;

    @Autowired
    public BlockService(@Qualifier("blockRepositoryAdapter") BlockRepositoryPort blockRepositoryPort) {
        this.blockRepositoryPort = blockRepositoryPort;
    }

    @Transactional(readOnly = true)
    @Override
    public List<BlockDto> getAllBlocks() {
        return blockRepositoryPort.getAll();
    }

    @Transactional
    @Override
    public BlockDto createBlock(BlockRequestDto newBlock) {
        BlockDto blockDto = new BlockDto();
        blockDto.setName(newBlock.getName());
        blockDto.setNumberOfFloors(newBlock.getNumberOfFloors());
        return blockRepositoryPort.save( blockDto );
    }

    @Transactional
    @Override
    public Optional<BlockDto> updateBlock(BlockDto block) {
        Optional<BlockDto> foundBlock = blockRepositoryPort.findById(block.getId());
        return foundBlock.isEmpty() ? Optional.empty() : Optional.of(blockRepositoryPort.save(block));
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<BlockDto> findBlockById(Integer id) {
        return blockRepositoryPort.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<BlockDto> findBlockByName(String blockName) {
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
