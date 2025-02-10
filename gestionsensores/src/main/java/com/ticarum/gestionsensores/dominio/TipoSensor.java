package com.ticarum.gestionsensores.dominio;

public enum TipoSensor {
	TEMPERATURA("ºC"),
	HUMEDAD("%"),
	PRESION("hPa"),
	VELOCIDAD_VIENTO("m/s");
	
	private String magnitud;
	
	private TipoSensor(String magnitud) {
		this.magnitud = magnitud; 
	}

	public String getMagnitud() {
		return magnitud;
	}
	
}
