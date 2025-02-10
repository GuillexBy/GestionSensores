package com.ticarum.gestionsensores.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticarum.gestionsensores.dominio.Sensor;
import com.ticarum.gestionsensores.dominio.TipoSensor;
import com.ticarum.gestionsensores.servicio.ISensorServicio;

@RestController
@RequestMapping("/sensores")
public class SensorControlador {

	@Autowired
	private ISensorServicio servicioSensor;
	
	
	
	@PostMapping
	public ResponseEntity<?> registrarSensores(){
		try {
			List<TipoSensor> sensores = servicioSensor.registroSensor();
			if (sensores.isEmpty()) {
				return ResponseEntity.badRequest().body("Todos los sensores ya han sido registrados");
			}
			return ResponseEntity.ok().body("Sensores creados:" + sensores + "\nLos sensores restantes ya han sido registrados previamente");
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Sensor>> obtenerSensores() {
		return ResponseEntity.ok(servicioSensor.listaSensores());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> borrarSensor(@PathVariable("id") Long id){
		if(servicioSensor.borrarSensor(id)) {
			return ResponseEntity.ok().body("Sensor borrado correctamente");
		}
		return ResponseEntity.notFound().build();
	}
}
