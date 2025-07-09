DO
$do$
BEGIN
   IF NOT EXISTS (
      SELECT FROM pg_catalog.pg_roles
      WHERE  rolname = 'coffee_user') THEN
      
      CREATE USER coffee_user WITH ENCRYPTED PASSWORD 'coffee_password';
   END IF;
END
$do$;

-- Dar permisos al usuario
GRANT ALL PRIVILEGES ON DATABASE coffee_platform TO coffee_user;
ALTER USER coffee_user CREATEDB;
ALTER USER coffee_user SUPERUSER;

-- Crear esquemas básicos
CREATE SCHEMA IF NOT EXISTS coffee_schema;
GRANT ALL ON SCHEMA public TO coffee_user;
GRANT ALL ON SCHEMA coffee_schema TO coffee_user;

-- Verificar que todo está correcto
SELECT 'Database initialized successfully!' as status;