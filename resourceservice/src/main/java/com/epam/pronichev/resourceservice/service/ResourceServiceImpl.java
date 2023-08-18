package com.epam.pronichev.resourceservice.service;

import com.epam.pronichev.resourceservice.exceptions.ResourceNotFoundException;
import com.epam.pronichev.resourceservice.mapping.ResourceMapper;
import com.epam.pronichev.resourceservice.model.dto.ResourceCreatedDto;
import com.epam.pronichev.resourceservice.model.entities.Resource;
import com.epam.pronichev.resourceservice.repository.CloudRepository;
import com.epam.pronichev.resourceservice.repository.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final ResourceMapper resourceMapper;
    private final ResourceRepository resourceRepository;
    private final CloudRepository cloudRepository;

    @Transactional
    @Override
    public ResourceCreatedDto create(byte[] data) {
        var cloudId = generateCloudId();
        cloudRepository.save(cloudId, data);

        var resource = resourceMapper.toModel(cloudId);
        var savedResource = resourceRepository.save(resource);

        return resourceMapper.toCreatedDto(savedResource);
    }

    private String generateCloudId() {
        return UUID.randomUUID().toString();
    }

    @Transactional
    @Override
    public byte[] getById(Long id) {
        return resourceRepository.findById(id)
            .map(Resource::getCloudId)
            .map(cloudRepository::get)
            .orElseThrow(() -> new ResourceNotFoundException("No Resource found with id: " + id));
    }

    @Transactional
    @Override
    public void deleteByIds(List<Long> ids) {
        resourceRepository.deleteAllById(ids);
    }
}
