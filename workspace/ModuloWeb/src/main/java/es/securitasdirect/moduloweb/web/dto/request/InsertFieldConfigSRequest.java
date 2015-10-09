package es.securitasdirect.moduloweb.web.dto.request;

import es.securitasdirect.moduloweb.model.FieldConfig;

/**
 * 
 * @author JAS
 *	Clase para recibir combinationkeys para insertar
 *	@param CombinationKeys
 * 	
 */
public class InsertFieldConfigSRequest {
	private FieldConfig campo;
	
	public InsertFieldConfigSRequest(FieldConfig campo) {
		super();
		this.campo=campo;
	}
	
	public InsertFieldConfigSRequest(){}

	public FieldConfig getCampo() {
		return campo;
	}

	public void setCampo(FieldConfig campo) {
		this.campo = campo;
	}

	
	

}
