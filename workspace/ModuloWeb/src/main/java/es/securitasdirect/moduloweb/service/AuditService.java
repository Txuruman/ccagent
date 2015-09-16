package es.securitasdirect.moduloweb.service;

import es.securitasdirect.moduloweb.exceptions.BusinessException;
import es.securitasdirect.moduloweb.exceptions.FrameworkException;
import es.securitasdirect.moduloweb.model.Audit;
import es.securitasdirect.moduloweb.model.DirectAccess;
import es.securitasdirect.moduloweb.model.DummyGenerator;
import es.securitasdirect.moduloweb.model.InstallationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.ws.dataservice.CCAGENTAUDPortType;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Date;
import java.util.List;

/**
 * Service to query and log the audit
 */
@Named(value = "auditService")
@Singleton
public class AuditService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditService.class);

	@Inject
	protected CCAGENTAUDPortType wsAudit;


	//TODO BOrrar la clase
    public List<Audit> getAudit(Integer installationNumber,String appFilter, Date startDateFilter) {
    	List<Audit> list= DummyGenerator.getAudit();
    	if (list.isEmpty()) {
    		throw new BusinessException(BusinessException.ErrorCode.ERROR_AUDIT_NOT_FOUND);
		}else{
			return list;
		}
    }


	public void insert(org.wso2.ws.dataservice.Audit audit) {
		//TODO LOG
		try {
			//wsAudit.insertAuditOperation(TODO METER TODOS LOS PARAMETROS);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			throw new FrameworkException(e);
		}
	}
}
