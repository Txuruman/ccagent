package es.securitasdirect.moduloweb.web.dto.request;

import java.util.ArrayList;
import java.util.List;

import es.securitasdirect.moduloweb.model.ActionPlan;

/**
 * 
 * @author JAS
 *	Clase que recibe parametros para insertar o modificar action plans
 *	@param String installationNumber
 * 	@param List<ActionPlan> contactos
 * 	@param ActionPlan addedPlan
 */
public class UpdateActionPlansRequest extends AgentRequest{
	private String installationNumber;
	private ArrayList<ActionPlan> contactos;
	private ActionPlan addedPlan;
	
	public UpdateActionPlansRequest(String installationNumber, ArrayList<ActionPlan> contactos, ActionPlan addedPlan) {
		super();
		this.installationNumber = installationNumber;
		this.contactos = contactos;
		this.addedPlan = addedPlan;
	}

	public UpdateActionPlansRequest(){}

	public String getInstallationNumber() {
		return installationNumber;
	}

	public void setInstallationNumber(String installationNumber) {
		this.installationNumber = installationNumber;
	}

	public ArrayList<ActionPlan> getContactos() {
		return contactos;
	}

	public void setContactos(ArrayList<ActionPlan> contactos) {
		this.contactos = contactos;
	}

	public ActionPlan getAddedPlan() {
		return addedPlan;
	}

	public void setAddedPlan(ActionPlan addedPlan) {
		this.addedPlan = addedPlan;
	}

	@Override
	public String toString() {
		return "UpdateActionPlansRequest [installationNumber=" + installationNumber + ", contactos=" + contactos
				+ ", addedPlan=" + addedPlan + "]";
	}

}
