package com.epam.pronichev.resourceservice.mapping;

import com.epam.pronichev.resourceservice.config.ResourseServiceMapperConfig;
import com.epam.pronichev.resourceservice.model.entities.Resource;
import com.epam.pronichev.resourceservice.model.external.SongMetadataDto;
import org.apache.tika.metadata.Metadata;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = ResourseServiceMapperConfig.class)
public interface SongMetadataMapper {

    @Mapping(target = "resourceId", source = "resource.id")
    @Mapping(target = "name", source = "metadata", qualifiedByName = "name")
    @Mapping(target = "artist", source = "metadata", qualifiedByName = "artist")
    @Mapping(target = "album", source = "metadata", qualifiedByName = "album")
    @Mapping(target = "length", source = "metadata", qualifiedByName = "length")
    @Mapping(target = "year", source = "metadata", qualifiedByName = "year")
    SongMetadataDto toDto(Resource resource, Metadata metadata);

    @Named("name")
    default String getName(Metadata metadata) {
        return metadata.get("dc:title");
    }

    @Named("artist")
    default String getArtist(Metadata metadata) {
        return metadata.get("xmpDM:artist");
    }

    @Named("album")
    default String getAlbum(Metadata metadata) {
        return metadata.get("xmpDM:album");
    }

    @Named("length")
    default String getDuration(Metadata metadata) {
        var duration = metadata.get("xmpDM:duration");

        double durationInSeconds = Double.parseDouble(duration);
        long minutes = (long) (durationInSeconds / 60);
        long seconds = (long) (durationInSeconds % 60);

        return String.format("%02d:%02d", minutes, seconds);
    }

    @Named("year")
    default Short getYear(Metadata metadata) {
        var year = metadata.get("xmpDM:releaseDate");
        if (year == null) {
            return null;
        }
        return Short.valueOf(year);
    }
}
