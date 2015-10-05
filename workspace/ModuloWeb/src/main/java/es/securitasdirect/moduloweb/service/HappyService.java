package es.securitasdirect.moduloweb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.wso2.ws.dataservice.CCAGENTADMPortType;
import org.wso2.ws.dataservice.CCAGENTAUDPortType;
import org.wso2.ws.dataservice.SPInstallationMonDataPortType;

import es.securitasdirect.moduloweb.service.model.HappyData;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Pedro on 05/10/2015.
 */
@Named(value = "happyService")
@Singleton
public class HappyService {


    private static final Logger LOGGER = LoggerFactory.getLogger(HappyService.class);

    private static final String INFOPOINT_SERVICE = "Infopoint";
    //private static final String FSM_LIGHT = "FSMDataServiceLight";
    //private static final String AIOTAREAS2_SERVICE = "AioTareas2";
    private static final String INSTALLATIONMONDATA_SERVICE = "spInstallationMonData";
    //private static final String CCL_SERVICE = "CCLIntegration";
    private static final String SECURITY = "Security";

    private static final String CCAGENTADM_SERVICE = "CCAgentAdm";
    private static final String CCAGENTAUD_SERVICE = "CCAgentAud";

    private static final String OK = "Ok";
    private static final String ERROR = "Error";

    @Resource
    protected String applicationUser ;
//    @Autowired
//    protected SecurityService securityService;

    protected Date upTime;

    @Inject
    protected CCAGENTADMPortType wsAdmin;
    @Inject
    protected CCAGENTAUDPortType wsAudit;
//    @Inject
//    protected InfopointService infopointService;
    //@Inject
    //protected InstallationService installationService;
    @Inject
    protected SPInstallationMonDataPortType spInstallationMonData;
    //@Inject
    //protected SPAIOTAREAS2PortType spAioTareas2;
    //@Inject
    //protected CCLIntegration cclIntegration;
    //@Inject
    //protected FSMDataServiceLightPortType fsmDataServiceLight;

    @PostConstruct
    protected void init() {
        upTime = new Date();
    }

    public HappyData getHappyData() {
        HappyData happyData = new HappyData();
        //Up Time
        happyData.setUpSince(upTime);

        //Security
        try {
//            securityService.check();
            happyData.addService(SECURITY, OK);
        } catch (Throwable e) {
            happyData.addService(SECURITY,ERROR, e.getMessage());
        }


        //Web Services
        try {
            wsAdmin.getFieldConfig();
            happyData.addService(CCAGENTADM_SERVICE,OK);
        } catch (Exception e) {
            happyData.addService(CCAGENTADM_SERVICE,ERROR, e.getMessage());
        }

        try {
            // fechaActual
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(new Date(System.currentTimeMillis()));
            XMLGregorianCalendar fechaActual = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
            //Esto es necesario porque el formato es <dateTime>2015-09-10T19:19:19</dateTime>
            fechaActual.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
            fechaActual.setTimezone(DatatypeConstants.FIELD_UNDEFINED);

          //  wsAudit.getSelectAuditByParametersOperation("1", "1", fechaActual);
            happyData.addService(CCAGENTAUD_SERVICE,OK);
        } catch (Exception e) {
            happyData.addService(CCAGENTAUD_SERVICE,ERROR, e.getMessage());
        }
/*
        try {
            fsmDataServiceLight.getMember("1760731");
            happyData.addService(FSM_LIGHT,OK);
        } catch (Exception e) {
            happyData.addService(FSM_LIGHT,ERROR, e.getMessage());
        }
*/
        try {
           // infopointService.validateSession("check infopoint service");
            happyData.addService(INFOPOINT_SERVICE,OK);
        } catch (Exception e) {
            happyData.addService(INFOPOINT_SERVICE,ERROR, e.getMessage());
        }
/*
        try {
            spAioTareas2.getKey1DIY();
            happyData.addService(AIOTAREAS2_SERVICE, OK);
        } catch (Exception e) {
            happyData.addService(AIOTAREAS2_SERVICE,ERROR, e.getMessage());
        }
*/
        try {
            spInstallationMonData.getPanel(111111);
            happyData.addService(INSTALLATIONMONDATA_SERVICE, OK);
        } catch (Exception e) {
            happyData.addService(INSTALLATIONMONDATA_SERVICE,ERROR, e.getMessage());
        }

/*
        try {
            cclIntegration.getCCLVersion("M0OOS",applicationUser,"","SPAIN");
            happyData.addService(CCL_SERVICE, OK);
        } catch (Exception e) {
            happyData.addService(CCL_SERVICE,ERROR, e.getMessage());
        }
*/

        return happyData;
    }

}

