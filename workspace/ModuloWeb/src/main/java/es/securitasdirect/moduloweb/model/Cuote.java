package es.securitasdirect.moduloweb.model;
/**
 * 
 * @author JAS
 *	Modelo de Cuotas de Instalación.
 *	Una instalación solo está relacionada con un Resumen de Cuotas.
 */
public class Cuote {

    /**
     * clave autonumerica para la tabla
     */
    private Integer id;
	/** 
	 * Número de instalación
	 */
    private Integer installationNumber;
	/**
	 * Cuota del mes actual.
	 */
	private Double mesActual;
	/**
	 * Cuota del mes de enero del año actual.
	 */
	private Double eneroActual;
	/**
	 * Couta del mes de enero del año pasado.
	 */
	private Double eneroPasado1;


    //constructor copia de la clase Cuote
    /*
    public Cuote(final org.wso2.ws.dataservice.GetCuoteResult getCuoteResult) {
        this.id = getCuoteResult.getId().intValue();
        this.installationNumber = getCuoteResult.geInstallationNumber();
        this.mesActual = getCuoteResult.getMesActual();
        this.eneroActual = getCuoteResult.getEneroActual();
        this.eneroPasado1 = getCuoteResult.getEneroPasado1();
    }
    */


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	
	public Integer getInstallationNumber() {
		return installationNumber;
	}
	public void setInstallationNumber(Integer installationNumber) {
		this.installationNumber = installationNumber;
	}
	public Double getMesActual() {
		return mesActual;
	}
	public void setMesActual(Double mesActual) {
		this.mesActual = mesActual;
	}
	public Double getEneroActual() {
		return eneroActual;
	}
	public void setEneroActual(Double eneroActual) {
		this.eneroActual = eneroActual;
	}
	public Double getEneroPasado1() {
		return eneroPasado1;
	}
	public void setEneroPasado1(Double eneroPasado1) {
		this.eneroPasado1 = eneroPasado1;
	}
	
	
}
