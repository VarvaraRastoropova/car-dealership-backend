--liquibase formatted sql

--changeset vr:008
CREATE TABLE car_models (
    id UUID PRIMARY KEY,
    name VARCHAR NOT NULL,
    base_price INT NOT NULL,
    brand VARCHAR NOT NULL,
    body_id UUID,
    engine_id UUID,
    gearbox_id UUID,
    interior_id UUID,
    steering_wheel_id UUID,
    wheel_id UUID,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    removed BOOLEAN
);

CREATE TABLE car_model_bodies (
    car_model_id UUID,
    body_id UUID
);

CREATE TABLE car_model_engines (
    car_model_id UUID,
    engine_id UUID
);

CREATE TABLE car_model_gearboxes (
    car_model_id UUID,
    gearbox_id UUID
);

CREATE TABLE car_model_interiors (
    car_model_id UUID,
    interior_id UUID
);

CREATE TABLE car_model_steering_wheels (
    car_model_id UUID,
    steering_wheel_id UUID
);

CREATE TABLE car_model_wheels (
    car_model_id UUID,
    wheel_id UUID
)