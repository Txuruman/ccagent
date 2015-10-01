package es.securitasdirect.moduloweb.web.dto.response;

import es.securitasdirect.moduloweb.model.InstallationData;
import es.securitasdirect.moduloweb.web.dto.support.BaseResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JAS on 30/09/2015.
 */
public class CodewordChangeResponse extends BaseResponse {
    
	private InstallationData installation=new InstallationData();
    
       
    public CodewordChangeResponse() {
		super();
	}


	public InstallationData getInstallation() {
		return installation;
	}


	public void setInstallation(InstallationData installation) {
		this.installation = installation;
	}
    
	
}
