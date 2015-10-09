package es.securitasdirect.moduloweb.web.dto.response;

import java.util.List;

import org.wso2.ws.dataservice.GetTiposTelefonoResult;

import es.securitasdirect.moduloweb.web.dto.support.BaseResponse;

/**
 * Created by JAS on 06/10/2015.
 */
public class GetPhoneTypesResponse extends BaseResponse {

	List<GetTiposTelefonoResult> phoneTypeList;

	public List<GetTiposTelefonoResult> getPhoneTypeList() {
		return phoneTypeList;
	}

	public void setPhoneTypeList(List<GetTiposTelefonoResult> phoneTypeList) {
		this.phoneTypeList = phoneTypeList;
	}
}
