--liquibase formatted sql

--changeset vr:014
INSERT INTO users(id, name, role, created_at, updated_at, removed)
SELECT
    gen_random_uuid(),
    anon.dummy_first_name() || ' ' || anon.dummy_last_name(),
    (ARRAY['CUSTOMER','CAR_MANAGER','WAREHOUSE_ADMINISTRATOR','SYSTEM_ADMINISTRATOR'])[floor(random()*4 + 1)],
    anon.random_date_between(NOW() - interval '30 days', NOW()),
    NOW(),
    random() < 0.5
FROM generate_series(1, 10 * ${SEED_COUNT});