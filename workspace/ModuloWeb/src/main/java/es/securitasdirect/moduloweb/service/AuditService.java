package es.securitasdirect.moduloweb.service;

import es.securitasdirect.moduloweb.exceptions.BusinessException;
import es.securitasdirect.moduloweb.model.Audit;
import es.securitasdirect.moduloweb.model.DirectAccess;
import es.securitasdirect.moduloweb.model.DummyGenerator;
import es.securitasdirect.moduloweb.model.InstallationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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


    public List<Audit> getAudit(Integer installationNumber,String appFilter, Date startDateFilter) {
    	List<Audit> list= DummyGenerator.getAudit();
    	if (list.isEmpty()) {
    		throw new BusinessException(BusinessException.ErrorCode.ERROR_AUDIT_NOT_FOUND);
		}else{
			return list;
		}
    }



}
