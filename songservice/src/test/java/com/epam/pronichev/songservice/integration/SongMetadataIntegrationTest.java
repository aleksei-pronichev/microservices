package com.epam.pronichev.songservice.integration;

import com.epam.pronichev.songservice.model.dto.SongMetaDataCreatedDto;
import com.epam.pronichev.songservice.model.dto.SongMetadataDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SongMetadataIntegrationTest {

    private static final String SONG_PATH = "/v1/songs";

    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:13.1")
        .withUsername("postgres")
        .withPassword("password")
        .withDatabaseName("test");

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeAll
    static void initDB() {
        container.start();
        System.setProperty("spring.datasource.url", container.getJdbcUrl());
        System.setProperty("spring.datasource.username", container.getUsername());
        System.setProperty("spring.datasource.password", container.getPassword());
    }

    @Test
    void whenCreateSong_thenStatusOk() {
        var songMetadataDto = new SongMetadataDto();
        songMetadataDto.setName("Test Song");

        var response = restTemplate.postForEntity(SONG_PATH, songMetadataDto, SongMetaDataCreatedDto.class);

        assertThat(response.getStatusCode())
            .isEqualTo(HttpStatus.OK);
        assertThat(response.getBody())
            .isNotNull();
        assertThat(response.getBody().getId())
            .isNotNull();
    }

    @Test
    void whenGetSong_thenStatusOk() {
        var songMetadataDto = new SongMetadataDto();
        songMetadataDto.setName("Test Song");

        var postResponse = restTemplate.postForEntity(SONG_PATH, songMetadataDto, SongMetaDataCreatedDto.class);

        var getResponse = restTemplate.getForEntity(SONG_PATH + "/" + postResponse.getBody().getId(), SongMetadataDto.class);

        assertThat(getResponse.getStatusCode())
            .isEqualTo(HttpStatus.OK);

        assertThat(getResponse.getBody())
            .isEqualTo(songMetadataDto);
    }

    @Test
    void whenGetSong_thenStatusNotFound() {
        var notRealId = 34853495L;
        var getResponse = restTemplate.getForEntity(SONG_PATH + "/" + notRealId, SongMetadataDto.class);

        assertThat(getResponse.getStatusCode())
            .isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void whenDeleteSongs_thenStatusOk() {
        var songMetadataDto = new SongMetadataDto();
        songMetadataDto.setName("Test Song");

        var response1 = restTemplate.postForEntity(SONG_PATH, songMetadataDto, SongMetaDataCreatedDto.class);
        var response2 = restTemplate.postForEntity(SONG_PATH, songMetadataDto, SongMetaDataCreatedDto.class);

        var id1 = response1.getBody().getId();
        var id2 = response2.getBody().getId();

        var getResponse1before = restTemplate.getForEntity(SONG_PATH + "/" + id1, SongMetadataDto.class);
        var getResponse2before = restTemplate.getForEntity(SONG_PATH + "/" + id2, SongMetadataDto.class);

        assertThat(getResponse1before.getStatusCode())
            .isEqualTo(HttpStatus.OK);
        assertThat(getResponse2before.getStatusCode())
            .isEqualTo(HttpStatus.OK);

        var idsForQuery = Stream.of(id1, id2)
            .map(String::valueOf)
            .collect(Collectors.joining(","));
        restTemplate.delete(SONG_PATH + "?ids=" + idsForQuery);

        var getResponse1 = restTemplate.getForEntity(SONG_PATH + "/" + id1, SongMetadataDto.class);
        var getResponse2 = restTemplate.getForEntity(SONG_PATH + "/" + id2, SongMetadataDto.class);

        assertThat(getResponse1.getStatusCode())
            .isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(getResponse2.getStatusCode())
            .isEqualTo(HttpStatus.NOT_FOUND);
    }
}
