package es.securitasdirect.moduloweb.service;

import es.securitasdirect.moduloweb.exceptions.BusinessException;
import es.securitasdirect.moduloweb.model.Cuote;
import es.securitasdirect.moduloweb.model.CycleFeeds;
import es.securitasdirect.moduloweb.model.DummyGenerator;
import es.securitasdirect.moduloweb.model.InvoiceData;
import es.securitasdirect.moduloweb.model.InvoiceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.ws.dataservice.Installation;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.List;

/**
 * Service to access the Invoice Info
 */
@Named(value = "invoiceService")
@Singleton
public class InvoiceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceService.class);

    public InvoiceInfo getInvoice(Integer invoiceId) {
    	InvoiceInfo info=DummyGenerator.getInvoice(invoiceId);
    	if (info==null) {
    		throw new BusinessException(BusinessException.ErrorCode.ERROR_INVOICE_INFO_NOT_FOUND);
		}else{
			return info;
		}
        
    }
    public List<CycleFeeds> getCycleFeeds(Integer installationId) {
    	List<CycleFeeds> list= DummyGenerator.getCycleFeeds(installationId);
    	if (list.isEmpty()) {
    		throw new BusinessException(BusinessException.ErrorCode.ERROR_CYCLE_FEEDS_NOT_FOUND);
    	}else{
			return list;
		}
    }
    public List<InvoiceData>getListInvoices(Integer installationNumber){
    	List<InvoiceData> list= DummyGenerator.getListInvoices(installationNumber);
    	if (list.isEmpty()) {
    		throw new BusinessException(BusinessException.ErrorCode.ERROR_INVOICE_LIST_NOT_FOUND);
    	}else{
			return list;
		}
    }
    public Cuote getCuotes(Integer installationNumber){
    	Cuote cuote= DummyGenerator.getCuotes(installationNumber);
    	if (cuote==null) {
    		throw new BusinessException(BusinessException.ErrorCode.ERROR_CUOTES_NOT_FOUND);
		}else{
			return cuote;
		}
    }
}
