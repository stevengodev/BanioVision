package com.foliaco.bathrooms.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class MaintenanceScheduleDto {

    private Integer id;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Integer bathroomId;
}
