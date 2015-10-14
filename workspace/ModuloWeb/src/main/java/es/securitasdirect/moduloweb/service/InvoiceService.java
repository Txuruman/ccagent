package es.securitasdirect.moduloweb.service;

import es.securitasdirect.moduloweb.exceptions.BusinessException;
import es.securitasdirect.moduloweb.model.Audit;
import es.securitasdirect.moduloweb.model.Cuote;
import es.securitasdirect.moduloweb.model.CycleFeeds;
import es.securitasdirect.moduloweb.model.DummyGenerator;
import es.securitasdirect.moduloweb.model.InvoiceData;
import es.securitasdirect.moduloweb.model.InvoiceGlobal;
import es.securitasdirect.moduloweb.model.InvoiceInfo;
import es.securitasdirect.moduloweb.model.InvoiceItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.ws.dataservice.Billingdataresult;
import org.wso2.ws.dataservice.DataServiceFault;
import org.wso2.ws.dataservice.GetCycleFeesResult;
import org.wso2.ws.dataservice.GetEmail;
import org.wso2.ws.dataservice.GetEvolucionCuotasResult;
import org.wso2.ws.dataservice.GetFlagsResult;
import org.wso2.ws.dataservice.GetInvoiceResult;
import org.wso2.ws.dataservice.GetInvoicesDetailResult;
import org.wso2.ws.dataservice.GetSepaResult;
import org.wso2.ws.dataservice.GetUmrResult;
import org.wso2.ws.dataservice.SPInstallationBillDataPortType;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Service to access the Invoice Info
 */
@Named(value = "invoiceService")
@Singleton
public class InvoiceService {
	
	@Inject
	protected SPInstallationBillDataPortType spInstallationBillDataPortType;
	
	@Inject
	protected AuditService auditService;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceService.class);

    public InvoiceInfo getInvoice(Integer invoiceId) {
    	InvoiceInfo info=DummyGenerator.getInvoice(invoiceId);
    	if (info==null) {
    		throw new BusinessException(BusinessException.ErrorCode.ERROR_INVOICE_INFO_NOT_FOUND);
		}else{
			return info;
		}
        
    }
    /** Obtener lista de cycleFees */
    private List<CycleFeeds> getCycleFeeds(String sIns) {
    	List<CycleFeeds> list= new ArrayList<CycleFeeds>();
    	try{
    		List<GetCycleFeesResult> getCycleFeesResults=spInstallationBillDataPortType.getCyclefees(Integer.parseInt(sIns));
        	if (!getCycleFeesResults.isEmpty()) {
        		for (GetCycleFeesResult getCycleFeesResult : getCycleFeesResults) {
        			CycleFeeds cycle=new CycleFeeds();
        			cycle.setCount(getCycleFeesResult.getFeeIx());
        			cycle.setFee(getCycleFeesResult.getFee());
        			cycle.setFromDate(getCycleFeesResult.getDesde());
        			cycle.setToDate(getCycleFeesResult.getHasta());
        			list.add(cycle);
    			}
        		return list;
        	}else{
        		throw new BusinessException(BusinessException.ErrorCode.ERROR_CYCLE_FEEDS_NOT_FOUND);
    		}
    	}catch(Exception e){
    		throw new BusinessException(BusinessException.ErrorCode.ERROR_CYCLE_FEEDS_NOT_FOUND);
    	}
    	
    }
    
    /**
     * Obtener el listado de facturas correspondientes a la instalacion
     * entra el sins de la instalacion
     * sale listado de facturas
     */
    public List<InvoiceData> getListInvoices(String sIns, String sAcc){
    	List<InvoiceData> listInvoiceData= new ArrayList<InvoiceData>();
		try {
			List<GetInvoiceResult> list=spInstallationBillDataPortType.getInvoices(Integer.parseInt(sAcc));
    		if (!list.isEmpty()) {
				for (GetInvoiceResult getInvoiceResult : list) {
					InvoiceData invoice=new InvoiceData();
					invoice.setAmount(getInvoiceResult.getAmount());
					invoice.setDueDate(getInvoiceResult.getDueDate());
					invoice.setExtInvoiceNo(getInvoiceResult.getEXtInvoiceNo());
//					invoice.setId(getInvoiceResult.get);
					invoice.setSins(sIns);
					invoice.setInvoiceNumber(getInvoiceResult.getInvoice());
					String cycle=getInvoiceResult.getCycFlg();
					String one=getInvoiceResult.getOtFlg();
					/** Si es cycle damos valor 0 si es one time 1*/
					if (cycle.equals("X")) {
						invoice.setInvoiceType(0);
					}else if (one.equals("X")){
						invoice.setInvoiceType(1);
					}
					invoice.setBalance(getInvoiceResult.getBalAmount());
					invoice.setSystemDate(getInvoiceResult.getSysDate());
					invoice.setTransactionDate(getInvoiceResult.getTranDate());
					listInvoiceData.add(invoice);
				}
				return listInvoiceData;
			}else{
				throw new BusinessException(BusinessException.ErrorCode.ERROR_GET_INVOICE_LIST);
			}
		} catch (Exception e) {
			throw new BusinessException(BusinessException.ErrorCode.ERROR_GET_INVOICE_LIST);
		}
    }
    /**
     * Obtener el detalle de cada factura
     * @param invoiceNumber
     * @return List<InvoiceItem>
     */
    public List<InvoiceItem> getInvoiceDetail(String sInv){
    	List<InvoiceItem> itemList=new ArrayList<InvoiceItem>();
		try {
			List<GetInvoicesDetailResult> getInvoicesDetailResults=spInstallationBillDataPortType.getInvoicesDetail(Integer.parseInt(sInv));
			if (!getInvoicesDetailResults.isEmpty()) {
				for (GetInvoicesDetailResult getInvoicesDetailResult : getInvoicesDetailResults) {
					InvoiceItem item=new InvoiceItem();
					item.setAmount(getInvoicesDetailResult.getAmount());
					item.setDescription(getInvoicesDetailResult.getDescripcion());
//					item.setId(getInvoicesDetailResult.get);
					item.setInvoiceNumber(sInv);
					item.setTax(getInvoicesDetailResult.getTax());
					item.setDesde(getInvoicesDetailResult.getDesde());
					item.setHasta(getInvoicesDetailResult.getHasta());
					itemList.add(item);
				}
				return itemList;
			}else{
				throw new BusinessException(BusinessException.ErrorCode.ERROR_GET_INVOICE_DETAIL);
			}
		}catch(Exception e){
			throw new BusinessException(BusinessException.ErrorCode.ERROR_GET_INVOICE_DETAIL);
		}
    }
    
    public Cuote getCuotes(String sAcc){
    	List<GetEvolucionCuotasResult> getEvolucionCuotasResults;
    	Cuote cuotas=new Cuote();
		try {
			getEvolucionCuotasResults = spInstallationBillDataPortType.getEvolucionCuotas(Integer.parseInt(sAcc));
	    	if (!getEvolucionCuotasResults.isEmpty()) {
	    		//Comprobaciones para evitar los null pointer
	    		if (getEvolucionCuotasResults.size()>0) {
	    			cuotas.setFechaActual(getEvolucionCuotasResults.get(0).getTranDate());
	    			if (getEvolucionCuotasResults.get(0).getAmount()!=null) {
		    			cuotas.setCoutaMesActual(getEvolucionCuotasResults.get(0).getAmount().toString());
					}
				}
	    		if (getEvolucionCuotasResults.size()>1) {
	    			cuotas.setFechaEneroActual(getEvolucionCuotasResults.get(1).getTranDate());
	    			if (getEvolucionCuotasResults.get(1).getAmount()!=null) {
		    			cuotas.setCuotaEneroActual(getEvolucionCuotasResults.get(1).getAmount().toString());
					}
	    		}
	    		if (getEvolucionCuotasResults.size()>2) {
	    			cuotas.setFechaEneroPasado(getEvolucionCuotasResults.get(2).getTranDate());
	    			if (getEvolucionCuotasResults.get(2).getAmount()!=null) {
		    			cuotas.setCuotaEneroPasado(getEvolucionCuotasResults.get(2).getAmount().toString());
					}
	    		}
	    		
	    		return cuotas;
			}else{
				throw new BusinessException(BusinessException.ErrorCode.ERROR_CUOTES_NOT_FOUND);
			}
		} catch (Exception e) {
			throw new BusinessException(BusinessException.ErrorCode.ERROR_CUOTES_NOT_FOUND);
		} 
    }
    
    /** Actualizar valor de la CCC */
    public void updateCCC(String debIban, String umr, String sIns, String agent){
    	//Auditoria
		Audit audit=new Audit();
		audit.setAction("CCC_Change");
		audit.setApp("Facturacion");
		audit.setUser(agent);
		audit.setDate(new Date());
    	/** WS para obtener los datos para meterlos en el set modificando solo el valor actualizado de la ccc */
		try {
			List<GetSepaResult> getSepaResults=spInstallationBillDataPortType.inetBlSepaGetQuery(umr);
			if (!getSepaResults.isEmpty()) {
				GetSepaResult getSepaResult=getSepaResults.get(0);
				List<Billingdataresult> billingdataresults=spInstallationBillDataPortType.getBillingInformation(Integer.parseInt(sIns));
				if(!billingdataresults.isEmpty()){
					Billingdataresult billingdataresult=billingdataresults.get(0);
					spInstallationBillDataPortType.inetBlSepaChg(billingdataresult.getSAcc().intValue(), Integer.parseInt(getSepaResult.getSpm()), getSepaResult.getPayMeanCd(),
							umr, getSepaResult.getType(), getSepaResult.getFirst(), getSepaResult.getSignedDate(), getSepaResult.getCancelled(),
							getSepaResult.getEndDate(), getSepaResult.getDebName(), getSepaResult.getDebAddr(), getSepaResult.getCrdZip(), 
							getSepaResult.getDebCity(), getSepaResult.getDebCountry(), debIban, getSepaResult.getDebBic(), getSepaResult.getCrdIdent(),
							getSepaResult.getCrdName(), getSepaResult.getCrdAddr(), getSepaResult.getCrdZip(), getSepaResult.getCrdCity(),
							getSepaResult.getCrdCountry(), getSepaResult.getFrq(), getSepaResult.getMaxamo(), getSepaResult.getTranType(),
							getSepaResult.getMisc1(), getSepaResult.getMisc2(), getSepaResult.getMisc3());
					audit.setAction("CCC Change");
	    			audit.setResult("OK");
	    			audit.setDetail("CCC actualizada "+debIban);
	    			auditService.insert(audit);
				}else{
					throw new BusinessException(BusinessException.ErrorCode.ERROR_UPDATE_CCC);
				}
			}else{
				throw new BusinessException(BusinessException.ErrorCode.ERROR_UPDATE_CCC);
			}
		} catch (Exception e) {
			audit.setResult("FAIL");
			audit.setDetail(BusinessException.ErrorCode.ERROR_UPDATE_CCC.toString());
			auditService.insert(audit);
			throw new BusinessException(BusinessException.ErrorCode.ERROR_UPDATE_CCC);
		}
		
    }
    
    /**
     * Modificar Email Billing y envio automático
     */
    public void updateEmailBillingAndActivation(String installationNumber, String emailBilling, boolean activation, boolean updateActivation, String agent){
    	//Auditoria
		Audit audit=new Audit();
		audit.setApp("Facturacion");
		audit.setUser(agent);
		audit.setDate(new Date());
    	try{
    		if (emailBilling!=null && !emailBilling.isEmpty()) {
    			spInstallationBillDataPortType.setEmailDataFromInstallation(installationNumber, emailBilling);
    			audit.setAction("EmailBilling Change");
    			audit.setResult("OK");
    			audit.setDetail("EmailBilling actualizado "+emailBilling);
    			auditService.insert(audit);
			}
    		if (updateActivation) {
    			//0 marca 1 desmarca
        		Integer flag;
        		if(activation==true){
        			flag=0;
        		}else{
        			flag=1;
        		}
        		spInstallationBillDataPortType.setEnvioAutomatico(installationNumber, flag);
        		audit.setAction("Envio Automatico Change");
    			audit.setResult("OK");
    			audit.setDetail("Envio Automatico actualizado "+activation);
    			auditService.insert(audit);
			}
    	}catch(Exception e){
    		audit.setResult("FAIL");
			audit.setDetail(BusinessException.ErrorCode.ERROR_UPDATE_DATA.toString());
			auditService.insert(audit);
    		throw new BusinessException(BusinessException.ErrorCode.ERROR_UPDATE_DATA);
    	}
    	
    }
    /**
     * Obtener el emailBilling
     * @param installationNumber
     * @return
     */
    public String getEmailBilling(String installationNumber){
    	try{
    		List<GetEmail> getEmails=spInstallationBillDataPortType.getEmailDataFromInstallation(installationNumber);
    		if (!getEmails.isEmpty()) {
    			return getEmails.get(0).getEmail();
    		}else{
    			throw new BusinessException(BusinessException.ErrorCode.ERROR_GET_EMAILBILLING);
    		}
    	}catch(Exception e){
    		throw new BusinessException(BusinessException.ErrorCode.ERROR_GET_EMAILBILLING);
    	}
    	
    }
    /**
     * Obtener la activacion automatica
     */
    public Boolean getActivation(String sIns){
    	try{
	    	//Envio automatico
			List<GetFlagsResult> getFlagsResult=spInstallationBillDataPortType.getEnvioAutomatico(Integer.parseInt(sIns));
			if(!getFlagsResult.isEmpty()){
				String XX=getFlagsResult.get(0).getMiscflgs().substring(0, 1);
				if(XX.equals("XX")){
					return true;
				}
			}
			return false;
    	}catch(Exception e){
    		throw new BusinessException(BusinessException.ErrorCode.ERROR_GET_ACTIVATION);
    	}
    }
    
    /**
     * Montar la informacion global de la pestalla de facturacion
     * String sIns - Numero interno de la instalacion
     * */
    public InvoiceGlobal getInvoiceGlobal(String sIns){
    	InvoiceGlobal invoiceGlobal= new InvoiceGlobal();
    	InvoiceInfo invoiceInfo=new InvoiceInfo();
    	try {
    		/**WS para obtener varable que sirve para llamar a otro ws para obtener el resto de datos*/
//			List<Billingdataresult> billingdataresults=spInstallationBillDataPortType.getBillingInformation(Integer.parseInt(sIns));
    		List<GetUmrResult> getUmrResults=spInstallationBillDataPortType.getUmr(Integer.parseInt(sIns));
			if (!getUmrResults.isEmpty()) {
				GetUmrResult getUmrResult=getUmrResults.get(0);
				/** WS para obtener numero de cuenta*/
				List<GetSepaResult> getSepaResults=spInstallationBillDataPortType.inetBlSepaGetQuery(getUmrResult.getUmr());
				if(!getSepaResults.isEmpty()){
					GetSepaResult getSepaResult=getSepaResults.get(0);
					//invoiceInfo - CCC
					invoiceInfo.setCcc(getSepaResult.getDebIban());
					//invoiceInfo - UMR
					invoiceInfo.setUmr(getUmrResult.getUmr());
					//Envio automatico
					List<GetFlagsResult> getFlagsResult=spInstallationBillDataPortType.getEnvioAutomatico(Integer.parseInt(sIns));
					if(!getFlagsResult.isEmpty()){
						String XX=getFlagsResult.get(0).getMiscflgs().substring(0, 1);
						if(XX.equals("XX")){
							invoiceInfo.setInvoiceSend(true);
						}else{
							invoiceInfo.setInvoiceSend(false);
						}
					}else{
						invoiceInfo.setInvoiceSend(false);
					}
					//Obtenemos el sAcc
					List<Billingdataresult> billingdataresults= spInstallationBillDataPortType.getBillingInformation(Integer.parseInt(sIns));
			    	if (!billingdataresults.isEmpty()) {
			    		invoiceGlobal.setCuote(this.getCuotes(billingdataresults.get(0).getSAcc().toString()));
			    		invoiceGlobal.setInvoiceList(this.getListInvoices(sIns, billingdataresults.get(0).getSAcc().toString()));
			    	}
			    	invoiceGlobal.setInvoiceInfo(invoiceInfo);
					invoiceGlobal.setCycleFeeds(this.getCycleFeeds(sIns));
					
//					//Email Billing de momento lo seteamos al obtener la instalación
//					List<GetEmail> getEmails=spInstallationBillDataPortType.getEmailDataFromInstallation(installationNumber);
//					if (!getEmails.isEmpty()) {
//						invoiceInfo.setEmailBilling(getEmails.get(0).getEmail());
//					}else{
//						throw new BusinessException(BusinessException.ErrorCode.ERROR_INVOICE_INFO_NOT_FOUND);
//					}
				}else{
					throw new BusinessException(BusinessException.ErrorCode.ERROR_INVOICE_INFO_NOT_FOUND);
				}
				return invoiceGlobal; 
			}else{
				throw new BusinessException(BusinessException.ErrorCode.ERROR_INVOICE_INFO_NOT_FOUND);
			}
    	
    	} catch (Exception e) {
			throw new BusinessException(BusinessException.ErrorCode.ERROR_INVOICE_INFO_NOT_FOUND);
		}
    }
}
