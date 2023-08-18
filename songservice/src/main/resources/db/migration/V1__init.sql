CREATE TABLE metadata
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(255),
    artist     VARCHAR(255),
    album      VARCHAR(255),
    length     VARCHAR(10),
    resource_id VARCHAR(255),
    year       SMALLINT
);