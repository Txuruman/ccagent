package es.securitasdirect.moduloweb.web.dto.response;

import es.securitasdirect.moduloweb.model.Audit;
import es.securitasdirect.moduloweb.model.InstallationData;
import es.securitasdirect.moduloweb.web.dto.support.BaseResponse;

import java.util.List;

/**
 * Response for the audit query
 */
public class AuditResponse extends BaseResponse {
    private List<Audit> auditory;

    public List<Audit> getAuditory() {
        return auditory;
    }

    public void setAuditory(List<Audit> auditory) {
        this.auditory = auditory;
    }
}
