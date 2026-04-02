--liquibase formatted sql

--changeset vr:015
INSERT INTO body_details(id, name, price, color, type, created_at, updated_at, removed)
SELECT
    gen_random_uuid(),
    anon.dummy_first_name() || ' ' || anon.dummy_last_name(),
    anon.random_int_between(1000, 5000),
    anon.dummy_color(),
    (ARRAY['SEDAN','ESTATE','COUPE','CONVERTIBLE'])[floor(random()*4 + 1)],
    anon.random_date_between(NOW() - interval '30 days', NOW()),
    NOW(),
    random() < 0.5
FROM generate_series(1, 10 * ${SEED_COUNT});