--liquibase formatted sql

--changeset vr:015
INSERT INTO engine_details(id, name, price, engine_power, engine_volume, created_at, updated_at, removed)
VALUES
    ('72b35ac6-2bcd-44df-b9cf-cece1743d8eb', 'name', 100, 200, 60, NOW(), NOW(), false),
    ('06156396-0291-4da2-affc-9cad5f8d43c5', 'name', 100, 300, 70, NOW(), NOW(), false),
    ('23a1b29a-1cf6-4738-a0ee-a59194a4d788', 'name', 100, 100, 80, NOW(), NOW(), false),
    ('4154824f-b2cb-45b2-bd67-4d930da73303', 'name', 100, 200, 90, NOW(), NOW(), false)