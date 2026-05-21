--liquibase formatted sql

--changeset vr:015
INSERT INTO wheels_details(id, name, price, created_at, updated_at, removed)
VALUES
    ('5b92cb3f-59da-4d41-bacb-0f1ab7f58eb8','name', 100, NOW(), NOW(), false),
    ('61fadd97-9c08-45a4-9294-c5f655e2717a','name', 100, NOW(), NOW(), false),
    ('da7cc08e-9a63-4941-ae5d-1ed90c76d374','name', 100, NOW(), NOW(), false),
    ('c165ece4-ad88-4990-af5b-2758b953630c','name', 100, NOW(), NOW(), false)