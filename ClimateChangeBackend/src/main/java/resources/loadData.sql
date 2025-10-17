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
                                                                                                  (29.5, '2023-07-18 10:45:00+00', 1, 1);    -- Temperatura