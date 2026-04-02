--liquibase formatted sql

--changeset vr:009
CREATE TABLE cars (
    id UUID PRIMARY KEY,
    car_model_id UUID NOT NULL,
    body_id UUID,
    engine_id UUID,
    gearbox_id UUID,
    interior_id UUID,
    steering_wheel_id UUID,
    wheel_id UUID,
    is_available_for_test_drive BOOLEAN NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    removed BOOLEAN
)