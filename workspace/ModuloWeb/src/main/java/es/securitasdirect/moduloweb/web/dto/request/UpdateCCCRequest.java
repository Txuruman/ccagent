package es.securitasdirect.moduloweb.web.dto.request;

import es.securitasdirect.moduloweb.model.Agent;

/**
 * 
 * @author JAS
 *	Clase que recibe parametros para actualizar la CCC
 *	@param debIban - numero de cuenta actualizado
 * 	@param umr
 * 	@param sIns
 */
public class UpdateCCCRequest extends AgentRequest{
	
	private String debIban;
	private String umr;
	private String sIns;
	
	public UpdateCCCRequest(Agent agent, String debIban, String umr, String sIns) {
		super(agent);
		this.debIban = debIban;
		this.umr = umr;
		this.sIns = sIns;
	}

	public UpdateCCCRequest(){}

	public String getDebIban() {
		return debIban;
	}

	public void setDebIban(String debIban) {
		this.debIban = debIban;
	}

	public String getUmr() {
		return umr;
	}

	public void setUmr(String umr) {
		this.umr = umr;
	}

	public String getsIns() {
		return sIns;
	}

	public void setsIns(String sIns) {
		this.sIns = sIns;
	}
	
}
