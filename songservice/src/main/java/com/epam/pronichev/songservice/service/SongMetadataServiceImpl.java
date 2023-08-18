package com.epam.pronichev.songservice.service;

import com.epam.pronichev.songservice.exception.SongMetadataNotFoundException;
import com.epam.pronichev.songservice.mapping.SongMetadataMapper;
import com.epam.pronichev.songservice.model.dto.SongMetaDataCreatedDto;
import com.epam.pronichev.songservice.model.dto.SongMetadataDto;
import com.epam.pronichev.songservice.repository.SongMetadataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongMetadataServiceImpl implements SongMetadataService {

    private final SongMetadataRepository songMetadataRepository;
    private final SongMetadataMapper songMetadataMapper;

    @Transactional
    @Override
    public SongMetaDataCreatedDto createSongMetadata(SongMetadataDto songMetadataDto) {
        var songMetadata = songMetadataMapper.toModel(songMetadataDto);
        var savedSongMetadata = songMetadataRepository.save(songMetadata);
        return songMetadataMapper.toCreateDto(savedSongMetadata);
    }

    @Transactional(readOnly = true)
    @Override
    public SongMetadataDto getSById(Long id) {
        return songMetadataRepository.findById(id)
            .map(songMetadataMapper::toDto)
            .orElseThrow(() -> new SongMetadataNotFoundException("No SongMetadata found with id: " + id));
    }

    @Transactional
    @Override
    public void deleteByIds(List<Long> ids) {
        songMetadataRepository.deleteAllById(ids);
    }
}