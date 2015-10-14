package es.securitasdirect.moduloweb.model;
/**
 * Modelo de Item de Factura
 * @author JAS
 *
 */
public class InvoiceItem {

    /**
     * clave autonumerica para la tabla
     */
    private String id;
	/**
	 * Nº de la factura a la que está asociado el concepto
	 */
	private String InvoiceNumber;
	
	/**
	 * Concepto
	 */
	private String Description;
	
	/**
	 * Periodo del servicio prestado
	 */
	private String desde;
	private String hasta;
	/**
	 * Cantidad del concepto sin IVA
	 */
	private String Amount;
	
	/**
	 * Impuestos aplicados al concepto
	 */
	private String Tax;

    //constructor copia de la clase InvoiceItem
    /*
    public InvoiceItem(final org.wso2.ws.dataservice.GetInvoiceItemResult getInvoiceItemResult) {
        this.id = getInvoiceItemResult.getId().intValue();
        this.InvoiceNumber = getInvoiceItemResult.getInvoiceNumber();
        this.Description = getInvoiceItemResult.getDescription();
        this.Period = getInvoiceItemResult.getPeriod();
        this.Amount = getInvoiceItemResult.getAmount();
        this.Tax = getInvoiceItemResult.getTax();

    }
    */


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getInvoiceNumber() {
		return InvoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		InvoiceNumber = invoiceNumber;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getDesde() {
		return desde;
	}

	public void setDesde(String desde) {
		this.desde = desde;
	}

	public String getHasta() {
		return hasta;
	}

	public void setHasta(String hasta) {
		this.hasta = hasta;
	}

	public String getAmount() {
		return Amount;
	}

	public void setAmount(String amount) {
		Amount = amount;
	}

	public String getTax() {
		return Tax;
	}

	public void setTax(String tax) {
		Tax = tax;
	}
	
}
