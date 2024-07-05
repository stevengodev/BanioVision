package com.foliaco.bathrooms.infrastructure.mapper;

import com.foliaco.bathrooms.domain.model.Block;
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
    Block toBlock(BlockEntity blockEntity);
    @InheritInverseConfiguration
    @Mapping(target = "bathroomEntities", ignore = true)
    BlockEntity toBlockEntity(Block block);
    List<Block> toBlockList(List<BlockEntity> blockEntityList);
    List<BlockEntity> toBlockEntityList(List<Block> blockList);

}