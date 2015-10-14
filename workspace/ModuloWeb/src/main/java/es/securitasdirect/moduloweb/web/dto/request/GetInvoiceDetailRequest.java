package es.securitasdirect.moduloweb.web.dto.request;

import es.securitasdirect.moduloweb.model.Agent;

public class GetInvoiceDetailRequest extends AgentRequest{
	private String sInv;

	public GetInvoiceDetailRequest(Agent agent, String sInv) {
		super(agent);
		this.sInv = sInv;
	}

	public GetInvoiceDetailRequest() {}

	public String getsInv() {
		return sInv;
	}

	public void setsInv(String sInv) {
		this.sInv = sInv;
	}
	
}
