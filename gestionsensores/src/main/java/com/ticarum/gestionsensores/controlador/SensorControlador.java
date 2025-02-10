package com.ticarum.gestionsensores.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticarum.gestionsensores.dominio.Sensor;
import com.ticarum.gestionsensores.servicio.ISensorServicio;

@RestController
@RequestMapping("/sensores")
public class SensorControlador {

	@Autowired
	private ISensorServicio servicioSensor;
	
	
	
	@PostMapping
	public ResponseEntity<?> registrarSensores(){
		try {
			servicioSensor.registroSensor();
			return ResponseEntity.ok().body("Sensores creados");
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Sensor>> getSensores() {
		return ResponseEntity.ok(servicioSensor.listaSensores());
	}
}
