--liquibase formatted sql

--changeset vr:008
CREATE TABLE cars (
    id UUID PRIMARY KEY,
    car_model_id UUID NOT NULL,
    body_id UUID,
    engine_id UUID,
    gearbox_id UUID,
    interior_id UUID,
    steering_wheel_id UUID,
    wheels_id UUID,
    is_available_for_test_drive BOOLEAN NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    removed BOOLEAN,
    FOREIGN KEY (car_model_id) REFERENCES car_models (id),
    FOREIGN KEY (body_id) REFERENCES body_details (id),
    FOREIGN KEY (engine_id) REFERENCES engine_details (id),
    FOREIGN KEY (gearbox_id) REFERENCES gearbox_details (id),
    FOREIGN KEY (interior_id) REFERENCES interior_details (id),
    FOREIGN KEY (steering_wheel_id) REFERENCES steering_wheel_details (id),
    FOREIGN KEY (wheels_id) REFERENCES wheels_details (id)
)