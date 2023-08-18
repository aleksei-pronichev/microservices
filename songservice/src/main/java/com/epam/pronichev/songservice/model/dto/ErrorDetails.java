package com.epam.pronichev.songservice.model.dto;

import lombok.Data;

@Data
public class ErrorDetails {
    private String message;
    private int status;
}