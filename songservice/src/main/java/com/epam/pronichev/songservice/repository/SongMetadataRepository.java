package com.epam.pronichev.songservice.repository;

import com.epam.pronichev.songservice.model.entities.SongMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongMetadataRepository extends JpaRepository<SongMetadata, Long> {

}