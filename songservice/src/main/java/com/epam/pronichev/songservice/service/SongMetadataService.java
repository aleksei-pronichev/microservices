package com.epam.pronichev.songservice.service;

import com.epam.pronichev.songservice.model.dto.SongMetaDataCreatedDto;
import com.epam.pronichev.songservice.model.dto.SongMetadataDto;

import java.util.List;

public interface SongMetadataService {

    SongMetaDataCreatedDto createSongMetadata(SongMetadataDto dto);

    SongMetadataDto getSById(Long id);

    void deleteByIds(List<Long> ids);
}
