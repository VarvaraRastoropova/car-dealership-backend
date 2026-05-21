--liquibase formatted sql

--changeset vr:002
CREATE TABLE car_purchase_order (
    id UUID PRIMARY KEY,
    customer_id UUID NOT NULL,
    employee_id UUID NOT NULL,
    car_model_id UUID NOT NULL,
    car_model_price INT NOT NULL,
    status VARCHAR NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    removed BOOLEAN,
    FOREIGN KEY (customer_id) REFERENCES users (id),
    FOREIGN KEY (employee_id) REFERENCES users (id)
)