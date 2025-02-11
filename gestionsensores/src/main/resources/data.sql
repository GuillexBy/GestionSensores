ALTER TABLE SENSOR ALTER COLUMN id INT AUTO_INCREMENT;
ALTER TABLE HISTORIAL ALTER COLUMN id INT AUTO_INCREMENT;


-- Insertar Sensores
INSERT INTO SENSOR (tipo, magnitud, valor) VALUES ('PRESION', 'hPa', 1013.25);
INSERT INTO SENSOR (tipo, magnitud, valor) VALUES ('HUMEDAD', '%', 65.0);

-- Insertar Historial para Sensor de Presión (ID = 1)
INSERT INTO HISTORIAL (sensor_id, fecha, valor) VALUES (1, '2025-01-20', 1052.0);
INSERT INTO HISTORIAL (sensor_id, fecha, valor) VALUES (1, '2024-12-25', 1015.5);
INSERT INTO HISTORIAL (sensor_id, fecha, valor) VALUES (1, '2024-11-11', 911.8);

-- Insertar Historial para Sensor de Humedad (ID = 2)
INSERT INTO HISTORIAL (sensor_id, fecha, valor) VALUES (2, '2025-01-20', 26.0);
INSERT INTO HISTORIAL (sensor_id, fecha, valor) VALUES (2, '2024-12-25', 55.5);
INSERT INTO HISTORIAL (sensor_id, fecha, valor) VALUES (2, '2024-11-11', 82.2);
