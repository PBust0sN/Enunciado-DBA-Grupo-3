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
);


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

