package com.foliaco.bathrooms.infrastructure.entity;

import com.foliaco.bathrooms.domain.enums.IncidentMessage;
import com.foliaco.bathrooms.infrastructure.listeners.CustomAuditListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "incidentes")
@Setter
@Getter
@ToString
public class IncidentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "problema")
    @Enumerated(EnumType.STRING)
    private IncidentMessage problem;

    @Column(name = "comentario")
    private String comment;

    @OneToMany(mappedBy = "incidentEntity")
    private List<BathroomIncidentEntity> bathroomIncidentEntityList = new ArrayList<>();

}
