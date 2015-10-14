package es.securitasdirect.moduloweb.model;
/**
 * 
 * @author JAS
 *	Modelo de Cuotas de Instalación.
 *	Una instalación solo está relacionada con un Resumen de Cuotas.
 */
public class Cuote {

    /**
	 * Cuota del mes actual.
	 */
	private String coutaMesActual;
	private String fechaActual;
	/**
	 * Cuota del mes de enero del año actual.
	 */
	private String cuotaEneroActual;
	private String fechaEneroActual;
	/**
	 * Couta del mes de enero del año pasado.
	 */
	private String cuotaEneroPasado;
	private String fechaEneroPasado;
	
	public Cuote() {}
	
	public String getCoutaMesActual() {
		return coutaMesActual;
	}
	public void setCoutaMesActual(String coutaMesActual) {
		this.coutaMesActual = coutaMesActual;
	}
	public String getCuotaEneroActual() {
		return cuotaEneroActual;
	}
	public void setCuotaEneroActual(String cuotaEneroActual) {
		this.cuotaEneroActual = cuotaEneroActual;
	}
	public String getCuotaEneroPasado() {
		return cuotaEneroPasado;
	}
	public void setCuotaEneroPasado(String cuotaEneroPasado) {
		this.cuotaEneroPasado = cuotaEneroPasado;
	}

	public String getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(String fechaActual) {
		this.fechaActual = fechaActual;
	}

	public String getFechaEneroActual() {
		return fechaEneroActual;
	}

	public void setFechaEneroActual(String fechaEneroActual) {
		this.fechaEneroActual = fechaEneroActual;
	}

	public String getFechaEneroPasado() {
		return fechaEneroPasado;
	}

	public void setFechaEneroPasado(String fechaEneroPasado) {
		this.fechaEneroPasado = fechaEneroPasado;
	}

}
