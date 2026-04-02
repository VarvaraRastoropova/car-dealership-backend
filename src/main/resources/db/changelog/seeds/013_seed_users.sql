--liquibase formatted sql

--changeset vr:013
INSERT INTO users(id, name, role, created_at, updated_at, removed)
VALUES
    (gen_random_uuid(), 'Jody Kuhlman', 'CUSTOMER', NOW(), NOW(), false),
    (gen_random_uuid(), 'Trisha Collins', 'CAR_MANAGER', NOW(), NOW(), false),
    (gen_random_uuid(), 'Bertram Johnson', 'WAREHOUSE_ADMINISTRATOR', NOW(), NOW(), false),
    (gen_random_uuid(), 'Adolphus Maggio', 'SYSTEM_ADMINISTRATOR', NOW(), NOW(), false)