--liquibase formatted sql

--changeset vr:003
CREATE TABLE gearbox_details (
    id UUID PRIMARY KEY,
    name VARCHAR NOT NULL,
    price INT NOT NULL,
    type VARCHAR NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    removed BOOLEAN
)