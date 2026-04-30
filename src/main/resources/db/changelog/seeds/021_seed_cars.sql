--liquibase formatted sql

--changeset vr:021
INSERT INTO cars(id, car_model_id, body_id, engine_id, gearbox_id, interior_id, steering_wheel_id,
                 wheels_id, is_available_for_test_drive, created_at, updated_at, removed)
SELECT
    gen_random_uuid(),
    cm.id,
    cm.body_id,
    cm.engine_id,
    cm.gearbox_id,
    cm.interior_id,
    cm.steering_wheel_id,
    cm.wheels_id,
    true, NOW(), NOW(), false
FROM car_models cm
         CROSS JOIN generate_series(1, 2);