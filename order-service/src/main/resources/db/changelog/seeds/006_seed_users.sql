--liquibase formatted sql

--changeset vr:006
INSERT INTO users(id, name, role, created_at, updated_at, removed)
VALUES
    ('ce6af829-e318-45a8-82f9-83aaebb830a8', 'Jody Kuhlman', 'CUSTOMER', NOW(), NOW(), false),
    ('0d863f3d-9aba-48a5-abe1-7269fe478e1f', 'Trisha Collins', 'CAR_MANAGER', NOW(), NOW(), false),
    ('31ef09b6-ec52-4d47-9805-8f7ea7fb8cb2', 'Bertram Johnson', 'WAREHOUSE_ADMINISTRATOR', NOW(), NOW(), false),
    ('d0a75ba0-9cc8-40c7-8ac2-d5b4ab9fbd8e', 'Adolphus Maggio', 'SYSTEM_ADMINISTRATOR', NOW(), NOW(), false)