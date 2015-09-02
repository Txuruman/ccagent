package es.securitasdirect.moduloweb.web.dto.response;

import es.securitasdirect.moduloweb.model.DirectAccess;
import es.securitasdirect.moduloweb.model.InstallationData;
import es.securitasdirect.moduloweb.web.dto.support.BaseResponse;

import java.util.List;

/**
 * Response for the installation query
 */
public class InstallationResponse extends BaseResponse {
    private InstallationData installation;
    public InstallationResponse(){
    	super();
    }
    public InstallationData getInstallation() {
        return installation;
    }

    public void setInstallation(InstallationData installation) {
        this.installation = installation;
    }
}
