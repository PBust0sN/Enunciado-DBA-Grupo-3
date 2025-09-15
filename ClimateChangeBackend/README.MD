# Implementación de JWT

## Instrucciones para ejecutar

1. Configurar las variables de entorno en Intellij
    - DB_HOST=jdbc:postgresql://localhost:5432/tu_basededatos;
    - DB_PASSWORD=tu_contraseña;
    - DB_USER=tu_usuario;
    - JWT_SECRET=tu_secreto_jwt;

        - Para obtener JWT secreto seguro, generar uno [aquí](https://jwtsecrets.com/#generator).

2. Inicializar dbCreate.sql para crear las tablas necesarias en la base de datos PostgreSQL.
    - Ubicación del archivo: `src/main/resources/dbCreate.sql`
3. Ejecutar la aplicación desde Intellij.
3. Probar los endpoints usando Postman o cualquier otra herramienta similar.
    - Ejemplo de uso:
        - Método POST: `http://localhost:8080/api/v1/auth/register` body raw (JSON):
           ```json
          {
          "rut": "12345678-9",
          "first_name": "Nombre",
          "last_name": "Apellido",
          "email": "correo@dominio.com",
          "password": "Hola1234"
          }
           ```
        - Metodo POST: `http://localhost:8080/api/v1/auth/login` body raw (JSON):
          ```json
          {
          "rut": "12345678-9",
          "password": "Hola1234"
          }
          ```
        - Una vez logueado, se obtiene un token JWT, lo cuales se además se guardan en dos Cookies: SESSION y REFRESHTOKEN.
        - El token almacenado en SESSION tiene una duración de 1 hora, mientras que el token en REFRESHTOKEN tiene una duración de 1 semana (modificable).
        - Para verificar que se generó correctamente el token, puedes visitar la página [jwt.io](https://jwt.io/) e ingresando tu JWT_SECRET seguido de un . con el token generado.

## Dependencias
- Spring Boot Starter Web
- Spring Boot Starter Security
- PostgreSQL Driver
- Java JWT (jjwt-api, jjwt-impl, jjwt-jackson)
- Jakarta validation
- jdbc