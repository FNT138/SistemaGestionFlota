CREATE ROLE dev_user WITH LOGIN PASSWORD 'dev_pass';
CREATE DATABASE flota_dev OWNER dev_user;
\c flota_dev
-- (Opcional) Crear extensiones:
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
