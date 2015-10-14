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
        public static final String AVERIAS="AVE";
        public static final String ADMIN="ADM";
    }

    

    /**
     * Query the active Tab for the keys combination
     * Obtenemos todas y comprobamos:
     * Si la hay mas de una que coincide la primera key, comprobamos por la segunda y si tambien hay mas de una comprobamos por la tercera
     * Si al final de todas las comprobaciones hay mas de una coincidencia se va a la por defecto que es instalacion
     * @param key1
     * @param key2
     * @param key3
     * @return
     */
    public String getActiveTabFromKeys(String key1, String key2, String key3) {
    	List<CombinationsKeys> combinationsKeys1=this.getCombinationsKeys();
    	List<CombinationsKeys> combinationsKeys2=new ArrayList<CombinationsKeys>();
    	for (int i = 0; i < combinationsKeys1.size(); i++) {
    		if(combinationsKeys1.get(i).getKey1().equals(key1)){
				combinationsKeys2.add(combinationsKeys1.get(i));
			}
		}
       	if(combinationsKeys2.isEmpty()){
    		return TABS.INSTALLATION;
    	}else if(combinationsKeys2.size()==1){
    		return combinationsKeys2.get(0).getTab();
    	}else{
    		boolean difTab=false;
    		combinationsKeys1=new ArrayList<CombinationsKeys>();
    		for (int i = 0; i < combinationsKeys2.size(); i++) {
    			if(combinationsKeys2.get(i).getKey2().equals(key2)){
    				combinationsKeys1.add(combinationsKeys2.get(i));
    				if(i>0 && !combinationsKeys2.get(i).getTab().equals(combinationsKeys2.get(i-1).getTab())){
    					difTab=true;
    				}
    			}
			}
    		if(combinationsKeys1.isEmpty()){
    			if (difTab) {
    				return TABS.INSTALLATION;
				}else{
					return combinationsKeys2.get(0).getTab();
				}
        	}else if(combinationsKeys1.size()==1){
        		return combinationsKeys1.get(0).getTab();
        	}else{
        		difTab=false;
        		combinationsKeys2=new ArrayList<CombinationsKeys>();
        		for (int i = 0; i < combinationsKeys1.size(); i++) {
        			if(combinationsKeys1.get(i).getKey3().equals(key3)){
        				combinationsKeys2.add(combinationsKeys1.get(i));
        				if(i>0 && !combinationsKeys1.get(i).getTab().equals(combinationsKeys1.get(i-1).getTab())){
        					difTab=true;
        				}
        			}
    			}
        		if(combinationsKeys2.size()==1){
            		return combinationsKeys2.get(0).getTab();
        		}else if(combinationsKeys2.isEmpty()){
        			if (difTab) {
        				return TABS.INSTALLATION;
    				}else{
    					return combinationsKeys1.get(0).getTab();
    				}
            	}else{
            		return TABS.INSTALLATION;
            	}
        	}
    	}
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

    /**
     * Insert Direct Access
     * @return
     */
    public void insertDirectAccess(DirectAccess directAccess){
        try{
            wsAdmin.insertDirectAccessOperation(directAccess.getName(), directAccess.getDescription(), directAccess.getUrl(), directAccess.getPosition());
        }catch(Exception e){
            throw new BusinessException(BusinessException.ErrorCode.ERROR_INSERT_DIRECT_ACCESS);
        }

        try{
            // INSERTAMOS LA LISTA DE PARAMETROS DEL ACCESO DIRECTO
            List<DirectAccessParams> listDirectAccessParams = directAccess.getParams();
            for (DirectAccessParams directAccessParams : listDirectAccessParams) {
                wsAdmin.insertDirectAccessParamsOperation(directAccessParams.getName(), directAccessParams.getValue(), directAccessParams.getDirectAccess());
            }
        }catch(Exception e){
            throw new BusinessException(BusinessException.ErrorCode.ERROR_INSERT_DIRECT_ACCESS_PARAMS);
        }
    }
    /**
     * Update Direct Access
     * @return
     */
    public void updateDirectAccess(DirectAccess directAccess) {
        try{
            wsAdmin.updateDirectAccessOperation(directAccess.getName(), directAccess.getDescription(), directAccess.getUrl(), directAccess.getPosition(), directAccess.getId());
        }catch(Exception e){
            throw new BusinessException(BusinessException.ErrorCode.ERROR_UPDATE_DIRECT_ACCESS);
        }

        try{
            // ACTUALIZAMOS LA LISTA DE PARAMETROS DEL ACCESO DIRECTO
            List<DirectAccessParams> listDirectAccessParams = directAccess.getParams();
            for (DirectAccessParams directAccessParams : listDirectAccessParams) {
                wsAdmin.updateDirectAccessParamsOperation(directAccessParams.getName(), directAccessParams.getValue(), directAccessParams.getDirectAccess(), directAccessParams.getId());
            }
        }catch(Exception e){
            throw new BusinessException(BusinessException.ErrorCode.ERROR_UPDATE_DIRECT_ACCESS_PARAMS);
        }
    }
    /**
     * Delete DirectAccess
     * @param directAccess
     */
    public void deleteDirectAccess(DirectAccess directAccess){
        try{
            wsAdmin.deleteDirectAccessOperation(directAccess.getId());
        }catch(Exception e){
            throw new BusinessException(BusinessException.ErrorCode.ERROR_DELETE_DIRECT_ACCESS);
        }


        try{
            // BORRAMOS LA LISTA DE PARAMETROS DEL ACCESO DIRECTO
            List<DirectAccessParams> listDirectAccessParams = directAccess.getParams();
            for (DirectAccessParams directAccessParams : listDirectAccessParams) {
                wsAdmin.deleteDirectAccessParamsOperation(directAccessParams.getId());
            }
        }catch(Exception e){
            throw new BusinessException(BusinessException.ErrorCode.ERROR_DELETE_DIRECT_ACCESS_PARAMS);
        }
    }

    /**
     * Delete DirectAccess
     * @param directAccess
     */
    public void deleteDirectAccessParams(DirectAccess directAccess){

        try{
            // BORRAMOS LA LISTA DE PARAMETROS DEL ACCESO DIRECTO
            List<DirectAccessParams> listDirectAccessParams = directAccess.getParams();
            for (DirectAccessParams directAccessParams : listDirectAccessParams) {
                wsAdmin.deleteDirectAccessParamsOperation(directAccessParams.getId());
            }
        }catch(Exception e){
            throw new BusinessException(BusinessException.ErrorCode.ERROR_DELETE_DIRECT_ACCESS_PARAMS);
        }
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
        	throw new BusinessException(BusinessException.ErrorCode.ERROR_GET_KEY);
        }

        return listCombinationsKeys;
    }

    /**
     * Insert Combination Keys
     * @return
     */
    public void insertCombinationsKeys(CombinationsKeys combinationKeys){
    	try{
    		wsAdmin.insertCombinationsKeysOperation(combinationKeys.getKey1(), combinationKeys.getKey2(), combinationKeys.getKey3(), combinationKeys.getTab());
    	}catch(Exception e){
    		throw new BusinessException(BusinessException.ErrorCode.ERROR_INSERT_KEY);
    	}
    }
    /**
     * Update Combination Keys
     * @return
     */
    public void updateCombinationsKeys(CombinationsKeys combinationKeys) {
    	try{
    		wsAdmin.updateCombinationsKeysOperation(combinationKeys.getKey1(), combinationKeys.getKey2(), combinationKeys.getKey3(), combinationKeys.getTab(), combinationKeys.getId());
    	}catch(Exception e){
    		throw new BusinessException(BusinessException.ErrorCode.ERROR_UPDATE_KEY);
    	}
	}
    /**
     * Delete Combination Keys
     * @param combinationKeys
     */
    public void deleteCombinationsKeys(CombinationsKeys combinationKeys){
    	try{
    		wsAdmin.deleteCombinationsKeysOperation(combinationKeys.getId());
    	}catch(Exception e){
    		throw new BusinessException(BusinessException.ErrorCode.ERROR_DELETE_KEY);
    	}
    }
    
    
    /**
     * Gestion de campos
     * Listar todos los campos
     * @return
     */
    public List<FieldConfig> getFieldConfig() {

        List<FieldConfig> listFieldConfig = new ArrayList<FieldConfig>();

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
    
    /**
     * Obtener campos por pestaña
     * @param app
     * @return
     */
    public List<FieldConfig> getFieldConfigByApp(String app) {

        List<FieldConfig> listFieldConfig = new ArrayList<FieldConfig>();

        LOGGER.debug("Calling for Get the FieldConfig List of "+app);

        try {
            List<EntrygetFieldConfigByAppResult> listGetFieldConfigResult = wsAdmin.getFieldConfigByAppOperation(app);

            for (EntrygetFieldConfigByAppResult getFieldConfigResult : listGetFieldConfigResult) {
                FieldConfig FieldConfig = new FieldConfig(getFieldConfigResult);
                listFieldConfig.add(FieldConfig);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
            throw new FrameworkException(e);
        }

        return listFieldConfig;
    }
    
    public void addFieldConfig(FieldConfig campo){
    	LOGGER.debug("Calling for insert FieldConfig: {}"+campo);
    	try {
    		wsAdmin.insertFieldConfigOperation(campo.getApp(), campo.getIdentifier(), campo.getDescription(),campo.isVisible(), campo.isEditable(), campo.isAdministrable(),campo.getPosition());
		} catch (Exception e) {
			throw new BusinessException(BusinessException.ErrorCode.ERROR_INSERT_FIELD);
		}
    	
    }

    /**
     * Update Field Config
     * @return
     */
    public void updateFieldConfig(FieldConfig fieldConfig) {
        try{
            wsAdmin.updateFieldConfigOperation(fieldConfig.getApp(), fieldConfig.getIdentifier(), fieldConfig.getDescription(),
                    fieldConfig.isVisible(), fieldConfig.isEditable(), fieldConfig.isAdministrable(),
                    fieldConfig.getPosition(), fieldConfig.getId());
        }catch(Exception e){
            throw new BusinessException(BusinessException.ErrorCode.ERROR_UPDATE_FIELD);
        }
    }
    /**
     * Delete Field Config
     * @param fieldConfig
     */
    public void deleteFieldConfig(FieldConfig fieldConfig){
        try{
            wsAdmin.deleteFieldConfigOperation(fieldConfig.getId());
        }catch(Exception e){
            throw new BusinessException(BusinessException.ErrorCode.ERROR_DELETE_FIELD);
        }
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
