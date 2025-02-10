package com.ticarum.gestionsensores.servicio;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticarum.gestionsensores.dominio.Sensor;
import com.ticarum.gestionsensores.dominio.TipoSensor;
import com.ticarum.gestionsensores.repositorio.ISensorRepositorio;

@Service
public class SensorServicio implements ISensorServicio{

	@Autowired
	private ISensorRepositorio repositorio; 

	@Override
	public List<TipoSensor> registroSensor() {
		List<Sensor> sensores = repositorio.getAll();
		LinkedList<TipoSensor> listaSensores = new LinkedList<TipoSensor>();
//		LinkedList<Sensor> sensores = new LinkedList<Sensor>();
		for (TipoSensor tipo : TipoSensor.values()) {
//			Sensor sensor = new Sensor(tipo);
			if (!sensores.stream().anyMatch(s -> s.getTipo().equals(tipo))) {
				Sensor response = repositorio.create(new Sensor(tipo));
				System.out.println(response.getTipo());
//				sensores.add(response);
				listaSensores.add(response.getTipo());	
			}
		}
		return listaSensores;
	}

	@Override
	public List<Sensor> listaSensores() {
		// TODO Auto-generated method stub
		List<Sensor> sensores = repositorio.getAll();
		System.out.println("Lista de Sensores registrados: " + sensores);
		return sensores;
	}

	@Override
	public boolean borrarSensor(Long id) {
		return repositorio.delete(id);
	}
	
	
}
