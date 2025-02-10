package com.ticarum.gestionsensores.repositorio;

import java.util.List;


import com.ticarum.gestionsensores.dominio.Sensor;

public interface ISensorRepositorio {

	List<Sensor> getAll();
	Sensor create(Sensor sensor);
	boolean delete(Long id);
	}
