package com.foliaco.bathrooms.domain.enums;

import lombok.Getter;

@Getter
public enum IncidentMessage {
    FALTA_DE_JABON("Falta de jabón"),
    FALTA_DE_PAPEL("Falta de papel higiénico"),
    FALTA_DE_AGUA("Falta de agua en lavamanos"),
    INODORO_AVERIADO("Inodoro averiado"),
    OTRO("Otro");

    private final String description;

    IncidentMessage(String description) {
        this.description = description;
    }

}
