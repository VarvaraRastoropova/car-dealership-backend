--liquibase formatted sql

--changeset vr:022
INSERT INTO car_purchase_order(id, customer_id, employee_id, car_model_id, car_model_price, status, created_at, updated_at, removed)
VALUES
    (gen_random_uuid(),
     (SELECT id FROM users LIMIT 1),
    (SELECT id FROM users LIMIT 1),
    (SELECT id FROM car_models LIMIT 1),
    1000, 'DRAFTED', NOW(), NOW(), false),
    (gen_random_uuid(),
     (SELECT id FROM users LIMIT 1),
    (SELECT id FROM users LIMIT 1),
    (SELECT id FROM car_models LIMIT 1),
    1000, 'DRAFTED', NOW(), NOW(), false),
    (gen_random_uuid(),
     (SELECT id FROM users LIMIT 1),
    (SELECT id FROM users LIMIT 1),
    (SELECT id FROM car_models LIMIT 1),
    1000, 'DRAFTED', NOW(), NOW(), false);