package com.foliaco.bathrooms.domain.ports.in;

import com.foliaco.bathrooms.domain.dto.BathroomDto;
import com.foliaco.bathrooms.domain.dto.BathroomIncidentDto;

import java.util.List;
import java.util.Optional;

public interface BathroomUseCase {

    List<BathroomDto> getAllBathrooms();
    BathroomDto createBathroom(BathroomDto newBathroom);
    Optional<BathroomDto> updateBathroom(BathroomDto bathroom);
    Optional<BathroomDto> getBathroomById(Integer id);
    boolean deleteBathroom(Integer id);

}
