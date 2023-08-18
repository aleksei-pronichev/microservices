package com.epam.pronichev.songservice.handler;

import com.epam.pronichev.songservice.exception.SongMetadataNotFoundException;
import com.epam.pronichev.songservice.model.dto.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SongMetadataNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleSongMetadataNotFoundException(SongMetadataNotFoundException ex) {
        var errorDetails = new ErrorDetails();
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}