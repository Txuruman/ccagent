package es.securitasdirect.moduloweb.service;

import es.securitasdirect.moduloweb.exceptions.BusinessException;
import es.securitasdirect.moduloweb.model.Cuote;
import es.securitasdirect.moduloweb.model.DirectAccess;
import es.securitasdirect.moduloweb.model.DummyGenerator;
import es.securitasdirect.moduloweb.model.InstallationData;
import es.securitasdirect.moduloweb.web.dto.request.SearchInstallationRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.ws.dataservice.DataServiceFault;
import org.wso2.ws.dataservice.GetAlarmIdsBasicInfoResult;
import org.wso2.ws.dataservice.GetMonitoringStatusResult;
import org.wso2.ws.dataservice.Installation;
import org.wso2.ws.dataservice.Mainstallationdataresult;
import org.wso2.ws.dataservice.ResultcheckInstallationNumber;
import org.wso2.ws.dataservice.SPInstallationMonDataPortType;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Service to access the Installation Info
 */
@Named(value = "installationService")
@Singleton
public class InstallationService {
	
	@Inject
    protected SPInstallationMonDataPortType spInstallationMonData;
	
	
	
    private static final Logger LOGGER = LoggerFactory.getLogger(InstallationService.class);


    /**
     * Obtener instalación por installationNumber
     * @param installationNumber
     * @return
     */
    public InstallationData getInstallation(String installationNumber) {
    	//Comprobamos la instalacion
    	List<GetMonitoringStatusResult> getMonitoringStatusResult;
    	InstallationData installation =new InstallationData();
    	try {
    		//TODO:BORRAR
			installationNumber="1087817";
			
			getMonitoringStatusResult=spInstallationMonData.getMonitoringStatus(Integer.parseInt(installationNumber));
			LOGGER.debug("Monitoring status LIST {}", getMonitoringStatusResult);
			if (!getMonitoringStatusResult.isEmpty()) {
				String monitoringStatus=getMonitoringStatusResult.get(0).getMonStat();
	    		LOGGER.debug("Monitoring status {}", monitoringStatus);
			}
    		
			//TODO: comprobar estado
        	//Si el estado es correcto buscamos la instalacion
			
			/**
			 * WS getInstallationData
			 * in: installationNumber
			 * out: List<Mainstallationdataresult>
			 */
			List<Mainstallationdataresult> mainstallationdataresults=spInstallationMonData.getInstallationData(installationNumber);
			//Si no hay instalación no se sigue
			if (mainstallationdataresults.isEmpty()) {
				//TODO: Exception no instalacion
			}
			//Cogemos el primer elemento de la lista
			Mainstallationdataresult mainstallationdataresult=mainstallationdataresults.get(0);
			
			//Instalation Number
			installation.setInstallationNumber(mainstallationdataresult.getInsNo());
			//Panel
			installation.setPanel(mainstallationdataresult.getPanel());
			//CustomerName --> Apellidos, Nombre 
			installation.setCustomerName(mainstallationdataresult.getName() + ", " + mainstallationdataresult.getFname());
			//Address --> calle "calle" numero codigo postal
			installation.setAddress(mainstallationdataresult.getStreet1No2() +" "+ mainstallationdataresult.getStreet2() +" "+ mainstallationdataresult.getStreet1No1() + ", " + mainstallationdataresult.getZip());
			//City --> Ciudad, provincia
			installation.setCity(mainstallationdataresult.getCity()+ ", " +mainstallationdataresult.getCity2());
			//Monitoring Status
			installation.setMonitoringStatus(mainstallationdataresult.getMonStat());
			//Panel Phone
			installation.setPanelPhone(mainstallationdataresult.getPhone());
			//Language
			installation.setLanguage(mainstallationdataresult.getIdiomaServicio());
			//Email de servicio
			installation.setEmailServicios(mainstallationdataresult.getEmailServicio());
			//Lista de planes de accion
			installation.setActionplans(mainstallationdataresult.getInstallationcontactsresults().getInstallationcontactsresult());
			
			/**
			 * WS CheckInstallationNumber
			 * in: country, sins, instalationNumber
			 * out: resultcheckInstallationNumber
			 */
			List<ResultcheckInstallationNumber> resultcheckInstallationNumber=spInstallationMonData.checkInstallationNumber("ES", 0, installationNumber);
			LOGGER.debug("WS checkInstallationNumber {}", resultcheckInstallationNumber);
			
			BigInteger sins=resultcheckInstallationNumber.get(0).getSins();
			String dealer=resultcheckInstallationNumber.get(0).getDealer();
			
			/**
			 * WS getAlarmIdsBasicInfo
			 * in: installationNumber, cid=dealer (leído en checkInstallationNumber), alaid (leído en getInstallationData)
			 * out: revision=versión del panel
			 */
			List<GetAlarmIdsBasicInfoResult> versions=spInstallationMonData.getAlarmIdsBasicInfo(sins.intValue(), Integer.parseInt(dealer), mainstallationdataresult.getAlaid());
			//Version del panel
			installation.setVersion(versions.get(0).getRevision());
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (DataServiceFault e) {
			e.printStackTrace();
		}
    	
		return installation;
    	
//    	InstallationData installation = DummyGenerator.getInstallation(installationNumber);
//    	if (installation==null) {
//    		throw new BusinessException(BusinessException.ErrorCode.ERROR_INSTALLATION_NOT_FOUND);
//		}else{
//			return installation;
//		}
    	
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
