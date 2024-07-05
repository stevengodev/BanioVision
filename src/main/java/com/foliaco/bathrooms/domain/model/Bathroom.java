package com.foliaco.bathrooms.domain.model;

import com.foliaco.bathrooms.domain.enums.BathroomStatus;
import com.foliaco.bathrooms.domain.enums.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Bathroom {
    private Integer id;
    private Gender gender;
    private Integer blockId;
    private BathroomStatus status;
    private String floor;
}
