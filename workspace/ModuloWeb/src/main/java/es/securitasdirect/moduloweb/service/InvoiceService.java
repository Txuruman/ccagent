package es.securitasdirect.moduloweb.service;

import es.securitasdirect.moduloweb.model.DummyGenerator;
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
        return DummyGenerator.getInvoice(invoiceId);
    }

}
