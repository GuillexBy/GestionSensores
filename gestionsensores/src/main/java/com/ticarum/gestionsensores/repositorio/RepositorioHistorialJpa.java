package com.ticarum.gestionsensores.repositorio;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticarum.gestionsensores.dominio.Historial;

@Repository
public interface RepositorioHistorialJpa extends JpaRepository<Historial, Long> {

	List<Historial>findBySensor_IdAndFechaBetween(Long id, LocalDate fechaI, LocalDate fechaF);
}
