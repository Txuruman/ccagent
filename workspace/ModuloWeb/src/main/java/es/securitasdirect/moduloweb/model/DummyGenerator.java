package es.securitasdirect.moduloweb.model;

import org.wso2.ws.dataservice.*;
import org.wso2.ws.dataservice.Installation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Javier Naval on 24/07/2015.
 */
public class DummyGenerator {

    public static List<DirectAccess> getDirectAcess() {
        List<DirectAccess> l = new ArrayList<DirectAccess>();

        DirectAccess da1 = new DirectAccess();
        da1.setName("aaaaa");
        da1.setDescription("descr");
        da1.setUrl("http://www.google.es");
        da1.setPosition(0);
        l.add(da1);


        DirectAccess da2 = new DirectAccess();
        da2.setName("bbbbb");
        da2.setDescription("descr");
        da2.setUrl("http://www.google.es");
        da2.setPosition(1);
        l.add(da2);

        return l;
    }

    public static InstallationData getInstallation(Integer installationNumber) {
        InstallationData installation = new InstallationData();
        //TODO Inicializar con basura
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
        return installation;
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
