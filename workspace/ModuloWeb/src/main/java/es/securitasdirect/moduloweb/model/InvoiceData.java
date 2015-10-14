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
	private String InvoiceNumber;
	
	/**
	 * Nº externo asociado a la factura
	 */
	private String ExtInvoiceNo;
	
	/**
	 * Installation Number
	 */
	private String sins;
	
	/**
	 * Tipo de factura Cyle 0 o One Time 1
	 */
	private Integer InvoiceType;
	
	/**
	 * Cantidad total de la factura
	 */
	private String Amount;
	
	/**
	 * Fecha de generación de la factura
	 */
	private String	SystemDate;
	
	/**
	 * Fecha de pago
	 */
	private String TransactionDate;
	
	/**
	 * Fecha límite de pago
	 */
	private String DueDate;
	
	/**
	 * balance de la factura
	 */
	private String balance;
    //constructor copia de la clase InvoiceData
    /*
    public InvoiceData(final org.wso2.ws.dataservice.GetInvoiceDataResult getInvoiceDataResult) {
        this.id = getInvoiceDataResult.getId().intValue();
        this.InvoiceNumber = getInvoiceDataResult.getInvoiceNumber();
        this.ExtInvoiceNo = getInvoiceDataResult.getExtInvoiceNo();
        this.InstallationNumber = getInvoiceDataResult.getInstallationNumber();
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

	public String getInvoiceNumber() {
		return InvoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		InvoiceNumber = invoiceNumber;
	}

	public String getExtInvoiceNo() {
		return ExtInvoiceNo;
	}

	public void setExtInvoiceNo(String extInvoiceNo) {
		ExtInvoiceNo = extInvoiceNo;
	}
	
	public String getSins() {
		return sins;
	}

	public void setSins(String sins) {
		this.sins = sins;
	}

	public Integer getInvoiceType() {
		return InvoiceType;
	}

	public void setInvoiceType(Integer invoiceType) {
		InvoiceType = invoiceType;
	}

	public String getAmount() {
		return Amount;
	}

	public void setAmount(String amount) {
		Amount = amount;
	}

	public String getSystemDate() {
		return SystemDate;
	}

	public void setSystemDate(String systemDate) {
		SystemDate = systemDate;
	}

	public String getTransactionDate() {
		return TransactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		TransactionDate = transactionDate;
	}

	public String getDueDate() {
		return DueDate;
	}

	public void setDueDate(String dueDate) {
		DueDate = dueDate;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}
	
}
