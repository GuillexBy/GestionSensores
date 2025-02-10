package com.ticarum.gestionsensores.dominio;

public enum TipoSensor {
	TEMPERATURA("ºC", -90, 57), 			// De -90ºC a 57ºC
	HUMEDAD("%", 0, 100), 				// De 0% a 100%
	PRESION("hPa", 870, 1081), 			// De 870hPa a 1081hPa 
	VELOCIDAD_VIENTO("m/s", 0, 104); 	// De 0m/s a 104m/s
	
	private String magnitud;
	private double min;
	private double max;
	
	private TipoSensor(String magnitud, double min, double max) {
		this.magnitud = magnitud; 
		this.min = min;
		this.max = max;
	}

	public String getMagnitud() {
		return magnitud;
	}
	
	public double getMin() {
		return min;
	}
	
	public double getMax() {
		return max;
	}
	
}
