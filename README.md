# SistemaGestionFlota



### Configuracion local de PostgreSQL
1. Instalar PostgreSQL (ver seccion [Instalacion](#instalacion))
2. Ejecutar script de inicializacion:

```bash
    psql -U postgresql -f src/main/resources/db/init-db.sql
```


# Casos de Uso
## 1. Autenticación

### 1.1 Login Solicitante

```bash

curl -i -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "email=solicitante@mail.com&password=1234"

```

**Esperado:**

- **200 OK**
- JSON con `{ id, email, password: “$2a…”, rol: “SOLICITANTE” }`

### 1.2 Login Encargado

```bash

curl -i -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "email=encargado@mail.com&password=1234"

```

**Esperado:**

- **200 OK**
- JSON con `{ id, email, password: “$2a…”, rol: “ENCARGADO” }`

---

## 2. Solicitudes de Viaje (Solicitante)

### 2.1 Crear Solicitud

```bash

curl -i -X POST http://localhost:8080/solicitante/solicitudes \
  -u solicitante@mail.com:1234 \
  -H "Content-Type: application/json; charset=UTF-8" \
  --data-binary @- << 'EOF'
{
  "fechaSalida":"2025-05-20T09:00:00",
  "fechaRegreso":"2025-05-20T17:00:00",
  "destino":"Facultad Ingeniería",
  "motivo":"Prueba",
  "prioridad":"ALTA"
}
EOF

```

**Esperado:**

- **200 OK** (o **201 Created**)
- JSON de `SolicitudViaje` con `estado: "PENDIENTE"`

### 2.2 Listar Mis Solicitudes

```bash

curl -i -X GET http://localhost:8080/solicitante/solicitudes \
  -u solicitante@mail.com:1234

```

**Esperado:**

- **200 OK**
- Array JSON que incluye la solicitud recién creada

---

## 3. Gestión de Solicitudes (Encargado)

### 3.1 Ver Pendientes

```bash

curl -i -X GET http://localhost:8080/encargado/solicitudes/pendientes \
  -u encargado@mail.com:1234

```

**Esperado:**

- **200 OK**
- Array JSON con todas las `SolicitudViaje` en `PENDIENTE`

### 3.2 Aprobar Solicitud

```bash

curl -i -X POST http://localhost:8080/encargado/solicitudes/7/aprobar \
  -u encargado@mail.com:1234 \
  -H "Content-Type: application/json" \
  -d '{"comentarios":"Aprobada para prueba"}'

```

**Esperado:**

- **200 OK**
- JSON de `Aprobacion`
- La solicitud con ID 1 cambia a `estado: "APROBADA"`

### 3.3 Rechazar Solicitud

```bash

curl -i -X POST http://localhost:8080/encargado/solicitudes/1/rechazar \
  -u encargado@mail.com:1234 \
  -H "Content-Type: text/plain" \
  -d "Rechazada por prueba"

```

**Esperado:**

- **200 OK**
- JSON de `Aprobacion`
- La solicitud con ID 1 cambia a `estado: "RECHAZADA"`

---

## 4. Asignación de Vehículo (Encargado)

### 4.1 Listar Vehículos Disponibles

```bash

curl -i -X GET http://localhost:8080/vehiculos/disponibles \
  -u encargado@mail.com:1234

```

**Esperado:**

- **200 OK**
- Array JSON con vehículos en `estado: "DISPONIBLE"`

### 4.2 Asignar Vehículo

```bash

curl -i -X POST http://localhost:8080/encargado/solicitudes/{sid}/asignar/{vid} \
  -u encargado@mail.com:1234

```

**Esperado:**

- **200 OK**
- JSON del `Vehiculo` asignado
- La solicitud con ID 1 pasa a `ASIGNADA` y el vehículo a `ASIGNADO`

---

## 5. Registro de Viaje (Encargado)

```bash

curl -i -X POST http://localhost:8080/encargado/solicitudes/{sid}/viaje \
  -u encargado@mail.com:1234 \
  -d "kmInicio=100&kmFin=150&combustibleInicio=50&combustibleFin=20"

```

**Esperado:**

- **200 OK**
- JSON de `Viaje`
- La solicitud con ID 1 pasa a estado `CERRADA` 

---

## 6. Mantenimiento (Encargado)

```bash

curl -i -X POST http://localhost:8080/encargado/vehiculos/3/mantenimiento \
  -u encargado@mail.com:1234 \
  -H "Content-Type: application/json" \
  -d '{
        "vehiculoId":3,
        "fecha":"2025-05-16T10:00:00",
        "tipoServicio":"Cambio aceite",
        "km":150.0,
        "facturaAdjunta":"F123"
      }'

```

**Esperado:**

- **200 OK**
- JSON de `Mantenimiento` registrado

---

## 7. Incidentes (Encargado)

```bash

curl -i -X POST http://localhost:8080/encargado/vehiculos/3/incidente \
  -u encargado@mail.com:1234 \
  -H "Content-Type: application/json" \
  -d '{
        "vehiculoId":3,
        "fecha":"2025-05-16T11:00:00",
        "descripcion":"Choque leve",
        "partePolicial":"P456",
        "fotos":["foto1.jpg","foto2.jpg"]
      }'

```

**Esperado:**

- **200 OK**
- JSON de `Incidente` registrado

---

### Verificación en la base

Tras cada sección, abrí tu cliente SQL (H2 Console o psql) y ejecutá:

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

