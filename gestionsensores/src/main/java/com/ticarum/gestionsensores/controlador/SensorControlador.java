package com.ticarum.gestionsensores.controlador;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticarum.gestionsensores.dominio.Sensor;
import com.ticarum.gestionsensores.dominio.TipoSensor;
import com.ticarum.gestionsensores.excepciones.GenericDatabaseException;
import com.ticarum.gestionsensores.excepciones.HistorialNotFoundException;
import com.ticarum.gestionsensores.excepciones.SensorNotFoundException;
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
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Todos los sensores ya han sido registrados");
			}
			return ResponseEntity.status(HttpStatus.CREATED).body("Sensores creados:" + sensores + "\nLos sensores restantes ya han sido registrados previamente");
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar los sensores: " + e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Sensor>> obtenerSensores() throws GenericDatabaseException {
		return ResponseEntity.ok(servicioSensor.listaSensores());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> borrarSensor(@PathVariable("id") Long id) throws GenericDatabaseException, SensorNotFoundException{
		if(id > 0) {
			servicioSensor.borrarSensor(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Sensor borrado correctamente");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El ID debe ser un número entero mayor que 0");
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<String> obtenerSensor(@PathVariable("id") Long id) throws SensorNotFoundException, GenericDatabaseException{
		if(id <= 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El ID debe ser un número entero mayor que 0");
		}
		double response = servicioSensor.obtenerSensor(id);
		return ResponseEntity.ok().body("El valor del sensor consultado es: " + response);
	}
	
	@GetMapping("/{id}/media/{fechaInicio}/{fechaFin}")
	public ResponseEntity<String> calcularMedia(@PathVariable("id") Long id, @PathVariable("fechaInicio") LocalDate fechaI, @PathVariable("fechaFin") LocalDate fechaF) throws GenericDatabaseException, HistorialNotFoundException{
		if(id <= 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El ID debe ser un número entero mayor que 0");
		}
		if(fechaI.isAfter(LocalDate.now()) && fechaF.isAfter(LocalDate.now()) && fechaI.isAfter(fechaF)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Las fechas indicadas no son correctas");
		}
		
		double media = servicioSensor.calcularMedia(id, fechaI, fechaF);
		return ResponseEntity.ok().body("El valor medio registrado entre las fechas "+fechaI+" y "+fechaF+" es: "+ media);
	}
	
	@GetMapping("/{id}/histórico")
	public ResponseEntity<?> obtenerHistorial(@PathVariable("id") Long id) throws SensorNotFoundException, GenericDatabaseException{
		if(id <= 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El ID debe ser un número entero mayor que 0");
		}
		return ResponseEntity.ok(servicioSensor.obtenerHistorial(id));
	}
}
