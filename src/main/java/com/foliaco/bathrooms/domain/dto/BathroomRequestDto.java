package com.foliaco.bathrooms.domain.dto;

import com.foliaco.bathrooms.domain.enums.BathroomStatus;
import com.foliaco.bathrooms.domain.enums.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BathroomRequestDto {
    private Gender gender;
    private Integer blockId;
    private BathroomStatus status;
    private String floor;
}
