package com.epam.pronichev.resourceservice.handler;

import com.epam.pronichev.resourceservice.exceptions.AudioParsingException;
import com.epam.pronichev.resourceservice.exceptions.ResourceNotFoundException;
import com.epam.pronichev.resourceservice.exceptions.SongMetadataException;
import com.epam.pronichev.resourceservice.model.dto.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleSongMetadataNotFoundException(ResourceNotFoundException ex) {
        var errorDetails = new ErrorDetails();
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AudioParsingException.class)
    public ResponseEntity<ErrorDetails> handleAudioParsingException(AudioParsingException ex) {
        var errorDetails = new ErrorDetails();
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SongMetadataException.class)
    public ResponseEntity<ErrorDetails> handleSongMetadataException(SongMetadataException ex) {
        var errorDetails = new ErrorDetails();
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}