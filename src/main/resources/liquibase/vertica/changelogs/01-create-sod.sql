-- liquibase formatted sql
-- Add new status column to selected XPLATFORM tables (and _AUD tables)

-- changeset SOD:Test-01

CREATE TABLE xanadu_native.sod
( id INT NOT NULL, name VARCHAR(100) NOT NULL )
;
