package es.securitasdirect.moduloweb.web.dto.request;

import es.securitasdirect.moduloweb.model.InstallationData;

/**
 * 
 * @author JAS
 *	Clase para recibir parametros de búsqueda de instalación
 *	@param installationNumber
 * 	@param phone
 * 	@param email
 * 	@param installationActive
 */
public class SearchInstallationRequest {
	/**
	 * Búsqueda por installationNumber
	 * */
	private String installationNumber;
	/**
	 * Búsqueda por teléfono
	 * */
	private String phone;
	/**
	 * Búsqueda por email
	 * */
	private String email;
	/**
	 * Instalación activa
	 */
	private InstallationData installationActive;
	
	public SearchInstallationRequest(String installationNumber, String phone, String email, InstallationData installationActive) {
		super();
		this.installationNumber = installationNumber;
		this.phone = phone;
		this.email = email;
		this.installationActive=installationActive;
	}
	
	public SearchInstallationRequest(){}
	
	public String getInstallationNumber() {
		return installationNumber;
	}

	public void setInstallationNumber(String installationNumber) {
		this.installationNumber = installationNumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public InstallationData getInstallationActive() {
		return installationActive;
	}

	public void setInstallationActive(InstallationData installationActive) {
		this.installationActive = installationActive;
	}

	@Override
	public String toString() {
		return "SearchInstallationRequest [installationNumber=" + installationNumber + ", phone=" + phone + ", email="
				+ email + ", installationActive=" + installationActive + "]";
	}

}
