package es.securitasdirect.moduloweb.web.dto.request;

import java.util.ArrayList;
import java.util.List;

import es.securitasdirect.moduloweb.model.ActionPlan;

/**
 * 
 * @author JAS
 *	Clase para recibir action plans para eliminar
 *	@param String installationNumber
 * 	@param List<ActionPlan> contactos
 */
public class DeleteActionPlansRequest extends AgentRequest{
	private String installationNumber;
	private List<ActionPlan> contactos=new ArrayList<ActionPlan>();
	
	public DeleteActionPlansRequest(String installationNumber, List<ActionPlan> contactos) {
		super();
		this.installationNumber=installationNumber;
		this.contactos=contactos;
	}
	
	public DeleteActionPlansRequest(){}

	public String getInstallationNumber() {
		return installationNumber;
	}

	public void setInstallationNumber(String installationNumber) {
		this.installationNumber = installationNumber;
	}

	public List<ActionPlan> getContactos() {
		return contactos;
	}

	public void setContactos(List<ActionPlan> contactos) {
		this.contactos = contactos;
	}

	@Override
	public String toString() {
		return "DeleteActionPlansRequest [installationNumber=" + installationNumber + ", contactos=" + contactos + "]";
	}

	
}
