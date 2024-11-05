package com.foliaco.bathrooms.application.service;

import com.foliaco.bathrooms.domain.dto.BathroomDto;
import com.foliaco.bathrooms.domain.ports.in.BathroomUseCase;
import com.foliaco.bathrooms.domain.ports.out.BathroomRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BathroomService implements BathroomUseCase {

    private final BathroomRepositoryPort bathroomRepositoryPort;

    @Autowired
    public BathroomService(BathroomRepositoryPort bathroomRepositoryPort) {
        this.bathroomRepositoryPort = bathroomRepositoryPort;
    }

    @Override
    public List<BathroomDto> getAllBathrooms() {
        return bathroomRepositoryPort.getAll();
    }

    @Override
    public BathroomDto createBathroom(BathroomDto newBathroom) {
        return bathroomRepositoryPort.save(newBathroom);
    }

    @Override
    public Optional<BathroomDto> updateBathroom(BathroomDto bathroom) {
        Optional<BathroomDto> foundBathroom = bathroomRepositoryPort.findById(bathroom.getId());
        return foundBathroom.isEmpty() ? Optional.empty() : Optional.of(bathroomRepositoryPort.save(bathroom));
    }

    @Override
    public Optional<BathroomDto> getBathroomById(Integer id) {
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