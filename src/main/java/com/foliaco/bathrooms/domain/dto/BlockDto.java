package com.foliaco.bathrooms.domain.dto;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BlockDto {
    private Integer id;
    private String name;
    private Integer numberOfFloors;
}
