--liquibase formatted sql

--changeset vr:020
INSERT INTO car_models(id, name, base_price, brand, body_id, engine_id, gearbox_id,
                       interior_id, steering_wheel_id, wheels_id, created_at, updated_at, removed)
VALUES
    ('a1b2c3d4-e5f6-4a1b-8c2d-3e4f5a6b7c8d', '530i', 1000, 'BMW',
     '7cc9d3b5-675c-4389-b242-885ea773f1e6',
     '72b35ac6-2bcd-44df-b9cf-cece1743d8eb',
     '9c8fd2a6-e297-4b73-8441-3e70c93bcd1a',
     '8613ac17-1c83-4882-af80-1555409d8399',
     '693b271c-c290-4dbf-a077-35f0f6e09a8d',
     '5b92cb3f-59da-4d41-bacb-0f1ab7f58eb8',
     NOW(), NOW(), false),
    ('b2c3d4e5-f6a7-4b2c-9d3e-4f5a6b7c8d9e', 'E21', 1000, 'BMW',
     '4b0a8a9e-fc65-47a5-913f-bda82e860c05',
     '23a1b29a-1cf6-4738-a0ee-a59194a4d788',
     'c537f069-5518-44c6-a3bf-fd05b9449049',
     'a6f51c5f-c5a1-498a-aa6a-eaf3f466d023',
     '10c81cbe-f83b-4440-80d3-18d6221f2f79',
     '61fadd97-9c08-45a4-9294-c5f655e2717a',
     NOW(), NOW(), false),
    ('c3d4e5f6-a7b8-4c3d-ae4f-5a6b7c8d9e0f', 'i5', 1000, 'BMW',
     '7cc9d3b5-675c-4389-b242-885ea773f1e6',
     '4154824f-b2cb-45b2-bd67-4d930da73303',
     '487e14a5-514a-4772-8018-f5a4a2c7a53d',
     'b53d2dce-6307-4c11-9b31-ce4521933f52',
     'ed34c714-7782-4921-81c0-94eec49f3b12',
     'da7cc08e-9a63-4941-ae5d-1ed90c76d374',
     NOW(), NOW(), false),
    ('d4e5f6a7-b8c9-4d4e-bf5a-6b7c8d9e0f1a', 'Cayenne Turbo GT', 1000, 'Porsche',
     '07f69521-0191-4bcc-80dd-a2d5662dcea9',
     '06156396-0291-4da2-affc-9cad5f8d43c5',
     '9c8fd2a6-e297-4b73-8441-3e70c93bcd1a',
     '8ed5d253-c7ea-4186-a6fa-22d5d10e5d3f',
     '2fb3bbeb-5c3a-44a4-bcff-b98caf37498f',
     'c165ece4-ad88-4990-af5b-2758b953630c',
     NOW(), NOW(), false),
    ('e5f6a7b8-c9d0-4e5f-ca6b-7c8d9e0f1a2b', 'Panamera GTS', 1000, 'Porsche',
     '07f69521-0191-4bcc-80dd-a2d5662dcea9',
     '06156396-0291-4da2-affc-9cad5f8d43c5',
     '487e14a5-514a-4772-8018-f5a4a2c7a53d',
     '8ed5d253-c7ea-4186-a6fa-22d5d10e5d3f',
     '10c81cbe-f83b-4440-80d3-18d6221f2f79',
     'da7cc08e-9a63-4941-ae5d-1ed90c76d374',
     NOW(), NOW(), false),
    ('f6a7b8c9-d0e1-4f6a-db7c-8d9e0f1a2b3c', '911', 1000, 'Porsche',
     '4b0a8a9e-fc65-47a5-913f-bda82e860c05',
     '06156396-0291-4da2-affc-9cad5f8d43c5',
     '10abc1ce-3b2d-440e-8eba-ca1e40c54882',
     '8ed5d253-c7ea-4186-a6fa-22d5d10e5d3f',
     'ed34c714-7782-4921-81c0-94eec49f3b12',
     '61fadd97-9c08-45a4-9294-c5f655e2717a',
     NOW(), NOW(), false);

INSERT INTO car_model_bodies(car_model_id, body_id)
SELECT cm.id, bd.id
FROM car_models cm, body_details bd
WHERE cm.name = '530i' AND cm.brand = 'BMW' AND bd.type = 'SEDAN'
   OR cm.name = 'i5' AND cm.brand = 'BMW' AND bd.type = 'SEDAN'
   OR cm.name = 'E21' AND cm.brand = 'BMW' AND bd.type = 'COUPE'
   OR cm.name = 'Cayenne Turbo GT' AND cm.brand = 'Porsche' AND bd.type = 'ESTATE'
   OR cm.name = 'Panamera GTS' AND cm.brand = 'Porsche' AND bd.type = 'ESTATE'
   OR cm.name = '911' AND cm.brand = 'Porsche' AND bd.type IN ('COUPE', 'CONVERTIBLE');

INSERT INTO car_model_engines(car_model_id, engine_id)
SELECT cm.id, ed.id
FROM car_models cm, engine_details ed
WHERE cm.name = '530i' AND cm.brand = 'BMW' AND ed.engine_power >= 200
   OR cm.name = 'i5' AND cm.brand = 'BMW' AND ed.engine_power >= 200
   OR cm.name = 'E21' AND cm.brand = 'BMW' AND ed.engine_power <= 200
   OR cm.name = 'Cayenne Turbo GT' AND cm.brand = 'Porsche' AND ed.engine_power >= 300
   OR cm.name = 'Panamera GTS' AND cm.brand = 'Porsche' AND ed.engine_power >= 300
   OR cm.name = '911' AND cm.brand = 'Porsche' AND ed.engine_power >= 300;

INSERT INTO car_model_gearboxes(car_model_id, gearbox_id)
SELECT cm.id, gd.id
FROM car_models cm, gearbox_details gd
WHERE cm.name = '530i' AND cm.brand = 'BMW' AND gd.type = 'AUTOMATIC'
   OR cm.name = 'i5' AND cm.brand = 'BMW' AND gd.type = 'AUTOMATIC'
   OR cm.name = 'E21' AND cm.brand = 'BMW' AND gd.type = 'MANUAL'
   OR cm.name = 'Cayenne Turbo GT' AND cm.brand = 'Porsche' AND gd.type = 'AUTOMATIC'
   OR cm.name = 'Panamera GTS' AND cm.brand = 'Porsche' AND gd.type = 'AUTOMATIC'
   OR cm.name = '911' AND cm.brand = 'Porsche' AND gd.type IN ('AUTOMATIC', 'MANUAL');

INSERT INTO car_model_interiors(car_model_id, interior_id)
SELECT cm.id, ind.id
FROM car_models cm, interior_details ind
WHERE cm.name IN ('530i', 'i5', 'E21') AND cm.brand = 'BMW' AND ind.color IN ('red', 'blue')
   OR cm.name IN ('Cayenne Turbo GT', 'Panamera GTS', '911') AND cm.brand = 'Porsche' AND ind.color IN ('green', 'yellow');

INSERT INTO car_model_steering_wheels(car_model_id, steering_wheel_id)
SELECT cm.id, swd.id
FROM car_models cm, steering_wheel_details swd
WHERE cm.name IN ('530i', 'i5', 'Cayenne Turbo GT', 'Panamera GTS', '911');

INSERT INTO car_model_wheels(car_model_id, wheels_id)
SELECT cm.id, wd.id
FROM car_models cm, wheels_details wd
WHERE cm.name IN ('530i', 'i5', 'Cayenne Turbo GT', 'Panamera GTS', '911');