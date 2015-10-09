package es.securitasdirect.moduloweb.service;

import es.securitasdirect.moduloweb.exceptions.BusinessException;
import es.securitasdirect.moduloweb.model.Cuote;
import es.securitasdirect.moduloweb.model.CycleFeeds;
import es.securitasdirect.moduloweb.model.DummyGenerator;
import es.securitasdirect.moduloweb.model.InvoiceData;
import es.securitasdirect.moduloweb.model.InvoiceGlobal;
import es.securitasdirect.moduloweb.model.InvoiceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.ws.dataservice.Billingdataresult;
import org.wso2.ws.dataservice.DataServiceFault;
import org.wso2.ws.dataservice.GetSepaResult;
import org.wso2.ws.dataservice.GetUmrResult;
import org.wso2.ws.dataservice.Installation;
import org.wso2.ws.dataservice.SPInstallationBillDataPortType;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.List;

/**
 * Service to access the Invoice Info
 */
@Named(value = "invoiceService")
@Singleton
public class InvoiceService {
	
	@Inject
	protected SPInstallationBillDataPortType spInstallationBillDataPortType;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceService.class);

    public InvoiceInfo getInvoice(Integer invoiceId) {
    	InvoiceInfo info=DummyGenerator.getInvoice(invoiceId);
    	if (info==null) {
    		throw new BusinessException(BusinessException.ErrorCode.ERROR_INVOICE_INFO_NOT_FOUND);
		}else{
			return info;
		}
        
    }
    public List<CycleFeeds> getCycleFeeds(Integer installationId) {
    	List<CycleFeeds> list= DummyGenerator.getCycleFeeds(installationId);
    	if (list.isEmpty()) {
    		throw new BusinessException(BusinessException.ErrorCode.ERROR_CYCLE_FEEDS_NOT_FOUND);
    	}else{
			return list;
		}
    }
    public List<InvoiceData>getListInvoices(Integer installationNumber){
    	List<InvoiceData> list= DummyGenerator.getListInvoices(installationNumber);
    	if (list.isEmpty()) {
    		throw new BusinessException(BusinessException.ErrorCode.ERROR_INVOICE_LIST_NOT_FOUND);
    	}else{
			return list;
		}
    }
    public Cuote getCuotes(Integer installationNumber){
    	Cuote cuote= DummyGenerator.getCuotes(installationNumber);
    	if (cuote==null) {
    		throw new BusinessException(BusinessException.ErrorCode.ERROR_CUOTES_NOT_FOUND);
		}else{
			return cuote;
		}
    }
    
    /** Actualizar valor de la CCC */
    public void updateCCC(String debIban, String umr, String sIns){
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
				}else{
					throw new BusinessException(BusinessException.ErrorCode.ERROR_UPDATE_CCC);
				}
			}else{
				throw new BusinessException(BusinessException.ErrorCode.ERROR_UPDATE_CCC);
			}
		} catch (Exception e) {
			throw new BusinessException(BusinessException.ErrorCode.ERROR_UPDATE_CCC);
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
				}else{
					throw new BusinessException(BusinessException.ErrorCode.ERROR_INVOICE_INFO_NOT_FOUND);
				}
				
				
				invoiceGlobal.setInvoiceInfo(invoiceInfo);
				invoiceGlobal.setCuote(this.getCuotes(111111));
				invoiceGlobal.setCycleFeeds(this.getCycleFeeds(11111));
				invoiceGlobal.setInvoiceList(this.getListInvoices(11111));
				return invoiceGlobal; 
			}else{
				throw new BusinessException(BusinessException.ErrorCode.ERROR_INVOICE_INFO_NOT_FOUND);
			}
    	
    	} catch (Exception e) {
			throw new BusinessException(BusinessException.ErrorCode.ERROR_INVOICE_INFO_NOT_FOUND);
		}
    }
}
