package com.ticarum.gestionsensores.servicio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ticarum.gestionsensores.dominio.Historial;
import com.ticarum.gestionsensores.dominio.Sensor;
import com.ticarum.gestionsensores.dominio.TipoSensor;
import com.ticarum.gestionsensores.excepciones.GenericDatabaseException;
import com.ticarum.gestionsensores.excepciones.HistorialNotFoundException;
import com.ticarum.gestionsensores.excepciones.SensorNotFoundException;
import com.ticarum.gestionsensores.repositorio.ISensorRepositorio;

class SensorServicioTest {

	@Mock
	private ISensorRepositorio repositorio;

	@InjectMocks
	private SensorServicio servicio;
	
	private Sensor sensor1;
	private Sensor sensor2;
	private List<Sensor> sensoresTest2;
	private List<Sensor> sensoresTest3;
	private List<Historial> historial;
	private Long idSensor;
	private LocalDate fechaI;
	private LocalDate fechaF;
	private double media;
	private List<Historial> historialVacio;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		sensor1 = new Sensor(TipoSensor.TEMPERATURA);
		sensor2 = new Sensor(TipoSensor.TEMPERATURA);
		sensoresTest2 = List.of(new Sensor(TipoSensor.TEMPERATURA), new Sensor(TipoSensor.VELOCIDAD_VIENTO));
		sensoresTest3 = List.of(new Sensor(TipoSensor.TEMPERATURA),new Sensor(TipoSensor.HUMEDAD), new Sensor(TipoSensor.PRESION), new Sensor(TipoSensor.VELOCIDAD_VIENTO));
		idSensor = 1L;
		historial = new ArrayList<Historial>();
		historial.add(new Historial(25.6, sensor2));
		historial.add(new Historial(16.8, sensor2));
		media = (25.6+16.8)/2;
		sensor2.setHistorico(historial);
		fechaI = LocalDate.of(2025, 01, 01);
		fechaF = LocalDate.of(2025, 02, 01);
		historialVacio = new ArrayList<Historial>();
	}

	@Test
	void testRegistroSensor_WhenAllSensoresAdded() throws GenericDatabaseException {
		when(repositorio.getAll()).thenReturn(Collections.emptyList());

		when(repositorio.create(any(Sensor.class))).thenAnswer(invocation -> invocation.getArgument(0));

		List<TipoSensor> resultado = servicio.registroSensor();

		assertEquals(TipoSensor.values().length, resultado.size());

		verify(repositorio, times(TipoSensor.values().length)).create(any(Sensor.class));
	}
	
	@Test
	void testRegistroSensor_WhenSomeSensoresExist() throws GenericDatabaseException {
		when(repositorio.getAll()).thenReturn(sensoresTest2);
		
		when(repositorio.create(any(Sensor.class))).thenAnswer(invocation -> invocation.getArgument(0));
		
		List<TipoSensor> resultado = servicio.registroSensor();
		
		assertEquals(TipoSensor.values().length - sensoresTest2.size(), resultado.size());
		
		verify(repositorio, times(TipoSensor.values().length - sensoresTest2.size())).create(any(Sensor.class));
	}
	
	@Test
	void testRegistroSensor_WhenAllSensoresExist() throws GenericDatabaseException {
		when(repositorio.getAll()).thenReturn(sensoresTest3);
		
		List<TipoSensor> resultado = servicio.registroSensor();
		
		assertEquals(resultado.size(), 0);
		
		verify(repositorio, never()).create(any(Sensor.class));		
	}
	
	@Test
	void testRegistroSensor_GenericDatabaseExceptionGetAllFails() throws GenericDatabaseException {
		when(repositorio.getAll()).thenThrow( new GenericDatabaseException());
		
		assertThrows(GenericDatabaseException.class, () -> servicio.registroSensor());
	}
	
	@Test
	void testRegistroSensores_GenericDatabaseExceptionCreateFails() throws GenericDatabaseException {
		when(repositorio.getAll()).thenReturn(sensoresTest2);
		when(repositorio.create(any(Sensor.class))).thenThrow( new GenericDatabaseException());
		
		assertThrows(GenericDatabaseException.class, () -> servicio.registroSensor());
	}
	
	@Test
	void testListaSensores_WhenSensoresDontExist() throws GenericDatabaseException {
		when(repositorio.getAll()).thenReturn(Collections.emptyList());
		
		List<Sensor> sensores = repositorio.getAll();
		
		assertNotNull(sensores);
		assertTrue(sensores.isEmpty());
	}
	
	@Test
	void testListaSensores_WhenSensoresExist() throws GenericDatabaseException {
		when(repositorio.getAll()).thenReturn(sensoresTest2);
		
		List<Sensor> sensores = repositorio.getAll();
		
		assertEquals(sensoresTest2.size(), sensores.size());
		assertEquals(sensores, sensoresTest2);
	}

	@Test
	void testListaSensores_GenericDatabaseExceptionGetAllFails() throws GenericDatabaseException {
		when(repositorio.getAll()).thenThrow( new GenericDatabaseException());
		
		assertThrows(GenericDatabaseException.class, () -> servicio.registroSensor());
	}
	
	@Test
	void testBorrarSensor_WhenSensorExists() throws GenericDatabaseException, SensorNotFoundException {
		when(repositorio.delete(idSensor)).thenReturn(true);
		
		boolean resultado = servicio.borrarSensor(idSensor);
		
		assertTrue(resultado);
		verify(repositorio, times(1)).delete(idSensor);
	}
	
	@Test
	void testBorrarSensor_WhenSensorDoesntExists() throws GenericDatabaseException, SensorNotFoundException {
		when(repositorio.delete(idSensor)).thenThrow( new SensorNotFoundException(idSensor));
		
		assertThrows(SensorNotFoundException.class, () -> servicio.borrarSensor(idSensor));
		verify(repositorio, times(1)).delete(idSensor);
	}
	
	@Test
	void testBorrarSensor_GenericDatabaseExceptionDeleteFails() throws GenericDatabaseException, SensorNotFoundException {
		when(repositorio.delete(idSensor)).thenThrow( new GenericDatabaseException());
		
		assertThrows(GenericDatabaseException.class, () -> servicio.borrarSensor(idSensor));
	}
	
	@Test
	void testObtenerSensor_WhenSensorExists() throws GenericDatabaseException, SensorNotFoundException {
		when(repositorio.get(idSensor)).thenReturn(sensor1);
		
		double resultado = servicio.obtenerSensor(idSensor);
		assertNotEquals(resultado, 0);
		assertFalse(sensor1.getHistorico().isEmpty());
		assertEquals(resultado, sensor1.getHistorico().get(0).getValor());
		
		verify(repositorio, times(1)).update(sensor1);
		
	}
	
	@Test
	void testObtenerSensor_WhenSensorDoesntExists() throws SensorNotFoundException, GenericDatabaseException {
		when(repositorio.get(idSensor)).thenThrow( new SensorNotFoundException(idSensor));
		
		assertThrows(SensorNotFoundException.class, () -> servicio.obtenerSensor(idSensor));
		
		verify(repositorio, never()).update(any(Sensor.class));
	}
	
	@Test
	void testObtenerSensor_GenericDatabaseExceptionGetFails() throws SensorNotFoundException, GenericDatabaseException {
		when(repositorio.get(idSensor)).thenThrow( new GenericDatabaseException());
		
		assertThrows(GenericDatabaseException.class, () -> servicio.obtenerSensor(idSensor));
		
		verify(repositorio, never()).update(any(Sensor.class));
	}
	
	@Test
	void testObtenerSensor_GenericDatabaseExceptionUpdateFails() throws SensorNotFoundException, GenericDatabaseException {
		when(repositorio.get(idSensor)).thenReturn(sensor1);
		when(repositorio.update(sensor1)).thenThrow(new GenericDatabaseException());
		
		assertThrows(GenericDatabaseException.class, () -> servicio.obtenerSensor(idSensor));
	}
	
	@Test
	void testObtenerHistorial_WhenHistorialExists() throws SensorNotFoundException, GenericDatabaseException {
		when(repositorio.get(idSensor)).thenReturn(sensor2);
		
		assertEquals(servicio.obtenerHistorial(idSensor).size(), sensor2.getHistorico().size());
		assertEquals(servicio.obtenerHistorial(idSensor), sensor2.getHistorico());
	}
	
	@Test
	void testObtenerHistorial_WhenHistorialDoesntExists() throws SensorNotFoundException, GenericDatabaseException {
		when(repositorio.get(idSensor)).thenReturn(sensor1);
		
		List<Historial> resultado = servicio.obtenerHistorial(idSensor);
		
		assertTrue(resultado.isEmpty());
	}
	
	@Test
	void testObtenerHistorial_SensorNotFoundException() throws SensorNotFoundException, GenericDatabaseException {
		when(repositorio.get(idSensor)).thenThrow( new SensorNotFoundException(idSensor));
		
		assertThrows(SensorNotFoundException.class, () -> servicio.obtenerHistorial(idSensor));
	}
	
	@Test
	void testObtenerHistorial_GenericDatabaseException() throws SensorNotFoundException, GenericDatabaseException {
		when(repositorio.get(idSensor)).thenThrow( new GenericDatabaseException());
		
		assertThrows(GenericDatabaseException.class, () -> servicio.obtenerHistorial(idSensor));
	}
	
	@Test
	void testCalcularMedia_WhenHistorialHasData() throws GenericDatabaseException, HistorialNotFoundException {
		when(repositorio.getHistorial(idSensor, fechaI, fechaF)).thenReturn(historial);
		
		double resultado = servicio.calcularMedia(idSensor, fechaI, fechaF);
		
		assertEquals(resultado, media);
		
		verify(repositorio, times(1)).getHistorial(idSensor, fechaI, fechaF);
	}
	
	@Test
	void testCalcularMedia_HistorialNotFoundExceptio() throws GenericDatabaseException, HistorialNotFoundException {
		when(repositorio.getHistorial(idSensor, fechaI, fechaF)).thenReturn(historialVacio);
		
		assertThrows(HistorialNotFoundException.class, () -> servicio.calcularMedia(idSensor, fechaI, fechaF));
	}
	
	@Test
	void testCalcularMedia_GenericDataExceptionGetHistorialFails() throws GenericDatabaseException, HistorialNotFoundException {
		when(repositorio.getHistorial(idSensor, fechaI, fechaF)).thenThrow( new GenericDatabaseException());
		
		assertThrows(GenericDatabaseException.class, () -> servicio.calcularMedia(idSensor, fechaI, fechaF));
	}
}
