package com.ticarum.gestionsensores.repositorio;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ticarum.gestionsensores.dominio.Historial;
import com.ticarum.gestionsensores.dominio.Sensor;
import com.ticarum.gestionsensores.excepciones.GenericDatabaseException;
import com.ticarum.gestionsensores.excepciones.SensorNotFoundException;

@Repository
public class SensorRepositorio implements ISensorRepositorio{
	
	@Autowired
	private RepositorioJPA repositorio;
	@Autowired
	private RepositorioHistorialJpa repositorioH;

	@Override
	public List<Sensor> getAll() throws GenericDatabaseException {
		try {
			return repositorio.findAll();
		} catch (Exception e) {
			throw new GenericDatabaseException();
		}
	}

	@Override
	public Sensor create(Sensor sensor) throws GenericDatabaseException {
		try {
			return repositorio.save(sensor);
		} catch (Exception e) {
			throw new GenericDatabaseException();
		}
	}
	
	@Override
	public boolean delete(Long id) throws SensorNotFoundException, GenericDatabaseException {
		try {
			repositorio.findById(id).orElseThrow(() -> new SensorNotFoundException(id));
			repositorio.deleteById(id);
			return true;
		} catch (SensorNotFoundException e) {
			throw new SensorNotFoundException(id);
		} catch (Exception e) {
			throw new GenericDatabaseException();
		}
	}

	@Override
	public Sensor get(Long id) throws SensorNotFoundException, GenericDatabaseException {
			try {
				return repositorio.findById(id).orElseThrow(() -> new SensorNotFoundException(id));
			} catch (SensorNotFoundException e) {
				throw new SensorNotFoundException(id);
			} catch (Exception e) {
				throw new GenericDatabaseException();
			}
	}

	@Override
	public Sensor update(Sensor sensor) throws GenericDatabaseException {
		try {
			return repositorio.save(sensor);
		} catch (Exception e) {
			throw new GenericDatabaseException();
		}
	}
	
	@Override
	public List<Historial> getHistorial(Long id, LocalDate fechaI, LocalDate fechaF) throws GenericDatabaseException {
		try {
			return repositorioH.findBySensor_IdAndFechaBetween(id, fechaI, fechaF);
		} catch (Exception e) {
			throw new GenericDatabaseException();
		}
	}
	
}
