package es.securitasdirect.moduloweb.web.dto.request;

import es.securitasdirect.moduloweb.model.Agent;

/**
 * 
 * @author JAS
 *	Clase para recibir parametros de búsqueda de instalación
 *	@param installationNumber
 * 	@param agent
 */
public class InstallationRequest extends AgentRequest{
	/**
	 * Búsqueda por installationNumber
	 * */
	private String installationNumber;
	
	
	public InstallationRequest(String installationNumber) {
		super();
		this.installationNumber = installationNumber;
	}
	
	public InstallationRequest(){}
	
	public String getInstallationNumber() {
		return installationNumber;
	}

	public void setInstallationNumber(String installationNumber) {
		this.installationNumber = installationNumber;
	}


	@Override
	public String toString() {
		return "InstallationRequest [installationNumber=" + installationNumber + "]";
	}

}
