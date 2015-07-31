package es.securitasdirect.moduloweb.web.dto.response;

import es.securitasdirect.moduloweb.web.dto.support.BaseResponse;

/**
 * Return response for simple responses.
 */
public class SimpleResponse extends BaseResponse {

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
