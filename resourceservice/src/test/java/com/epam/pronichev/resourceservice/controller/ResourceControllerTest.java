package com.epam.pronichev.resourceservice.controller;

import com.epam.pronichev.resourceservice.model.dto.ResourceCreatedDto;
import com.epam.pronichev.resourceservice.service.ResourceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResourceControllerTest {

    @Mock
    private ResourceService resourceService;

    @InjectMocks
    private ResourceController resourceController;

    @Test
    void testUploadAudio() {
        byte[] mockData = new byte[]{1, 2, 3};
        var createdDto = new ResourceCreatedDto(5L);

        when(resourceService.create(mockData))
            .thenReturn(createdDto);

        var response = resourceController.uploadAudio(mockData);

        assertThat(response.getStatusCode())
            .isEqualTo(HttpStatus.OK);
        assertThat(response.getBody())
            .isEqualTo(createdDto);

        verify(resourceService)
            .create(mockData);
    }

    @Test
    void testGetSong() {
        var id = 1L;
        byte[] mockData = new byte[]{1, 1, 55};

        when(resourceService.getById(id))
            .thenReturn(mockData);

        var response = resourceController.getSong(id);

        assertThat(response.getStatusCode())
            .isEqualTo(HttpStatus.OK);
        assertThat(response.getBody())
            .isEqualTo(mockData);

        verify(resourceService)
            .getById(id);
    }

    @Test
    void testDeleteSongs() {
        var ids = List.of(1L, 2L, 3L);

        var response = resourceController.deleteSongs(ids);

        assertThat(response.getStatusCode())
            .isEqualTo(HttpStatus.OK);

        verify(resourceService)
            .deleteByIds(ids);
    }
}
