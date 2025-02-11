package com.ticarum.gestionsensores.controlador;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticarum.gestionsensores.dominio.Historial;
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
	
	@GetMapping("/{id}")
	public ResponseEntity<String> obtenerSensor(@PathVariable("id") Long id){
		double response = servicioSensor.obtenerSensor(id);
		return ResponseEntity.ok().body("El valor del sensor consultado es: " + response);
	}
	
	@GetMapping("/{id}/media/{fechaInicio}/{fechaFin}")
	public ResponseEntity<String> calcularMedia(@PathVariable("id") Long id, @PathVariable("fechaInicio") LocalDate fechaI, @PathVariable("fechaFin") LocalDate fechaF){
		double media = servicioSensor.calcularMedia(id, fechaI, fechaF);
		return ResponseEntity.ok().body("El valor medio registrado entre las fechas "+fechaI+" y "+fechaF+" es: "+ media);
	}
	
	@GetMapping("/{id}/histórico")
	public ResponseEntity<List<Historial>> obtenerHistorial(@PathVariable("id") Long id){
		return ResponseEntity.ok(servicioSensor.obtenerHistorial(id));
	}
}
