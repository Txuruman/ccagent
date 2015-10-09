package es.securitasdirect.moduloweb.model;

import es.securitasdirect.moduloweb.model.InvoiceInfo;

import java.util.List;

import es.securitasdirect.moduloweb.model.Cuote;
import es.securitasdirect.moduloweb.model.CycleFeeds;
import es.securitasdirect.moduloweb.model.DummyGenerator;
import es.securitasdirect.moduloweb.model.InvoiceData;
import es.securitasdirect.moduloweb.web.dto.support.BaseResponse;


/**
 * Modelo global para la pestaña facturacion
 */
public class InvoiceGlobal{
    private InvoiceInfo invoiceInfo;
    private List<CycleFeeds> cycleFeeds;
    private List<InvoiceData> invoiceList;
    private Cuote cuote;
    
    public List<CycleFeeds> getCycleFeeds() {
		return cycleFeeds;
	}

	public void setCycleFeeds(List<CycleFeeds> cycleFeeds) {
		this.cycleFeeds = cycleFeeds;
	}

	public InvoiceInfo getInvoiceInfo() {
        return invoiceInfo;
    }

    public void setInvoiceInfo(InvoiceInfo invoiceInfo) {
        this.invoiceInfo = invoiceInfo;
    }

	public List<InvoiceData> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(List<InvoiceData> invoiceList) {
		this.invoiceList = invoiceList;
	}

	public Cuote getCuote() {
		return cuote;
	}

	public void setCuote(Cuote cuote) {
		this.cuote = cuote;
	}
    
	
}
