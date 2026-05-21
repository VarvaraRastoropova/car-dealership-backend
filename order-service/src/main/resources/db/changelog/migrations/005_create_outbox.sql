--liquibase formatted sql

--changeset vr:005
CREATE TABLE outbox (
    id UUID PRIMARY KEY,
    source_order_id UUID NOT NULL,
    event_type VARCHAR NOT NULL,
    payload JSONB NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    processed BOOLEAN NOT NULL DEFAULT FALSE
);