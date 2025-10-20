--Detección de Eventos Extremos: Escribe una consulta que identifique todos los días en el último año donde
-- la temperatura máxima registrada en cualquier punto de medición superó un umbral de 35°C.
-- Muestra la fecha y el valor máximo de temperatura de ese día.


SELECT
    DATE(m.date_measurement) AS fecha,
    MAX(m.value_measurement) AS temperatura_maxima
FROM
    measurements m
JOIN
    measure_points p ON m.id_measure_points = p.id_measure_points
WHERE
    p.sensor_type = 'Temperatura'
    -- últimos 365 días desde la consulta
    AND m.date_measurement >= NOW() - INTERVAL '1 year'
GROUP BY
    DATE(m.date_measurement)
HAVING
    MAX(m.value_measurement) > 35
ORDER BY
    fecha;