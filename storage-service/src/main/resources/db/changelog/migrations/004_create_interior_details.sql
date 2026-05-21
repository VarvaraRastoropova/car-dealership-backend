--liquibase formatted sql

--changeset vr:004
CREATE TABLE interior_details (
    id UUID PRIMARY KEY,
    name VARCHAR NOT NULL,
    price INT NOT NULL,
    color VARCHAR NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    removed BOOLEAN
)