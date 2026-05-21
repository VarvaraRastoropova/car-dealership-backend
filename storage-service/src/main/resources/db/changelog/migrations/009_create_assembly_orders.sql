--liquibase formatted sql

--changeset vr:009
CREATE TABLE assembly_orders (
    id UUID PRIMARY KEY,
    source_order_id UUID NOT NULL,
    type VARCHAR NOT NULL,
    car_model_id UUID NOT NULL,
    car_id UUID NOT NULL,
    required_components_ids UUID[],
    employee_id UUID NOT NULL,
    status VARCHAR NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    removed BOOLEAN NOT NULL DEFAULT FALSE
)