--Detección de Eventos Extremos: Escribe una consulta que identifique todos los días en el último año donde
-- la temperatura máxima registrada en cualquier punto de medición superó un umbral de 35°C.
-- Muestra la fecha y el valor máximo de temperatura de ese día.


SELECT
    DATE(date_measurement) AS fecha,
    MAX(value_measurement) AS temperatura_maxima
FROM
    measurements
WHERE
-- datos de los ultimos 365 dias a partir de la llamada
    date_measurement >= NOW() - INTERVAL '1 year'
GROUP BY
    DATE(date_measurement)
HAVING
    MAX(value_measurement) > 35
ORDER BY
    fecha;