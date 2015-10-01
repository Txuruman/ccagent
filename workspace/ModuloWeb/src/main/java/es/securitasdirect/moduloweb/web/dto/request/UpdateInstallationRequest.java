package es.securitasdirect.moduloweb.web.dto.request;

import es.securitasdirect.moduloweb.model.CombinationsKeys;
import es.securitasdirect.moduloweb.model.InstallationData;

/**
 * 
 * @author JAS
 *	Clase para actualizar la instalacion
 *	@param installationData
 * 	
 */
public class UpdateInstallationRequest extends AgentRequest{
	private InstallationData installation;
	
	public UpdateInstallationRequest(InstallationData installation) {
		super();
		this.installation=installation;
	}
	
	public UpdateInstallationRequest() {
		super();
	}

	public InstallationData getInstallation() {
		return installation;
	}

	public void setInstallation(InstallationData installation) {
		this.installation = installation;
	}

	@Override
	public String toString() {
		return "UpdateInstallationRequest [installation=" + installation + "]";
	}
}
