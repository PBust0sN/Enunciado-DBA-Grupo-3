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

CREATE TABLE IF NOT EXISTS measure_points(
    id_measure_points SERIAL PRIMARY KEY,
    latitud DOUBLE PRECISION NOT NULL,
    longitud DOUBLE PRECISION NOT NULL,
    sensor_type VARCHAR(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS dataset(
    id_dataset SERIAL PRIMARY KEY,
    name_dataset VARCHAR(80) NOT NULL,
    description_dataset VARCHAR(255) NOT NULL,
    source_dataset VARCHAR(80) NOT NULL,
    date_authorization_dataset DATE
);
CREATE TABLE IF NOT EXISTS measurements (
    id_measurement SERIAL PRIMARY KEY,
    value_measurement DOUBLE PRECISION NOT NULL,
    date_measurement timestamp with time zone NOT NULL ,
    id_measure_points BIGINT NOT NULL,
    id_dataset BIGINT NOT NULL,
    FOREIGN KEY (id_measure_points) REFERENCES measure_points(id_measure_points),
    FOREIGN KEY (id_dataset) REFERENCES dataset(id_dataset)
);