--liquibase formatted sql

--changeset vr:018
INSERT INTO assembly_orders(id, source_order_id, type, car_model_id, car_id, required_components_ids, employee_id, status, created_at, updated_at, removed)
VALUES
    ('9a8b7c6d-5e4f-3a2b-1c0d-9e8f7a6b5c4d',
     'f47ac10b-58cc-4372-a567-0e02b2c3d479',
     'CAR_PURCHASE',
     'a1b2c3d4-e5f6-4a1b-8c2d-3e4f5a6b7c8d',
     '3c4d5e6f-7a8b-9c0d-1e2f-3a4b5c6d7e8f',
    NULL,
     'd0a75ba0-9cc8-40c7-8ac2-d5b4ab9fbd8e',
    'CREATED', NOW(), NOW(), false),
    ('d4e5f6a7-b8c9-0d1e-2f3a-4b5c6d7e8f9a',
     '8a5b2c3d-4e6f-4a7b-8c9d-0e1f2a3b4c5d',
     'CAR_WITH_CONFIGURATION',
     'b2c3d4e5-f6a7-4b2c-9d3e-4f5a6b7c8d9e',
     '3c4d5e6f-7a8b-9c0d-1e2f-3a4b5c6d7e8f',
     ARRAY[(SELECT id FROM body_details LIMIT 1), (SELECT id FROM engine_details LIMIT 1), (SELECT id FROM gearbox_details LIMIT 1)]::UUID[],
     'd0a75ba0-9cc8-40c7-8ac2-d5b4ab9fbd8e',
     'CREATED', NOW(), NOW(), false),
    ('123e4567-e89b-12d3-a456-426614174000',
     'd1e2f3a4-b5c6-47d8-9e0f-1a2b3c4d5e6f',
     'CAR_WITH_CONFIGURATION',
     'c3d4e5f6-a7b8-4c3d-ae4f-5a6b7c8d9e0f',
     '6a7b8c9d-0e1f-2a3b-4c5d-6e7f8a9b0c1d',
     ARRAY[(SELECT id FROM body_details LIMIT 1), (SELECT id FROM engine_details LIMIT 1), (SELECT id FROM gearbox_details LIMIT 1)]::UUID[],
     'd0a75ba0-9cc8-40c7-8ac2-d5b4ab9fbd8e',
     'ASSEMBLED', NOW(), NOW(), false);