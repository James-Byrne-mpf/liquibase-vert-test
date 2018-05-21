-- liquibase formatted sql
-- Add new status column to selected XPLATFORM tables (and _AUD tables)

-- changeset SOD:Test-02

ALTER TABLE xanadu_native.sod
DROP COLUMN name
;
