package es.securitasdirect.moduloweb.service;

import es.securitasdirect.moduloweb.model.DirectAccess;
import es.securitasdirect.moduloweb.model.DummyGenerator;
import es.securitasdirect.moduloweb.model.InstallationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.ws.dataservice.Installation;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.List;

/**
 * Service to access the Installation Info
 */
@Named(value = "installationService")
@Singleton
public class InstallationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InstallationService.class);


    public List<DirectAccess> getDirectAccess() {
        return DummyGenerator.getDirectAcess();
    }


    public InstallationData getInstallation(Integer installationNumber) {
        return DummyGenerator.getInstallation(installationNumber);
    }

}
