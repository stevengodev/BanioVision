package com.foliaco.bathrooms.domain.ports.out;

import com.foliaco.bathrooms.domain.dto.BathroomDto;

import java.util.List;
import java.util.Optional;

public interface BathroomRepositoryPort {

    List<BathroomDto> getAll();
    BathroomDto save(BathroomDto bathroom);
    Optional<BathroomDto> findById(Integer id);
    void delete(Integer id);

}
