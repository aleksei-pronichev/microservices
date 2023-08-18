package com.epam.pronichev.songservice.controller;

import com.epam.pronichev.songservice.model.dto.SongMetaDataCreatedDto;
import com.epam.pronichev.songservice.model.dto.SongMetadataDto;
import com.epam.pronichev.songservice.service.SongMetadataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SongMetadataControllerTest {

    @Mock
    private SongMetadataService songMetadataService;

    @InjectMocks
    private SongMetadataController songMetadataController;

    private SongMetadataDto songMetadataDto;

    @BeforeEach
    void init() {
        songMetadataDto = new SongMetadataDto();
        songMetadataDto.setName("Test Song");
    }

    @Test
    void whenCreateSong_thenServiceMethodIsCalled() {
        var createdSong = new SongMetaDataCreatedDto(1L);
        when(songMetadataService.createSongMetadata(songMetadataDto))
            .thenReturn(createdSong);

        songMetadataController.createSong(songMetadataDto);

        verify(songMetadataService)
            .createSongMetadata(songMetadataDto);
    }

    @Test
    void whenGetSong_thenServiceMethodIsCalled() {
        when(songMetadataService.getSById(1L))
            .thenReturn(songMetadataDto);

        songMetadataController.getSong(1L);

        verify(songMetadataService)
            .getSById(1L);
    }

    @Test
    void whenDeleteSongs_thenServiceMethodIsCalled() {
        var ids = List.of(1L, 2L);

        doNothing().when(songMetadataService)
            .deleteByIds(ids);

        songMetadataController.deleteSongs(ids);

        verify(songMetadataService)
            .deleteByIds(ids);
    }
}
