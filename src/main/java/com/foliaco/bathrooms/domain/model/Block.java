package com.foliaco.bathrooms.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Block {
    private Integer id;
    private String name;
    private Integer numberOfFloors;
}
