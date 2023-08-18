package com.epam.pronichev.resourceservice.service;

import com.epam.pronichev.resourceservice.mapping.SongMetadataMapper;
import com.epam.pronichev.resourceservice.model.entities.Resource;
import com.epam.pronichev.resourceservice.model.external.SongMetadataDto;
import com.epam.pronichev.resourceservice.service.external.SongMetadataService;
import com.epam.pronichev.resourceservice.service.parser.AudioParser;
import lombok.RequiredArgsConstructor;
import org.apache.tika.metadata.Metadata;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MetadataServiceImpl implements MetadataService {

    private final AudioParser audioParser;
    private final SongMetadataMapper songMetadataMapper;
    private final SongMetadataService songMetadataService;

    @Override
    public void processMetadata(Resource resource) {

        Metadata metadata = audioParser.getMetadata(resource.getData());
        SongMetadataDto songMetadataDto = songMetadataMapper.toDto(resource, metadata);
        songMetadataService.create(songMetadataDto);
    }
}
