package es.securitasdirect.moduloweb.model;
/**
 * Modelo de Item de Factura
 * @author JAS
 *
 */
public class InvoiceItem {
	/**
	 * Nº de la factura a la que está asociado el concepto
	 */
	private Integer InvoiceNumber;
	
	/**
	 * Concepto
	 */
	private String Description;
	
	/**
	 * Periodo del servicio prestado
	 */
	private Integer Period;
	
	/**
	 * Cantidad del concepto sin IVA
	 */
	private Double Amount;
	
	/**
	 * Impuestos aplicados al concepto
	 */
	private Double Tax;

	public Integer getInvoiceNumber() {
		return InvoiceNumber;
	}

	public void setInvoiceNumber(Integer invoiceNumber) {
		InvoiceNumber = invoiceNumber;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Integer getPeriod() {
		return Period;
	}

	public void setPeriod(Integer period) {
		Period = period;
	}

	public Double getAmount() {
		return Amount;
	}

	public void setAmount(Double amount) {
		Amount = amount;
	}

	public Double getTax() {
		return Tax;
	}

	public void setTax(Double tax) {
		Tax = tax;
	}
	
}
