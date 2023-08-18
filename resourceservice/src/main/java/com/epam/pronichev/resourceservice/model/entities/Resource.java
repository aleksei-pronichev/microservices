package com.epam.pronichev.resourceservice.model.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(name = "audio_files")
public class Resource {

    @Id
    @Column("id")
    private Long id;

    @Column("data")
    private byte[] data;
}
