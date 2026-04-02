--liquibase formatted sql

--changeset vr:017
INSERT INTO interior_details(id, name, price, color, created_at, updated_at, removed)
VALUES
    (gen_random_uuid(),'name', 100, 'red', NOW(), NOW(), false),
    (gen_random_uuid(),'name', 100, 'green', NOW(), NOW(), false),
    (gen_random_uuid(),'name', 100, 'yellow', NOW(), NOW(), false),
    (gen_random_uuid(),'name', 100, 'blue', NOW(), NOW(), false)