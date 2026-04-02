--liquibase formatted sql

--changeset vr:012
CREATE TABLE test_drive_order (
    id UUID PRIMARY KEY,
    customer_id UUID NOT NULL,
    car_model_id UUID NOT NULL,
    date VARCHAR NOT NULL,
    time VARCHAR NOT NULL,
    status VARCHAR NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    removed BOOLEAN
)