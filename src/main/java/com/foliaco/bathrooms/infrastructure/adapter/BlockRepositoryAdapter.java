package com.foliaco.bathrooms.infrastructure.adapter;

import com.foliaco.bathrooms.domain.model.Block;
import com.foliaco.bathrooms.domain.ports.out.BlockRepositoryPort;
import com.foliaco.bathrooms.infrastructure.entity.BlockEntity;
import com.foliaco.bathrooms.infrastructure.mapper.IBlockMapper;
import com.foliaco.bathrooms.infrastructure.repository.IBlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BlockRepositoryAdapter implements BlockRepositoryPort {

    private final IBlockRepository blockRepository;
    private final IBlockMapper blockMapper;

    @Autowired
    public BlockRepositoryAdapter(IBlockRepository blockRepository, IBlockMapper blockMapper) {
        this.blockRepository = blockRepository;
        this.blockMapper = blockMapper;
    }
    @Override
    public List<Block> getAll() {
        return blockMapper.toBlockList(blockRepository.findAll());
    }

    @Override
    public Block save(Block newBlock) {
        BlockEntity blockEntity = blockMapper.toBlockEntity(newBlock);
        return blockMapper.toBlock(blockRepository.save( blockEntity ));    }

    @Override
    public Optional<Block> findById(Integer id) {
        return blockRepository.findById(id).map(blockMapper::toBlock);
    }

    @Override
    public Optional<Block> findByName(String blockName) {
        return blockRepository.findByName(blockName).map(blockMapper::toBlock);
    }

    @Override
    public void delete(Integer id) {
        blockRepository.deleteById(id);
    }
}
