package com.foliaco.bathrooms.infrastructure.repository;

import com.foliaco.bathrooms.infrastructure.entity.BlockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IBlockRepository extends JpaRepository<BlockEntity, Integer>{
    Optional<BlockEntity> findByName(String name);

}
