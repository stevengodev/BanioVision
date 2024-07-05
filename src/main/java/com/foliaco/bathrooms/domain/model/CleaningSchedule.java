package com.foliaco.bathrooms.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class CleaningSchedule {
    private Integer id;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Integer bathroomId;
}
