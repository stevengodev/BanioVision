package com.foliaco.bathrooms.domain.dto;

import com.foliaco.bathrooms.domain.enums.IncidentMessage;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class IncidentDto {

    private Integer id;

    @Enumerated(EnumType.STRING)
    private IncidentMessage problem;

    private String comment;

}
