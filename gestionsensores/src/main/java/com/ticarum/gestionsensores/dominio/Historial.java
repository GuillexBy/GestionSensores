package com.ticarum.gestionsensores.dominio;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Historial {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate fecha;
	private double valor;
	
	@ManyToOne
	@JoinColumn(name = "sensor_id", nullable = false)
	private Sensor sensor;
	
	public Historial() {}
	
	public Historial(double valor, Sensor sensor) {
		this.fecha = LocalDate.now();
		this.valor = valor;
		this.sensor = sensor;
	}

	public Long getId() {
		return id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public double getValor() {
		return valor;
	}
	
	
}
