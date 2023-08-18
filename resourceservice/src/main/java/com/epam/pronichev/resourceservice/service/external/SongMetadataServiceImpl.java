package com.epam.pronichev.resourceservice.service.external;

import com.epam.pronichev.resourceservice.client.SongMetadataClient;
import com.epam.pronichev.resourceservice.exceptions.SongMetadataException;
import com.epam.pronichev.resourceservice.model.external.SongMetadataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongMetadataServiceImpl implements SongMetadataService {

    private final SongMetadataClient songMetadataClient;

    @Override
    public void create(SongMetadataDto songMetadataDto) {
        var response = sendDto(songMetadataDto);
        validateResponse(response);
    }

    private ResponseEntity<Void> sendDto(SongMetadataDto songMetadataDto) {
        try {
            return songMetadataClient.create(songMetadataDto);
        } catch (Exception e) {
            throw new SongMetadataException("Can't create metadata, unexpected error: " + e.getMessage());
        }
    }

    private void validateResponse(ResponseEntity<Void> response) {
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            return;
        }
        throw new SongMetadataException("Can't create metadata, service return unexpected status code" + response.getStatusCode());
    }
}
