package es.securitasdirect.moduloweb.model;

/**
 * Representa los datos generales asociados a una instalación sobre la información de facturación.
 */
public class InvoiceInfo {

    /** Indica si está activado el envío de facturas para la instalación. */
    public Boolean invoiceSend;

    /** Importe en euros adeudado. */
    public Double debtAmount;

    /** Entidad que financia. */
    public String financialEntity;

    /** Modo de pago. */
    public String payMode;

    //TODO DESCUENTOS


    public Boolean getInvoiceSend() {
        return invoiceSend;
    }

    public void setInvoiceSend(Boolean invoiceSend) {
        this.invoiceSend = invoiceSend;
    }

    public Double getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(Double debtAmount) {
        this.debtAmount = debtAmount;
    }

    public String getFinancialEntity() {
        return financialEntity;
    }

    public void setFinancialEntity(String financialEntity) {
        this.financialEntity = financialEntity;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }
}
