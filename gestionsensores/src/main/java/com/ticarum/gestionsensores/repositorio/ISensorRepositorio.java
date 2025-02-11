package com.ticarum.gestionsensores.repositorio;

import java.time.LocalDate;
import java.util.List;

import com.ticarum.gestionsensores.dominio.Historial;
import com.ticarum.gestionsensores.dominio.Sensor;

public interface ISensorRepositorio {

	List<Sensor> getAll();
	Sensor create(Sensor sensor);
	boolean delete(Long id);
	Sensor get(Long id);
	Sensor update(Sensor sensor);
	List<Historial> getHistorial(Long id, LocalDate fechaI, LocalDate fechaF);
	}
