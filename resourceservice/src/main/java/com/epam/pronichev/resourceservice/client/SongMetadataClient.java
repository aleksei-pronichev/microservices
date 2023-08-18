package com.epam.pronichev.resourceservice.client;

import com.epam.pronichev.resourceservice.model.external.SongMetadataDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${client.metadata.name}")
public interface SongMetadataClient {

    @PostMapping(value = "/v1/songs", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> create(@RequestBody SongMetadataDto songMetadataDto);
}