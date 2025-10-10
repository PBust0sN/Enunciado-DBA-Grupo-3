package com.example.ClimateChangeBackend.repositories;

import com.example.ClimateChangeBackend.entities.DatasetEntity;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DatasetRepositoryImpl implements DatasetRepository {

    private final JdbcTemplate jdbcTemplate;
    public DatasetRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<DatasetEntity>  findById(Long id) {
        String sql = "SELECT * FROM dataset WHERE id_dataset = ?";
        try {
            DatasetEntity datasetEntity = jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(DatasetEntity.class),
                    id
            );
            return Optional.ofNullable(datasetEntity);
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<DatasetEntity> findAll(){
        String sql = "SELECT * FROM dataset";
        try{
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DatasetEntity.class));
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        } catch (Exception e){
            throw new RuntimeException("Error al obtener dataset",e);
        }
    }

    @Override
    public Optional<DatasetEntity>  findByName(String name) {
        String sql = "SELECT * FROM dataset WHERE name_dataset = ?";
        try {
            DatasetEntity datasetEntity = jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(DatasetEntity.class),
                    name
            );
            return Optional.ofNullable(datasetEntity);
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public DatasetEntity save(DatasetEntity datasetEntity) {
        if (datasetEntity.getIdDataset() == null){
            String sql = "INSERT INTO dataset (id_dataset, name_dataset, description_dataset, source_dataset, date_autorization_dataset) VALUES (?, ?, ?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(
                    connection ->  {
                        PreparedStatement ps = connection.prepareStatement(
                                sql,
                                new String[] { "id" }
                        );
                        ps.setString(1, datasetEntity.getNameDataset());
                        ps.setString(2, datasetEntity.getDescriptionDataset());
                        ps.setString(3, datasetEntity.getSourceDataset());
                        ps.setDate(4, (Date) datasetEntity.getDateAutorizationDataset());
                        return ps;
                    },
                    keyHolder
            );
            Number generatedId = keyHolder.getKey();
            if(generatedId != null) {
                datasetEntity.setIdDataset(generatedId.longValue());
            }
        }else {
            String sql = "UPDATE dataset SET name_dataset = ?, description_dataset = ?, source_dataset = ?, date_autorization_dataset = ? WHERE id_dataset = ?";
            jdbcTemplate.update(
                    sql,
                    datasetEntity.getNameDataset(),
                    datasetEntity.getDescriptionDataset(),
                    datasetEntity.getSourceDataset(),
                    datasetEntity.getDateAutorizationDataset(),
                    datasetEntity.getIdDataset()
            );

        }
        return datasetEntity;
    }


}
