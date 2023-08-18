package com.epam.pronichev.resourceservice.service.parser;

import org.apache.tika.metadata.Metadata;

public interface AudioParser {

    Metadata getMetadata(byte[] data);

}
