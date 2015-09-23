package es.securitasdirect.moduloweb.web.dto.request;

import es.securitasdirect.moduloweb.model.CombinationsKeys;

/**
 * 
 * @author JAS
 *	Clase para recibir combinationkeys para insertar
 *	@param CombinationKeys
 * 	
 */
public class UpdateCombinationsKeysRequest {
	private CombinationsKeys combinationKeys;
	
	public UpdateCombinationsKeysRequest(CombinationsKeys combinationKeys) {
		super();
		this.combinationKeys=combinationKeys;
	}
	
	public UpdateCombinationsKeysRequest(){}

	public CombinationsKeys getCombinationKeys() {
		return combinationKeys;
	}

	public void setCombinationKeys(CombinationsKeys combinationKeys) {
		this.combinationKeys = combinationKeys;
	}

	@Override
	public String toString() {
		return "UpdateCombinationsKeysRequest [combinationKeys=" + combinationKeys + "]";
	}
	
	

}
