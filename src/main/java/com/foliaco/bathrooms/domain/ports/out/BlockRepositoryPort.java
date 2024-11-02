package com.foliaco.bathrooms.domain.ports.out;

import com.foliaco.bathrooms.domain.dto.BlockDto;

import java.util.List;
import java.util.Optional;

public interface BlockRepositoryPort {

    List<BlockDto> getAll();
    BlockDto save(BlockDto newBlock);
    Optional<BlockDto> findById(Integer id);
    Optional<BlockDto> findByName(String blockName);
    void delete(Integer id);
}
