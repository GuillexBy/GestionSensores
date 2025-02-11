package com.ticarum.gestionsensores.servicio;

import java.time.LocalDate;
import java.util.List;

import com.ticarum.gestionsensores.dominio.Historial;
import com.ticarum.gestionsensores.dominio.Sensor;
import com.ticarum.gestionsensores.dominio.TipoSensor;

public interface ISensorServicio {

	List<TipoSensor> registroSensor();
	List<Sensor> listaSensores();
	boolean borrarSensor(Long id);
	double obtenerSensor(Long id);
	List<Historial> obtenerHistorial(Long id);
	double calcularMedia(Long id, LocalDate fechaI, LocalDate fechaF);
}
