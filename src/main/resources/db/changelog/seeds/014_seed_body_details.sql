--liquibase formatted sql

--changeset vr:014
INSERT INTO body_details(id, name, price, color, type, created_at, updated_at, removed)
VALUES
    (gen_random_uuid(), 'name', 100, 'red', 'SEDAN', NOW(), NOW(), false),
    (gen_random_uuid(), 'name', 100, 'blue', 'ESTATE', NOW(), NOW(), false),
    (gen_random_uuid(), 'name', 100, 'green', 'COUPE', NOW(), NOW(), false),
    (gen_random_uuid(), 'name', 100, 'yellow', 'CONVERTIBLE', NOW(), NOW(), false)