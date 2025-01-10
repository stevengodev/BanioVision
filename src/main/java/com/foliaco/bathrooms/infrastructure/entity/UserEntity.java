package com.foliaco.bathrooms.infrastructure.entity;


import com.foliaco.bathrooms.domain.enums.Role;
import com.foliaco.bathrooms.infrastructure.annotations.AuditedEntityName;
import com.foliaco.bathrooms.infrastructure.listeners.CustomAuditListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@ToString
@EntityListeners(CustomAuditListener.class)
@AuditedEntityName("Usuarios")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombres")
    private String names;

    @Column(name = "apellidos")
    private String lastNames;

    @Column(name = "correo", unique = true)
    private String email;

    @Column(name = "contrasenia")
    private String password;

    @Column(name = "rol")
    @Enumerated(EnumType.STRING)
    private Role role;

}
