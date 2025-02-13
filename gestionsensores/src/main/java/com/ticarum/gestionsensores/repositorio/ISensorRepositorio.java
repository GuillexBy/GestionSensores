package com.ticarum.gestionsensores.repositorio;

import java.time.LocalDate;
import java.util.List;

import com.ticarum.gestionsensores.dominio.Historial;
import com.ticarum.gestionsensores.dominio.Sensor;
import com.ticarum.gestionsensores.excepciones.GenericDatabaseException;
import com.ticarum.gestionsensores.excepciones.SensorNotFoundException;

public interface ISensorRepositorio {

	List<Sensor> getAll() throws GenericDatabaseException;
	Sensor create(Sensor sensor) throws GenericDatabaseException;
	boolean delete(Long id) throws GenericDatabaseException, SensorNotFoundException;
	Sensor get(Long id) throws SensorNotFoundException, GenericDatabaseException;
	Sensor update(Sensor sensor) throws GenericDatabaseException;
	List<Historial> getHistorial(Long id, LocalDate fechaI, LocalDate fechaF) throws GenericDatabaseException;
	}
