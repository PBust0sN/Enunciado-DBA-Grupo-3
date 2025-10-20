-- Poblar tabla de usuarios (password default para todos es 'Hola1234')
INSERT INTO users (first_name, last_name, rut, password, email, role) VALUES
('Juan', 'Pérez', '12345678-9', '$2a$10$RgltMjiv61gUbaivPGiFmu/SDaamf7kdmtJ/IAxh2pgYn3XBKw7Pu', 'juan.perez@ejemplo.com', 'ROLE_ADMIN'),
('María', 'González', '98765432-1', '$2a$10$RgltMjiv61gUbaivPGiFmu/SDaamf7kdmtJ/IAxh2pgYn3XBKw7Pu', 'maria.gonzalez@ejemplo.com', 'ROLE_USER'),
('Carlos', 'Rodríguez', '11111111-1', '$2a$10$RgltMjiv61gUbaivPGiFmu/SDaamf7kdmtJ/IAxh2pgYn3XBKw7Pu', 'carlos.rodriguez@ejemplo.com', 'ROLE_USER'),
('Ana', 'Martínez', '22222222-2', '$2a$10$RgltMjiv61gUbaivPGiFmu/SDaamf7kdmtJ/IAxh2pgYn3XBKw7Pu', 'ana.martinez@ejemplo.com', 'ROLE_ADMIN'),
('Pedro', 'Sánchez', '33333333-3', '$2a$10$RgltMjiv61gUbaivPGiFmu/SDaamf7kdmtJ/IAxh2pgYn3XBKw7Pu', 'pedro.sanchez@ejemplo.com', 'ROLE_USER'),
('Laura', 'Díaz', '44444444-4', '$2a$10$RgltMjiv61gUbaivPGiFmu/SDaamf7kdmtJ/IAxh2pgYn3XBKw7Pu', 'laura.diaz@ejemplo.com', 'ROLE_USER');

-- Poblar tabla de puntos de medición (latitud y longitud estan multiplicados por 1,000,000 para evitar decimales)
INSERT INTO measure_points (latitud, longitud, sensor_type) VALUES
(-33.456789, -70.123456, 'Temperatura'),
(-33.456123, -70.124567, 'Emisiones de CO2'),
(-33.612125, -71.632559, 'Nivel del mar'),
(-33.454321, -70.126789, 'Masa de hielo'),
(-33.453456, -70.127890, 'Temperatura'),
(-33.452345, -70.128901, 'Emisiones de CO2'),
(-33.451234, -70.129012, 'Nivel del mar'),
(-33.450123, -70.130123, 'Masa de hielo'),
(-33.449012, -70.131234, 'Temperatura'),
(-33.448901, -70.132345, 'Emisiones de CO2'),
(-33.447890, -70.133456, 'Nivel del mar'),
(-33.446789, -70.134567, 'Masa de hielo'),
(-33.445678, -70.135678, 'Temperatura'),
(-33.444567, -70.136789, 'Emisiones de CO2'),
(-33.443456, -70.137890, 'Nivel del mar');

-- Poblar tabla de conjuntos de datos
INSERT INTO dataset (name_dataset, description_dataset, source_dataset, date_authorization_dataset) VALUES
('Clima Santiago 2023', 'Datos climáticos recopilados durante el año 2023', 'Estación Meteorológica Central', '2023-01-15'),
('Contaminación Urbana', 'Mediciones de contaminantes en zona metropolitana', 'Red de Monitoreo Ambiental', '2023-02-20'),
('Radiación Solar', 'Niveles de radiación UV en distintos puntos de la ciudad', 'Instituto Meteorológico', '2023-03-10'),
('Monitoreo CO2 Global', 'Datos sobre emisiones de CO2 en diferentes regiones', 'Red Global Climática', '2023-04-05'),
('Niveles Oceánicos 2023', 'Mediciones de nivel del mar en costas chilenas', 'Instituto Oceanográfico', '2023-05-12'),
('Glaciares Andinos', 'Monitoreo de masas de hielo en la cordillera', 'Centro Glaciológico', '2023-06-18');

-- Poblar tabla de mediciones
INSERT INTO measurements (value_measurement, date_measurement, id_measure_points, id_dataset) VALUES
(25.4, '2023-06-15 14:30:00+00', 1, 1),    -- Temperatura para Clima Santiago
(452.8, '2023-06-15 15:00:00+00', 2, 4),   -- Emisiones de CO2 para Monitoreo CO2
(3.25, '2023-06-15 15:30:00+00', 3, 5),    -- Nivel del mar para Niveles Oceánicos
(12500.5, '2023-06-16 10:15:00+00', 4, 6), -- Masa de hielo para Glaciares
(26.7, '2023-06-16 12:00:00+00', 5, 1),    -- Temperatura para Clima Santiago
(468.2, '2023-06-17 13:45:00+00', 6, 4),   -- Emisiones de CO2 para Monitoreo CO2
(3.34, '2023-06-17 14:30:00+00', 7, 5),    -- Nivel del mar para Niveles Oceánicos
(12350.8, '2023-07-10 09:15:00+00', 8, 6), -- Masa de hielo para Glaciares
(28.9, '2023-07-10 10:30:00+00', 9, 1),    -- Temperatura
(475.6, '2023-07-11 11:45:00+00', 10, 4),  -- Emisiones de CO2
(3.45, '2023-07-12 13:00:00+00', 11, 5),   -- Nivel del mar
(12100.2, '2023-07-13 14:15:00+00', 12, 6), -- Masa de hielo
(24.3, '2023-07-14 15:30:00+00', 13, 1),   -- Temperatura
(480.1, '2023-07-15 16:45:00+00', 14, 4),  -- Emisiones de CO2
(3.52, '2023-07-16 17:00:00+00', 15, 5),   -- Nivel del mar
(11950.7, '2023-07-17 09:30:00+00', 8, 6), -- Masa de hielo
(29.5, '2023-07-18 10:45:00+00', 1, 1),    -- Temperatura
-- Punto 1 - Temperatura
(27.1, '2024-01-10 12:00:00+00', 1, 1),
(26.8, '2024-03-05 09:30:00+00', 1, 1),
(28.0, '2024-07-01 15:45:00+00', 1, 1),
-- Punto 2 - CO2
(460.0, '2024-01-12 08:15:00+00', 2, 4),
(470.2, '2024-02-18 14:30:00+00', 2, 4),
-- Punto 3 - Nivel del mar
(3.40, '2024-03-22 11:00:00+00', 3, 5),
(3.55, '2024-05-10 13:30:00+00', 3, 5),
-- Punto 4 - Masa de hielo
(12200.0, '2024-04-15 10:00:00+00', 4, 6),
(12450.5, '2024-06-20 12:45:00+00', 4, 6),
-- Punto 5 - Temperatura
(27.5, '2024-05-10 14:00:00+00', 5, 1),
(28.0, '2024-06-15 16:30:00+00', 5, 1),
-- Punto 6 - CO2
(465.0, '2024-03-10 09:15:00+00', 6, 4),
(472.0, '2024-07-12 15:00:00+00', 6, 4),
-- Punto 7 - Nivel del mar
(3.60, '2024-01-20 10:30:00+00', 7, 5),
(3.65, '2024-02-25 11:45:00+00', 7, 5),
-- Punto 8 - Masa de hielo
(12300.0, '2024-01-30 09:00:00+00', 8, 6),
(12380.5, '2024-06-01 14:15:00+00', 8, 6),
-- Punto 9 - Temperatura
(29.0, '2024-04-12 12:45:00+00', 9, 1),
(28.5, '2024-06-20 10:30:00+00', 9, 1),
-- Punto 10 - CO2
(478.0, '2024-05-15 13:00:00+00', 10, 4),
(480.5, '2024-07-10 15:45:00+00', 10, 4),
-- Punto 1 - Temperatura
(27.2, '2025-01-10 12:00:00+00', 1, 1),
(26.9, '2025-03-05 09:30:00+00', 1, 1),
(28.1, '2025-06-01 15:45:00+00', 1, 1),
-- Punto 2 - CO2
(462.5, '2025-01-12 08:15:00+00', 2, 4),
(471.0, '2025-02-18 14:30:00+00', 2, 4),
-- Punto 3 - Nivel del mar
(3.48, '2025-03-22 11:00:00+00', 3, 5),
(3.57, '2025-05-10 13:30:00+00', 3, 5),
-- Punto 4 - Masa de hielo
(12250.0, '2025-04-15 10:00:00+00', 4, 6),
(12480.5, '2025-06-20 12:45:00+00', 4, 6),
-- Punto 5 - Temperatura
(27.8, '2025-05-10 14:00:00+00', 5, 1),
(28.2, '2025-06-15 16:30:00+00', 5, 1),
-- Punto 6 - CO2
(466.0, '2025-03-10 09:15:00+00', 6, 4),
(473.5, '2025-07-12 15:00:00+00', 6, 4),
-- Punto 7 - Nivel del mar
(3.62, '2025-01-20 10:30:00+00', 7, 5),
(3.66, '2025-02-25 11:45:00+00', 7, 5),
-- Punto 8 - Masa de hielo
(12310.0, '2025-01-30 09:00:00+00', 8, 6),
(12390.5, '2025-06-01 14:15:00+00', 8, 6),
-- Punto 9 - Temperatura
(29.2, '2025-04-12 12:45:00+00', 9, 1),
(28.7, '2025-06-20 10:30:00+00', 9, 1),
-- Punto 10 - CO2
(479.0, '2025-05-15 13:00:00+00', 10, 4),
(481.5, '2025-07-10 15:45:00+00', 10, 4),
-- Punto 11 - Nivel del mar
(3.58, '2025-03-12 09:00:00+00', 11, 5),
(3.63, '2025-06-18 11:15:00+00', 11, 5),
-- Punto 12 - Masa de hielo
(12150.0, '2025-02-10 10:30:00+00', 12, 6),
(12300.0, '2025-05-25 14:00:00+00', 12, 6),
-- Punto 13 - Temperatura
(25.5, '2025-01-18 12:15:00+00', 13, 1),
(26.0, '2025-06-10 13:30:00+00', 13, 1),
-- Punto 14 - CO2
(482.0, '2025-02-05 09:45:00+00', 14, 4),
(484.5, '2025-06-22 16:00:00+00', 14, 4),
-- Punto 15 - Nivel del mar
(3.55, '2025-01-25 10:00:00+00', 15, 5),
(3.60, '2025-06-18 12:45:00+00', 15, 5);


DROP FUNCTION get_aggregated_measurements(bigint,timestamp without time zone,timestamp without time zone)

-- Sentencia 6:
CREATE OR REPLACE FUNCTION get_aggregated_measurements(
    p_id_dataset BIGINT,
    p_start_date TIMESTAMP,
    p_end_date TIMESTAMP
)
RETURNS TABLE (
    period_start DATE,
    avg_value DOUBLE PRECISION
)
LANGUAGE plpgsql
AS $$
BEGIN
    -- Si el rango es menor o igual a 90 días → agrupamos por semana
    IF (p_end_date - p_start_date) <= INTERVAL '90 days' THEN
        RETURN QUERY
SELECT
    date_trunc('week', m.date_measurement)::DATE AS period_start,
    AVG(m.value_measurement) AS avg_value
FROM measurements m
WHERE m.id_dataset = p_id_dataset
  AND m.date_measurement BETWEEN p_start_date AND p_end_date
GROUP BY date_trunc('week', m.date_measurement)
ORDER BY period_start;

-- Si el rango es mayor a 90 días → agrupamos por mes
ELSE
            RETURN QUERY
SELECT
    date_trunc('month', m.date_measurement)::DATE AS period_start,
    AVG(m.value_measurement) AS avg_value
FROM measurements m
WHERE m.id_dataset = p_id_dataset
  AND m.date_measurement BETWEEN p_start_date AND p_end_date
GROUP BY date_trunc('month', m.date_measurement)
ORDER BY period_start;
END IF;
END;
$$;

/*  CONSULTA 1:
 Cálculo de Anomalía de Temperatura: Escribe una consulta SQL que,
para cada punto de medición, calcule la temperatura promedio del último
año y la compare con el promedio histórico de ese mismo punto. Muestra el
ID del punto de medición y la diferencia (anomalía) en grados.
 */


-- TRIGGER FUNCION
CREATE OR REPLACE FUNCTION calcular_anomalia_punto()
    RETURNS TRIGGER AS $$
DECLARE
promedio_historico DOUBLE PRECISION;
    promedio_ultimo_anio DOUBLE PRECISION;
    anomalia DOUBLE PRECISION;
BEGIN
    -- Calcular promedio histórico del punto
SELECT AVG(value_measurement)
INTO promedio_historico
FROM measurements
WHERE id_measure_points = NEW.id_measure_points;

-- Calcular promedio del último año del punto
SELECT AVG(value_measurement)
INTO promedio_ultimo_anio
FROM measurements
WHERE id_measure_points = NEW.id_measure_points
  AND date_measurement >= CURRENT_DATE - INTERVAL '1 year';

-- Calcular anomalía
anomalia := promedio_ultimo_anio - promedio_historico;

    -- Mostrar el resultado en consola (no se guarda)
    RAISE NOTICE 'Punto % -> Anomalía: %',
        NEW.id_measure_points, anomalia;

RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- TRIGGER
CREATE TRIGGER trg_calcular_anomalia_punto
    AFTER INSERT OR UPDATE
                        ON measurements
                        FOR EACH ROW
                        EXECUTE FUNCTION calcular_anomalia_punto();

-- INDEX BY DATE
CREATE INDEX idx_measurements_date
    ON measurements (date_measurement);

/* CONSULTA 5:
  Simulación de Interpolación de Datos: Escribe un procedimiento
almacenado llamado interpolar_datos_semanales que reciba un ID
de dataset. El procedimiento debe calcular el promedio semanal de las
mediciones y almacenarlo en una tabla de resumen, llenando los días sin
datos con el promedio semanal.
 */

CREATE OR REPLACE FUNCTION interpolar_datos_semanales(p_id_dataset BIGINT)
    RETURNS TABLE(
                     id_measure_points BIGINT,
                     week_start DATE,
                     week_end DATE,
                     avg_value DOUBLE PRECISION
                 ) AS $$
DECLARE
rec RECORD;
    semana_inicio DATE;
    semana_fin DATE;
    rango_inicio DATE;
    rango_fin DATE;
    avg_semana DOUBLE PRECISION;
    dias_con_datos INT;
    dias_sin_datos INT;
BEGIN
    -- Iterar por cada punto de medición del dataset
FOR rec IN
SELECT DISTINCT m.id_measure_points
FROM measurements m
WHERE m.id_dataset = p_id_dataset
    LOOP
-- Rango total de fechas del dataset para este punto
SELECT MIN(m.date_measurement)::date, MAX(m.date_measurement)::date
INTO rango_inicio, rango_fin
FROM measurements m
WHERE m.id_measure_points = rec.id_measure_points
  AND m.id_dataset = p_id_dataset;

semana_inicio := rango_inicio;

            -- Recorremos las semanas dentro del rango
            WHILE semana_inicio <= rango_fin LOOP
                    semana_fin := semana_inicio + INTERVAL '6 days';

                    -- Promedio de la semana (solo días con datos)
SELECT AVG(m.value_measurement), COUNT(DISTINCT m.date_measurement::date)
INTO avg_semana, dias_con_datos
FROM measurements m
WHERE m.id_measure_points = rec.id_measure_points
  AND m.id_dataset = p_id_dataset
  AND m.date_measurement::date BETWEEN semana_inicio AND semana_fin;

-- Si no hay datos en esa semana, asignamos 0
IF avg_semana IS NULL THEN
                        avg_semana := 0;
                        dias_con_datos := 0;
END IF;

                    -- Días sin datos
                    dias_sin_datos := 7 - dias_con_datos;

                    -- Interpolación: los días sin datos cuentan con el promedio semanal
                    -- (el promedio de la semana sigue siendo el mismo)
                    avg_semana := avg_semana;

                    -- Retornar fila
                    id_measure_points := rec.id_measure_points;
                    week_start := semana_inicio;
                    week_end := semana_fin;
                    avg_value := avg_semana;

                    RETURN NEXT;

                    -- Avanzar a la siguiente semana
                    semana_inicio := semana_inicio + INTERVAL '7 days';
END LOOP;
END LOOP;
END;
$$ LANGUAGE plpgsql;