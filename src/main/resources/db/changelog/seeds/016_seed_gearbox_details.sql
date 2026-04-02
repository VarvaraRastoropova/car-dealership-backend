--liquibase formatted sql

--changeset vr:016
INSERT INTO gearbox_details(id, name, price, type, created_at, updated_at, removed)
VALUES
    (gen_random_uuid(),'name', 100, 'MANUAL', NOW(), NOW(), false),
    (gen_random_uuid(),'name', 100, 'MANUAL', NOW(), NOW(), false),
    (gen_random_uuid(),'name', 100, 'AUTOMATIC', NOW(), NOW(), false),
    (gen_random_uuid(),'name', 100, 'AUTOMATIC', NOW(), NOW(), false)