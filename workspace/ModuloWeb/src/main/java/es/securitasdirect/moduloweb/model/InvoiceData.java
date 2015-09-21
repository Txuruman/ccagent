package es.securitasdirect.moduloweb.model;

import java.util.Date;

/**
 * Datos de las facturas
 * @author JAS
 *
 */
public class InvoiceData {
	
	/**
	 * Clase para asignar valores estáticos al tipo de factura
	 * @author JAS
	 *
	 */
	public interface TYPE {
        public static final String ONE="One Time";
        public static final String CYCLE="Cycle Feeds";
    }


    /**
     * clave autonumerica para la tabla
     */
    private Integer id;
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

    //constructor copia de la clase InvoiceData
    /*
    public InvoiceData(final org.wso2.ws.dataservice.GetInvoiceDataResult getInvoiceDataResult) {
        this.id = getInvoiceDataResult.getId().intValue();
        this.InvoiceNumber = getInvoiceDataResult.getInvoiceNumber();
        this.ExtInvoiceNo = getInvoiceDataResult.getExtInvoiceNo();
        this.IntallationNumber = getInvoiceDataResult.getIntallationNumber();
        this.InvoiceType = getInvoiceDataResult.getInvoiceType();
        this.Amount = getInvoiceDataResult.getAmount();
        this.SystemDate = getInvoiceDataResult.getSystemDate();
        this.TransactionDate = getInvoiceDataResult.getTransactionDate();
        this.DueDate = getInvoiceDataResult.getDueDate();
    }
    */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
