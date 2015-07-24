package es.securitasdirect.moduloweb.web.dto.response;

import es.securitasdirect.moduloweb.model.DirectAccess;
import es.securitasdirect.moduloweb.web.dto.support.BaseResponse;

import java.util.List;

/**
 * Created by Javier Naval on 24/07/2015.
 */
public class ListDirectAccessResponse extends BaseResponse {
    private List<DirectAccess> directAcess;

    public List<DirectAccess> getDirectAcess() {
        return directAcess;
    }

    public void setDirectAcess(List<DirectAccess> directAcess) {
        this.directAcess = directAcess;
    }
}
