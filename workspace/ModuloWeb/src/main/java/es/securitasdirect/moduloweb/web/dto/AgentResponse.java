package es.securitasdirect.moduloweb.web.dto;

import es.securitasdirect.moduloweb.model.Agent;
import es.securitasdirect.moduloweb.web.dto.support.BaseResponse;

/**
 * Response with Agent Info
 */
public class AgentResponse extends BaseResponse {

    private Agent agent;

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }
}
