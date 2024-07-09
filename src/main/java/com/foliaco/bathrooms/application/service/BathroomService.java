package com.foliaco.bathrooms.application.service;

import com.foliaco.bathrooms.domain.model.Bathroom;
import com.foliaco.bathrooms.domain.ports.in.BathroomUseCase;
import com.foliaco.bathrooms.domain.ports.out.BathroomRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BathroomService implements BathroomUseCase {

    private final BathroomRepositoryPort bathroomRepositoryPort;

    @Autowired
    public BathroomService( @Qualifier("bathroomRepositoryAdapter") BathroomRepositoryPort bathroomRepositoryPort) {
        this.bathroomRepositoryPort = bathroomRepositoryPort;
    }

    @Override
    public List<Bathroom> getAllBathrooms() {
        return bathroomRepositoryPort.getAll();
    }

    @Override
    public Bathroom createBathroom(Bathroom newBathroom) {
        return bathroomRepositoryPort.save(newBathroom);
    }

    @Override
    public Optional<Bathroom> updateBathroom(Bathroom bathroom) {
        Optional<Bathroom> foundBathroom = bathroomRepositoryPort.findById(bathroom.getId());
        return foundBathroom.isEmpty() ? Optional.empty() : Optional.of(bathroomRepositoryPort.save(bathroom));
    }

    @Override
    public Optional<Bathroom> getBathroomById(Integer id) {
        return bathroomRepositoryPort.findById(id);
    }

    @Override
    public boolean deleteBathroom(Integer id) {
        boolean isDeleted = false;

        if (bathroomRepositoryPort.findById(id).isPresent()){
            bathroomRepositoryPort.delete(id);
            isDeleted = true;
        }
        return isDeleted;
    }
}
