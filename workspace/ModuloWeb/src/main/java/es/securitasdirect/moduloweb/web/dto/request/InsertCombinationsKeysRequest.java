package es.securitasdirect.moduloweb.web.dto.request;

import es.securitasdirect.moduloweb.model.CombinationsKeys;

/**
 * 
 * @author JAS
 *	Clase para recibir combinationkeys para insertar
 *	@param CombinationKeys
 * 	
 */
public class InsertCombinationsKeysRequest {
	private CombinationsKeys combinationKeys;
	
	public InsertCombinationsKeysRequest(CombinationsKeys combinationKeys) {
		super();
		this.combinationKeys=combinationKeys;
	}
	
	public InsertCombinationsKeysRequest(){}

	public CombinationsKeys getCombinationKeys() {
		return combinationKeys;
	}

	public void setCombinationKeys(CombinationsKeys combinationKeys) {
		this.combinationKeys = combinationKeys;
	}

	@Override
	public String toString() {
		return "InsertCombinationsKeysRequest [combinationKeys=" + combinationKeys + "]";
	}
	
	

}
