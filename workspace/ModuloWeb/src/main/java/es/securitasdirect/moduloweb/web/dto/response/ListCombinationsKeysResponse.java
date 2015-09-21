package es.securitasdirect.moduloweb.web.dto.response;

import es.securitasdirect.moduloweb.model.CombinationsKeys;
import es.securitasdirect.moduloweb.web.dto.support.BaseResponse;

import java.util.List;

/**
 * Created by Pedro on 21/09/2015.
 */
public class ListCombinationsKeysResponse extends BaseResponse {

    private List<CombinationsKeys> combinationsKeys;

    public List<CombinationsKeys> getCombinationsKeys() {
        return combinationsKeys;
    }

    public void setCombinationsKeys(List<CombinationsKeys> combinationsKeys) {
        this.combinationsKeys = combinationsKeys;
    }
}
