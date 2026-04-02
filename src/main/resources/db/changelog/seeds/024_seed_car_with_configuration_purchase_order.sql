--liquibase formatted sql

--changeset vr:024
INSERT INTO car_with_configuration_purchase_order(id, customer_id, employee_id, car_model_id, car_model_price,
                                                  body_id, engine_id, gearbox_id, interior_id, steering_wheel_id,
                                                  wheel_id, status, created_at, updated_at, removed)
SELECT
    gen_random_uuid(),
    (SELECT id FROM users ORDER BY random() LIMIT 1),
    (SELECT id FROM users ORDER BY random() LIMIT 1),
    (SELECT id FROM car_models ORDER BY random() LIMIT 1),
    anon.random_int_between(1000, 5000),
    (SELECT id FROM body_details ORDER BY random() LIMIT 1),
    (SELECT id FROM engine_details ORDER BY random() LIMIT 1),
    (SELECT id FROM gearbox_details ORDER BY random() LIMIT 1),
    (SELECT id FROM interior_details ORDER BY random() LIMIT 1),
    (SELECT id FROM steering_wheel_details ORDER BY random() LIMIT 1),
    (SELECT id FROM wheels_details ORDER BY random() LIMIT 1),
    (ARRAY['COMPLETED', 'READY', 'WAITING_DELIVERY', 'PAID', 'CANCELLED', 'WAITING_PAYMENT', 'APPROVED', 'DRAFTED'])[floor(random()*8 + 1)],
    anon.random_date_between(NOW() - interval '30 days', NOW()),
    NOW(),
    random() < 0.5
FROM generate_series(1, 10 * ${SEED_COUNT});