package com.foliaco.bathrooms.infrastructure.entity;

import com.foliaco.bathrooms.domain.enums.BathroomStatus;
import com.foliaco.bathrooms.domain.enums.Gender;
import com.foliaco.bathrooms.infrastructure.annotations.AuditedEntityName;
import com.foliaco.bathrooms.infrastructure.listeners.CustomAuditListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.beans.Visibility;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "banos")
@Getter
@Setter
@ToString
@EntityListeners(CustomAuditListener.class)
@AuditedEntityName("Banos")
public class BathroomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "genero")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "bloque_id")
    private Integer blockId;

    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private BathroomStatus status;

    @Column(name = "piso")
    private String floor;

    @ManyToOne
    @JoinColumn(name = "bloque_id", insertable = false, updatable = false)
    private BlockEntity blockEntity;

    @OneToMany(mappedBy = "bathroomEntity", orphanRemoval = true)
    private List<CleaningScheduleEntity> cleaningScheduleEntities = new ArrayList<>();

    @OneToMany(mappedBy = "bathroomEntity", orphanRemoval = true)
    private List<BathroomIncidentEntity> bathroomIncidentEntities = new ArrayList<>();

}
