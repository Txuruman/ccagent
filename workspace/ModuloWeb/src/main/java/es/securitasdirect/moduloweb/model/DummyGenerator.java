package es.securitasdirect.moduloweb.model;



import javax.swing.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Javier Naval on 24/07/2015.
 */
public class DummyGenerator {

	/**
	 * Obtener lista de accesos directos
	 * @return
	 */
    public static List<DirectAccess> getDirectAcess() {
        List<DirectAccess> l = new ArrayList<DirectAccess>();


        DirectAccess da;

        da= new DirectAccess();
        da.setName("Protom");
        da.setDescription("descr");
        da.setUrl("http://www.google.es");
        da.setPosition(0);
        List<DirectAccessParams> mapa1=new ArrayList<DirectAccessParams>();
        mapa1.add(new DirectAccessParams("param1", "hola", 1));
        mapa1.add(new DirectAccessParams("param2", "hola2", 1));
        mapa1.add(new DirectAccessParams("param3", "hola3", 1));
        mapa1.add(new DirectAccessParams("param4", "hola4", 1));
        da.setParams(mapa1);
        l.add(da);


        da= new DirectAccess();
        da.setName("Intranet ATC");
        da.setDescription("descr");
        da.setUrl("http://www.google.es");
        da.setPosition(1);
        List<DirectAccessParams> mapa2=new ArrayList<DirectAccessParams>();
        mapa2.add(new DirectAccessParams("param1", "hola", 2));
        mapa2.add(new DirectAccessParams("param3", "hola3", 2));
        mapa2.add(new DirectAccessParams("param4", "hola4", 2));
        da.setParams(mapa2);
        l.add(da);

        da= new DirectAccess();
        da.setName("Intranet ATC");
        da.setDescription("descr");
        da.setUrl("http://www.google.es");
        da.setPosition(2);
        List<DirectAccessParams> mapa3=new ArrayList<DirectAccessParams>();
        mapa3.add(new DirectAccessParams("param2", "hola2", 3));
        mapa3.add(new DirectAccessParams("param4", "hola4", 3));
        da.setParams(mapa3);
        l.add(da);
 

        da= new DirectAccess();
        da.setName("Printing External");
        da.setDescription("descr");
        da.setUrl("http://www.google.es");
        da.setPosition(4);
        List<DirectAccessParams> mapa4=new ArrayList<DirectAccessParams>();
        mapa4.add(new DirectAccessParams("param1", "hola", 4));
        mapa4.add(new DirectAccessParams("param2", "hola2", 4));
        mapa4.add(new DirectAccessParams("param4", "hola4", 4));
        da.setParams(mapa4);
        l.add(da);
 

        da= new DirectAccess();
        da.setName("Infopoint Recall");
        da.setDescription("descr");
        da.setUrl("http://www.google.es");
        da.setPosition(5);
        da.setParams(mapa1);
        l.add(da);

        da= new DirectAccess();
        da.setName("Infopoint Cliente");
        da.setDescription("descr");
        da.setUrl("http://www.google.es");
        da.setPosition(3);
        da.setParams(mapa3);
        l.add(da);


        return l;
    }
    
    /**
     * Obtener datos de instalación
     * @param installationNumber
     * @return
     */
    public static InstallationData getInstallation(String installationNumber) {
        InstallationData installation = new InstallationData();
        //INSTALACION
        installation.setAddress("Gran Vía 12");
        installation.setAka("AKA...");
        installation.setCamera("Samsumg");
        //installation.setCcc("CCC...");
        installation.setCity("Guadalajara");
        installation.setCoercionPassword("1234");
        installation.setCustomerName("Francisco Herrero");
        installation.setCustomerPassword("12345");
        installation.setEmailBilling("correo1@prueba.es");
        installation.setEmailMonitoring("frherrero@email.com");
        installation.setEmailUpdate("01-01-2015");
        installation.setInstallationNumber(installationNumber);
        installation.setMonitoringStatus("Estado");
        installation.setPanel("SDVFAST");
        installation.setPanelPhone("916392717");
        installation.setSecuritasPassword("123456");
        installation.setSubtype("Subtipo");
        installation.setVersion("FAST A.06");
        installation.setLanguage("Español");
        installation.setEmailServices("servicios@email.es");
        installation.setTelefono1("944169426");
        installation.setTelefono3("923694267");
        installation.setTelefono2("914582690");
        installation.setTelefonoServicios("902514569");
        //installation.setActionplans(getActionPlan());

        return installation;
    }

    /**
     * Obtener datos del Plan de acción
     * @return
     */
    public static List<ActionPlan> getActionPlan() {
//        List<ActionPlan> result = new ArrayList<ActionPlan>();
//        ActionPlan a1 = new ActionPlan();
//        a1.setContactName("JON RUIZ SAGARNA");
//        a1.setPhone1(new Phone(Phone.TYPE.FIJO, "9133344455"));
//        a1.setPhone2(new Phone(Phone.TYPE.MOVIL, "696252525"));
//        a1.setPhone3(new Phone(Phone.TYPE.FIJO, "9133344455"));
//        a1.setSecuence(0);
//        a1.setType("1");
//        result.add(a1);
//
//
//        ActionPlan a2 = new ActionPlan();
//        a2.setContactName("ANA ARREGUI");
//        a2.setPhone1(new Phone(Phone.TYPE.FIJO, "9133344455"));
//        a2.setPhone2(new Phone(Phone.TYPE.MOVIL, "696252525"));
//        a2.setPhone3(new Phone(Phone.TYPE.FIJO, "9133344455"));
//        a2.setSecuence(0);
//        a2.setType("2");
//        result.add(a2);
//        
//        ActionPlan a3 = new ActionPlan();
//        a3.setContactName("GOTZON BARRONDO");
//        a3.setPhone1(new Phone(Phone.TYPE.FIJO, "9133344455"));
//        a3.setPhone2(new Phone(Phone.TYPE.MOVIL, "696252525"));
//        a3.setPhone3(new Phone(Phone.TYPE.FIJO, "9133344455"));
//        a3.setSecuence(0);
//        a3.setType("3");
//        result.add(a3);
//        
//        ActionPlan a4 = new ActionPlan();
//        a4.setContactName("FRANCISCO RUIZ");
//        a4.setPhone1(new Phone(Phone.TYPE.FIJO, "9133344455"));
//        a4.setPhone2(new Phone(Phone.TYPE.MOVIL, "696252525"));
//        a4.setPhone3(new Phone(Phone.TYPE.FIJO, "9133344455"));
//        a4.setSecuence(0);
//        a4.setType("4");
//        result.add(a4);
//        
//        ActionPlan a5 = new ActionPlan();
//        a5.setContactName("JON RUIZ SAGARNA");
//        a5.setPhone1(new Phone(Phone.TYPE.FIJO, "9133344455"));
//        a5.setPhone2(new Phone(Phone.TYPE.MOVIL, "696252525"));
//        a5.setPhone3(new Phone(Phone.TYPE.FIJO, "9133344455"));
//        a5.setSecuence(15);
//        a5.setType("1");
//        result.add(a5);
//        
//        return result;
    	return null;
    }
    
    /**
     * Obtener datos para el visor de cambios de la pestaña instalación
     */
    public static List<Audit> getAudit() {
        List<Audit> result = new ArrayList<Audit>();
        Audit a1 = new Audit();
        a1.setDate(new Date());
        a1.setAction(Audit.ACCTION.READ);
        a1.setApp("INST");
        a1.setResult(Audit.RESULT.OK);
        a1.setUser("user");
        a1.setId(1);
        a1.setDetail("text detail");
        result.add(a1);

        Audit a2 = new Audit();
        a2.setDate(new Date());
        a2.setAction(Audit.ACCTION.READ);
        a2.setApp("FACTU");
        a2.setResult(Audit.RESULT.ERROR);
        a2.setUser("user");
        a2.setId(2);
        a2.setDetail("text detail");
        result.add(a1);

        return result;

    }
    
    /**
     * Obtener la información de la factura
     * @param invoiceId
     * @return
     */
    public static InvoiceInfo getInvoice(Integer invoiceId) {
        InvoiceInfo invoice = new InvoiceInfo();
        //INVOICE
        invoice.setDebtAmount(120D);
        invoice.setFinancialEntity("Finconsum");
        invoice.setInvoiceSend(true);
        invoice.setPayMode("Caixa");
        invoice.setDiscount(true);
        invoice.setEmailBilling("frherrero@email.com");
        invoice.setCcc("1234-5684-99-1203264125");
        return invoice;
    }


    /**
     * Obtener los cycles feeds
     */
    public static List<CycleFeeds> getCycleFeeds(Integer installationId){
    	List<CycleFeeds> cyclefeeds= new ArrayList<CycleFeeds>();
    	SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
    	
    	CycleFeeds cycle=new CycleFeeds();
    	cycle.setInsNo(installationId.toString());
    	cycle.setCount(1);
    	cycle.setDescription("INSTALACION KIT SD 2000 PRO");
    	cycle.setFee(37.67);
    	try{
    		cycle.setFromDate(formatoDelTexto.parse("19-01-2009"));
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	cycle.setRevTp("CCE1");
    	cyclefeeds.add(cycle);
    	
    	CycleFeeds cycle2=new CycleFeeds();
    	cycle2.setInsNo(installationId.toString());
    	cycle2.setCount(3);
    	cycle2.setDescription("DETECTOR VOLUMETRICO VIA CABLE");
    	cycle2.setFee(-3);
    	try{
    		cycle2.setFromDate(formatoDelTexto.parse("19-01-2009"));
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	cycle2.setRevTp("CCE2");
    	cyclefeeds.add(cycle2);
    	
    	
    	CycleFeeds cycle3=new CycleFeeds();
    	cycle3.setInsNo(installationId.toString());
    	cycle3.setCount(1);
    	cycle3.setDescription("MODULO COMUNICACION SD2000 PRO");
    	cycle3.setFee(1);
    	try{
    		cycle3.setFromDate(formatoDelTexto.parse("19-01-2009"));
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	cycle3.setRevTp("CCE3");
    	cyclefeeds.add(cycle3);
    	
    	CycleFeeds cycle4=new CycleFeeds();
    	cycle4.setInsNo(installationId.toString());
    	cycle4.setCount(1);
    	cycle4.setDescription("TARIFA PLANA TRANSMISION SEGURIDAD");
    	cycle4.setFee(-1);
    	try{
    		cycle4.setFromDate(formatoDelTexto.parse("19-01-2009"));
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	cycle4.setRevTp("CCE2");
    	cyclefeeds.add(cycle4);
    	
    	CycleFeeds cycle5=new CycleFeeds();
    	cycle5.setInsNo(installationId.toString());
    	cycle5.setCount(1);
    	cycle5.setDescription("DETECTOR VOLUMETRICO VIA CABLE");
    	cycle5.setFee(1);
    	try{
    		cycle5.setFromDate(formatoDelTexto.parse("19-01-2009"));
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	cycle5.setRevTp("CCE2");
    	cyclefeeds.add(cycle3);
    	cyclefeeds.add(cycle);
    	cyclefeeds.add(cycle4);
    	cyclefeeds.add(cycle2);
    	cyclefeeds.add(cycle5);
    	cyclefeeds.add(cycle);
    	return cyclefeeds;
    }
    
    /**
     * Obtener listado de Facturas
     */
    public static List<InvoiceData> getListInvoices(Integer installationNumber) {
    	List<InvoiceData> result = new ArrayList<InvoiceData>();
    	SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
    	
    	InvoiceData invoice=new InvoiceData();
    	invoice.setInvoiceNumber(79824792);
    	invoice.setExtInvoiceNo("13FR00026697");
    	invoice.setAmount(-49.54);
    	invoice.setInstallationNumber(installationNumber);
    	invoice.setInvoiceType(InvoiceData.TYPE.ONE);
    	
    	try{
    		invoice.setSystemDate(formatoDelTexto.parse("27/03/2013"));
    		invoice.setDueDate(formatoDelTexto.parse("10/01/2013"));
    		invoice.setTransactionDate(formatoDelTexto.parse("01/03/2013"));
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	result.add(invoice);
    	
    	InvoiceData invoice2=new InvoiceData();
    	invoice2.setInvoiceNumber(75421398);
    	invoice2.setExtInvoiceNo("13FR00026696");
    	invoice2.setAmount(-49.54);
    	invoice2.setInstallationNumber(installationNumber);
    	invoice2.setInvoiceType(InvoiceData.TYPE.ONE);
    	
    	try{
    		invoice2.setSystemDate(formatoDelTexto.parse("27/03/2013"));
    		invoice2.setDueDate(formatoDelTexto.parse("10/01/2013"));
    		invoice2.setTransactionDate(formatoDelTexto.parse("01/03/2013"));
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	result.add(invoice2);
    	
    	InvoiceData invoice3=new InvoiceData();
    	invoice3.setInvoiceNumber(75652154);
    	invoice3.setExtInvoiceNo("13FR00026652");
    	invoice3.setAmount(-48.15);
    	invoice3.setInstallationNumber(installationNumber);
    	invoice3.setInvoiceType(InvoiceData.TYPE.CYCLE);
    	
    	try{
    		invoice3.setSystemDate(formatoDelTexto.parse("27/03/2013"));
    		invoice3.setDueDate(formatoDelTexto.parse("10/01/2013"));
    		invoice3.setTransactionDate(formatoDelTexto.parse("01/03/2013"));
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	result.add(invoice3);
    	
    	InvoiceData invoice4=new InvoiceData();
    	invoice4.setInvoiceNumber(75654321);
    	invoice4.setExtInvoiceNo("1302C0420864");
    	invoice4.setAmount(49.54);
    	invoice4.setInstallationNumber(installationNumber);
    	invoice4.setInvoiceType(InvoiceData.TYPE.CYCLE);
    	
    	try{
    		invoice4.setSystemDate(formatoDelTexto.parse("22/01/2013"));
    		invoice4.setDueDate(formatoDelTexto.parse("10/02/2013"));
    		invoice4.setTransactionDate(formatoDelTexto.parse("01/03/2013"));
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	result.add(invoice4);
    	
    	InvoiceData invoice5=new InvoiceData();
    	invoice5.setInvoiceNumber(75646545);
    	invoice5.setExtInvoiceNo("1301C0420464");
    	invoice5.setAmount(49.54);
    	invoice5.setInstallationNumber(installationNumber);
    	invoice5.setInvoiceType(InvoiceData.TYPE.CYCLE);
    	
    	try{
    		invoice5.setSystemDate(formatoDelTexto.parse("22/01/2013"));
    		invoice5.setDueDate(formatoDelTexto.parse("10/02/2013"));
    		invoice5.setTransactionDate(formatoDelTexto.parse("01/03/2013"));
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	result.add(invoice5);
    	
    	InvoiceData invoice6=new InvoiceData();
    	invoice6.setInvoiceNumber(76545645);
    	invoice6.setExtInvoiceNo("1212C6544844");
    	invoice6.setAmount(48.15);
    	invoice6.setInstallationNumber(installationNumber);
    	invoice6.setInvoiceType(InvoiceData.TYPE.ONE);
    	
    	try{
    		invoice6.setSystemDate(formatoDelTexto.parse("19/12/2012"));
    		invoice6.setDueDate(formatoDelTexto.parse("10/12/2012"));
    		invoice6.setTransactionDate(formatoDelTexto.parse("01/12/2013"));
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	result.add(invoice6);
    	
    	result.add(invoice);
    	result.add(invoice2);
    	result.add(invoice5);
    	result.add(invoice4);
    	result.add(invoice5);
    	result.add(invoice6);
    	result.add(invoice);
    	result.add(invoice2);
    	result.add(invoice2);
    	result.add(invoice);
    	result.add(invoice2);
    	result.add(invoice3);
    	result.add(invoice4);
    	result.add(invoice5);
    	result.add(invoice2);
    	result.add(invoice3);
    	result.add(invoice4);
    	result.add(invoice);
    	result.add(invoice6);
    	return result;
    }
    
    /**
     * Obtenemos el resumen de cuotas de la instalación
     */
    public static Cuote getCuotes(Integer installationNumber){
    	Cuote cuota=new Cuote();
    	cuota.setInstallationNumber(installationNumber);
    	cuota.setEneroActual(33.2);
    	cuota.setEneroPasado1(31.7);
    	cuota.setMesActual(12.8);
    	return cuota;
    }
    
    /**
     * Obtener la configuración
     * @return
     */
    public static List<FieldConfig> getFieldConfig() {
        List<FieldConfig> result = new ArrayList<FieldConfig>();
        FieldConfig fc;

        fc = new FieldConfig();
        fc.setApp("INST"); //TODO Ya veremos como mejorar
        fc.setIdentifier("panel"); //Utilicemos el nombre del atributo java como id
        fc.setDescription("Panel de la instalacion");
        fc.setEditable(true);
        fc.setVisible(true);
        result.add(fc);

        fc = new FieldConfig();
        fc.setApp("INST"); //TODO Ya veremos como mejorar
        fc.setIdentifier("panelPhone"); //Utilicemos el nombre del atributo java como id
        fc.setDescription("xxxxxxxxx");
        fc.setEditable(true);
        fc.setVisible(true);
        result.add(fc);

        fc = new FieldConfig();
        fc.setApp("INST"); //TODO Ya veremos como mejorar
        fc.setIdentifier("version"); //Utilicemos el nombre del atributo java como id
        fc.setDescription("xxxxxxx");
        fc.setEditable(true);
        fc.setVisible(true);
        result.add(fc);

        fc = new FieldConfig();
        fc.setApp("INST"); //TODO Ya veremos como mejorar
        fc.setIdentifier("panel"); //Utilicemos el nombre del atributo java como id
        fc.setDescription("xxxxxxxx");
        fc.setEditable(true);
        fc.setVisible(true);
        result.add(fc);

        return result;
    }
    
    public static List<TabKeys> getTabKeys(){
    	List<TabKeys> result = new ArrayList<TabKeys>();
    	TabKeys tabkey1=new TabKeys();
    	tabkey1.setTabId("1");
    	tabkey1.setKey1("3");
    	tabkey1.setKey2("6");
    	tabkey1.setKey3("2");
    	tabkey1.setName("INFO INST");
    	result.add(tabkey1);
    	
    	TabKeys tabkey2=new TabKeys();
    	tabkey2.setTabId("2");
    	tabkey2.setKey1("7");
    	tabkey2.setKey2("2");
    	tabkey2.setKey3("5");
    	tabkey2.setName("FACTURACIÓN");
    	result.add(tabkey2);
    	
    	TabKeys tabkey3=new TabKeys();
    	tabkey3.setTabId("3");
    	tabkey3.setKey1("4");
    	tabkey3.setKey2("7");
    	tabkey3.setKey3("8");
    	tabkey3.setName("AVERÍAS");
    	result.add(tabkey3);
    	
    	TabKeys tabkey4=new TabKeys();
    	tabkey4.setTabId("4");
    	tabkey4.setKey1("7");
    	tabkey4.setKey2("3");
    	tabkey4.setKey3("1");
    	tabkey4.setName("ADMINISTRACIÓN");
    	result.add(tabkey4);
    	
    	return result;
    }
}
