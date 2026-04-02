--liquibase formatted sql

--changeset vr:024
INSERT INTO test_drive_order(id, customer_id, car_model_id, date, time, status, created_at, updated_at, removed)
VALUES
    (gen_random_uuid(),
     (SELECT id FROM users LIMIT 1),
    (SELECT id FROM car_models LIMIT 1),
    CURRENT_DATE::text, NOW()::time::text, 'COMPLETED', NOW(), NOW(), false),
    (gen_random_uuid(),
     (SELECT id FROM users LIMIT 1),
    (SELECT id FROM car_models LIMIT 1),
    CURRENT_DATE::text, NOW()::time::text, 'READY', NOW(), NOW(), false),
    (gen_random_uuid(),
     (SELECT id FROM users LIMIT 1),
    (SELECT id FROM car_models LIMIT 1),
    CURRENT_DATE::text, NOW()::time::text, 'PAID', NOW(), NOW(), false);