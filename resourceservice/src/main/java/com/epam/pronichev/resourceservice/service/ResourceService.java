package com.epam.pronichev.resourceservice.service;

import com.epam.pronichev.resourceservice.model.dto.ResourceCreatedDto;

import java.util.List;

public interface ResourceService {

    ResourceCreatedDto create(byte[] data);

    byte[] getById(Long id);

    void deleteByIds(List<Long> ids);
}
