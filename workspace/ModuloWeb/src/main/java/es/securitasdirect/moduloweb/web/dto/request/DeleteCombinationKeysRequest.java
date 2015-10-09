package es.securitasdirect.moduloweb.web.dto.request;

import java.util.ArrayList;
import java.util.List;

import es.securitasdirect.moduloweb.model.ActionPlan;
import es.securitasdirect.moduloweb.model.CombinationsKeys;

/**
 * 
 * @author JAS
 *	Clase para recibir action plans para eliminar
 *	@param String installationNumber
 * 	@param List<ActionPlan> contactos
 */
public class DeleteCombinationKeysRequest extends AgentRequest{
	private CombinationsKeys combinationKeys;
	
	public DeleteCombinationKeysRequest(CombinationsKeys combinationKeys) {
		super();
		this.combinationKeys=combinationKeys;
	}
	
	public DeleteCombinationKeysRequest(){}

	public CombinationsKeys getCombinationKeys() {
		return combinationKeys;
	}

	public void setCombinationKeys(CombinationsKeys combinationKeys) {
		this.combinationKeys = combinationKeys;
	}

	@Override
	public String toString() {
		return "DeleteCombinationKeysRequest [combinationKeys=" + combinationKeys + "]";
	}

}
