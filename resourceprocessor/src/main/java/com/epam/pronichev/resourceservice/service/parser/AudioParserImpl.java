package com.epam.pronichev.resourceservice.service.parser;

import com.epam.pronichev.resourceservice.exceptions.AudioParsingException;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
public class AudioParserImpl implements AudioParser {

    @Override
    public Metadata getMetadata(byte[] data) {
        var metadata = new Metadata();
        var handler = new BodyContentHandler();
        var parser = new AutoDetectParser();

        try (var input = new ByteArrayInputStream(data)) {
            parser.parse(input, handler, metadata, new ParseContext());
        } catch (IOException | SAXException | TikaException e) {
            throw new AudioParsingException("Failed to extract metadata: " + e.getMessage());
        }
        return metadata;
    }
}
