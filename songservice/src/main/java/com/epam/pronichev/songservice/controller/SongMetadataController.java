package com.epam.pronichev.songservice.controller;

import com.epam.pronichev.songservice.model.dto.SongMetaDataCreatedDto;
import com.epam.pronichev.songservice.model.dto.SongMetadataDto;
import com.epam.pronichev.songservice.service.SongMetadataService;
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
@RequestMapping("/v1/songs")
public class SongMetadataController {

    private final SongMetadataService songMetadataService;

    public SongMetadataController(SongMetadataService songMetadataService) {
        this.songMetadataService = songMetadataService;
    }

    @PostMapping
    public ResponseEntity<SongMetaDataCreatedDto> createSong(@RequestBody SongMetadataDto songMetadataDto) {
        var createdDto = songMetadataService.createSongMetadata(songMetadataDto);
        return new ResponseEntity<>(createdDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongMetadataDto> getSong(@PathVariable Long id) {
        SongMetadataDto songMetadataDto = songMetadataService.getSById(id);
        return new ResponseEntity<>(songMetadataDto, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteSongs(@RequestParam List<@Size(min = 1, max = 200) Long> ids) {
        songMetadataService.deleteByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}