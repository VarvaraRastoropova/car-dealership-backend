--liquibase formatted sql

--changeset vr:002
CREATE TABLE engine_details (
    id UUID PRIMARY KEY,
    name VARCHAR NOT NULL,
    price INT NOT NULL,
    engine_power INT NOT NULL,
    engine_volume INT NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    removed BOOLEAN
)