package com.foliaco.bathrooms.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BlockDto {
    private Integer id;
    private String name;
    private Integer numberOfFloors;
}
