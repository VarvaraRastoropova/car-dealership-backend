--liquibase formatted sql

--changeset vr:022
INSERT INTO cars(id, car_model_id, body_id, engine_id, gearbox_id, interior_id, steering_wheel_id,
                 wheel_id, is_available_for_test_drive, created_at, updated_at, removed)
SELECT
    gen_random_uuid(),
    (SELECT id FROM car_models ORDER BY random() LIMIT 1),
    (SELECT id FROM body_details ORDER BY random() LIMIT 1),
    (SELECT id FROM engine_details ORDER BY random() LIMIT 1),
    (SELECT id FROM gearbox_details ORDER BY random() LIMIT 1),
    (SELECT id FROM interior_details ORDER BY random() LIMIT 1),
    (SELECT id FROM steering_wheel_details ORDER BY random() LIMIT 1),
    (SELECT id FROM wheels_details ORDER BY random() LIMIT 1),
    random() < 0.5,
    anon.random_date_between(NOW() - interval '30 days', NOW()),
    NOW(),
    random() < 0.5
FROM generate_series(1, 10 * ${SEED_COUNT});