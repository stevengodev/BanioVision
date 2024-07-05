package com.foliaco.bathrooms.infrastructure.repository;

import com.foliaco.bathrooms.infrastructure.entity.BathroomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBathroomRepository extends JpaRepository<BathroomEntity, Integer> {
}
