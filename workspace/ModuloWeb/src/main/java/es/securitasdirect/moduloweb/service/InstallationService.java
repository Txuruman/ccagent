package es.securitasdirect.moduloweb.service;

import es.securitasdirect.moduloweb.exceptions.BusinessException;
import es.securitasdirect.moduloweb.model.Cuote;
import es.securitasdirect.moduloweb.model.DirectAccess;
import es.securitasdirect.moduloweb.model.DummyGenerator;
import es.securitasdirect.moduloweb.model.InstallationData;
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


//    public List<DirectAccess> getDirectAccess() {
//    	List<DirectAccess> list= new ArrayList<DirectAccess>();//DummyGenerator.getDirectAcess();
//    	if (list.isEmpty()) {
//    		throw new BusinessException(BusinessException.ErrorCode.ERROR_DIRECT_ACCESS_NOT_FOUND);
//		}else{
//			return list;
//		}
//    }


    public InstallationData getInstallation(Integer installationNumber) {
    	InstallationData installation = DummyGenerator.getInstallation(installationNumber);
    	if (installation==null) {
    		throw new BusinessException(BusinessException.ErrorCode.ERROR_INSTALLATION_NOT_FOUND);
		}else{
			return installation;
		}
    	
    }

}
