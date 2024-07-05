package com.foliaco.bathrooms.domain.ports.in;

import com.foliaco.bathrooms.domain.model.Bathroom;

import java.util.List;
import java.util.Optional;

public interface BathroomUseCase {

    List<Bathroom> getAllBathrooms();
    Bathroom createBathroom(Bathroom newBathroom);
    Optional<Bathroom> updateBathroom(Bathroom bathroom);
    Optional<Bathroom> getBathroomById(Integer id);
    boolean deleteBathroom(Integer id);

}
