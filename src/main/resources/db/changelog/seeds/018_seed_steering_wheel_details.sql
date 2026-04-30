--liquibase formatted sql

--changeset vr:018
INSERT INTO steering_wheel_details(id, name, price, created_at, updated_at, removed)
VALUES
    ('693b271c-c290-4dbf-a077-35f0f6e09a8d', 'name', 100, NOW(), NOW(), false),
    ('10c81cbe-f83b-4440-80d3-18d6221f2f79', 'name', 100, NOW(), NOW(), false),
    ('ed34c714-7782-4921-81c0-94eec49f3b12', 'name', 100, NOW(), NOW(), false),
    ('2fb3bbeb-5c3a-44a4-bcff-b98caf37498f', 'name', 100, NOW(), NOW(), false)