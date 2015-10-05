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
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
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



    public List<Audit> getAudit(String usuario, String appFilter, String startDateFilter) {


        try {
            XMLGregorianCalendar xmlStartDateFilter;
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(new Date(Long.parseLong(startDateFilter)));
            xmlStartDateFilter = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
            //Esto es necesario porque el formato es <dateTime>2015-09-10T19:19:19</dateTime>
            xmlStartDateFilter.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
            xmlStartDateFilter.setTimezone(DatatypeConstants.FIELD_UNDEFINED);

            List<org.wso2.ws.dataservice.Entry> list=wsAudit.selectAuditByParametersOperation(usuario, appFilter, xmlStartDateFilter);
            List<Audit> listaAudit=new ArrayList<Audit>();
            
            for (org.wso2.ws.dataservice.Entry entry : list) {
                Audit audit = new Audit(entry);
                listaAudit.add(audit);
            }

            if (list.isEmpty()) {
                throw new BusinessException(BusinessException.ErrorCode.ERROR_AUDIT_NOT_FOUND);
            }else{
                return listaAudit;
            }
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage(),e);
            throw new BusinessException(BusinessException.ErrorCode.ERROR_AUDIT_NOT_FOUND);
        }



    }


    public void insert(Audit audit) {
        try {
            String event=audit.getAction();
            String result=audit.getResult();
            String actor=audit.getUser();
            String detail=audit.getDetail();

            XMLGregorianCalendar auditDate =null;

            XMLGregorianCalendar xmlAuditDate;
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(audit.getDate());
            xmlAuditDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
            //Esto es necesario porque el formato es <dateTime>2015-09-10T19:19:19</dateTime>
            xmlAuditDate.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
            xmlAuditDate.setTimezone(DatatypeConstants.FIELD_UNDEFINED);

            String app=audit.getApp();
            wsAudit.insertAuditOperation(xmlAuditDate, actor, app, event, result, detail);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
            throw new FrameworkException(e);
        }
    }
}

