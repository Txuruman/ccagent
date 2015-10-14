package es.securitasdirect.moduloweb.web.dto.request;


import es.securitasdirect.moduloweb.model.Agent;

/**
 * 
 * @author JAS
 *	Clase que recibe parametros para modificar EmailBilling y Activation
 *	@param String installationNumber
 * 	@param emailbilling
 * 	@param activation
 */
public class UpdateEmailBillingAndActivationRequest extends AgentRequest{
	private String installationNumber;
	private String sins;
	private String emailbilling;
	private Boolean activation;
	private Boolean updateActivation;
	
	public UpdateEmailBillingAndActivationRequest(Agent agent, String installationNumber, String sins,
			String emailbilling, Boolean activation, Boolean updateActivation) {
		super(agent);
		this.installationNumber = installationNumber;
		this.sins = sins;
		this.emailbilling = emailbilling;
		this.activation = activation;
		this.updateActivation = updateActivation;
	}

	public UpdateEmailBillingAndActivationRequest(){}

	public String getInstallationNumber() {
		return installationNumber;
	}

	public void setInstallationNumber(String installationNumber) {
		this.installationNumber = installationNumber;
	}

	public String getEmailbilling() {
		return emailbilling;
	}

	public void setEmailbilling(String emailbilling) {
		this.emailbilling = emailbilling;
	}

	public Boolean getActivation() {
		return activation;
	}

	public void setActivation(Boolean activation) {
		this.activation = activation;
	}

	public String getSins() {
		return sins;
	}

	public void setSins(String sins) {
		this.sins = sins;
	}

	public Boolean getUpdateActivation() {
		return updateActivation;
	}

	public void setUpdateActivation(Boolean updateActivation) {
		this.updateActivation = updateActivation;
	}
	
}
