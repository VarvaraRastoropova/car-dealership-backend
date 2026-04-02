--liquibase formatted sql

--changeset vr:002
CREATE TABLE body_details (
    id UUID PRIMARY KEY,
    name VARCHAR NOT NULL,
    price INT NOT NULL,
    color VARCHAR NOT NULL,
    type VARCHAR NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    removed BOOLEAN
)