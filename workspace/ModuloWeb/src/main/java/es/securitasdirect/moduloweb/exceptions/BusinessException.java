package es.securitasdirect.moduloweb.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Excepcion para errores de negocio.
 * Mantiene una lista de codigos de error que hay que internacionalizar
 * Esta clase se utiliza en la capa de vista (MessageUtil) para mostrar los errores
 * @author Javier Naval
 */
public class BusinessException extends RuntimeException {
	
	static final long serialVersionUID = 500;

    //Internacionalizar estos textos tal y como aparecen
	public static enum ErrorCode {
		ERROR_CREATE_SESSION_INFOPONT,
		ERROR_INSTALLATION_NOT_FOUND,
		ERROR_DIRECT_ACCESS_NOT_FOUND,
		ERROR_AUDIT_NOT_FOUND,
		ERROR_INVOICE_INFO_NOT_FOUND,
		ERROR_CYCLE_FEEDS_NOT_FOUND,
		ERROR_INVOICE_LIST_NOT_FOUND,
		ERROR_CUOTES_NOT_FOUND,
		ERROR_NOT_AUTHENTICATED,
		ERROR_TAB_KEYS_NOT_FOUND,
		ERROR_FIND_INSTALLATION,
		ERROR_UPDATE_CODEWORD,
		ERROR_UPDATE_INSTALLATION,
		ERROR_DELETE_ACTION_PLAN,
		ERROR_ADD_ACTION_PLAN,
		ERROR_UPDATE_ACTION_PLAN,
		ERROR_GET_PHONE_TYPES,
		ERROR_INSERT_KEY,
		ERROR_UPDATE_KEY,
		ERROR_DELETE_KEY,
		ERROR_GET_KEY,
		ERROR_UPDATE_CCC,
		ERROR_INSERT_FIELD,
        ERROR_UPDATE_FIELD,
        ERROR_DELETE_FIELD,
        ERROR_INSERT_DIRECT_ACCESS,
        ERROR_UPDATE_DIRECT_ACCESS,
        ERROR_DELETE_DIRECT_ACCESS,
        ERROR_INSERT_DIRECT_ACCESS_PARAMS,
        ERROR_UPDATE_DIRECT_ACCESS_PARAMS,
        ERROR_DELETE_DIRECT_ACCESS_PARAMS,		ERROR_GET_INVOICE_LIST,
		ERROR_GET_INVOICE_DETAIL,
		ERROR_UPDATE_DATA,
		ERROR_GET_EMAILBILLING,
		ERROR_GET_ACTIVATION    };
	
	private ErrorCode errorCode = null;
	
	/**
	 * Lista con parametros para rellenar los mensajes de error
	 */
	private List<String> errorParams = null;
	
	public BusinessException(){
		super();
	}
	
	public BusinessException(ErrorCode errorCode){
		super();
		assert errorCode!=null: "Es necesario al un codigo de error";
		this.errorCode = errorCode;
	}

    public BusinessException(ErrorCode errorCode, String... errorParams) {
        super();
        this.errorCode = errorCode;
        this.errorParams = new ArrayList<String>(1);
        if (errorParams!=null)
            for (String errorParam : errorParams) {
                this.errorParams.add(errorParam);
            }
    }
	
	public BusinessException(Exception e) {
		super(e);
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public List<String> getErrorParams() {
		return errorParams;
	}

	public void setErrorParams(List<String> errorParams) {
		this.errorParams = errorParams;
	}
	
	public BusinessException addErrorParams(String... errorParams) {
		if (this.errorParams==null) this.errorParams = new ArrayList<String>(errorParams.length);
		if (errorParams!=null) for (String ep:errorParams) this.errorParams.add(ep);
		return this;
	}

	/**
	 * Constructs a <code>String</code> with all attributes
	 * in name = value format.
	 *
	 * @return a <code>String</code> representation 
	 * of this object.
	 */
	public String toString()
	{
	    final String TAB = "    ";
	
	    StringBuilder retValue = new StringBuilder();
	    
	    retValue.append("BusinessException ( ")
	        .append(super.toString()).append(TAB)
	        .append("errorCode = ").append(this.errorCode).append(TAB)
	        .append("errorParams = ").append(this.errorParams).append(TAB)
	        .append(" )");
	    
	    return retValue.toString();
	}
	
	
	
}
