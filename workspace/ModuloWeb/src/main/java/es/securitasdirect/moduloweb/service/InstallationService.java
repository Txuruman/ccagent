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
import org.wso2.ws.dataservice.DataServiceFault;
import org.wso2.ws.dataservice.GetAlarmIdsBasicInfoResult;
import org.wso2.ws.dataservice.GetEmail;
import org.wso2.ws.dataservice.GetInstallation;
import org.wso2.ws.dataservice.GetMonitoringStatusResult;
import org.wso2.ws.dataservice.GetTieneCamara;
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
	           
	            /** Claves
	             * Si no esta vacia la lista
	             * Comprobar si hay suficientes antes de obtenerlas
	             */
	            if (!mainstallationdataresult.getGetCodeWordsResults().getGetCodeWordsResult().isEmpty()){
	            	 //Customer Password
	            	if (mainstallationdataresult.getGetCodeWordsResults().getGetCodeWordsResult().size()>=2) {
	            		installation.setCustomerPassword(mainstallationdataresult.getGetCodeWordsResults().getGetCodeWordsResult().get(1).getCode());
					}
	            	//Securitas Password
	            	if (mainstallationdataresult.getGetCodeWordsResults().getGetCodeWordsResult().size()>=3) {
	            		installation.setSecuritasPassword(mainstallationdataresult.getGetCodeWordsResults().getGetCodeWordsResult().get(2).getCode());
					}
	            	//Coercion Password
	            	if (mainstallationdataresult.getGetCodeWordsResults().getGetCodeWordsResult().size()>=6) {
	            		 installation.setCoercionPassword(mainstallationdataresult.getGetCodeWordsResults().getGetCodeWordsResult().get(5).getCode());
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
			Integer cix= 0;
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
			List<Mainstallationdataresult> mainstallationdataresults=spInstallationMonData.getInstallationData(installationData.getInstallationNumber());
			Mainstallationdataresult mainstallationdataresult=mainstallationdataresults.get(0);
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
			String subTp=mainstallationdataresult.getSubTp();
			String panel1=mainstallationdataresult.getPanel();
			String insNo=mainstallationdataresult.getInsNo();
			String monStat=mainstallationdataresult.getMonStat();
			String name=mainstallationdataresult.getName();
			String fname=mainstallationdataresult.getFname();
			String iname=mainstallationdataresult.getIname();
			String street1No2=mainstallationdataresult.getStreet1No2();
			String street1No1=mainstallationdataresult.getStreet1No1();
			String street1=mainstallationdataresult.getStreet1();
			String street2=mainstallationdataresult.getStreet2();
			String city=mainstallationdataresult.getCity();
			String city2=mainstallationdataresult.getCity2();
			String state=mainstallationdataresult.getState();
			String zip=mainstallationdataresult.getZip();
			String aliasName=mainstallationdataresult.getAliasName();
			Integer sIns=mainstallationdataresult.getSIns().intValue();
			String alaid1=mainstallationdataresult.getAlaid();
			String dealId=mainstallationdataresult.getDealId();
			String lang=mainstallationdataresult.getIdiomaServicio();
			
			
			/**
			 * Desconocidos
			 */
			String repFlg="";
			String tmzonpr="";
			String id="";
			String passver="";
			String protArea="";
			String pbr="";
			String testInt3="0";
			String skill="";
			String mgrp2="";
			String panel2="";
			String prtreq="";
			String insuId="";
			String tname="";
			String dcc1="";
			String insDate="";
			String brSale="";
			String mapGrid="";
			String telcoLoc="";
			String perm="";
			String txtzonref="";
			String miscdate3="";
			String phoArea="";
			String comment2="";
			String miscdate1="";
			String crossStreet="";
			String userId="";
			Integer stamp=0;
			String alaid2="";
			String phtxt1="";
			String phtxt2="";
			String sales2="";
			String miscdate6="";
			String inst1="";
			String monStatDate="";
			String panelLoc="";
			String ocname="";
			String miscno1="";
			String testInt1="";
			String maintype="";
			String instId="";
			String code="";
			String miscdate5="";
			String sales1="";
			String alaid3="";
			String comment1="";
			String miscdate2="";
			String panel3="";
			String fax="";
			String duress="";
			String backupver="";
			String testInt2="0";
			String mainexpdate="";
			String br="";
			String subDeal="";
			String miscdate4="";
			String dcc2="";
			String rbr="";
			String mgrp1="";
			String mbr="";
			String ocexp="";
			String subzone="";
			String brServ="";
			String ro="";
			String subExptp="";
			String sbr="";
			String mainfreq="";
			String preg="";
			String brInst="";
			String refcid="";
			String sreg="";
			String inst2="";
			
			
			/**
			 * misc
			 */
			String misc45="";
			String misc28="";
			String misc5="";
			String misc48="";
			String misc27="";
			String misc3="";
			String misc44="";
			String misc49="";
			String misc1="";
			String misc29="";
			String misc39="";
			String misc20="";
			String misc6="";
			String miscno2="";
			String misc35="";
			String misc43="";
			String misc31="";
			String misc36="";
			String misc34="";
			String misc46="";
			String misc41="";
			String misc47="";
			String misc38="";
			String misc10="";
			String misc22="";
			String misc23="";
			String misc26="";
			String misc24="";
			String misc32="";
			String misc80="";
			String misc4="";
			String misc37="";
			String misc25="";
			String misc30="";
			String misc2="";
			String misc9="";
			String misc21="";
			String misc33="";
			String misc7="";
			String misc8="";
			String misc40="";
			String misc50="";
			String misc42="";
			
			
			List<Inetinstallationupdresult> inetinstallationupdresult=spInstallationMonData.inetInstallationUpd(sIns, stamp, insNo, dealId, subDeal, alaid1, alaid2, alaid3, panel1, panel2, panel3, testInt1, testInt2, testInt3, id, code, perm, tmzonpr, subzone, passver, instId, insDate, monStat, monStatDate, subTp, comment1, comment2, txtzonref, phoArea, subExptp, repFlg, insuId, name, fname, iname, tname, street1, street1No1, street1No2, street2, city, city2, state, zip, aliasName, phone1, phone2, phone3, phtxt1, phtxt2, fax, crossStreet, mapGrid, mgrp1, mgrp2, duress, telcoLoc, panelLoc, br, rbr, sbr, mbr, pbr, sreg, preg, refcid, miscno1, miscno2, ro, skill, lang, dcc1, dcc2, backupver, ocexp, ocname, prtreq, maintype, mainfreq, mainexpdate, inst1, inst2, sales1, sales2, brInst, brSale, brServ, miscdate1, miscdate2, miscdate3, miscdate4, miscdate5, miscdate6, misc1, misc2, misc3, misc4, misc5, misc6, misc7, misc8, misc9, misc10, misc20, misc21, misc22, misc23, misc24, misc25, misc26, misc27, misc28, misc29, misc30, misc31, misc32, misc33, misc34, misc35, misc36, misc37, misc38, misc39, misc40, misc41, misc42, misc43, misc44, misc45, misc46, misc47, misc48, misc49, misc50, userId, protArea, email1, email2, misc80);
			LOGGER.debug("Update installation result: {}", inetinstallationupdresult);
			audit.setResult("OK");
			audit.setDetail("Instalacion actualizada "+ installationData.getInstallationNumber());
			auditService.insert(audit);
		} catch (Exception e) {
			audit.setResult("FAIL");
			audit.setDetail(e.getMessage());
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
						
					spIBSActionPlanData.maActplanCallLinUpd(Integer.parseInt(actionplan.getSins()), 
							Integer.parseInt(actionplan.getPix()), Integer.parseInt(actionplan.getSpc()), 
							Integer.parseInt(actionplan.getScont()), Integer.parseInt(actionplan.getScix()), 
							Integer.parseInt("2"), "", "", actionplan.getName(), actionplan.getPhone1().getNumber(), actionplan.getSeq(), 
							"", "", "", "", "", "", Integer.parseInt(actionplan.getSpc()), 
							Integer.parseInt(actionplan.getScont()), Integer.parseInt(actionplan.getScix()));
					
					audit.setResult("OK");
					audit.setDetail("Action Plan borrado " +actionplan.getSeq() +" - "+ actionplan.getName());
					auditService.insert(audit);
				}
			}else{
				throw new BusinessException(BusinessException.ErrorCode.ERROR_DELETE_ACTION_PLAN);
			}

		}catch (Exception e){
			audit.setResult("FAIL");
			audit.setDetail(e.getMessage());
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
				spIBSActionPlanData.maActplanCallLinUpd(Integer.getInteger(actionplan.getSins()), 
						Integer.getInteger(actionplan.getPix()), Integer.getInteger(actionplan.getSpc()), 
						Integer.getInteger(actionplan.getScont()), Integer.getInteger(actionplan.getScix()), 
						Integer.getInteger("1"), "", "", actionplan.getName(), actionplan.getPhone1().getNumber(), actionplan.getSeq(), 
						"", "", "", "", "", "", Integer.getInteger(actionplan.getSpc()), 
						Integer.getInteger(actionplan.getScont()), Integer.getInteger(actionplan.getScix()));	
				
			
				
				audit.setResult("OK");
				audit.setDetail("Action Plan insertado " +actionplan.getSeq() +" - "+ actionplan.getName());
				auditService.insert(audit);
			}else{
				throw new BusinessException(BusinessException.ErrorCode.ERROR_ADD_ACTION_PLAN);
			}
		}catch (Exception e){
			audit.setResult("FAIL");
			audit.setDetail(e.getMessage());
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
					
					spIBSActionPlanData.maActplanCallLinUpd(Integer.getInteger(actionplan.getSins()), 
							Integer.getInteger(actionplan.getPix()), Integer.getInteger(actionplan.getSpc()), 
							Integer.getInteger(actionplan.getScont()), Integer.getInteger(actionplan.getScix()), 
							Integer.getInteger("1"), "", "", actionplan.getName(), actionplan.getPhone1().getNumber(), actionplan.getSeq(), 
							"", "", "", "", "", "", Integer.getInteger(actionplan.getSpc()), 
							Integer.getInteger(actionplan.getScont()), Integer.getInteger(actionplan.getScix()));
					
					audit.setResult("OK");
					audit.setDetail("Action Plan modificado " +actionplan.getSeq() +" - "+ actionplan.getName());
					auditService.insert(audit);
					
				}
			}else{
				throw new BusinessException(BusinessException.ErrorCode.ERROR_UPDATE_ACTION_PLAN);
			}
			
			
		}catch (Exception e){
			audit.setResult("FAIL");
			audit.setDetail(e.getMessage());
			auditService.insert(audit);
			throw new BusinessException(BusinessException.ErrorCode.ERROR_UPDATE_ACTION_PLAN);
		}
	}
}
