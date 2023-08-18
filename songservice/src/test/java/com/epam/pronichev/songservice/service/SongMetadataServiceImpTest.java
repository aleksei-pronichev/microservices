package com.epam.pronichev.songservice.service;

import com.epam.pronichev.songservice.exception.SongMetadataNotFoundException;
import com.epam.pronichev.songservice.mapping.SongMetadataMapper;
import com.epam.pronichev.songservice.model.dto.SongMetadataDto;
import com.epam.pronichev.songservice.model.entities.SongMetadata;
import com.epam.pronichev.songservice.repository.SongMetadataRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SongMetadataServiceImpTest {

    @InjectMocks
    private SongMetadataServiceImpl songMetadataService;

    @Mock
    private SongMetadataRepository songMetadataRepository;
    @Mock
    private SongMetadataMapper songMetadataMapper;

    @Test
    void whenCreateSongMetadata() {
        var songMetadataDto = new SongMetadataDto();
        songMetadataDto.setName("Test Song");

        var songMetadata = new SongMetadata();
        songMetadata.setName("Test Song");

        when(songMetadataMapper.toModel(songMetadataDto))
            .thenReturn(songMetadata);
        when(songMetadataRepository.save(songMetadata))
            .thenReturn(songMetadata);

        var createdId = songMetadataService
            .createSongMetadata(songMetadataDto);

        assertThat(createdId)
            .isEqualTo(songMetadata.getId());
        verify(songMetadataMapper)
            .toModel(songMetadataDto);
        verify(songMetadataRepository)
            .save(songMetadata);
    }

    @Test
    void whenGetSongMetadataById() {
        var songMetadata = new SongMetadata();
        songMetadata.setId(1L);

        SongMetadataDto songMetadataDto =
            new SongMetadataDto("song name", "Ray Charlse", "1970s best", "5:84", "some id", (short) 1999);
        songMetadataDto.setName("name");

        when(songMetadataRepository.findById(1L))
            .thenReturn(Optional.of(songMetadata));
        when(songMetadataMapper.toDto(songMetadata))
            .thenReturn(songMetadataDto);

        var found = songMetadataService.getSById(1L);

        assertThat(found)
            .isEqualTo(songMetadataDto);
        verify(songMetadataRepository)
            .findById(1L);
    }

    @Test
    void whenGetSongMetadataById_NotFound() {
        when(songMetadataRepository.findById(1L))
            .thenReturn(Optional.empty());

        assertThatThrownBy(() -> songMetadataService.getSById(1L))
            .isInstanceOf(SongMetadataNotFoundException.class)
            .hasMessageContaining("No SongMetadata found with id: 1");

        verify(songMetadataRepository)
            .findById(1L);
    }

    @Test
    void whenDeleteSongMetadataByIds() {
        var ids = Arrays.asList(1L, 2L);

        songMetadataService.deleteByIds(ids);

        verify(songMetadataRepository).deleteAllById(ids);
    }
}