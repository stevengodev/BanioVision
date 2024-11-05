package com.foliaco.bathrooms.domain.ports.out;

import com.foliaco.bathrooms.infrastructure.entity.AuditEntity;

public interface AudityRepositoryPort {

    void save(AuditEntity auditEntity);

}
