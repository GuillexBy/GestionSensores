package com.ticarum.gestionsensores;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ticarum.gestionsensores.dominio.Sensor;
import com.ticarum.gestionsensores.dominio.TipoSensor;
import com.ticarum.gestionsensores.excepciones.GenericDatabaseException;
import com.ticarum.gestionsensores.repositorio.ISensorRepositorio;
import com.ticarum.gestionsensores.servicio.SensorServicio;

class prueba {

	@Mock
	private ISensorRepositorio repositorio;

	@InjectMocks
	private SensorServicio servicio;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testRegistroSensor_CuandoNoExistenSensores() throws GenericDatabaseException {
		// Simular que no hay sensores registrados
		when(repositorio.getAll()).thenReturn(Collections.emptyList());

		when(repositorio.create(any(Sensor.class))).thenAnswer(invocation -> invocation.getArgument(0));

		// Ejecutar el método
		List<TipoSensor> resultado = servicio.registroSensor();

		// Verificar que se crearon todos los sensores
		assertEquals(TipoSensor.values().length, resultado.size());

		// Verificar que se llama al repositorio para guardar sensores
		verify(repositorio, times(TipoSensor.values().length)).create(any(Sensor.class));
	}

}
