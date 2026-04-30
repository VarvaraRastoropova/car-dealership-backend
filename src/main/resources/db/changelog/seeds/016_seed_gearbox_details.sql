--liquibase formatted sql

--changeset vr:016
INSERT INTO gearbox_details(id, name, price, type, created_at, updated_at, removed)
VALUES
    ('c537f069-5518-44c6-a3bf-fd05b9449049','name', 100, 'MANUAL', NOW(), NOW(), false),
    ('10abc1ce-3b2d-440e-8eba-ca1e40c54882','name', 100, 'MANUAL', NOW(), NOW(), false),
    ('9c8fd2a6-e297-4b73-8441-3e70c93bcd1a','name', 100, 'AUTOMATIC', NOW(), NOW(), false),
    ('487e14a5-514a-4772-8018-f5a4a2c7a53d','name', 100, 'AUTOMATIC', NOW(), NOW(), false)