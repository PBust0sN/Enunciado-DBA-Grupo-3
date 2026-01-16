# Instrucciones de clonación

1. Copiar el HTTP o SSH del repositorio.

2. Escribir en la terminal donde se quiera clonar:

```
git clone <HTTP o SSH>
```


# Instrucciones de configuración

1. Crear base de datos en PostgreSQL.

2. Configurar las variables de entorno en Intellij
    - DB_HOST=jdbc:postgresql://localhost:5432/tu_basededatos;
    - DB_PASSWORD=tu_contraseña;
    - DB_USER=tu_usuario;
    - JWT_SECRET=tu_secreto_jwt;

        - Para obtener JWT secreto seguro, generar uno [aquí](https://jwtsecrets.com/#generator) de 512 bits.


3. Crear tablas y cargar datos, aqui existen dos opciones
    3.1 Crear y cargar a mano mediante dbCreate.sql y loadData.sql

    3.1.1  Ejecutar dbCreate.sql en una query desde PostgreSQL para crear las tablas necesarias en la base de datos.
        - Ubicación del archivo: `ClimateChangeBackend/src/main/java/resources/dbCreate.sql`

    3.1.2. Ejecutar loadData.sql en una query desde PostgreSQL para poblar las tablas.
        - Ubicación del archivo: `ClimateChangeBackend/src/main/java/resources/loadData.sql`

    3.2 Hacer restore del archivo backup

   3.2.1 Abrir pgadmin y hacer click derecho en la base creada y hacer click en restore

   3.2.2 Abir en el apartado de filename el .backup que se encuentra en al raiz del repositorio `dbbackup.backup`
   
# Instrucciones de ejecución

1. Ejecutar la aplicación desde Intellij.

2. Escribir el comando en terminal desde la carpeta "ClimateChangeFrontend"

```
npm i
npm run dev
```

 - Luego abrir el enlace que salga en la terminal. por lo general.

```
http://localhost:5173/
```

3. Iniciar sesión desde la pagina home, usar las siguientes credenciales:

```
Rut: 12345678-9
Contraseña: Hola1234
```

4. Disfrutar 😊👌
