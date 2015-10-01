package es.securitasdirect.moduloweb.web.dto.response;

import es.securitasdirect.moduloweb.model.InstallationData;
import es.securitasdirect.moduloweb.web.dto.support.BaseResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JAS on 16/09/2015.
 */
public class SearchInstallationResponse extends BaseResponse {
    
	private List<InstallationData> installationList=new ArrayList<InstallationData>();
    
    /**
     * 0 por instalacion, 1 por telefono, 2 por email
     */
    
    private int searchBy;
    
    /**
     * Si no se ha realizado busqueda retorna true
     */
    private boolean noSearched = false;
    
    public SearchInstallationResponse() {
		super();
	}
    
    
	public int getSearchBy() {
		return searchBy;
	}


	public void setSearchBy(int searchBy) {
		this.searchBy = searchBy;
	}


	public boolean isNoSearched() {
		return noSearched;
	}


	public void setNoSearched(boolean noSearched) {
		this.noSearched = noSearched;
	}


	public List<InstallationData> getInstallationList() {
        return installationList;
    }

    public void setInstallationList(List<InstallationData> installationList) {
        this.installationList = installationList;
    }
}
