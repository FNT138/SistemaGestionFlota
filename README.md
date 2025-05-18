# SistemaGestionFlota

### Configuracion local de PostgreSQL
1. Instalar PostgreSQL (ver seccion [Instalacion](#instalacion))
2. Ejecutar script de inicializacion:

```bash
    psql -U postgresql -f src/main/resources/db/init-db.sql
```
### Ejecucion
```bash
    export JDBC_URL=jdbc:postgresql://localhost:5432/flota_dev
    export JDBC_USER=dev_user
    export JDBC_PASS=dev_pass
    mvn spring-boot:run

```

# instalacion
### 1.1 Obtener el instalador
Descargá el instalador oficial certificado por EnterpriseDB:

* Paso al sitio oficial de PostgreSQL y seleccioná Windows → [Download](https://www.postgresql.org/download/windows)
PostgreSQL.

El instalador incluye el servidor, pgAdmin y StackBuilder.

### 1.2 Ejecutar instalación
Hacé doble clic en el .exe descargado y seguí el asistente:

* Seleccioná carpeta de instalación (por defecto C:\Program Files\PostgreSQL\xx).
* Indicá carpeta de datos (por ejemplo C:\pgsql\data).
* Definí la contraseña del superusuario postgres(recomendado postgres).
* Confirmá el puerto (5432 por defecto).
* Elegí el locale (dejá el valor por defecto).
* Al finalizar, marcá “Launch StackBuilder” si querés agregar extensiones (opcional).

###  2. Verificar la instalación
#### 2.1 Con pgAdmin4
   * Abrí pgAdmin4 (se instaló junto al servidor).
   * Iniciá sesión con postgres y la contraseña que configuraste
PostgreSQL. Deberías ver el servidor activo y poder navegar bases de datos.

### 2.2 Con CLI psql
* Abrí “SQL Shell (psql)” desde el menú Inicio.

* Ingresá los valores:

```bash
    Server [localhost]:
    Database [postgres]:
    Port [5432]:
    Username [postgres]:
    Password for user postgres: <tu_pass>
```
Ejecutar:
````bash

SELECT version();
#Debería devolver la versión de PostgreSQL
````

### 3. Crear base de datos para desarrollo
   En pgAdmin o en psql, creá un rol y base de datos específicos:

````sql
-- En psql o Query Tool
CREATE ROLE dev_user WITH LOGIN PASSWORD 'dev_pass';
CREATE DATABASE flota_dev OWNER dev_user;
--Concedé privilegios:
GRANT ALL PRIVILEGES ON DATABASE flota_dev TO dev_user;
````

