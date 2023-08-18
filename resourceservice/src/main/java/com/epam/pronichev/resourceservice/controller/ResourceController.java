package com.epam.pronichev.resourceservice.controller;

import com.epam.pronichev.resourceservice.model.dto.ResourceCreatedDto;
import com.epam.pronichev.resourceservice.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("/v1/resources")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @PostMapping(consumes = "audio/mpeg")
    public ResponseEntity<ResourceCreatedDto> uploadAudio(@RequestBody byte[] audioData) {
        var created = resourceService.create(audioData);
        return new ResponseEntity<>(created, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getSong(@PathVariable Long id) {
        var data = resourceService.getById(id);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteSongs(@RequestParam List<@Size(min = 1, max = 200) Long> ids) {
        resourceService.deleteByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
