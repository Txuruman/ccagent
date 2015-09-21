package es.securitasdirect.moduloweb.service;

import es.securitasdirect.moduloweb.exceptions.BusinessException;
import es.securitasdirect.moduloweb.exceptions.FrameworkException;
import es.securitasdirect.moduloweb.model.*;
import es.securitasdirect.moduloweb.model.CombinationsKeys;
import es.securitasdirect.moduloweb.model.DirectAccess;
import es.securitasdirect.moduloweb.model.DirectAccessParams;
import es.securitasdirect.moduloweb.model.FieldConfig;
import es.securitasdirect.moduloweb.model.TabKeys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.ws.dataservice.*;


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

   List<DirectAccess> listDirectAccess = new ArrayList();

        LOGGER.debug("Calling for Get the DirectAccess List");

        try {
            List<GetDirectAccessResult> listGetDirectAccessResult = wsAdmin.getDirectAccess();

            for (GetDirectAccessResult getDirectAccessResult : listGetDirectAccessResult) {

                List<GetDirectAccessParamsResult> listGetDirectAccessParamsResult = wsAdmin.getDirectAccessParams(Integer.parseInt(getDirectAccessResult.getId()));

                // LISTA DE PARAMETROS DE CADA ACCESO DIRECTO
                List<DirectAccessParams> listDirectAccessParams = new ArrayList();
                for (GetDirectAccessParamsResult getDirectAccessParamsResult : listGetDirectAccessParamsResult) {
                    listDirectAccessParams.add(new DirectAccessParams(getDirectAccessParamsResult));
                }

                DirectAccess directAccess = new DirectAccess(getDirectAccessResult);
                directAccess.setParams(listDirectAccessParams);
                listDirectAccess.add(directAccess);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
            throw new FrameworkException(e);
        }

        return listDirectAccess;
    }
    
    public List<TabKeys> getTabKeys(){
    	List<TabKeys> tabKeys= DummyGenerator.getTabKeys();
    	if (tabKeys.isEmpty()) {
            throw new BusinessException(BusinessException.ErrorCode.ERROR_TAB_KEYS_NOT_FOUND);
        }else{
            return tabKeys;
        }
    }

    /**
     * Get the CombinationsKeys List
     *
     * @return
     */
    public List<CombinationsKeys> getCombinationsKeys() {

        List<CombinationsKeys> listCombinationsKeys = new ArrayList();

        LOGGER.debug("Calling for Get the CombinationsKeys List");

        try {
            List<GetCombinationsKeysResult> listGetCombinationsKeysResult = wsAdmin.getCombinationsKeys();

            for (GetCombinationsKeysResult getCombinationsKeysResult : listGetCombinationsKeysResult) {
                CombinationsKeys CombinationsKeys = new CombinationsKeys(getCombinationsKeysResult);
                listCombinationsKeys.add(CombinationsKeys);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
            throw new FrameworkException(e);
        }

        return listCombinationsKeys;
    }


    public List<FieldConfig> getFieldConfig() {

        List<FieldConfig> listFieldConfig = new ArrayList();

        LOGGER.debug("Calling for Get the FieldConfig List");

        try {
            List<GetFieldConfigResult> listGetFieldConfigResult = wsAdmin.getFieldConfig();

            for (GetFieldConfigResult getFieldConfigResult : listGetFieldConfigResult) {
                FieldConfig FieldConfig = new FieldConfig(getFieldConfigResult);
                listFieldConfig.add(FieldConfig);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
            throw new FrameworkException(e);
        }

        return listFieldConfig;
    }

    public List<Users> getUsers() {

        List<Users> listUsers = new ArrayList();

        LOGGER.debug("Calling for Get the Users List");

        try {
            List<GetUsersResult> listGetUsersResult = wsAdmin.getUsers();

            for (GetUsersResult getUsersResult : listGetUsersResult) {
                Users Users = new Users(getUsersResult);
                listUsers.add(Users);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
            throw new FrameworkException(e);
        }

        return listUsers;
    }


}
