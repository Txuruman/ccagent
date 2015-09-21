package es.securitasdirect.moduloweb.model;

/**
 * Representa los datos generales asociados a una instalación sobre la información de facturación.
 */
public class InvoiceInfo {

    /**
     * clave autonumerica para la tabla
     */
    private Integer id;
    /** Indica si está activado el envío de facturas para la instalación. */
    public Boolean invoiceSend;

    /** Importe en euros adeudado. */
    public Double debtAmount;

    /** Entidad que financia. */
    public String financialEntity;

    /** Modo de pago. */
    public String payMode;
    
    /** Descuento */
    public Boolean discount;
    //TODO DESCUENTOS
    
    /** Email de Facturación*/
    private String emailBilling;
    
    /** Cuenta corriente */
    private String ccc;

    //constructor copia de la clase InvoiceInfo
    /*
    public InvoiceInfo(final org.wso2.ws.dataservice.GetInvoiceInfoResult getInvoiceInfoResult) {
        this.id = getInvoiceInfoResult.getId().intValue();
        this.debtAmount = getInvoiceInfoResult.getDebtAmount();
        this.financialEntity = getInvoiceInfoResult.getFinancialEntity();
        this.payMode = getInvoiceInfoResult.getPayMode();
        this.discount = getInvoiceInfoResult.getDiscount();
        this.emailBilling = getInvoiceInfoResult.getEmailBilling();
        this.ccc = getInvoiceInfoResult.getCcc();
    }*/


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

	public Boolean getDiscount() {
		return discount;
	}

	public void setDiscount(Boolean discount) {
		this.discount = discount;
	}

	public String getEmailBilling() {
		return emailBilling;
	}

	public void setEmailBilling(String emailBilling) {
		this.emailBilling = emailBilling;
	}

	public String getCcc() {
		return ccc;
	}

	public void setCcc(String ccc) {
		this.ccc = ccc;
	}
    
    
}
