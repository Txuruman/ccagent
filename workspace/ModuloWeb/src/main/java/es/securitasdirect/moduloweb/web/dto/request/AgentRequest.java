package es.securitasdirect.moduloweb.web.dto.request;

import es.securitasdirect.moduloweb.model.Agent;

/**
 * 
 * @author JAS
 *	Clase padre con el agente
 *	@param String agent
 */
public class AgentRequest{
	private Agent agent;
	
	public AgentRequest(Agent agent) {
		super();
		this.agent=agent;
	}
	
	public AgentRequest(){}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	@Override
	public String toString() {
		return "AgentRequest [agent=" + agent + "]";
	}

}
