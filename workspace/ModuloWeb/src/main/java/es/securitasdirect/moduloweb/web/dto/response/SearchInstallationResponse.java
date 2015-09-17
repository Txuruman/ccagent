package es.securitasdirect.moduloweb.web.dto.response;

import es.securitasdirect.moduloweb.model.InstallationData;
import es.securitasdirect.moduloweb.web.dto.support.BaseResponse;

import java.util.List;

/**
 * Created by JAS on 16/09/2015.
 */
public class SearchInstallationResponse extends BaseResponse {
    private List<InstallationData> installationList;

    public List<InstallationData> getInstallationList() {
        return installationList;
    }

    public void setInstallationList(List<InstallationData> installationList) {
        this.installationList = installationList;
    }
}
