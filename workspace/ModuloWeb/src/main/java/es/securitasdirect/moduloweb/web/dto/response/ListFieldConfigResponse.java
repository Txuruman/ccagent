package es.securitasdirect.moduloweb.web.dto.response;

import es.securitasdirect.moduloweb.model.FieldConfig;
import es.securitasdirect.moduloweb.web.dto.support.BaseResponse;

import java.util.List;

/**
 * Created by Pedro on 21/09/2015.
 */
public class ListFieldConfigResponse extends BaseResponse {

    private List<FieldConfig> fieldConfig;

    public List<FieldConfig> getFieldConfig() {
        return fieldConfig;
    }

    public void setFieldConfig(List<FieldConfig> fieldConfig) {
        this.fieldConfig = fieldConfig;
    }
}
