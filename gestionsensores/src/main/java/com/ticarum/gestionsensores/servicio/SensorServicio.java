package com.ticarum.gestionsensores.servicio;

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
	public Sensor registroSensor() {
		
		return null;
	}

	@Override
	public List<Sensor> listaSensores() {
		// TODO Auto-generated method stub
		return null;
	}
}
