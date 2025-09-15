package com.example.ClimateChangeBackend.repositories;

import com.example.ClimateChangeBackend.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<UserEntity> findByRut(String rut) {
        String sql = "SELECT id, first_name, last_name, rut, password, email, refresh_token, refresh_token_expiration, role FROM users WHERE rut = ?";
        try {
            UserEntity userEntity = jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(UserEntity.class),
                    rut
            );
            return Optional.ofNullable(userEntity);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        String sql = "SELECT id, first_name, last_name, rut, password, email, refresh_token, refresh_token_expiration, role FROM users WHERE id = ?";
        try {
            UserEntity userEntity = jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(UserEntity.class),
                    id
            );
            return Optional.ofNullable(userEntity);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserEntity> findByRefreshToken(String refreshToken) {
        String sql = "SELECT id, first_name, last_name, rut, password, email, refresh_token, refresh_token_expiration, role FROM users WHERE refresh_token = ?";
        try {
            UserEntity userEntity = jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(UserEntity.class),
                    refreshToken
            );
            return Optional.ofNullable(userEntity);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

    @Override
    public boolean existsByRut(String rut) {
        String sql = "SELECT COUNT(*) FROM users WHERE rut = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, rut);
        return count != null && count > 0;
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        if (userEntity.getId() == null) {
            String sql = "INSERT INTO users (first_name, last_name, rut, password, email, refresh_token, refresh_token_expiration, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(
                    connection -> {
                        PreparedStatement ps = connection.prepareStatement(
                                sql,
                                new String[] { "id" }
                        );
                        ps.setString(1, userEntity.getFirstName());
                        ps.setString(2, userEntity.getLastName());
                        ps.setString(3, userEntity.getRut());
                        ps.setString(4, userEntity.getPassword());
                        ps.setString(5, userEntity.getEmail());
                        ps.setString(6, userEntity.getRefreshToken());
                        if (userEntity.getRefreshTokenExpiration() != null) {
                            ps.setLong(7, userEntity.getRefreshTokenExpiration());
                        } else {
                            ps.setNull(7, java.sql.Types.BIGINT);
                        }
                        ps.setString(8, userEntity.getRole().toString());
                        return ps;
                    },
                    keyHolder
            );
            Number generatedId = keyHolder.getKey();
            if (generatedId != null) {
                userEntity.setId(generatedId.longValue());
            }
        } else {
            String sql = "UPDATE users SET first_name = ?, last_name = ?, rut = ?, password = ?, email = ?, refresh_token = ?, refresh_token_expiration = ?, role = ? WHERE id = ?";
            jdbcTemplate.update(
                    sql,
                    userEntity.getFirstName(),
                    userEntity.getLastName(),
                    userEntity.getRut(),
                    userEntity.getPassword(),
                    userEntity.getEmail(),
                    userEntity.getRefreshToken(),
                    userEntity.getRefreshTokenExpiration(),
                    userEntity.getRole().toString(),
                    userEntity.getId()
            );
        }
        return userEntity;
    }

    @Override
    public int updateRefreshToken(Long userId, String refreshToken, Long expiration) {
        String sql = "UPDATE users SET refresh_token = ?, refresh_token_expiration = ? WHERE id = ?";
        return jdbcTemplate.update(sql, refreshToken, expiration, userId);
    }

    @Override
    public int clearRefreshToken(Long userId) {
        String sql = "UPDATE users SET refresh_token = NULL, refresh_token_expiration = NULL WHERE id = ?";
        return jdbcTemplate.update(sql, userId);
    }

}