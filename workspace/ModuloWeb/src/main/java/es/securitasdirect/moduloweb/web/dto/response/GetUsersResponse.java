package es.securitasdirect.moduloweb.web.dto.response;

import es.securitasdirect.moduloweb.model.Users;
import es.securitasdirect.moduloweb.web.dto.support.BaseResponse;

import java.util.List;

/**
 * Created by Javier Naval on 24/07/2015.
 */
public class GetUsersResponse extends BaseResponse {
    private List<Users> users;

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }
}
