package com.foliaco.bathrooms.domain.ports.in;

import com.foliaco.bathrooms.domain.dto.BathroomDto;
import com.foliaco.bathrooms.domain.dto.BathroomIncidentDto;
import com.foliaco.bathrooms.domain.dto.BathroomRequestDto;
import com.foliaco.bathrooms.domain.dto.BathroomResponseDto;

import java.util.List;
import java.util.Optional;

public interface BathroomUseCase {

    List<BathroomResponseDto> getAllBathrooms();
    BathroomDto createBathroom(BathroomRequestDto bathroomRequest);
    Optional<BathroomDto> updateBathroom(BathroomDto bathroom);
    Optional<BathroomDto> getBathroomById(Integer id);
    boolean deleteBathroom(Integer id);

}
