package es.securitasdirect.moduloweb.web.dto.response;

import es.securitasdirect.moduloweb.model.Audit;
import es.securitasdirect.moduloweb.web.dto.support.BaseResponse;

import java.util.List;

/**
 * Response for the audit query
 */
public class AuditResponse extends BaseResponse {
    private List<Audit> audit;

    public List<Audit> getAudit() {
        return audit;
    }

    public void setAudit(List<Audit> audit) {
        this.audit = audit;
    }
}
