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
    wheels_id UUID,
    status VARCHAR NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    removed BOOLEAN,
    FOREIGN KEY (customer_id) REFERENCES users (id),
    FOREIGN KEY (employee_id) REFERENCES users (id),
    FOREIGN KEY (car_model_id) REFERENCES car_models (id),
    FOREIGN KEY (body_id) REFERENCES body_details (id),
    FOREIGN KEY (engine_id) REFERENCES engine_details (id),
    FOREIGN KEY (gearbox_id) REFERENCES gearbox_details (id),
    FOREIGN KEY (interior_id) REFERENCES interior_details (id),
    FOREIGN KEY (steering_wheel_id) REFERENCES steering_wheel_details (id),
    FOREIGN KEY (wheels_id) REFERENCES wheels_details (id)
)