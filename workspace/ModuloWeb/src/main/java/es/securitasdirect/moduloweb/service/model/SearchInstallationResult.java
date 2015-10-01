package es.securitasdirect.moduloweb.service.model;

import java.util.ArrayList;
import java.util.List;

import es.securitasdirect.moduloweb.model.InstallationData;

/**
 * Para devolver datos del resultado de searchInstallation
 */
public class SearchInstallationResult {

	/**
	 * Lista de instalaciones
	 */
    private List<InstallationData> installationList=new ArrayList<InstallationData>();
    /**
     * 0 por instalacion, 1 por telefono, 2 por email
     */
    private int searchBy;
    /**
     * Si no se ha realizado busqueda retorna true
     */
    private boolean noSearched = false;
	
    public SearchInstallationResult() {
		super();
	}

	public List<InstallationData> getInstallationList() {
		return installationList;
	}

	public void setInstallationList(List<InstallationData> installationList) {
		this.installationList = installationList;
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

	@Override
	public String toString() {
		return "SearchInstallationResult [installationList=" + installationList + ", searchBy=" + searchBy
				+ ", noSearched=" + noSearched + "]";
	}
    
    
    
}
