package es.securitasdirect.moduloweb.web.dto.response;

import es.securitasdirect.moduloweb.model.InvoiceInfo;

import java.util.List;

import es.securitasdirect.moduloweb.model.CycleFeeds;
import es.securitasdirect.moduloweb.model.InvoiceData;
import es.securitasdirect.moduloweb.web.dto.support.BaseResponse;


/**
 * Response for the invoice query
 */
public class InvoiceResponse extends BaseResponse {
    private InvoiceInfo invoiceInfo;
    private List<CycleFeeds> cycleFeeds;
    private List<InvoiceData> invoiceList;
    
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
    
}
