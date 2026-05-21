--liquibase formatted sql

--changeset vr:006
CREATE TABLE wheels_details (
    id UUID PRIMARY KEY,
    name VARCHAR NOT NULL,
    price INT NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    removed BOOLEAN
)