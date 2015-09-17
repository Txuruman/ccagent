package es.securitasdirect.moduloweb.service;

import es.securitasdirect.moduloweb.exceptions.BusinessException;
import es.securitasdirect.moduloweb.model.Cuote;
import es.securitasdirect.moduloweb.model.DirectAccess;
import es.securitasdirect.moduloweb.model.DummyGenerator;
import es.securitasdirect.moduloweb.model.InstallationData;
import es.securitasdirect.moduloweb.web.dto.request.SearchInstallationRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.ws.dataservice.Installation;

import javax.inject.Named;
import javax.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Service to access the Installation Info
 */
@Named(value = "installationService")
@Singleton
public class InstallationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InstallationService.class);



    public InstallationData getInstallation(String installationNumber) {
    	InstallationData installation = DummyGenerator.getInstallation(installationNumber);
    	if (installation==null) {
    		throw new BusinessException(BusinessException.ErrorCode.ERROR_INSTALLATION_NOT_FOUND);
		}else{
			return installation;
		}
    	
    }
    
    /**
     * Buscamos instalaciones
     * Si el campo installationNumber esta relleno y no es la instalación activa --> Se busca por installationNumber
     * Si el campo telefono esta relleno e installationNumber no es la instalacion activa --> Se busca por telefono
     * Si el campo email esta relleno, installationNumber no es la instalacion activa y telefono esta vacio --> Se busca por email
     * @param request
     * @return
     */
    public List<InstallationData> searchInstallations(String installationNumber, String phone, String email, InstallationData installationActive){
    	List<InstallationData> installationList=new ArrayList<InstallationData>();
    	
    	//Si el campo installationNumber esta relleno y no es la instalación activa --> Se busca por installationNumber
    	if (installationNumber!=null && !installationNumber.isEmpty() && !installationNumber.equals(installationActive.getInstallationNumber())) {
			return this.searchByInstallationNumber(installationNumber);
		}
    	
    	//Si el campo telefono esta relleno e installationNumber no es la instalacion activa --> Se busca por telefono
    	else if (phone!=null && !phone.isEmpty()) {
			return this.searchByPhone(phone);
		}
    	//Si el campo email esta relleno, installationNumber no es la instalacion activa y telefono esta vacio --> Se busca por email
    	else if(email!=null && !email.isEmpty()){
			return this.searchByEmail(email);
		}
    	
    	else{
			return installationList;
		}
    }

	private List<InstallationData> searchByEmail(String email) {
		 LOGGER.debug("Buscando por email {}", email);
		// TODO Auto-generated method stub
		return null;
	}

	private List<InstallationData> searchByPhone(String phone) {
		 LOGGER.debug("Buscando por telefono {}", phone);
		// TODO Auto-generated method stub
		 return null;
	}

	private List<InstallationData> searchByInstallationNumber(String installationNumber) {
		 LOGGER.debug("Buscando por installationNumber {}", installationNumber);
		// TODO Auto-generated method stub
		 return null;
	}

}
