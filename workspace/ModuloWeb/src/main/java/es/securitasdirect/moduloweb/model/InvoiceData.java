package es.securitasdirect.moduloweb.model;

import java.util.Date;

/**
 * Datos de las facturas
 * @author JAS
 *
 */
public class InvoiceData {
	/**
	 * Nº de la factura
	 */
	private Integer InvoiceNumber;
	
	/**
	 * Nº externo asociado a la factura
	 */
	private String ExtInvoiceNo;
	
	/**
	 * Installation Number
	 */
	private Integer IntallationNumber;
	
	/**
	 * Tipo de factura Cyle o One Time
	 */
	private String InvoiceType;
	
	/**
	 * Cantidad total de la factura
	 */
	private Double Amount;
	
	/**
	 * Fecha de generación de la factura
	 */
	private Date	SystemDate;
	
	/**
	 * Fecha de pago
	 */
	private Date TransactionDate;
	
	/**
	 * Fecha límite de pago
	 */
	private Date DueDate;

	public Integer getInvoiceNumber() {
		return InvoiceNumber;
	}

	public void setInvoiceNumber(Integer invoiceNumber) {
		InvoiceNumber = invoiceNumber;
	}

	public String getExtInvoiceNo() {
		return ExtInvoiceNo;
	}

	public void setExtInvoiceNo(String extInvoiceNo) {
		ExtInvoiceNo = extInvoiceNo;
	}
	
	public Integer getIntallationNumber() {
		return IntallationNumber;
	}

	public void setIntallationNumber(Integer intallationNumber) {
		IntallationNumber = intallationNumber;
	}

	public String getInvoiceType() {
		return InvoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		InvoiceType = invoiceType;
	}

	public Double getAmount() {
		return Amount;
	}

	public void setAmount(Double amount) {
		Amount = amount;
	}

	public Date getSystemDate() {
		return SystemDate;
	}

	public void setSystemDate(Date systemDate) {
		SystemDate = systemDate;
	}

	public Date getTransactionDate() {
		return TransactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		TransactionDate = transactionDate;
	}

	public Date getDueDate() {
		return DueDate;
	}

	public void setDueDate(Date dueDate) {
		DueDate = dueDate;
	}
	
	
}
