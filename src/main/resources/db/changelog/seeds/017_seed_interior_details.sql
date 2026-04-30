--liquibase formatted sql

--changeset vr:017
INSERT INTO interior_details(id, name, price, color, created_at, updated_at, removed)
VALUES
    ('8613ac17-1c83-4882-af80-1555409d8399','name', 100, 'red', NOW(), NOW(), false),
    ('b53d2dce-6307-4c11-9b31-ce4521933f52','name', 100, 'green', NOW(), NOW(), false),
    ('8ed5d253-c7ea-4186-a6fa-22d5d10e5d3f','name', 100, 'yellow', NOW(), NOW(), false),
    ('a6f51c5f-c5a1-498a-aa6a-eaf3f466d023','name', 100, 'blue', NOW(), NOW(), false)