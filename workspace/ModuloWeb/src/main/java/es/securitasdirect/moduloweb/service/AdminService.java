package es.securitasdirect.moduloweb.service;

import es.securitasdirect.moduloweb.model.Audit;
import es.securitasdirect.moduloweb.model.DummyGenerator;
import es.securitasdirect.moduloweb.model.FieldConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import javax.inject.Singleton;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * Service to query Admin info.
 */
@Named(value = "adminService")
@Singleton
public class AdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminService.class);


    /**
     * Get the field config for a specific app
     * @param app
     * @return
     */
    public List<FieldConfig> getFieldConfig(String app) {
        assert app!=null;
        return DummyGenerator.getFieldConfig();
    }



}
