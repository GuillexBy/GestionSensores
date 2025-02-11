package com.ticarum.gestionsensores.repositorio;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ticarum.gestionsensores.dominio.Historial;
import com.ticarum.gestionsensores.dominio.Sensor;

@Repository
public class SensorRepositorio implements ISensorRepositorio{
	
	@Autowired
	private RepositorioJPA repositorio;
	@Autowired
	private RepositorioHistorialJpa repositorioH;

	@Override
	public List<Sensor> getAll() {
		return repositorio.findAll();
	}

	@Override
	public Sensor create(Sensor sensor) {
		return repositorio.save(sensor);
		
	}
	
	@Override
	public boolean delete(Long id) {
//		repositorio.deleteById(id);
		if (!repositorio.findById(id).isEmpty()) {
			repositorio.deleteById(id);
			return true;
		}
		else return false;
	}

	@Override
	public Sensor get(Long id) {
		return repositorio.findById(id).orElse(null);
	}

	@Override
	public Sensor update(Sensor sensor) {
		return repositorio.save(sensor);
	}
	
	@Override
	public List<Historial> getHistorial(Long id, LocalDate fechaI, LocalDate fechaF) {
		return repositorioH.findBySensor_IdAndFechaBetween(id, fechaI, fechaF);
	}
	
}
