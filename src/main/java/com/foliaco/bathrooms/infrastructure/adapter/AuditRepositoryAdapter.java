package com.foliaco.bathrooms.infrastructure.adapter;

import com.foliaco.bathrooms.domain.ports.out.AudityRepositoryPort;
import com.foliaco.bathrooms.infrastructure.entity.AuditEntity;
import com.foliaco.bathrooms.infrastructure.repository.IAudityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuditRepositoryAdapter implements AudityRepositoryPort {

    IAudityRepository audityRepository;

    @Autowired
    public AuditRepositoryAdapter(IAudityRepository audityRepository) {
        this.audityRepository = audityRepository;
    }

    @Override
    public void save(AuditEntity auditEntity) {
        audityRepository.save(auditEntity);
    }
}
