package es.securitasdirect.moduloweb.service;

import es.securitasdirect.moduloweb.model.DirectAccess;
import es.securitasdirect.moduloweb.model.DummyGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.List;
import java.util.Map;

/**
 * Service to access the Direct Access
 */
@Named(value = "directAccessService")
@Singleton
public class DirectAccessService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DirectAccessService.class);


    public List<DirectAccess> getDirectAccess() {
        return DummyGenerator.getDirectAcess();
    }



}
