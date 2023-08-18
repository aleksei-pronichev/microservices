package com.epam.pronichev.songservice.exception;

public class SongMetadataNotFoundException extends RuntimeException {

    public SongMetadataNotFoundException(String message) {
        super(message);
    }
}