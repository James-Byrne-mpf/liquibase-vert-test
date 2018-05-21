-- liquibase formatted sql
-- Add new status column to selected XPLATFORM tables (and _AUD tables)

-- changeset SOD:Test-03

ALTER TABLE xanadu_native.sod
ADD COLUMN name200 VARCHAR(200) NOT NULL
;
