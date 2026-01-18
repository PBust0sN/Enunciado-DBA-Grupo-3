package com.example.ClimateChangeBackend.repositories;

import com.example.ClimateChangeBackend.dtos.CO2DistanceDTO;
import com.example.ClimateChangeBackend.dtos.InvalidPointDTO;
import com.example.ClimateChangeBackend.dtos.MonthlyTendencyDTO;
import com.example.ClimateChangeBackend.dtos.PointVariationDTO;
import com.example.ClimateChangeBackend.dtos.PointWithoutGeorefDTO;
import com.example.ClimateChangeBackend.entities.MeasurePointsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MeasurePointsRepositoryImp implements MeasurePointsRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MeasurePointsRepositoryImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<MeasurePointsEntity> findById(Long id){
        String sql = "SELECT id_measure_points, Latitud, Longitud, sensor_type FROM measure_points WHERE id_measure_points = ?";
        try {
            MeasurePointsEntity measurePointsEntity = jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(MeasurePointsEntity.class),
                    id
            );
            return Optional.ofNullable(measurePointsEntity);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<MeasurePointsEntity> findAll(){
        String sql = "SELECT * FROM measure_points";
        try {
            return jdbcTemplate.query(
                    sql,
                    new BeanPropertyRowMapper<>(MeasurePointsEntity.class)
            );
        } catch (EmptyResultDataAccessException e) {
            return List.of();
        }
    }

    @Override
    public MeasurePointsEntity  save(MeasurePointsEntity measurePointsEntity){
        if (measurePointsEntity.getIdMeasurePoints() == null) {
            String sql = "INSERT INTO measure_points (latitud, longitud, sensor_type) VALUES (?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(
                    connection -> {
                        PreparedStatement ps = connection.prepareStatement(
                                sql,
                                new String[] { "idMeasurePoints" }
                        );
                        ps.setDouble(1, measurePointsEntity.getLatitud());
                        ps.setDouble(2, measurePointsEntity.getLongitud());
                        ps.setString(3, measurePointsEntity.getSensorType());
                        return ps;
                    },
                    keyHolder
            );
            Number generatedId = keyHolder.getKey();
            if (generatedId != null) {
                measurePointsEntity.setIdMeasurePoints(generatedId.longValue());
            }
        } else {
            String sql = "UPDATE measure_points SET latitud = ?, longitud = ?, sensor_type = ? WHERE id_measure_points = ?";
            jdbcTemplate.update(
                sql,
                measurePointsEntity.getLatitud(),
                measurePointsEntity.getLongitud(),
                measurePointsEntity.getSensorType(),
                measurePointsEntity.getIdMeasurePoints()
            );
        }
        return measurePointsEntity;
    }

    @Override
    public int update(MeasurePointsEntity  measurePointsEntity){
        String sql = "UPDATE measure_points SET longitud = ?, latitud = ?, sensor_type = ? WHERE id_measure_points = ?";
        return jdbcTemplate.update(sql, measurePointsEntity.getLongitud(),
                measurePointsEntity.getLatitud(),
                measurePointsEntity.getSensorType(),
                measurePointsEntity.getIdMeasurePoints());
    }

    // Consulta 2
    @Override
    public List<PointVariationDTO> findPointsWithHighestVariation(){
        String sql = "SELECT mp.id_measure_points, STDDEV(m.value_measurement) AS temperature_stddev " +
                "FROM measure_points mp " +
                "JOIN measurements m ON mp.id_measure_points = m.id_measure_points " +
                "WHERE m.date_measurement >= NOW() - INTERVAL '5 years' AND mp.sensor_type = 'Temperatura'" +
                "GROUP BY mp.id_measure_points " +
                "HAVING COUNT(m.id_measurement) > 1 " +
                "ORDER BY temperature_stddev DESC " +
                "LIMIT 10";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                PointVariationDTO.builder()
                        .idMeasurePoints(rs.getLong("id_measure_points"))
                        .temperatureStddev(rs.getDouble("temperature_stddev"))
                        .build()
        );
    }

    @Override
    public List<PointWithoutGeorefDTO> findPointsWithoutGeoreference() {
        String sql = "SELECT mp.id_measure_points, MAX(m.date_measurement) AS last_measurement_date " +
                "FROM measure_points mp " +
                "LEFT JOIN measurements m ON mp.id_measure_points = m.id_measure_points " +
                "WHERE mp.latitud IS NULL " +
                "OR mp.longitud IS NULL " +
                "OR (mp.latitud = 0 AND mp.longitud = 0) " +
                "GROUP BY mp.id_measure_points " +
                "ORDER BY last_measurement_date DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                PointWithoutGeorefDTO.builder()
                        .idMeasurePoints(rs.getLong("id_measure_points"))
                        .lastMeasurementDate(rs.getObject("last_measurement_date", java.time.OffsetDateTime.class))
                        .build()
        );
    }

    public Optional<MeasurePointsEntity> findByLatitudeAndLongitude(double lat, double lon, String type){
        String sql = "SELECT id_measure_points, latitud, longitud, sensor_type FROM measure_points WHERE latitud = ? AND longitud = ? AND sensor_type = ? LIMIT 1";
        try {
            MeasurePointsEntity measurePointsEntity = jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(MeasurePointsEntity.class),
                    lat,
                    lon,
                    type
            );
            return Optional.ofNullable(measurePointsEntity);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    // Consulta 3
    @Override
    public List<MeasurePointsEntity> getPointsLessThan50ByLatitudeAndLongitude(double latitude, double longitude){
        String sql = """ 
                    SELECT co2mp.id_measure_points,
                    co2mp.latitud,
                    co2mp.longitud,
                    co2mp.sensor_type
                    FROM measure_points AS mp
                    JOIN measure_points AS co2mp
                    ON co2mp.sensor_type = 'Emisiones de CO2'
                    WHERE mp.sensor_type = 'Temperatura'
                      AND mp.latitud = ?
                      AND mp.longitud = ?
                      AND (
                            6371 * 2 * ASIN(
                                SQRT(
                                    POWER(SIN(RADIANS(co2mp.latitud - mp.latitud) / 2), 2) +
                                    COS(RADIANS(mp.latitud)) * COS(RADIANS(co2mp.latitud)) *
                                    POWER(SIN(RADIANS(co2mp.longitud - mp.longitud) / 2), 2)
                                )
                            )
                        ) < 50;
                    """;
        try{
            return jdbcTemplate.query(
                    sql,
                    new BeanPropertyRowMapper<>(MeasurePointsEntity.class),
                    latitude,
                    longitude
            );
        }catch (EmptyResultDataAccessException e){
            return List.of();
        }
    }

    // Consulta 4.2 para puntos
    @Override
    public List<InvalidPointDTO> findInvalidPoints() {
        String sql = """
                SELECT id_measure_points AS id,
                       ST_AsText(geom) AS wkt,
                       latitud,
                       longitud
                FROM measure_points
                WHERE geom IS NULL
                      OR NOT ST_IsValid(geom)
                      OR ST_SRID(geom) <> 4326
                      OR ST_X(geom) NOT BETWEEN -180 AND 180
                      OR ST_Y(geom) NOT BETWEEN -90 AND 90
                      OR (ST_X(geom) = 0 AND ST_Y(geom) = 0)
                      OR latitud = 0 AND longitud = 0
                      OR latitud > 90 OR latitud < -90
                      OR longitud > 180 OR longitud < -180;
                """;
        try{
            return jdbcTemplate.query(
                    sql,
                    new BeanPropertyRowMapper<>(InvalidPointDTO.class)
            );

        }catch (EmptyResultDataAccessException e){
            return List.of();
        }
    }

    @Override
    public List<MonthlyTendencyDTO> findAllMonthlyTendencies() {
        String sql = "SELECT * FROM tendencia_mensual";
        try{
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MonthlyTendencyDTO.class));
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        } catch (Exception e){
            throw new RuntimeException("Error al obtener tendencia mensual",e);
        }
    }

    @Override
    public List<MonthlyTendencyDTO> findBySensorType(String sensorType) {
        String sql = "SELECT * FROM tendencia_mensual WHERE sensor_type = ?";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MonthlyTendencyDTO.class), sensorType);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener tendencia mensual por tipo de sensor", e);
        }
    }

    @Override
    public Double interpolateByNearestNeighbors(double lat, double lon) {

        String exactPointSql = """
        SELECT m.value_measurement
        FROM measurements m
        JOIN measure_points mp
            ON m.id_measure_points = mp.id_measure_points
        WHERE ST_Equals(
            mp.geom,
            ST_SetSRID(ST_MakePoint(?, ?), 4326)
        )
        LIMIT 1
    """;

        List<Double> exactValues = jdbcTemplate.query(
                exactPointSql,
                (rs, rowNum) -> rs.getDouble("value_measurement"),
                lon,
                lat
        );

        if (!exactValues.isEmpty()) {
            return exactValues.get(0);
        }

        String knnSql = """
        SELECT AVG(sub.value_measurement)
        FROM (
            SELECT m.value_measurement
            FROM measurements m
            JOIN measure_points mp
                ON m.id_measure_points = mp.id_measure_points
            ORDER BY mp.geom <-> ST_SetSRID(ST_MakePoint(?, ?), 4326)
            LIMIT 3
        ) AS sub
    """;

        Double interpolatedValue = jdbcTemplate.queryForObject(
                knnSql,
                Double.class,
                lon, // X
                lat  // Y
        );

        return interpolatedValue != null ? interpolatedValue : 0.0;
    }

    public List<CO2DistanceDTO> get50kmCO2Temperature() {
        String sql = """
                SELECT DISTINCT ON (co2.id_measure_points)
                    co2.id_measure_points AS co2_point_id,
                    temp.id_measure_points AS temp_point_id,
                	co2.latitud AS latitud,
                	co2.longitud AS longitud,
                	co2.sensor_type AS sensor_type,
                	ST_AsText(co2.geom) AS co2_geom,
                    ST_Distance(
                        co2.geom::geography,
                        temp.geom::geography
                    ) AS distance_meters
                FROM measure_points co2
                JOIN measure_points temp
                    ON ST_DWithin(
                        co2.geom::geography,
                        temp.geom::geography,
                        50000
                    )
                WHERE co2.sensor_type = 'Emisiones de CO2'
                  AND temp.sensor_type = 'Temperatura'
                ORDER BY co2.id_measure_points, distance_meters;
                """;
        try{
            List<CO2DistanceDTO> co2DistanceDTOS = jdbcTemplate.query(
                    sql,
                    new BeanPropertyRowMapper<>(CO2DistanceDTO.class)
            );
            System.out.println(co2DistanceDTOS);
            return co2DistanceDTOS;
        }catch (EmptyResultDataAccessException e){
            return List.of();
        }
    }

    public List<MeasurePointsEntity> getNoSensor() {
        String sql = """
                SELECT *
                FROM measure_points
                WHERE sensor_type = 'Sin sensor';
                """;
        try{
            List<MeasurePointsEntity> sinSensor = jdbcTemplate.query(
                    sql,
                    new BeanPropertyRowMapper<>(MeasurePointsEntity.class)
            );
            System.out.println(sinSensor);
            return sinSensor;
        }catch (EmptyResultDataAccessException e){
            return List.of();
        }
    }
}
