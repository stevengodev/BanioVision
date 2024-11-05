package com.foliaco.bathrooms.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "auditorias")
@Setter
@Getter
public class AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tabla")
    private String entityName;

    @Column(name = "accion")
    private String action;

    @Column(name = "usuario")
    private String user;

    @Column(name = "fecha")
    private LocalDateTime date;

}
