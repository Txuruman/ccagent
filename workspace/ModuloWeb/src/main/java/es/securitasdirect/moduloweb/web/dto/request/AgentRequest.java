package es.securitasdirect.moduloweb.web.dto.request;


/**
 * 
 * @author JAS
 *	Clase padre con el agente
 *	@param String agent
 */
public class AgentRequest{
	private String agent;
	
	public AgentRequest(String agent) {
		super();
		this.agent=agent;
	}
	
	public AgentRequest(){}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	@Override
	public String toString() {
		return "AgentRequest [agent=" + agent + "]";
	}

}
