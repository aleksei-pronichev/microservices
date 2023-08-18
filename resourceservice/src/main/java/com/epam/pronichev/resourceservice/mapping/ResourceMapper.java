package com.epam.pronichev.resourceservice.mapping;

import com.epam.pronichev.resourceservice.config.ResourseServiceMapperConfig;
import com.epam.pronichev.resourceservice.model.dto.ResourceCreatedDto;
import com.epam.pronichev.resourceservice.model.entities.Resource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = ResourseServiceMapperConfig.class)
public interface ResourceMapper {

    @Mapping(target = "id", ignore = true)
    Resource toModel(byte[] data);

    ResourceCreatedDto toCreatedDto(Resource savedResource);
}
