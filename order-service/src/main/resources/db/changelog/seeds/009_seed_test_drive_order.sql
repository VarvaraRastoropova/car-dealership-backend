--liquibase formatted sql

--changeset vr:009
INSERT INTO test_drive_order(id, customer_id, car_model_id, date, time, status, created_at, updated_at, removed)
VALUES
    ('f47ac10b-58cc-4372-a567-0e02b2c3d479',
     'ce6af829-e318-45a8-82f9-83aaebb830a8',
     'a1b2c3d4-e5f6-4a1b-8c2d-3e4f5a6b7c8d',
    CURRENT_DATE::text, NOW()::time::text, 'COMPLETED', NOW(), NOW(), false),
    ('6ba7b810-9dad-11d1-80b4-00c04fd430c8',
     '0d863f3d-9aba-48a5-abe1-7269fe478e1f',
     'b2c3d4e5-f6a7-4b2c-9d3e-4f5a6b7c8d9e',
    CURRENT_DATE::text, NOW()::time::text, 'READY', NOW(), NOW(), false),
    ('3e1c2f3a-4b5d-6e7f-8a9b-0c1d2e3f4a5b',
     '31ef09b6-ec52-4d47-9805-8f7ea7fb8cb2',
     'c3d4e5f6-a7b8-4c3d-ae4f-5a6b7c8d9e0f',
    CURRENT_DATE::text, NOW()::time::text, 'PAID', NOW(), NOW(), false);