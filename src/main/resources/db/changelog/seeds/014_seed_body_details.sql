--liquibase formatted sql

--changeset vr:014
INSERT INTO body_details(id, name, price, color, type, created_at, updated_at, removed)
VALUES
    ('7cc9d3b5-675c-4389-b242-885ea773f1e6', 'name', 100, 'red', 'SEDAN', NOW(), NOW(), false),
    ('07f69521-0191-4bcc-80dd-a2d5662dcea9', 'name', 100, 'blue', 'ESTATE', NOW(), NOW(), false),
    ('4b0a8a9e-fc65-47a5-913f-bda82e860c05', 'name', 100, 'green', 'COUPE', NOW(), NOW(), false),
    ('ccf44567-f163-4d75-a66c-a136d622cc52', 'name', 100, 'yellow', 'CONVERTIBLE', NOW(), NOW(), false)