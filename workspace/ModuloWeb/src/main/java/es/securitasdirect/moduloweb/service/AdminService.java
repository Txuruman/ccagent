package es.securitasdirect.moduloweb.service;

import es.securitasdirect.moduloweb.exceptions.BusinessException;
import es.securitasdirect.moduloweb.model.Audit;
import es.securitasdirect.moduloweb.model.DirectAccess;
import es.securitasdirect.moduloweb.model.DummyGenerator;
import es.securitasdirect.moduloweb.model.FieldConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.ws.dataservice.CCAGENTADMPortType;

import javax.inject.Inject;
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

    @Inject
    protected CCAGENTADMPortType wsAdmin;

    public interface TABS {
        /* Nombres a utilizar en la JSP mainFrame.jsp */
        public static final String INSTALLATION="INST";
        public static final String INVOICING="INV";
    }

    /**
     * Get the field config for a specific app
     * @param app
     * @return
     */
    public List<FieldConfig> getFieldConfig(String app) {
        assert app!=null;
        return DummyGenerator.getFieldConfig();
    }


    /**
     * Query the active Tab for the keys combination
     * @param key1
     * @param key2
     * @param key3
     * @return
     */
    public String getActiveTabFromKeys(String key1, String key2, String key3) {
        return TABS.INSTALLATION;
    }



    public List<DirectAccess> getDirectAccess() {
        List<DirectAccess> list= DummyGenerator.getDirectAcess();
        if (list.isEmpty()) {
            throw new BusinessException(BusinessException.ErrorCode.ERROR_DIRECT_ACCESS_NOT_FOUND);
        }else{
            return list;
        }
    }

}
