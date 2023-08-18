package com.epam.pronichev.songservice.mapping;

import com.epam.pronichev.songservice.config.SongServiceMapperConfig;
import com.epam.pronichev.songservice.model.dto.SongMetaDataCreatedDto;
import com.epam.pronichev.songservice.model.dto.SongMetadataDto;
import com.epam.pronichev.songservice.model.entities.SongMetadata;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = SongServiceMapperConfig.class)
public interface SongMetadataMapper {

    @Mapping(target = "id", ignore = true)
    SongMetadata toModel(SongMetadataDto dto);

    SongMetadataDto toDto(SongMetadata model);

    SongMetaDataCreatedDto toCreateDto(SongMetadata model);
}
