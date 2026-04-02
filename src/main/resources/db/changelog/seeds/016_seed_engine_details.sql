--liquibase formatted sql

--changeset vr:016
INSERT INTO engine_details(id, name, price, engine_power, engine_volume, created_at, updated_at, removed)
SELECT
    gen_random_uuid(),
    anon.dummy_first_name() || ' ' || anon.dummy_last_name(),
    anon.random_int_between(1000, 5000),
    anon.random_int_between(100, 300),
    anon.random_int_between(50, 100),
    anon.random_date_between(NOW() - interval '30 days', NOW()),
    NOW(),
    random() < 0.5
FROM generate_series(1, 10 * ${SEED_COUNT});