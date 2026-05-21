--liquibase formatted sql

--changeset vr:007
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
    wheels_id UUID,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    removed BOOLEAN,
    FOREIGN KEY (body_id) REFERENCES body_details (id),
    FOREIGN KEY (engine_id) REFERENCES engine_details (id),
    FOREIGN KEY (gearbox_id) REFERENCES gearbox_details (id),
    FOREIGN KEY (interior_id) REFERENCES interior_details (id),
    FOREIGN KEY (steering_wheel_id) REFERENCES steering_wheel_details (id),
    FOREIGN KEY (wheels_id) REFERENCES wheels_details (id)
);

CREATE TABLE car_model_bodies (
    car_model_id UUID,
    body_id UUID,
    FOREIGN KEY (car_model_id) REFERENCES car_models (id),
    FOREIGN KEY (body_id) REFERENCES body_details (id)
);

CREATE TABLE car_model_engines (
    car_model_id UUID,
    engine_id UUID,
    FOREIGN KEY (car_model_id) REFERENCES car_models (id),
    FOREIGN KEY (engine_id) REFERENCES engine_details (id)
);

CREATE TABLE car_model_gearboxes (
    car_model_id UUID,
    gearbox_id UUID,
    FOREIGN KEY (car_model_id) REFERENCES car_models (id),
    FOREIGN KEY (gearbox_id) REFERENCES gearbox_details (id)
);

CREATE TABLE car_model_interiors (
    car_model_id UUID,
    interior_id UUID,
    FOREIGN KEY (car_model_id) REFERENCES car_models (id),
    FOREIGN KEY (interior_id) REFERENCES interior_details (id)
);

CREATE TABLE car_model_steering_wheels (
    car_model_id UUID,
    steering_wheel_id UUID,
    FOREIGN KEY (car_model_id) REFERENCES car_models (id),
    FOREIGN KEY (steering_wheel_id) REFERENCES steering_wheel_details (id)
);

CREATE TABLE car_model_wheels (
    car_model_id UUID,
    wheels_id UUID,
    FOREIGN KEY (car_model_id) REFERENCES car_models (id),
    FOREIGN KEY (wheels_id) REFERENCES wheels_details (id)
)