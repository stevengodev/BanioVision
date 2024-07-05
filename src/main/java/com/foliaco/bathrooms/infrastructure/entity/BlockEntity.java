package com.foliaco.bathrooms.infrastructure.entity;

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
