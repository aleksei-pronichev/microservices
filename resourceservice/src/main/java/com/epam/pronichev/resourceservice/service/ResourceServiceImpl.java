package com.epam.pronichev.resourceservice.service;

import com.epam.pronichev.resourceservice.exceptions.ResourceNotFoundException;
import com.epam.pronichev.resourceservice.mapping.ResourceMapper;
import com.epam.pronichev.resourceservice.model.dto.ResourceCreatedDto;
import com.epam.pronichev.resourceservice.model.entities.Resource;
import com.epam.pronichev.resourceservice.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final ResourceMapper resourceMapper;
    private final ResourceRepository resourceRepository;
    private final MetadataService metadataService;

    @Transactional
    @Override
    public ResourceCreatedDto create(byte[] data) {
        var resource = resourceMapper.toModel(data);
        var savedResource = resourceRepository.save(resource);
        metadataService.processMetadata(savedResource);

        return resourceMapper.toCreatedDto(savedResource);
    }

    @Transactional
    @Override
    public byte[] getById(Long id) {
        return resourceRepository.findById(id)
            .map(Resource::getData)
            .orElseThrow(() -> new ResourceNotFoundException("No Resource found with id: " + id));
    }

    @Transactional
    @Override
    public void deleteByIds(List<Long> ids) {
        resourceRepository.deleteAllById(ids);
    }
}
