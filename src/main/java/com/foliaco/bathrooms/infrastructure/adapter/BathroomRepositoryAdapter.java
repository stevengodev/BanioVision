package com.foliaco.bathrooms.infrastructure.adapter;

import com.foliaco.bathrooms.domain.model.Bathroom;
import com.foliaco.bathrooms.domain.ports.out.BathroomRepositoryPort;
import com.foliaco.bathrooms.infrastructure.entity.BathroomEntity;
import com.foliaco.bathrooms.infrastructure.mapper.IBathroomMapper;
import com.foliaco.bathrooms.infrastructure.repository.IBathroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BathroomRepositoryAdapter implements BathroomRepositoryPort {

    private final IBathroomRepository bathroomRepository;
    private final IBathroomMapper bathroomMapper;

    @Autowired
    public BathroomRepositoryAdapter(@Qualifier("IBathroomRepository") IBathroomRepository bathroomRepository,
                                     @Qualifier("IBathroomMapperImpl") IBathroomMapper bathroomMapper) {
        this.bathroomRepository = bathroomRepository;
        this.bathroomMapper = bathroomMapper;
    }

    @Override
    public List<Bathroom> getAll() {
        return bathroomMapper.toBathrooms( bathroomRepository.findAll() );
    }

    @Override
    public Bathroom save(Bathroom bathroom) {
        BathroomEntity bathroomEntity = bathroomMapper.toBathroomEntity(bathroom);
        return bathroomMapper.toBathroom(bathroomRepository.save(bathroomEntity));
    }

    @Override
    public Optional<Bathroom> findById(Integer id) {
        return bathroomRepository.findById(id).
                map(bathroomMapper::toBathroom);
    }

    @Override
    public void delete(Integer id) {
        bathroomRepository.deleteById(id);
    }
}
