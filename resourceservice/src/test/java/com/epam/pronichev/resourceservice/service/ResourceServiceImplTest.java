package com.epam.pronichev.resourceservice.service;

import com.epam.pronichev.resourceservice.mapping.ResourceMapper;
import com.epam.pronichev.resourceservice.model.dto.ResourceCreatedDto;
import com.epam.pronichev.resourceservice.model.entities.Resource;
import com.epam.pronichev.resourceservice.repository.ResourceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResourceServiceImplTest {

    @Mock
    private ResourceMapper resourceMapper;
    @Mock
    private ResourceRepository resourceRepository;
    @Mock
    private MetadataService metadataService;

    @InjectMocks
    private ResourceServiceImpl resourceService;

    @Test
    void whenCreate_thenResourceCreated() {
        byte[] mockData = "audio data".getBytes();
        var resource = new Resource();
        resource.setData(mockData);
        var savedResource = new Resource();
        savedResource.setId(1L);
        savedResource.setData(mockData);
        var createdDto = new ResourceCreatedDto();
        createdDto.setId(savedResource.getId());

        when(resourceMapper.toModel(mockData))
            .thenReturn(resource);
        when(resourceRepository.save(resource))
            .thenReturn(savedResource);
        when(resourceMapper.toCreatedDto(savedResource))
            .thenReturn(createdDto);

        var result = resourceService.create(mockData);

        assertThat(result)
            .isEqualTo(createdDto);

        verify(resourceMapper)
            .toModel(mockData);
        verify(resourceRepository)
            .save(resource);
        verify(resourceMapper)
            .toCreatedDto(savedResource);
        verify(metadataService)
            .processMetadata(savedResource);
    }

    @Test
    void whenGetById_thenDataReturned() {
        var id = 1L;
        byte[] mockData = "audio data".getBytes();
        var resource = new Resource();
        resource.setData(mockData);

        when(resourceRepository.findById(id))
            .thenReturn(Optional.of(resource));

        var result = resourceService.getById(id);

        assertThat(result)
            .isEqualTo(mockData);

        verify(resourceRepository)
            .findById(id);
    }

    @Test
    void whenDeleteByIds_thenResourcesDeleted() {
        var ids = List.of(1L, 2L, 3L);

        resourceService.deleteByIds(ids);

        verify(resourceRepository)
            .deleteAllById(ids);
    }
}
