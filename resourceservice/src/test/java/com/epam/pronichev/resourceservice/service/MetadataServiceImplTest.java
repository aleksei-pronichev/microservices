package com.epam.pronichev.resourceservice.service;

import com.epam.pronichev.resourceservice.mapping.SongMetadataMapper;
import com.epam.pronichev.resourceservice.model.entities.Resource;
import com.epam.pronichev.resourceservice.model.external.SongMetadataDto;
import com.epam.pronichev.resourceservice.service.external.SongMetadataService;
import com.epam.pronichev.resourceservice.service.parser.AudioParser;
import org.apache.tika.metadata.Metadata;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MetadataServiceImplTest {

    @Mock
    private AudioParser audioParser;

    @Mock
    private SongMetadataMapper songMetadataMapper;

    @Mock
    private SongMetadataService songMetadataService;

    @InjectMocks
    private MetadataServiceImpl metadataService;

    @Test
    void whenProcessMetadata_thenMetadataProcessed() {
        byte[] mockData = "audio data".getBytes();
        var resource = new Resource();
        resource.setData(mockData);

        var metadata = new Metadata();
        var songMetadataDto = new SongMetadataDto();

        when(audioParser.getMetadata(resource.getData()))
            .thenReturn(metadata);
        when(songMetadataMapper.toDto(resource, metadata))
            .thenReturn(songMetadataDto);

        metadataService.processMetadata(resource);

        verify(audioParser)
            .getMetadata(resource.getData());
        verify(songMetadataMapper)
            .toDto(resource, metadata);
        verify(songMetadataService)
            .create(songMetadataDto);
    }
}
