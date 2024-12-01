package com.foliaco.bathrooms.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class BathroomIncidentPK implements Serializable {

    @Column(name = "bano_id")
    private Integer bathroomId;

    @Column(name = "incidente_id")
    private Integer incidentId;
}
