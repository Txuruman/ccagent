package es.securitasdirect.moduloweb.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.ws.dataservice.Billingdataresult;
import org.wso2.ws.dataservice.DataServiceFault;
import org.wso2.ws.dataservice.GetAlarmIdsBasicInfoResult;
import org.wso2.ws.dataservice.GetCodeWordsResult;
import org.wso2.ws.dataservice.GetEmail;
import org.wso2.ws.dataservice.GetInstallation;
import org.wso2.ws.dataservice.GetMonitoringStatusResult;
import org.wso2.ws.dataservice.GetTieneCamara;
import org.wso2.ws.dataservice.GetTiposTelefonoResult;
import org.wso2.ws.dataservice.Inetaplangetcontactresult;
import org.wso2.ws.dataservice.Inetcalllistgetcontresult;
import org.wso2.ws.dataservice.Inetcodewordchangeresult;
import org.wso2.ws.dataservice.Inetinstallationupdresult;
import org.wso2.ws.dataservice.Installation;
import org.wso2.ws.dataservice.Mainstallationdataresult;
import org.wso2.ws.dataservice.ResultcheckInstallationNumber;
import org.wso2.ws.dataservice.SPIBSActionPlanDataPortType;
import org.wso2.ws.dataservice.SPInstallationBillDataPortType;
import org.wso2.ws.dataservice.SPInstallationMonDataPortType;

import com.securitasdirect.ws.common.response.xsd.GetCamerasResultResponse;

import es.securitasdirect.moduloweb.exceptions.BusinessException;
import es.securitasdirect.moduloweb.model.ActionPlan;
import es.securitasdirect.moduloweb.model.Agent;
import es.securitasdirect.moduloweb.model.Audit;
import es.securitasdirect.moduloweb.model.InstallationData;
import es.securitasdirect.moduloweb.model.Phone;
import es.securitasdirect.moduloweb.service.model.SearchInstallationResult;
import ws.es.securitasdirect.com.CamServicePortType;

/**
 * Service to access the Installation Info
 */
@Named(value = "installationService")
@Singleton
public class InstallationService {
	
	@Inject
    protected SPInstallationMonDataPortType spInstallationMonData;
	
	@Inject
	protected SPIBSActionPlanDataPortType spIBSActionPlanData;
	
	@Inject
	protected AuditService auditService;
	
	@Inject
	protected SPInstallationBillDataPortType spInstallationBillDataPortType;
	
	@Inject
	protected CamServicePortType camServicePortType;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(InstallationService.class);

    /**Crear comentario */
    public void crearComentario(InstallationData installation, Agent agent, String event) {
    	spInstallationMonData.setCrearComentario(agent.getAgentIBS(), event, "BINS", installation.getInstallationNumber(), event, installation.getDealer());
    }
    
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
//			installationNumber="1087817";
    		
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
			 * WS getMonitoringStatus
			 * in: installationNumber
			 * out:
			 */
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
			if (!mainstallationdataresults.isEmpty()) {
				//Cogemos el primer elemento de la lista
				Mainstallationdataresult mainstallationdataresult=mainstallationdataresults.get(0);
				
				//TODO: BORRAR
				List<Billingdataresult> d=spInstallationBillDataPortType.getBillingInformation(mainstallationdataresult.getSIns().intValue());
				
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
				//Email de monitoring
	            installation.setEmailMonitoring(mainstallationdataresult.getEmailServicio()); //David dijo que eran iguales
	            //Email de servicio
	            installation.setEmailServices(mainstallationdataresult.getEmailServicio());
	            //Telefono de servicio
	            installation.setTelefonoServicios(mainstallationdataresult.getTelefonoServicio());
	            //AKA
	            installation.setAka(mainstallationdataresult.getAliasName());
	            //Phone3
	            installation.setPhone3(mainstallationdataresult.getPhone3());
	            //sins
	            installation.setSins(mainstallationdataresult.getSIns().toString());
	            /** Claves
	             * Si no esta vacia la lista
	             * Comprobar si hay suficientes antes de obtenerlas
	             */
	            if (!mainstallationdataresult.getGetCodeWordsResults().getGetCodeWordsResult().isEmpty()){
	            	 List<GetCodeWordsResult> results= mainstallationdataresult.getGetCodeWordsResults().getGetCodeWordsResult();
	            	for (GetCodeWordsResult result : results) {
						if(result.getIx().intValue()==1){
							 //Customer Password
							installation.setCustomerPassword(result.getCode().toString());
						}else if(result.getIx().intValue()==2){
							//Securitas Password
							installation.setSecuritasPassword(result.getCode().toString());
						}else if(result.getIx().intValue()==5){
							//Coercion Password
							String test= result.getCode();
//							installation.setCoercionPassword(result.getCode().toString());
							installation.setCoercionPassword(test);
							System.out.println(installation.getCoercionPassword());
						}
					}
	            }
	            
				/**
				 * Lista de planes de accion
				 * WS inet_call_list_getcont
				 * pix=-1 Con este valor devuelve todos los planes, si no devuelve uno en concreto
				 * SIns numero interno de la instalacion
				 */
	        	
				//installation.setActionplans(mainstallationdataresult.getInstallationcontactsresults().getInstallationcontactsresult());
	            Integer pix=0; 
				List<Inetcalllistgetcontresult> inetcalllistgetcontresults=spIBSActionPlanData.inetCallListGetCont(mainstallationdataresult.getSIns().intValue(), pix);
	            List<ActionPlan> listaContactos=new ArrayList<ActionPlan>();
	            for (Inetcalllistgetcontresult inetcalllistgetcontresult : inetcalllistgetcontresults) {
					
					//Los contactos con seq<0 son las agencias
					if (Integer.parseInt(inetcalllistgetcontresult.getSeq())>0 && Integer.parseInt(inetcalllistgetcontresult.getPix())!=999){
						
						ActionPlan a=new ActionPlan();
						
						a.setSins(mainstallationdataresult.getSIns().toString());
						a.setName(inetcalllistgetcontresult.getName());
						
						a.setSeq(Integer.parseInt(inetcalllistgetcontresult.getSeq()));
						
						//El tipo siempre será 0
						a.setType("0");
						
						a.setPix(inetcalllistgetcontresult.getPix());
						
						a.setScix(inetcalllistgetcontresult.getSCix());
						
						a.setScont(inetcalllistgetcontresult.getSCont());
						
						a.setSpc(inetcalllistgetcontresult.getSPc());
						
						//Obtenemos los tipos y los telefonos de contacto
						List<Inetaplangetcontactresult> inetaplangetcontactresult = spIBSActionPlanData.inetAplanGetContact(mainstallationdataresult.getSIns().intValue(),
								Integer.parseInt(inetcalllistgetcontresult.getPix()),	Integer.parseInt(inetcalllistgetcontresult.getSPc()),
								Integer.parseInt(inetcalllistgetcontresult.getSCont()),Integer.parseInt(inetcalllistgetcontresult.getSCix()),"");
						//Telefonos
						Phone p1=new Phone();
						Phone p2=new Phone();
						Phone p3=new Phone();
						
						//Phone1
						p1.setNumber(inetaplangetcontactresult.get(0).getPh1());
						p1.setType(inetaplangetcontactresult.get(0).getPh1Type());
						a.setPhone1(p1);
						
						//Phone2
						p2.setNumber(inetaplangetcontactresult.get(0).getPh2());
						p2.setType(inetaplangetcontactresult.get(0).getPh2Type());
						a.setPhone2(p2);
						
						//Phone3
						p3.setNumber(inetaplangetcontactresult.get(0).getPh3());
						p3.setType(inetaplangetcontactresult.get(0).getPh3Type());
						a.setPhone3(p3);
						
						listaContactos.add(a);
					}
				}
	            installation.setActionplans(listaContactos);
				
				/**
				 * WS getAlarmIdsBasicInfo
				 * in: installationNumber, cid=dealer (leído en checkInstallationNumber), alaid (leído en getInstallationData)
				 * out: revision=versión del panel
				 */
				List<GetAlarmIdsBasicInfoResult> versions=spInstallationMonData.getAlarmIdsBasicInfo(sins.intValue(), Integer.parseInt(dealer), mainstallationdataresult.getAlaid());
				//Version del panel
				installation.setVersion(versions.get(0).getRevision());
				//Phone 2
	            installation.setPhone2(spInstallationMonData.getPhone2(sins.intValue()).get(0).getPhone2());
	            
//	            /**
//	             * Comprobar si email billing es igual que el monitoring, si son distintos se avisa
//	             * Utilizar para la facturacion
//	             */
	            List<GetEmail> getEmails=spInstallationBillDataPortType.getEmailDataFromInstallation(installationNumber);
				if (!getEmails.isEmpty()) {
					if(!getEmails.get(0).getEmail().equals(installation.getEmailMonitoring())){
						installation.setEmailsAreDiferents(true);
					}
					//Email Billing
					installation.setEmailBilling(getEmails.get(0).getEmail());
				}
				
				/**
                 * WS getInstallationTieneCamara
                 * in: sins
                 * out: tieneCamaras
                 */
                 List<GetTieneCamara> tieneCamaras = spInstallationMonData.getInstallationTieneCamara(sins.intValue());
         
		         if ((tieneCamaras.get(0).getVideo()!=null) && (tieneCamaras.get(0).getVideo().equals("X"))){
		         //Tiene camara y lanzamos el WS CamService para obtener si tiene Samsung o Icantec
		           /**
	                * WS getCameras
	                * in: "ESP","CCAgent",installationNumber
	                * out: camaras
	                */
		         GetCamerasResultResponse camaras = camServicePortType.getCameras("ESP", "CCAgent", installationNumber);
		         
		          if ((camaras.getZonesList()!=null) && (camaras.getZonesList().size()>0)){
		                 installation.setCamera("SAMSUNG");
		         }
		         else{
		                 installation.setCamera("ICANTEC");
		         }
		         }
		         else{
		         //No tiene camara
		         installation.setCamera("NO");
		         }

			}else{
				 LOGGER.error("Can't find installation");
	             throw new BusinessException(BusinessException.ErrorCode.ERROR_FIND_INSTALLATION);
			}
		} catch (NumberFormatException e) {
			throw new BusinessException(BusinessException.ErrorCode.ERROR_INSTALLATION_NOT_FOUND);
		} catch (DataServiceFault e) {
			throw new BusinessException(BusinessException.ErrorCode.ERROR_INSTALLATION_NOT_FOUND);
		}catch (Exception e) {
			throw new BusinessException(BusinessException.ErrorCode.ERROR_INSTALLATION_NOT_FOUND);
		}
		return installation;
    }
    
    /**
     * Buscamos instalaciones
     * Si el campo installationNumber esta relleno y no es la instalación activa --> Se busca por installationNumber
     * Si el campo telefono esta relleno e installationNumber no es la instalacion activa --> Se busca por telefono
     * Si el campo email esta relleno, installationNumber no es la instalacion activa y telefono esta vacio --> Se busca por email
     * @param request
     * @return
     */
    public SearchInstallationResult searchInstallations(String installationNumber, String phone, String email, InstallationData installationActive){
    	SearchInstallationResult searchInstallationResult=new SearchInstallationResult();
    	if (installationActive==null) {
    		installationActive=new InstallationData();
    		installationActive.setInstallationNumber("");
		}
    	//Si el campo installationNumber esta relleno y no es la instalación activa --> Se busca por installationNumber
    	if (installationNumber!=null && !installationNumber.isEmpty() && !installationNumber.equals(installationActive.getInstallationNumber())) {
    		List<InstallationData> installationList=new ArrayList<InstallationData>();
    		installationList.add(this.getInstallation(installationNumber));
    		searchInstallationResult.setInstallationList(installationList);
    		searchInstallationResult.setSearchBy(0);
		}
    	
    	//Si el campo telefono esta relleno e installationNumber no es la instalacion activa --> Se busca por telefono
    	else if (phone!=null && !phone.isEmpty()) {
    		searchInstallationResult.setInstallationList(this.searchByPhone(phone));
    		searchInstallationResult.setSearchBy(1);
		}
    	//Si el campo email esta relleno, installationNumber no es la instalacion activa y telefono esta vacio --> Se busca por email
    	else if(email!=null && !email.isEmpty()){
    		searchInstallationResult.setInstallationList(this.searchByEmail(email));
    		searchInstallationResult.setSearchBy(2);
		}
    	
    	else{
    		searchInstallationResult.setNoSearched(true);
		}
    	return searchInstallationResult;
    }

	private List<InstallationData> searchByEmail(String email) {
		 LOGGER.debug("Buscando por email {}", email);
		 List<GetInstallation> getInstallation=new ArrayList<GetInstallation>();
		 try {
			getInstallation=spInstallationBillDataPortType.getInstallationDataFromEmail(email);
			LOGGER.debug("Buscando por email Resultado: {}", getInstallation);
		} catch (Exception e) {
			throw new BusinessException(BusinessException.ErrorCode.ERROR_INSTALLATION_NOT_FOUND);
		}
		 if(!getInstallation.isEmpty()){
			 List<InstallationData> installations=new ArrayList<InstallationData>();
			 for (GetInstallation installationNumber : getInstallation) {
				 installations.add(this.getInstallation(installationNumber.getInstallation()));
			 }
			 return installations;
		 }else{
			 LOGGER.error("Can't find installation");
			 throw new BusinessException(BusinessException.ErrorCode.ERROR_FIND_INSTALLATION);
		 }
	}
	/**
	 * Busqueda por telefono
	 * @param phone
	 * @return Lista de instalaciones
	 */
	private List<InstallationData> searchByPhone(String phone) {
		 LOGGER.debug("Buscando por telefono {}", phone);
		 List<Installation> installationNumbers=new ArrayList<Installation>();
		 try {
			installationNumbers=spInstallationMonData.getInstallationDataFromPhone(phone);
			LOGGER.debug("Buscando por telefono Resultado: {}", installationNumbers);
		 } catch (Exception e) {
			 throw new BusinessException(BusinessException.ErrorCode.ERROR_INSTALLATION_NOT_FOUND);
		 }
		if(!installationNumbers.isEmpty()){
			List<InstallationData> installations=new ArrayList<InstallationData>();
			for (Installation installationNumber : installationNumbers) {
				installations.add(this.getInstallation(installationNumber.getInstallation()));
			}
			return installations;
		}else{
			LOGGER.error("Can't find installation");
            throw new BusinessException(BusinessException.ErrorCode.ERROR_FIND_INSTALLATION);
		}
	}

	/**
	 * Actualización de valores para las palabras clave
	 * @param agent - agente para la auditoria
	 * @param codeword --> Si viene vacio se elimina la clave
	 * @param ix
	 * @param installationNumber
	 */
	public void codewordChange(String agent, String codeword, Integer ix, String installationNumber){
		//Auditoria
		Audit audit=new Audit();
		audit.setAction("codewordChange");
		audit.setApp("Info instalacion");
		audit.setUser(agent);
		audit.setDate(new Date());
		
		/**
		 * Obtención del SINS
		 * WS CheckInstallationNumber
		 * in: country, sins, instalationNumber
		 * out: resultcheckInstallationNumber
		 */
		List<ResultcheckInstallationNumber> resultcheckInstallationNumber;
		try {
			//TODO: mirar parametros de entrada
			resultcheckInstallationNumber = spInstallationMonData.checkInstallationNumber("ES", 0, installationNumber);
			LOGGER.debug("WS checkInstallationNumber {}", resultcheckInstallationNumber);
			Integer sIns= resultcheckInstallationNumber.get(0).getSins().intValue();
			
			/**
			 * Valores opcionales
			 */
			Integer cix= ix;
			String userId= "";
			String misc1= "";
			String misc2= "";
			Integer sCtr= 0;
			Integer cred = 0;
			Integer vtp= -1;
			String expdate= "";
			Integer sPc= 0;
			Integer sCont= 0;
			String type= "";
		
			List<Inetcodewordchangeresult> inetcodewordchangeresult=spInstallationMonData.inetCodewordChange(sIns, ix, codeword, type, expdate, vtp, sCtr, sPc, sCont, cix, misc1, misc2, cred, userId);
			
			audit.setResult("OK");
			audit.setDetail("Clave actualizada "+codeword.toString());
			auditService.insert(audit);
			if (Integer.parseInt(inetcodewordchangeresult.get(0).getReturnCode())!=0) {
				audit.setResult("FAIL");
				audit.setDetail(inetcodewordchangeresult.toString());
				auditService.insert(audit);
				throw new BusinessException(BusinessException.ErrorCode.ERROR_UPDATE_CODEWORD);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BusinessException(BusinessException.ErrorCode.ERROR_UPDATE_CODEWORD);
		}
	}
	
	public void updateInstallation(String agent, InstallationData installationData){
		//Auditoria
		Audit audit=new Audit();
		audit.setAction("updateInstallation");
		audit.setApp("Info instalacion");
		audit.setUser(agent);
		audit.setDate(new Date());
		try {
//			List<Mainstallationdataresult> mainstallationdataresults=spInstallationMonData.getInstallationData(installationData.getInstallationNumber());
//			Mainstallationdataresult mainstallationdataresult=mainstallationdataresults.get(0);
			
			List<org.wso2.ws.dataservice.InstallationData> installationDatas=spInstallationMonData.sdInetInstallationGetInformation(Integer.parseInt(installationData.getSins()));
			if (!installationDatas.isEmpty()) {
				org.wso2.ws.dataservice.InstallationData installationDataWS=installationDatas.get(0);	
				
				/**
				 * installationData
				 * solo son editables los telefonos y los emails
				 */
				String phone3=installationData.getPhone3();
				String phone2=installationData.getPhone2();
				String phone1=installationData.getPanelPhone();
				//TODO: asegurarse de email es cual
				String email2=installationData.getEmailServices();
				String email1=installationData.getEmailMonitoring();
				
				/**
				 * mainstallationdataresult
				 */
				String subTp=installationDataWS.getSubTp();
				String panel1=installationDataWS.getPanel1();
				String insNo=installationDataWS.getInsNo();
				String monStat=installationDataWS.getMonStat();
				String name=installationDataWS.getName();
				String fname=installationDataWS.getFname();
				String iname=installationDataWS.getIname();
				String street1No2=installationDataWS.getStreet1No2();
				String street1No1=installationDataWS.getStreet1No1();
				String street1=installationDataWS.getStreet1();
				String street2=installationDataWS.getStreet2();
				String city=installationDataWS.getCity();
				String city2=installationDataWS.getCity2();
				String state=installationDataWS.getState();
				String zip=installationDataWS.getZip();
				String aliasName=installationDataWS.getAliasName();
				Integer sIns=installationDataWS.getSIns().intValue();
				String alaid1=installationDataWS.getAlaid1();
				String dealId=installationDataWS.getDealId();
				String lang=installationDataWS.getLang();
				
				/**
				 * Falta
				 */
				String userId=agent;
				Integer stamp=1;
				
				/**
				 * Desconocidos
				 */
				String repFlg=installationDataWS.getRepFlg();
				String tmzonpr=installationDataWS.getTmzonpr();
				String id=installationDataWS.getId();
				String passver=installationDataWS.getPassver();
				String protArea=installationDataWS.getProtArea();
				String pbr=installationDataWS.getPbr();
				String testInt3=installationDataWS.getTestInt3();
				String skill=installationDataWS.getSkill();
				String mgrp2=installationDataWS.getMgrp2();
				String panel2=installationDataWS.getPanel2();
				String prtreq=installationDataWS.getPrtreq();
				String insuId=installationDataWS.getInsuId();
				String tname=installationDataWS.getTname();
				String dcc1=installationDataWS.getDcc1();
				String insDate=installationDataWS.getInsDate();
				String brSale=installationDataWS.getBrSale();
				String mapGrid=installationDataWS.getMapGrid();
				String telcoLoc=installationDataWS.getTelcoLoc();
				String perm=installationDataWS.getPerm().toString();
				String txtzonref=installationDataWS.getTxtzonref();
				String miscdate3=installationDataWS.getMiscdate3();
				String phoArea=installationDataWS.getPhoArea();
				String comment2=installationDataWS.getComment2();
				String miscdate1=installationDataWS.getMiscdate1();
				String crossStreet=installationDataWS.getCrossStreet();
				
				
				String alaid2=installationDataWS.getAlaid2();
				String phtxt1=installationDataWS.getPhtxt1();
				String phtxt2=installationDataWS.getPhtxt2();
				String sales2=installationDataWS.getSales2();
				String miscdate6=installationDataWS.getMiscdate6();
				String inst1=installationDataWS.getInst1();
				String monStatDate=installationDataWS.getMonStatDate();
				String panelLoc=installationDataWS.getPanelLoc();
				String ocname=installationDataWS.getOcname();
				String miscno1=installationDataWS.getMiscno1();
				String testInt1=installationDataWS.getTestInt1();
				String maintype=installationDataWS.getMaintype();
				String instId=installationDataWS.getInstId();
				String code=installationDataWS.getCode();
				String miscdate5=installationDataWS.getMiscdate5();
				String sales1=installationDataWS.getSales1();
				String alaid3=installationDataWS.getAlaid3();
				String comment1=installationDataWS.getComment1();
				String miscdate2=installationDataWS.getMiscdate2();
				String panel3=installationDataWS.getPanel3();
				String fax=installationDataWS.getFax();
				String duress=installationDataWS.getDuress();
				String backupver=installationDataWS.getBackupver();
				String testInt2=installationDataWS.getTestInt2();
				String mainexpdate=installationDataWS.getMainexpdate();
				String br=installationDataWS.getBr();
				String subDeal=installationDataWS.getSubDeal();
				String miscdate4=installationDataWS.getMiscdate4();
				String dcc2=installationDataWS.getDcc2();
				String rbr=installationDataWS.getRbr();
				String mgrp1=installationDataWS.getMgrp1();
				String mbr=installationDataWS.getMbr();
				String ocexp=installationDataWS.getOcexp();
				String subzone=installationDataWS.getSubzone();
				String brServ=installationDataWS.getBrServ();
				String ro=installationDataWS.getRo();
				String subExptp=installationDataWS.getSubExptp();
				String sbr=installationDataWS.getSbr();
				String mainfreq=installationDataWS.getMainfreq().toString();
				String preg=installationDataWS.getPreg();
				String brInst=installationDataWS.getBrInst();
				String refcid=installationDataWS.getRefcid();
				String sreg=installationDataWS.getSreg();
				String inst2=installationDataWS.getInst2();
				
				
				/**
				 * misc
				 */
				String misc45=installationDataWS.getMisc45();
				String misc28=installationDataWS.getMisc28();
				String misc5=installationDataWS.getMisc5();
				String misc48=installationDataWS.getMisc48();
				String misc27=installationDataWS.getMisc27();
				String misc3=installationDataWS.getMisc3();
				String misc44=installationDataWS.getMisc44();
				String misc49=installationDataWS.getMisc49();
				String misc1=installationDataWS.getMisc1();
				String misc29=installationDataWS.getMisc29();
				String misc39=installationDataWS.getMisc39();
				String misc20=installationDataWS.getMisc20();
				String misc6=installationDataWS.getMisc6();
				String miscno2=installationDataWS.getMisc2();
				String misc35=installationDataWS.getMisc35();
				String misc43=installationDataWS.getMisc43();
				String misc31=installationDataWS.getMisc31();
				String misc36=installationDataWS.getMisc36();
				String misc34=installationDataWS.getMisc34();
				String misc46=installationDataWS.getMisc46();
				String misc41=installationDataWS.getMisc41();
				String misc47=installationDataWS.getMisc47();
				String misc38=installationDataWS.getMisc38();
				String misc10=installationDataWS.getMisc10();
				String misc22=installationDataWS.getMisc22();
				String misc23=installationDataWS.getMisc23();
				String misc26=installationDataWS.getMisc26();
				String misc24=installationDataWS.getMisc24();
				String misc32=installationDataWS.getMisc32();
				String misc80=installationDataWS.getMisc80();
				String misc4=installationDataWS.getMisc4();
				String misc37=installationDataWS.getMisc37();
				String misc25=installationDataWS.getMisc25();
				String misc30=installationDataWS.getMisc30();
				String misc2=installationDataWS.getMisc2();
				String misc9=installationDataWS.getMisc9();
				String misc21=installationDataWS.getMisc21();
				String misc33=installationDataWS.getMisc33();
				String misc7=installationDataWS.getMisc7();
				String misc8=installationDataWS.getMisc8();
				String misc40=installationDataWS.getMisc40();
				String misc50=installationDataWS.getMisc50();
				String misc42=installationDataWS.getMisc42();
				
				
				List<Inetinstallationupdresult> inetinstallationupdresult=spInstallationMonData.inetInstallationUpd(sIns, stamp, insNo, dealId, subDeal, alaid1, alaid2, alaid3, panel1, panel2, panel3, testInt1, testInt2, testInt3, id, code, perm, tmzonpr, subzone, passver, instId, insDate, monStat, monStatDate, subTp, comment1, comment2, txtzonref, phoArea, subExptp, repFlg, insuId, name, fname, iname, tname, street1, street1No1, street1No2, street2, city, city2, state, zip, aliasName, phone1, phone2, phone3, phtxt1, phtxt2, fax, crossStreet, mapGrid, mgrp1, mgrp2, duress, telcoLoc, panelLoc, br, rbr, sbr, mbr, pbr, sreg, preg, refcid, miscno1, miscno2, ro, skill, lang, dcc1, dcc2, backupver, ocexp, ocname, prtreq, maintype, mainfreq, mainexpdate, inst1, inst2, sales1, sales2, brInst, brSale, brServ, miscdate1, miscdate2, miscdate3, miscdate4, miscdate5, miscdate6, misc1, misc2, misc3, misc4, misc5, misc6, misc7, misc8, misc9, misc10, misc20, misc21, misc22, misc23, misc24, misc25, misc26, misc27, misc28, misc29, misc30, misc31, misc32, misc33, misc34, misc35, misc36, misc37, misc38, misc39, misc40, misc41, misc42, misc43, misc44, misc45, misc46, misc47, misc48, misc49, misc50, userId, protArea, email1, email2, misc80);
				LOGGER.debug("Update installation result: {}", inetinstallationupdresult);
				audit.setResult("OK");
				audit.setDetail("Instalacion actualizada "+ installationData.getInstallationNumber());
				auditService.insert(audit);
			}
		} catch (Exception e) {
			audit.setResult("FAIL");
			audit.setDetail(BusinessException.ErrorCode.ERROR_UPDATE_INSTALLATION.toString());
			auditService.insert(audit);
			throw new BusinessException(BusinessException.ErrorCode.ERROR_UPDATE_INSTALLATION);
		}
	}
	
	/**
	 * 
	 * metodo para obtener el tipo de telefono de los contactos
	 * @param actionplan
	 * @param sins
	 * @param pix
	 * @param spc
	 * @param scont
	 * @param cix
	 * @param timezone
	 * @throws DataServiceFault 
	 */
//	private void getTypesPhones(ActionPlan actionplan, String sins, String pix, String spc, 
//			String scont, String cix, String timezone) throws DataServiceFault{
//		
//		try {
//			List<Inetaplangetcontactresult> inetaplangetcontactresult = spIBSActionPlanData.inetAplanGetContact(Integer.parseInt(sins),Integer.parseInt(pix),Integer.parseInt(spc),
//					Integer.parseInt(scont),Integer.parseInt(cix),timezone);
//		//Telefonos
//		Phone p1=new Phone();
//		Phone p2=new Phone();
//		Phone p3=new Phone();
//		
//		//Phone1
//		p1.setNumber(inetaplangetcontactresult.get(0).getPh1());
//		p1.setType(inetaplangetcontactresult.get(0).getPh1Type());
//		actionplan.setPhone1(p1);
//		
//		//Phone2
//		p2.setNumber(inetaplangetcontactresult.get(0).getPh2());
//		p2.setType(inetaplangetcontactresult.get(0).getPh2Type());
//		actionplan.setPhone1(p2);
//		
//		//Phone3
//		p3.setNumber(inetaplangetcontactresult.get(0).getPh3());
//		p3.setType(inetaplangetcontactresult.get(0).getPh3Type());
//		actionplan.setPhone1(p3);
//		
//		} catch (DataServiceFault e) {
//			LOGGER.error("getTypesPhones result: {}");
//			throw new DataServiceFault();
//		}
//		
//
//	}
	
	/**
	 * Eliminar Planes de acción
	 * @param agent
	 * @param actionplans
	 * @throws DataServiceFault 
	 */
	public void deleteActionPlans(String agent, List<ActionPlan> actionplans) {
		//Auditoria
		Audit audit=new Audit();
		audit.setAction("updateInstallation");
		audit.setApp("Info instalacion");
		audit.setUser(agent);
		audit.setDate(new Date());
		try{
			if (actionplans!=null){
				for (ActionPlan actionplan : actionplans){
					this.wsModifyOrDeletePlan(actionplan, 2);
//					spIBSActionPlanData.maActplanCallLinUpd(Integer.parseInt(actionplan.getSins()), 
//							Integer.parseInt(actionplan.getPix()), Integer.parseInt(actionplan.getSpc()), 
//							Integer.parseInt(actionplan.getScont()), Integer.parseInt(actionplan.getScix()), 
//							Integer.parseInt("2"), "", "", actionplan.getName(), actionplan.getPhone1().getNumber(), actionplan.getSeq(), 
//							"", "", "", "", "", "", Integer.parseInt(actionplan.getSpc()), 
//							Integer.parseInt(actionplan.getScont()), Integer.parseInt(actionplan.getScix()));
					
					audit.setResult("OK");
					audit.setDetail("Action Plan borrado " +actionplan.getSeq() +" - "+ actionplan.getName());
					auditService.insert(audit);
				}
			}else{
				throw new BusinessException(BusinessException.ErrorCode.ERROR_DELETE_ACTION_PLAN);
			}

		}catch (Exception e){
			audit.setResult("FAIL");
			audit.setDetail(BusinessException.ErrorCode.ERROR_DELETE_ACTION_PLAN.toString());
			auditService.insert(audit);
			throw new BusinessException(BusinessException.ErrorCode.ERROR_DELETE_ACTION_PLAN);
		}
	}
	
	/**
	 * Actualizar plan de accion
	 * Si hay que añadir algun plan se llama a insertar
	 * Además si hay que modificar alguno se llama a modificar
	 * @param installationNumber
	 * @param contactos
	 * @param addedPlan
	 */
	public void updateActionPlans(String agent, String installationNumber, List<ActionPlan> contactos, ActionPlan addedPlan) {
		if (addedPlan!=null) {
			this.insertActionPlan(agent, installationNumber, addedPlan);
		}
		if(!contactos.isEmpty()){
			this.modifyActionPlans(agent, installationNumber, contactos);
		}
	}
	/**
	 * Insertar nuevo plan de accion
	 * @param installationNumber
	 * @param addedPlan
	 */
	private void insertActionPlan(String agent, String installationNumber, ActionPlan actionplan){
		LOGGER.debug("Insert Action Plan: {} {}", installationNumber, actionplan);
		//Auditoria
		Audit audit=new Audit();
		audit.setAction("updateInstallation");
		audit.setApp("Info instalacion");
		audit.setUser(agent);
		audit.setDate(new Date());
		try{
			if (actionplan!=null){
//				spIBSActionPlanData.maActplanCallLinUpd(Integer.getInteger(actionplan.getSins()), 
//						Integer.getInteger(actionplan.getPix()), Integer.getInteger(actionplan.getSpc()), 
//						Integer.getInteger(actionplan.getScont()), Integer.getInteger(actionplan.getScix()), 
//						Integer.getInteger("1"), "", "", actionplan.getName(), actionplan.getPhone1().getNumber(), actionplan.getSeq(), 
//						"", "", "", "", "", "", Integer.getInteger(actionplan.getSpc()), 
//						Integer.getInteger(actionplan.getScont()), Integer.getInteger(actionplan.getScix()));	
				
				this.wsModifyOrDeletePlan(actionplan, 1);
				
				audit.setResult("OK");
				audit.setDetail("Action Plan insertado " +actionplan.getSeq() +" - "+ actionplan.getName());
				auditService.insert(audit);
			}else{
				throw new BusinessException(BusinessException.ErrorCode.ERROR_ADD_ACTION_PLAN);
			}
		}catch (Exception e){
			audit.setResult("FAIL");
			audit.setDetail(BusinessException.ErrorCode.ERROR_ADD_ACTION_PLAN.toString());
			auditService.insert(audit);
			throw new BusinessException(BusinessException.ErrorCode.ERROR_ADD_ACTION_PLAN);
		}
	}
	/**
	 * Modificar planes de acción
	 * @param installationNumber
	 * @param contactos
	 */
	private void modifyActionPlans(String agent, String installationNumber, List<ActionPlan> actionplans){
		LOGGER.debug("Modify Action Plans: {} {}", installationNumber, actionplans);
		//Auditoria
		Audit audit=new Audit();
		audit.setAction("updateInstallation");
		audit.setApp("Info instalacion");
		audit.setUser(agent);
		audit.setDate(new Date());
		try{
			
			if (actionplans!=null){
			
				for (ActionPlan actionplan : actionplans){
					this.wsModifyOrDeletePlan(actionplan, 1);
					audit.setResult("OK");
					audit.setDetail("Action Plan modificado " +actionplan.getSeq() +" - "+ actionplan.getName());
					auditService.insert(audit);
				}
			}else{
				audit.setResult("FAIL");
				audit.setDetail(BusinessException.ErrorCode.ERROR_UPDATE_ACTION_PLAN.toString());
				auditService.insert(audit);
				throw new BusinessException(BusinessException.ErrorCode.ERROR_UPDATE_ACTION_PLAN);
			}
			
			
		}catch (Exception e){
			audit.setResult("FAIL");
			audit.setDetail(BusinessException.ErrorCode.ERROR_UPDATE_ACTION_PLAN.toString());
			auditService.insert(audit);
			throw new BusinessException(BusinessException.ErrorCode.ERROR_UPDATE_ACTION_PLAN);
		}
	}
	
	/** Llamada al ws de modificar(1) o borrar(2) plan*/
	private void wsModifyOrDeletePlan(ActionPlan actionplan, Integer action){
		/** Variable que determina la accion: 1 modificar, 2 Borrar*/
		Integer opc=action; 
		/**Valores de los telefonos*/
		String phtyp1="", phone1="";
		if (actionplan.getPhone1()!=null) {
			phtyp1=actionplan.getPhone1().getType();
			phone1=actionplan.getPhone1().getNumber();
		}
		String phone2="",phtyp2="";
		if (actionplan.getPhone2()!=null) {
			phone2=actionplan.getPhone2().getNumber();
			phtyp2=actionplan.getPhone2().getType();
		}
		String phone3="",phtyp3="";
		if (actionplan.getPhone2()!=null) {
			phtyp3=actionplan.getPhone3().getType();
			phone3=actionplan.getPhone3().getNumber();
		}
		/** Otras */
		String code="";
		String br="";
		Integer mustcall=0;
		Integer osCont=Integer.parseInt(actionplan.getScont());
		String contTp="";
		String invest="";
		String passcidtext="";
		Integer debug=1;
		String tmpPlevel="1";
		String enabled="";
		String prreq="";
		String keyh="";
		String relName="";
		String highsec="";
		String email1="";
		Integer osPc=Integer.parseInt(actionplan.getSpc());
		Integer exptm=0;
		String passcid="";
		String dwnldb="";
		String allincl="";
		String ilevel="";
		Integer ocix=Integer.parseInt(actionplan.getScix());
		String flgEmailExclude="";
		String aprof="";
		String contRel="";
		String lix="";
		String multiCiChg="";
		spIBSActionPlanData.maActplanCallLinUpd2(Integer.parseInt(actionplan.getSins()), Integer.parseInt(actionplan.getPix()), Integer.parseInt(actionplan.getSpc()), Integer.parseInt(actionplan.getScont()), Integer.parseInt(actionplan.getScix()), opc, passcid, passcidtext, actionplan.getName(), phone1, actionplan.getSeq(), tmpPlevel, dwnldb, lix, allincl, prreq, br, osPc, osCont, ocix, exptm, phone2, phone3, ilevel, code, aprof, enabled, highsec, email1, contTp, contRel, relName, keyh, multiCiChg, invest, mustcall, phtyp1, phtyp2, phtyp3, flgEmailExclude, debug);
	}
	
	/**
	 * Obtener tipos de telefono para rellenar los combos
	 */
	public List<GetTiposTelefonoResult> getPhoneTypes(){
		try {
			List<GetTiposTelefonoResult> getTiposTelefonoResults = spInstallationMonData.getTiposTelefono();
			if (!getTiposTelefonoResults.isEmpty()) {
				return getTiposTelefonoResults;
			}else{
				throw new BusinessException(BusinessException.ErrorCode.ERROR_GET_PHONE_TYPES);
			}
		} catch (Exception e) {
			throw new BusinessException(BusinessException.ErrorCode.ERROR_GET_PHONE_TYPES);
		}
	}
}
