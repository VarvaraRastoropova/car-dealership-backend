--liquibase formatted sql

--changeset vr:015
INSERT INTO engine_details(id, name, price, engine_power, engine_volume, created_at, updated_at, removed)
VALUES
    (gen_random_uuid(), 'name', 100, 200, 60, NOW(), NOW(), false),
    (gen_random_uuid(), 'name', 100, 300, 70, NOW(), NOW(), false),
    (gen_random_uuid(), 'name', 100, 100, 80, NOW(), NOW(), false),
    (gen_random_uuid(), 'name', 100, 200, 90, NOW(), NOW(), false)