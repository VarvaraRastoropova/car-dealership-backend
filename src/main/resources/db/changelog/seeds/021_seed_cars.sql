--liquibase formatted sql

--changeset vr:021
INSERT INTO cars(id, car_model_id, body_id, engine_id, gearbox_id, interior_id, steering_wheel_id,
                 wheels_id, is_available_for_test_drive, created_at, updated_at, removed)
VALUES
    (gen_random_uuid(),
     (SELECT id FROM car_models LIMIT 1),
     (SELECT id FROM body_details LIMIT 1),
    (SELECT id FROM engine_details LIMIT 1),
    (SELECT id FROM gearbox_details LIMIT 1),
    (SELECT id FROM interior_details LIMIT 1),
    (SELECT id FROM steering_wheel_details LIMIT 1),
    (SELECT id FROM wheels_details LIMIT 1),
    true, NOW(), NOW(), false),
    (gen_random_uuid(),
     (SELECT id FROM car_models LIMIT 1),
     (SELECT id FROM body_details LIMIT 1),
    (SELECT id FROM engine_details LIMIT 1),
    (SELECT id FROM gearbox_details LIMIT 1),
    (SELECT id FROM interior_details LIMIT 1),
    (SELECT id FROM steering_wheel_details LIMIT 1),
    (SELECT id FROM wheels_details LIMIT 1),
    true, NOW(), NOW(), false),
    (gen_random_uuid(),
     (SELECT id FROM car_models LIMIT 1),
     (SELECT id FROM body_details LIMIT 1),
    (SELECT id FROM engine_details LIMIT 1),
    (SELECT id FROM gearbox_details LIMIT 1),
    (SELECT id FROM interior_details LIMIT 1),
    (SELECT id FROM steering_wheel_details LIMIT 1),
    (SELECT id FROM wheels_details LIMIT 1),
    true, NOW(), NOW(), false);