DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,first_name VARCHAR(100),
    last_name VARCHAR(100),
    rut VARCHAR(12) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    refresh_token VARCHAR(255),
    refresh_token_expiration BIGINT,
    role VARCHAR(50) NOT NULL
    );