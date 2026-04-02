--liquibase formatted sql

--changeset vr:018
INSERT INTO steering_wheel_details(id, name, price, created_at, updated_at, removed)
VALUES
    (gen_random_uuid(), 'name', 100, NOW(), NOW(), false),
    (gen_random_uuid(), 'name', 100, NOW(), NOW(), false),
    (gen_random_uuid(), 'name', 100, NOW(), NOW(), false),
    (gen_random_uuid(), 'name', 100, NOW(), NOW(), false)