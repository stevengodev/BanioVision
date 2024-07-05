package com.foliaco.bathrooms.domain.ports.out;

import com.foliaco.bathrooms.domain.model.Bathroom;

import java.util.List;
import java.util.Optional;

public interface BathroomRepositoryPort {

    List<Bathroom> getAll();
    Bathroom save(Bathroom bathroom);
    Optional<Bathroom> findById(Integer id);
    void delete(Integer id);

}
