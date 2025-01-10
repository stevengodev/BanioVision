package com.foliaco.bathrooms.infrastructure.listeners;

import com.foliaco.bathrooms.domain.ports.out.AudityRepositoryPort;
import com.foliaco.bathrooms.infrastructure.annotations.AuditEntityNameResolver;
import com.foliaco.bathrooms.infrastructure.entity.AuditEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CustomAuditListener {

    private final AudityRepositoryPort audityRepositoryPort;

    @Autowired
    public CustomAuditListener(@Lazy AudityRepositoryPort audityRepositoryPort) {
        this.audityRepositoryPort = audityRepositoryPort;
    }

    @PrePersist
    public void onPrePersist(Object entity) {
        registerAudit(entity, "CREATED");
    }

    @PreUpdate
    public void onPreUpdate(Object entity) {
        registerAudit(entity, "UPDATED");
    }

    @PreRemove
    public void onPreRemove(Object entity) {
        registerAudit(entity, "DELETED");
    }

    private void registerAudit(Object entity, String action) {
        String nameClass = AuditEntityNameResolver.getCustomEntityName(entity.getClass());
        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        AuditEntity auditEntity = new AuditEntity();
        auditEntity.setEntityName(nameClass);
        auditEntity.setDate(LocalDateTime.now());
        auditEntity.setAction(action);
        auditEntity.setUser(user);

        audityRepositoryPort.save(auditEntity);
    }

}
