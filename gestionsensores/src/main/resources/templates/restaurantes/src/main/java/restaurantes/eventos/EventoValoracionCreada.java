package restaurantes.eventos;

import restaurantes.modelo.ResumenOpinion;
import restaurantes.modelo.Valoracion;

public class EventoValoracionCreada {

	private String idOpinion;
	private Valoracion valoracion;
	private ResumenOpinion resumenOpinion;

	public String getIdOpinion() {
		return idOpinion;
	}

	public void setIdOpinion(String idOpinion) {
		this.idOpinion = idOpinion;
	}

	public Valoracion getValoracion() {
		return valoracion;
	}

	public void setValoracion(Valoracion valoracion) {
		this.valoracion = valoracion;
	}

	public ResumenOpinion getResumenOpinion() {
		return resumenOpinion;
	}

	public void setResumenOpinion(ResumenOpinion resumenOpinion) {
		this.resumenOpinion = resumenOpinion;
	}
}
