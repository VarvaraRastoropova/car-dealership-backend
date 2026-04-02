--liquibase formatted sql

--changeset vr:021
INSERT INTO car_models(id, name, base_price, brand, body_id, engine_id, gearbox_id,
                       interior_id, steering_wheel_id, wheel_id, created_at, updated_at, removed)
SELECT
    gen_random_uuid(),
    anon.dummy_first_name() || ' ' || anon.dummy_last_name(),
    anon.random_int_between(1000, 5000),
    anon.dummy_last_name(),
    (SELECT id FROM body_details ORDER BY random() LIMIT 1),
    (SELECT id FROM engine_details ORDER BY random() LIMIT 1),
    (SELECT id FROM gearbox_details ORDER BY random() LIMIT 1),
    (SELECT id FROM interior_details ORDER BY random() LIMIT 1),
    (SELECT id FROM steering_wheel_details ORDER BY random() LIMIT 1),
    (SELECT id FROM wheels_details ORDER BY random() LIMIT 1),
    anon.random_date_between(NOW() - interval '30 days', NOW()),
    NOW(),
    random() < 0.5
FROM generate_series(1, 10 * ${SEED_COUNT});

INSERT INTO car_model_bodies(car_model_id, body_id)
SELECT cm.id,bd.id
FROM car_models cm
JOIN body_details bd ON random() < 0.3;

INSERT INTO car_model_engines(car_model_id, engine_id)
SELECT cm.id,ed.id
FROM car_models cm
JOIN engine_details ed ON random() < 0.3;

INSERT INTO car_model_gearboxes(car_model_id, gearbox_id)
SELECT cm.id,gd.id
FROM car_models cm
JOIN gearbox_details gd ON random() < 0.3;

INSERT INTO car_model_interiors(car_model_id, interior_id)
SELECT cm.id,ind.id
FROM car_models cm
JOIN interior_details ind ON random() < 0.3;

INSERT INTO car_model_steering_wheels(car_model_id, steering_wheel_id)
SELECT cm.id,swd.id
FROM car_models cm
JOIN steering_wheel_details swd ON random() < 0.3;

INSERT INTO car_model_wheels(car_model_id, wheel_id)
SELECT cm.id,wd.id
FROM car_models cm
JOIN wheels_details wd ON random() < 0.3;