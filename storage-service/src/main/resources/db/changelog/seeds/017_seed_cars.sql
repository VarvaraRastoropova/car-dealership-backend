--liquibase formatted sql

--changeset vr:017
INSERT INTO cars(id, car_model_id, body_id, engine_id, gearbox_id, interior_id, steering_wheel_id,
                 wheels_id, is_available_for_test_drive, created_at, updated_at, removed)
SELECT
    '3c4d5e6f-7a8b-9c0d-1e2f-3a4b5c6d7e8f'::uuid,
    cm.id,
    cm.body_id,
    cm.engine_id,
    cm.gearbox_id,
    cm.interior_id,
    cm.steering_wheel_id,
    cm.wheels_id,
    true, NOW(), NOW(), false
FROM car_models cm
WHERE cm.id = 'a1b2c3d4-e5f6-4a1b-8c2d-3e4f5a6b7c8d'
UNION ALL
SELECT
    '6a7b8c9d-0e1f-2a3b-4c5d-6e7f8a9b0c1d'::uuid,
    cm.id,
    cm.body_id,
    cm.engine_id,
    cm.gearbox_id,
    cm.interior_id,
    cm.steering_wheel_id,
    cm.wheels_id,
    true, NOW(), NOW(), false
FROM car_models cm
WHERE cm.id = 'b2c3d4e5-f6a7-4b2c-9d3e-4f5a6b7c8d9e'