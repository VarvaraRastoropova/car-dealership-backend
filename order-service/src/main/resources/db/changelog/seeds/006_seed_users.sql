--liquibase formatted sql

--changeset vr:006
INSERT INTO users(id, name, role, created_at, updated_at, removed)
VALUES
    ('80920667-85ca-44a9-b65a-d81903edabbd', 'Jody Kuhlman', 'CUSTOMER', NOW(), NOW(), false),
    ('1a6076aa-4c4e-4aa1-b611-c4df5178f289', 'Trisha Collins', 'CAR_MANAGER', NOW(), NOW(), false),
    ('4a8b9907-9be2-40a7-98fb-a58866faa19d', 'Bertram Johnson', 'WAREHOUSE_ADMINISTRATOR', NOW(), NOW(), false),
    ('f401616b-cc02-4e80-bfae-18788cd990dc', 'Adolphus Maggio', 'SYSTEM_ADMINISTRATOR', NOW(), NOW(), false)