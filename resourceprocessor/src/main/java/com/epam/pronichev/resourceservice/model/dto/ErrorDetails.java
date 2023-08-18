package com.epam.pronichev.resourceservice.model.dto;

import lombok.Data;

@Data
public class ErrorDetails {
    private String message;
    private int status;
}