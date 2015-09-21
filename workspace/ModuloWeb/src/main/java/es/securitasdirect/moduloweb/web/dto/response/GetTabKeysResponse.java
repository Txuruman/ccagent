package es.securitasdirect.moduloweb.web.dto.response;

import es.securitasdirect.moduloweb.model.TabKeys;
import es.securitasdirect.moduloweb.web.dto.support.BaseResponse;

import java.util.List;

/**
 * Created by Javier Naval on 24/07/2015.
 */
public class GetTabKeysResponse extends BaseResponse {
    private List<TabKeys> tabKeys;

    public List<TabKeys> getTabKeys() {
        return tabKeys;
    }

    public void setTabKeys(List<TabKeys> tabKeys) {
        this.tabKeys = tabKeys;
    }
}
