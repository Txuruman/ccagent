package es.securitasdirect.moduloweb.web.dto.request;

/**
 * 
 * @author JAS
 *	Clase para recibir parametros para cambiar las palabras clave
 *	@param installationNumber
 * 	@param codeword
 * 	@param ix
 */
public class CodewordChangeRequest extends AgentRequest{
	/**
	 * Numero de instalacion
	 * */
	private String installationNumber;
	/**
	 * Valor de la clave
	 */
	private String codeword;
	/**
	 * Indice de la clave --> 5, 2, 1
	 */
	private Integer ix;
	
	public CodewordChangeRequest(String installationNumber, String codeword, Integer ix) {
		super();
		this.installationNumber = installationNumber;
		this.codeword = codeword;
		this.ix = ix;
	}

	public CodewordChangeRequest(){}
	
	public String getInstallationNumber() {
		return installationNumber;
	}

	public void setInstallationNumber(String installationNumber) {
		this.installationNumber = installationNumber;
	}

	public String getCodeword() {
		return codeword;
	}

	public void setCodeword(String codeword) {
		this.codeword = codeword;
	}

	public Integer getIx() {
		return ix;
	}

	public void setIx(Integer ix) {
		this.ix = ix;
	}

	@Override
	public String toString() {
		return "CodewordChangeRequest [installationNumber=" + installationNumber + ", codeword=" + codeword + ", ix="
				+ ix + "]";
	}
}
