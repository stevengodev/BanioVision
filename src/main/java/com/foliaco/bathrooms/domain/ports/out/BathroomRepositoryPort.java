package com.foliaco.bathrooms.domain.ports.out;

import com.foliaco.bathrooms.domain.dto.BathroomDto;
import com.foliaco.bathrooms.domain.dto.BathroomRequestDto;
import com.foliaco.bathrooms.domain.dto.BathroomResponseDto;

import java.util.List;
import java.util.Optional;

public interface BathroomRepositoryPort {

    List<BathroomResponseDto> getAll();
    BathroomDto save(BathroomRequestDto bathroom);
    BathroomDto save(BathroomDto bathroom);
    Optional<BathroomDto> findById(Integer id);
    void delete(Integer id);

}
