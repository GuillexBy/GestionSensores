package com.ticarum.gestionsensores.servicio;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticarum.gestionsensores.dominio.Historial;
import com.ticarum.gestionsensores.dominio.Sensor;
import com.ticarum.gestionsensores.dominio.TipoSensor;
import com.ticarum.gestionsensores.excepciones.GenericDatabaseException;
import com.ticarum.gestionsensores.excepciones.HistorialNotFoundException;
import com.ticarum.gestionsensores.excepciones.SensorNotFoundException;
import com.ticarum.gestionsensores.repositorio.ISensorRepositorio;

@Service
public class SensorServicio implements ISensorServicio{

	@Autowired
	private ISensorRepositorio repositorio; 

	@Override
	public List<TipoSensor> registroSensor() throws GenericDatabaseException {
		List<Sensor> sensores = repositorio.getAll();
		LinkedList<TipoSensor> listaSensores = new LinkedList<TipoSensor>();
//		LinkedList<Sensor> sensores = new LinkedList<Sensor>();
		for (TipoSensor tipo : TipoSensor.values()) {
//			Sensor sensor = new Sensor(tipo);
			if (!sensores.stream().anyMatch(s -> s.getTipo().equals(tipo))) {
				Sensor response = repositorio.create(new Sensor(tipo));
				//System.out.println(response.getTipo());
//				sensores.add(response);
				listaSensores.add(response.getTipo());	
			}
		}
		return listaSensores;
	}

	@Override
	public List<Sensor> listaSensores() throws GenericDatabaseException {
		// TODO Auto-generated method stub
		List<Sensor> sensores = repositorio.getAll();
		System.out.println("Lista de Sensores registrados: " + sensores);
		return sensores;
	}

	@Override
	public boolean borrarSensor(Long id) throws GenericDatabaseException, SensorNotFoundException {
		return repositorio.delete(id);
	}

	@Override
	public double obtenerSensor(Long id) throws SensorNotFoundException, GenericDatabaseException {
		Sensor sensor = repositorio.get(id);
		System.out.println(sensor);
		double valor = generarValor(sensor.getTipo());
		sensor.getHistorico().add(new Historial(valor, sensor));
		repositorio.update(sensor);
		return valor;
	}
	
	@Override
	public List<Historial> obtenerHistorial(Long id) throws SensorNotFoundException, GenericDatabaseException {
		return repositorio.get(id).getHistorico();
	}
	
	@Override
	public double calcularMedia(Long id, LocalDate fechaI, LocalDate fechaF) throws GenericDatabaseException, HistorialNotFoundException {
		List<Historial>historico = repositorio.getHistorial(id, fechaI, fechaF);
		System.out.println(historico);
		double media = 0.0;
		if(historico.isEmpty()) throw new HistorialNotFoundException(id);
		for (Historial historial : historico) {
			media += historial.getValor();
		}
		media = media / historico.size();
		return media;
	}
	
	private double generarValor(TipoSensor tipo) {
		double valor = new Random().nextDouble((tipo.getMax()-tipo.getMin()+1)+tipo.getMin());
		return valor;
	}
	
}
