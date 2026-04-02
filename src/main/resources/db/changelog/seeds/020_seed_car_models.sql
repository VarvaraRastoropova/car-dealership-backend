--liquibase formatted sql

--changeset vr:020
INSERT INTO car_models(id, name, base_price, brand, body_id, engine_id, gearbox_id,
                       interior_id, steering_wheel_id, wheels_id, created_at, updated_at, removed)
VALUES
    (gen_random_uuid(), '530i', 1000, 'BMW',
     (SELECT id FROM body_details LIMIT 1),
    (SELECT id FROM engine_details LIMIT 1),
    (SELECT id FROM gearbox_details LIMIT 1),
    (SELECT id FROM interior_details LIMIT 1),
    (SELECT id FROM steering_wheel_details LIMIT 1),
    (SELECT id FROM wheels_details LIMIT 1),
    NOW(), NOW(), false),
    (gen_random_uuid(), 'E21', 1000, 'BMW',
     (SELECT id FROM body_details LIMIT 1),
    (SELECT id FROM engine_details LIMIT 1),
    (SELECT id FROM gearbox_details LIMIT 1),
    (SELECT id FROM interior_details LIMIT 1),
    (SELECT id FROM steering_wheel_details LIMIT 1),
    (SELECT id FROM wheels_details LIMIT 1),
    NOW(), NOW(), false),
    (gen_random_uuid(), 'i5', 1000, 'BMW',
     (SELECT id FROM body_details LIMIT 1),
    (SELECT id FROM engine_details LIMIT 1),
    (SELECT id FROM gearbox_details LIMIT 1),
    (SELECT id FROM interior_details LIMIT 1),
    (SELECT id FROM steering_wheel_details LIMIT 1),
    (SELECT id FROM wheels_details LIMIT 1),
    NOW(), NOW(), false),
(gen_random_uuid(), 'Cayenne Turbo GT', 1000, 'Porsche',
     (SELECT id FROM body_details LIMIT 1),
    (SELECT id FROM engine_details LIMIT 1),
    (SELECT id FROM gearbox_details LIMIT 1),
    (SELECT id FROM interior_details LIMIT 1),
    (SELECT id FROM steering_wheel_details LIMIT 1),
    (SELECT id FROM wheels_details LIMIT 1),
    NOW(), NOW(), false),
    (gen_random_uuid(), 'Panamera GTS', 1000, 'Porsche',
     (SELECT id FROM body_details LIMIT 1),
    (SELECT id FROM engine_details LIMIT 1),
    (SELECT id FROM gearbox_details LIMIT 1),
    (SELECT id FROM interior_details LIMIT 1),
    (SELECT id FROM steering_wheel_details LIMIT 1),
    (SELECT id FROM wheels_details LIMIT 1),
    NOW(), NOW(), false),
    (gen_random_uuid(), '911', 1000, 'Porsche',
     (SELECT id FROM body_details LIMIT 1),
    (SELECT id FROM engine_details LIMIT 1),
    (SELECT id FROM gearbox_details LIMIT 1),
    (SELECT id FROM interior_details LIMIT 1),
    (SELECT id FROM steering_wheel_details LIMIT 1),
    (SELECT id FROM wheels_details LIMIT 1),
    NOW(), NOW(), false);

INSERT INTO car_model_bodies(car_model_id, body_id)
SELECT cm.id,bd.id
FROM car_models cm
JOIN body_details bd ON TRUE
LIMIT 5;

INSERT INTO car_model_engines(car_model_id, engine_id)
SELECT cm.id,ed.id
FROM car_models cm
JOIN engine_details ed ON TRUE
LIMIT 5;

INSERT INTO car_model_gearboxes(car_model_id, gearbox_id)
SELECT cm.id,gd.id
FROM car_models cm
JOIN gearbox_details gd ON TRUE
LIMIT 5;

INSERT INTO car_model_interiors(car_model_id, interior_id)
SELECT cm.id,ind.id
FROM car_models cm
JOIN interior_details ind ON TRUE
LIMIT 5;

INSERT INTO car_model_steering_wheels(car_model_id, steering_wheel_id)
SELECT cm.id,swd.id
FROM car_models cm
JOIN steering_wheel_details swd ON TRUE
LIMIT 5;

INSERT INTO car_model_wheels(car_model_id, wheels_id)
SELECT cm.id,wd.id
FROM car_models cm
JOIN wheels_details wd ON TRUE
LIMIT 5;