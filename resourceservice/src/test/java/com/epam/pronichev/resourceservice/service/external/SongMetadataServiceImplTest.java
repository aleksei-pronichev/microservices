package com.epam.pronichev.resourceservice.service.external;


import com.epam.pronichev.resourceservice.client.SongMetadataClient;
import com.epam.pronichev.resourceservice.exceptions.SongMetadataException;
import com.epam.pronichev.resourceservice.model.external.SongMetadataDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SongMetadataServiceImplTest {

    @Mock
    private SongMetadataClient songMetadataClient;

    @InjectMocks
    private SongMetadataServiceImpl songMetadataService;

    @Test
    void whenCreateSongMetadata_thenValidateResponse() {
        var songMetadataDto = getTestSongMetadataDto();
        var response = new ResponseEntity<Void>(HttpStatus.OK);

        when(songMetadataClient.create(songMetadataDto))
            .thenReturn(response);

        songMetadataService.create(songMetadataDto);

        verify(songMetadataClient)
            .create(songMetadataDto);
    }

    @Test
    void whenCreateSongMetadata_thenThrowException() {
        var songMetadataDto = getTestSongMetadataDto();
        var response = new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

        when(songMetadataClient.create(songMetadataDto))
            .thenReturn(response);

        assertThatThrownBy(() -> songMetadataService.create(songMetadataDto))
            .isInstanceOf(SongMetadataException.class)
            .hasMessageContaining("Can't create metadata, service return unexpected status code");
    }

    private SongMetadataDto getTestSongMetadataDto() {
        var songMetadataDto = new SongMetadataDto();
        songMetadataDto.setName("Test Song");
        songMetadataDto.setArtist("Test Artist");
        songMetadataDto.setAlbum("Test Album");
        songMetadataDto.setLength("04:20");
        songMetadataDto.setResourceId("TestResourceId");
        songMetadataDto.setYear((short) 2023);

        return songMetadataDto;
    }
}