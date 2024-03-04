-- db/migration/V3__add_status_column_to_configuration.sql

-- Adiciona a coluna 'status' à tabela 'configuration'
ALTER TABLE configuration
ADD COLUMN status VARCHAR(50);
