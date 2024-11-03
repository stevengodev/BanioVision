package com.foliaco.bathrooms.domain.dto;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BlockDto {
    private Integer id;
    private String name;
    private Integer numberOfFloors;
}
