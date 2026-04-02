--liquibase formatted sql

--changeset vr:019
INSERT INTO steering_wheel_details(id, name, price, created_at, updated_at, removed)
SELECT
    gen_random_uuid(),
    anon.dummy_first_name() || ' ' || anon.dummy_last_name(),
    anon.random_int_between(1000, 5000),
    anon.random_date_between(NOW() - interval '30 days', NOW()),
    NOW(),
    random() < 0.5
FROM generate_series(1, 10 * ${SEED_COUNT});