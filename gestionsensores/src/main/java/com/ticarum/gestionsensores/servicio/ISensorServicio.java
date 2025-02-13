package com.ticarum.gestionsensores.servicio;

import java.time.LocalDate;
import java.util.List;

import com.ticarum.gestionsensores.dominio.Historial;
import com.ticarum.gestionsensores.dominio.Sensor;
import com.ticarum.gestionsensores.dominio.TipoSensor;
import com.ticarum.gestionsensores.excepciones.GenericDatabaseException;
import com.ticarum.gestionsensores.excepciones.HistorialNotFoundException;
import com.ticarum.gestionsensores.excepciones.SensorNotFoundException;

public interface ISensorServicio {

	List<TipoSensor> registroSensor() throws GenericDatabaseException;
	List<Sensor> listaSensores() throws GenericDatabaseException;
	boolean borrarSensor(Long id) throws GenericDatabaseException, SensorNotFoundException;
	double obtenerSensor(Long id) throws SensorNotFoundException, GenericDatabaseException;
	List<Historial> obtenerHistorial(Long id) throws SensorNotFoundException, GenericDatabaseException;
	double calcularMedia(Long id, LocalDate fechaI, LocalDate fechaF) throws GenericDatabaseException, HistorialNotFoundException;
}
