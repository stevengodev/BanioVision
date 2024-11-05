package com.foliaco.bathrooms.infrastructure.repository;

import com.foliaco.bathrooms.infrastructure.entity.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAudityRepository extends JpaRepository<AuditEntity, Long> {
}
