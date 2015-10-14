package es.securitasdirect.moduloweb.web.dto.response;

import es.securitasdirect.moduloweb.web.dto.support.BaseResponse;


/**
 * Response for the invoice query
 */
public class UpdateEmailBillingAndActivationResponse extends BaseResponse {
	private String emailbilling;
	private Boolean activation;
	
	public UpdateEmailBillingAndActivationResponse() {}

	public UpdateEmailBillingAndActivationResponse(String emailbilling, Boolean activation) {
		super();
		this.emailbilling = emailbilling;
		this.activation = activation;
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
	
}
