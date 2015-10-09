package es.securitasdirect.moduloweb.web.dto.response;

import es.securitasdirect.moduloweb.model.InvoiceGlobal;
import es.securitasdirect.moduloweb.web.dto.support.BaseResponse;


/**
 * Response for the invoice query
 */
public class InvoiceResponse extends BaseResponse {
	private InvoiceGlobal invoiceGlobal=new InvoiceGlobal();

	public InvoiceResponse() {
		super();
	}

	public InvoiceGlobal getInvoiceGlobal() {
		return invoiceGlobal;
	}

	public void setInvoiceGlobal(InvoiceGlobal invoiceGlobal) {
		this.invoiceGlobal = invoiceGlobal;
	}
    
	
}
