package com.epam.pronichev.resourceservice.service;

import com.epam.pronichev.resourceservice.model.entities.Resource;

public interface MetadataService {

    void processMetadata(Resource resource);
}
