--liquibase formatted sql

--changeset vr:005
CREATE TABLE steering_wheel_details (
    id UUID PRIMARY KEY,
    name VARCHAR NOT NULL,
    price INT NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    removed BOOLEAN
)