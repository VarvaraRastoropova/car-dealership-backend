--liquibase formatted sql

--changeset vr:011
CREATE TABLE car_with_configuration_purchase_order (
    id UUID PRIMARY KEY,
    customer_id UUID NOT NULL,
    employee_id UUID NOT NULL,
    car_model_id UUID NOT NULL,
    car_model_price INT NOT NULL,
    body_id UUID,
    engine_id UUID,
    gearbox_id UUID,
    interior_id UUID,
    steering_wheel_id UUID,
    wheel_id UUID,
    status VARCHAR NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    removed BOOLEAN
)