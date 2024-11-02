package com.foliaco.bathrooms.infrastructure.mapper;

import com.foliaco.bathrooms.domain.dto.BlockDto;
import com.foliaco.bathrooms.infrastructure.entity.BlockEntity;
import org.mapstruct.*;

import java.util.List;
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IBlockMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "numberOfFloors", target = "numberOfFloors")
    })
    BlockDto toBlock(BlockEntity blockEntity);
    @InheritInverseConfiguration
    @Mapping(target = "bathroomEntities", ignore = true)
    BlockEntity toBlockEntity(BlockDto block);
    List<BlockDto> toBlockList(List<BlockEntity> blockEntityList);
    List<BlockEntity> toBlockEntityList(List<BlockDto> blockList);

}