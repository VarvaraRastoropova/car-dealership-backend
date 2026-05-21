--liquibase formatted sql

--changeset vr:007
INSERT INTO car_purchase_order(id, customer_id, employee_id, car_model_id, car_model_price, status, created_at, updated_at, removed)
VALUES
    ('f47ac10b-58cc-4372-a567-0e02b2c3d479',
     'ce6af829-e318-45a8-82f9-83aaebb830a8',
     'd0a75ba0-9cc8-40c7-8ac2-d5b4ab9fbd8e',
     'a1b2c3d4-e5f6-4a1b-8c2d-3e4f5a6b7c8d',
    1000, 'DRAFTED', NOW(), NOW(), false),
    ('7c9e6679-7425-40de-944b-e07fc1f90ae7',
     '0d863f3d-9aba-48a5-abe1-7269fe478e1f',
    'd0a75ba0-9cc8-40c7-8ac2-d5b4ab9fbd8e',
    'b2c3d4e5-f6a7-4b2c-9d3e-4f5a6b7c8d9e',
    1000, 'DRAFTED', NOW(), NOW(), false),
    ('3b3e4f1a-5b6c-4d7e-8f9a-0b1c2d3e4f5a',
     '31ef09b6-ec52-4d47-9805-8f7ea7fb8cb2',
    'd0a75ba0-9cc8-40c7-8ac2-d5b4ab9fbd8e',
    'c3d4e5f6-a7b8-4c3d-ae4f-5a6b7c8d9e0f',
    1000, 'DRAFTED', NOW(), NOW(), false);