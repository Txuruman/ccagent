package es.securitasdirect.moduloweb.service;

import es.securitasdirect.moduloweb.exceptions.BusinessException;
import es.securitasdirect.moduloweb.exceptions.FrameworkException;
import es.securitasdirect.moduloweb.model.Audit;
import es.securitasdirect.moduloweb.model.DirectAccess;
import es.securitasdirect.moduloweb.model.DummyGenerator;
import es.securitasdirect.moduloweb.model.FieldConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.ws.dataservice.CCAGENTADMPortType;
import org.wso2.ws.dataservice.GetDirectAccessResult;


import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.lang.reflect.Field;
import java.util.ArrayList;
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


    /**
     * Get the DirectAccess List
     *
     * @return
     */
    public List<DirectAccess> getDirectAccess() {

        List<DirectAccess> list = new ArrayList();

        LOGGER.debug("Calling for Get the DirectAccess List");

        try {
            List<GetDirectAccessResult> listGetDirectAccessResult = wsAdmin.getDirectAccess();

            for (GetDirectAccessResult getDirectAccessResult : listGetDirectAccessResult) {
                list.add(new DirectAccess(getDirectAccessResult));
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
            throw new FrameworkException(e);
        }

        return list;
    }

}
