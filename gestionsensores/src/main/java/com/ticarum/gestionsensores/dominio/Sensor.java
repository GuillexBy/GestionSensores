package com.ticarum.gestionsensores.dominio;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Sensor {
	
	@Id
	@GeneratedValue
	private Long id;
	private TipoSensor tipo;
	private double valor;
	
	@OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Historial> historico;
	
	public Sensor() {}
	
	public Sensor(TipoSensor tipo) {
		this.tipo = tipo;
		this.valor = 0;
		this.historico = new ArrayList<Historial>();
	}

	public Long getId() {
		return this.id;
	}

	public TipoSensor getTipo() {
		return tipo;
	}

	public double getValor() {
		return valor;
	}

	public List<Historial> getHistorico() {
		return historico;
	}
	
}
