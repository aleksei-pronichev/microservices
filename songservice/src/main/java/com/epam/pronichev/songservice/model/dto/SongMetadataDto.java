package com.epam.pronichev.songservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongMetadataDto {
    private String name;
    private String artist;
    private String album;
    private String length;
    private String resourceId;
    private Short year;
}