package com.foliaco.bathrooms.domain.dto;

import com.foliaco.bathrooms.domain.enums.BathroomStatus;
import com.foliaco.bathrooms.domain.enums.Gender;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BathroomResponseDto {

    private Integer id;
    private Gender gender;
    private String nameBlock;
    private BathroomStatus status;
    private String floor;

}
