package com.ticarum.gestionsensores.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticarum.gestionsensores.dominio.Sensor;

@Repository
public interface RepositorioJPA extends JpaRepository<Sensor, Long> {

}
