package com.foliaco.bathrooms.infrastructure.adapter;

import com.foliaco.bathrooms.domain.dto.BathroomDto;
import com.foliaco.bathrooms.domain.dto.BathroomRequestDto;
import com.foliaco.bathrooms.domain.dto.BathroomResponseDto;
import com.foliaco.bathrooms.domain.ports.out.BathroomRepositoryPort;
import com.foliaco.bathrooms.infrastructure.entity.BathroomEntity;
import com.foliaco.bathrooms.infrastructure.mapper.IBathroomMapper;
import com.foliaco.bathrooms.infrastructure.repository.IBathroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BathroomRepositoryAdapter implements BathroomRepositoryPort {

    private final IBathroomRepository bathroomRepository;
    private final IBathroomMapper bathroomMapper;

    @Autowired
    public BathroomRepositoryAdapter(IBathroomRepository bathroomRepository,
                                     IBathroomMapper bathroomMapper) {
        this.bathroomRepository = bathroomRepository;
        this.bathroomMapper = bathroomMapper;
    }

    @Override
    public List<BathroomResponseDto> getAll() {
        return bathroomRepository.findAll().stream()
                .map(bathroomMapper::toBathroomResponse)
                .toList();
    }

    @Override
    public BathroomDto save(BathroomDto bathroom) {
        BathroomEntity bathroomEntity = bathroomMapper.toBathroomEntity(bathroom);
        return bathroomMapper.toBathroom(bathroomRepository.save(bathroomEntity));
    }

    @Override
    public BathroomDto save(BathroomRequestDto bathroom) {
        BathroomEntity bathroomEntity = bathroomMapper.toBathroomEntity(bathroom);
        return bathroomMapper.toBathroom(bathroomRepository.save(bathroomEntity));
    }

    @Override
    public Optional<BathroomDto> findById(Integer id) {
        return bathroomRepository.findById(id).
                map(bathroomMapper::toBathroom);
    }

    @Override
    public void delete(Integer id) {
        bathroomRepository.deleteById(id);
    }
}
