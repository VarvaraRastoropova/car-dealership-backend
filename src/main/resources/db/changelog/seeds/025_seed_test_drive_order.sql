--liquibase formatted sql

--changeset vr:025
INSERT INTO test_drive_order(id, customer_id, car_model_id, date, time, status, created_at, updated_at, removed)
SELECT
    gen_random_uuid(),
    (SELECT id FROM users ORDER BY random() LIMIT 1),
    (SELECT id FROM car_models ORDER BY random() LIMIT 1),
    CURRENT_DATE::text,
    NOW()::time::text,
    (ARRAY['COMPLETED', 'READY', 'PAID', 'CANCELLED', 'WAITING_PAYMENT', 'APPROVED', 'DRAFTED'])[floor(random()*7 + 1)],
    anon.random_date_between(NOW() - interval '30 days', NOW()),
    NOW(),
    random() < 0.5
FROM generate_series(1, 10 * ${SEED_COUNT});