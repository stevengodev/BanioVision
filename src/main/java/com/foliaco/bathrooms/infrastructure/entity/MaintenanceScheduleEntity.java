package com.foliaco.bathrooms.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "horarios_mantenimiento")
@Setter
@Getter
@ToString
public class MaintenanceScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "fecha_inicio")
    private LocalDateTime startDateTime;

    @Column(name = "fecha_fin")
    private LocalDateTime endDateTime;

    @Column(name = "bano_id")
    private Integer bathroomId;

    @ManyToOne
    @JoinColumn(name = "bano_id", updatable = false, insertable = false)
    private BathroomEntity bathroomEntity;
}
