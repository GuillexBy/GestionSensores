package com.ticarum.gestionsensores.servicio;

import java.util.List;

import com.ticarum.gestionsensores.dominio.Sensor;
import com.ticarum.gestionsensores.dominio.TipoSensor;

public interface ISensorServicio {

	Sensor registroSensor();
	List<Sensor> listaSensores();
}
