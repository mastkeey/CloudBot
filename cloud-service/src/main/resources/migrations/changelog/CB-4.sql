--liquibase formatted sql

--changeset fetyukhin:CB-4

ALTER table file drop column checksum;
ALTER table file drop column relative_path;
ALTER table file add column file_extension VARCHAR(255);
ALTER table tg_user add column bucket_name VARCHAR(255);
ALTER table tg_user add column current_workspace_name VARCHAR(255);
