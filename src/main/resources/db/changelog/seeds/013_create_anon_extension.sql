--liquibase formatted sql

--changeset vr:013
CREATE EXTENSION IF NOT EXISTS anon;
SELECT anon.init();