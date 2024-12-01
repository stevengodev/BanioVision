package com.foliaco.bathrooms.infrastructure.entity;

import com.foliaco.bathrooms.infrastructure.listeners.CustomAuditListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "banos_incidentes")
@Setter
@Getter
@ToString
public class BathroomIncidentEntity {

    @EmbeddedId
    private BathroomIncidentPK id;

    @Column(name = "fecha")
    private LocalDate date;

    @Column(name = "estado")
    private String status;

    @ManyToOne
    @JoinColumn(name = "bano_id",insertable = false, updatable = false)
    private BathroomEntity bathroomEntity;

    @ManyToOne
    @JoinColumn(name = "incidente_id", insertable = false, updatable = false)
    private IncidentEntity incidentEntity;

}
