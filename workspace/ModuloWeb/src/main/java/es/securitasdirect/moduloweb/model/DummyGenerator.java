package es.securitasdirect.moduloweb.model;

import org.wso2.ws.dataservice.*;
import org.wso2.ws.dataservice.Installation;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Javier Naval on 24/07/2015.
 */
public class DummyGenerator {

    public static List<DirectAccess> getDirectAcess() {
        List<DirectAccess> l = new ArrayList<DirectAccess>();

        DirectAccess da4 = new DirectAccess();
        da4.setName("TOA");
        da4.setDescription("TOA");
        da4.setUrl("http://www.google.es");
        da4.setPosition(0);
        l.add(da4);

        DirectAccess da3 = new DirectAccess();
        da3.setName("INTRANET");
        da3.setDescription("INTRANET");
        da3.setUrl("http://www.google.es");
        da3.setPosition(1);
        l.add(da3);
        
        DirectAccess da2 = new DirectAccess();
        da2.setName("RECALL/REVIEW");
        da2.setDescription("RECALL/REVIEW");
        da2.setUrl("http://www.google.es");
        da2.setPosition(2);
        l.add(da2);
        
        DirectAccess da1 = new DirectAccess();
        da1.setName("PROTON");
        da1.setDescription("PROTON");
        da1.setUrl("http://www.google.es");
        da1.setPosition(3);
        l.add(da1);
        return l;
    }

    public static InstallationData getInstallation(Integer installationNumber) {
        InstallationData installation = new InstallationData();
        //INSTALACION
        installation.setAddress("dirección....");
        installation.setAka("AKA...");
        installation.setCamera(false);
        installation.setCcc("CCC...");
        installation.setCity("Guadalajara");
        installation.setCoercionPassword("1234");
        installation.setCustomerName("Cliente de prueba");
        installation.setCustomerPassword("12345");
        installation.setEmailBilling("correo1@prueba.es");
        installation.setEmailMonitoring("correo2@prueba.es");
        installation.setEmailUpdage("correo3@prueba.es");
        installation.setInstallationNumber(installationNumber);
        installation.setMonitoringStatus("Estado");
        installation.setPanel("Panel");
        installation.setPanelPhone("688888888");
        installation.setSecuritasPassword("123456");
        installation.setSubtype("Subtipo");
        installation.setVersion("versión");

        installation.setActionplans(getActionPlan());

        return installation;
    }


    public static List<ActionPlan> getActionPlan() {
        List<ActionPlan> result = new ArrayList<ActionPlan>();
        ActionPlan a1 = new ActionPlan();
        a1.setContactName("Fernando Perez");
        a1.setPhone1(new Phone(Phone.TYPE.FIJO, "9133344455"));
        a1.setPhone2(new Phone(Phone.TYPE.MOVIL, "696252525"));
        a1.setPhone3(new Phone(Phone.TYPE.FIJO, "9133344455"));
        a1.setSecuence(0);
        a1.setType("tipo1");
        result.add(a1);


        ActionPlan a2 = new ActionPlan();
        a2.setContactName("Carlos Garcia");
        a2.setPhone1(new Phone(Phone.TYPE.FIJO, "9133344455"));
        a2.setPhone2(new Phone(Phone.TYPE.MOVIL, "696252525"));
        a2.setPhone3(new Phone(Phone.TYPE.FIJO, "9133344455"));
        a2.setSecuence(1);
        a2.setType("tipo2");
        result.add(a2);
        return result;
    }

    public static List<Audit> getAudit() {
        List<Audit> result = new ArrayList<Audit>();
        Audit a1 = new Audit();
        a1.setDate(new Date());
        a1.setAction(Audit.ACCTION.READ);
        a1.setApp("INST");
        a1.setResult(Audit.RESULT.OK);
        a1.setUser("user");
        a1.setId("callId??");
        a1.setDetail("text detail");
        result.add(a1);

        Audit a2 = new Audit();
        a2.setDate(new Date());
        a2.setAction(Audit.ACCTION.READ);
        a2.setApp("FACTU");
        a2.setResult(Audit.RESULT.ERROR);
        a2.setUser("user");
        a2.setId("callId??");
        a2.setDetail("text detail");
        result.add(a1);

        return result;

    }
}
