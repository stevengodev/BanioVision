package com.foliaco.bathrooms.domain.ports.out;

import com.foliaco.bathrooms.domain.model.Block;

import java.util.List;
import java.util.Optional;

public interface BlockRepositoryPort {

    List<Block> getAll();
    Block save(Block newBlock);
    Optional<Block> findById(Integer id);
    Optional<Block> findByName(String blockName);
    void delete(Integer id);
}
