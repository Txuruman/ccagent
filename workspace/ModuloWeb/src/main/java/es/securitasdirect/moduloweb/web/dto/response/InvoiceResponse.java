package es.securitasdirect.moduloweb.web.dto.response;

import es.securitasdirect.moduloweb.model.InvoiceInfo;
import es.securitasdirect.moduloweb.web.dto.support.BaseResponse;


/**
 * Response for the invoice query
 */
public class InvoiceResponse extends BaseResponse {
    private InvoiceInfo invoiceInfo;

    public InvoiceInfo getInvoiceInfo() {
        return invoiceInfo;
    }

    public void setInvoiceInfo(InvoiceInfo invoiceInfo) {
        this.invoiceInfo = invoiceInfo;
    }
}
