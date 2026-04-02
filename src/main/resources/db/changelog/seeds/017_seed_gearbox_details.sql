--liquibase formatted sql

--changeset vr:017
INSERT INTO gearbox_details(id, name, price, type, created_at, updated_at, removed)
SELECT
    gen_random_uuid(),
    anon.dummy_first_name() || ' ' || anon.dummy_last_name(),
    anon.random_int_between(1000, 5000),
    (ARRAY['MANUAL','AUTOMATIC'])[floor(random()*2 + 1)],
    anon.random_date_between(NOW() - interval '30 days', NOW()),
    NOW(),
    random() < 0.5
FROM generate_series(1, 10 * ${SEED_COUNT});