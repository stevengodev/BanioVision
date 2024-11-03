package com.foliaco.bathrooms.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class BlockRequestDto {
    private String name;
    private Integer numberOfFloors;
}
