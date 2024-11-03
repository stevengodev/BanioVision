package com.foliaco.bathrooms.infrastructure.adapter;

import com.foliaco.bathrooms.domain.dto.BlockDto;
import com.foliaco.bathrooms.domain.dto.BlockRequestDto;
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
    public List<BlockDto> getAll() {
        return blockMapper.toBlockList(blockRepository.findAll());
    }

    @Override
    public BlockDto save(BlockDto newBlock) {
        BlockEntity blockEntity = blockMapper.toBlockEntity(newBlock);
        return blockMapper.toBlock(blockRepository.save( blockEntity ));
    }

    @Override
    public Optional<BlockDto> findById(Integer id) {
        return blockRepository.findById(id).map(blockMapper::toBlock);
    }

    @Override
    public Optional<BlockDto> findByName(String blockName) {
        return blockRepository.findByName(blockName).map(blockMapper::toBlock);
    }

    @Override
    public void delete(Integer id) {
        blockRepository.deleteById(id);
    }
}
