package com.foliaco.bathrooms.infrastructure.entity;

import com.foliaco.bathrooms.infrastructure.annotations.AuditedEntityName;
import com.foliaco.bathrooms.infrastructure.listeners.CustomAuditListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bloques")
@Setter
@Getter
@ToString
@EntityListeners(CustomAuditListener.class)
@AuditedEntityName("Bloques")
public class BlockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "numero_pisos")
    private Integer numberOfFloors;

    @OneToMany(mappedBy = "blockEntity", orphanRemoval = true)
    private List<BathroomEntity> bathroomEntities = new ArrayList<>();
}
