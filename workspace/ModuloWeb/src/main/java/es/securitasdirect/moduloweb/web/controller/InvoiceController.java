package es.securitasdirect.moduloweb.web.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.securitasdirect.moduloweb.model.Cuote;
import es.securitasdirect.moduloweb.model.InvoiceGlobal;
import es.securitasdirect.moduloweb.model.InvoiceItem;
import es.securitasdirect.moduloweb.service.AuditService;
import es.securitasdirect.moduloweb.service.InvoiceService;
import es.securitasdirect.moduloweb.web.dto.request.GetInvoiceDetailRequest;
import es.securitasdirect.moduloweb.web.dto.request.UpdateActionPlansRequest;
import es.securitasdirect.moduloweb.web.dto.request.UpdateCCCRequest;
import es.securitasdirect.moduloweb.web.dto.request.UpdateEmailBillingAndActivationRequest;
import es.securitasdirect.moduloweb.web.dto.response.InstallationResponse;
import es.securitasdirect.moduloweb.web.dto.response.InvoiceDetailResponse;
import es.securitasdirect.moduloweb.web.dto.response.InvoiceResponse;
import es.securitasdirect.moduloweb.web.dto.response.UpdateEmailBillingAndActivationResponse;
import es.securitasdirect.moduloweb.web.dto.support.BaseResponse;

/**
 * Controller for the Tab of the Installation
 */
@Controller
@RequestMapping("/invoice")
public class InvoiceController extends BaseController {


    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceController.class);

    @Inject
    protected InvoiceService invoiceService;
    
    @Inject
    protected AuditService auditService;

    @RequestMapping(value = "getInvoice", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse getInvoice(@RequestParam(value = "sIns", required = true) String sIns) {
    	try{
    		InvoiceResponse response = new InvoiceResponse();
    		InvoiceGlobal invoiceGlobal=invoiceService.getInvoiceGlobal(sIns);
    		invoiceGlobal.setCuote(this.setFechasInter(invoiceGlobal.getCuote()));
    		response.setInvoiceGlobal(invoiceGlobal);
            return response;
    	}catch(Exception exception){
    		return processException(exception);
    	}
    	
//        
//        if (invoice != null) {
//            response.success(messageUtil.getProperty("installationData.success"));
//        } else {
//            response.danger(messageUtil.getProperty("installationData.notFound"));
//        }
        
    }
    /**
     * Actualizar CCC
     */
    @RequestMapping(value = "/updateCCC", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse updateCCC(@RequestBody UpdateCCCRequest request) {
    	LOGGER.debug("Update CCC Request: {}", request);
    	InvoiceResponse response = new InvoiceResponse();
        try{
        	invoiceService.updateCCC(request.getDebIban(), request.getUmr(), request.getsIns(), request.getAgent().getAgentIBS());
	        response.setInvoiceGlobal(invoiceService.getInvoiceGlobal(request.getsIns()));
	        response.setAuditList(auditService.getAudit(request.getAgent().getAgentIBS(), "Facturacion", request.getAgent().getFechaInicioAudit()));
	        return response;
        }catch(Exception exception){
        	BaseResponse baseResponse=new BaseResponse();
        	baseResponse.setAuditList(auditService.getAudit(request.getAgent().getAgentIBS(), "Facturacion", request.getAgent().getFechaInicioAudit()));
        	return processException(baseResponse,exception);
        }
    }
    
    /**
     * Actualizar CCC
     */
    @RequestMapping(value = "/getInvoiceDetail", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse getInvoiceDetail(@RequestBody GetInvoiceDetailRequest request) {
    	LOGGER.debug("Update ActionPlans Request: {}", request);
    	InvoiceDetailResponse response = new InvoiceDetailResponse();
        try{
        	response.setItemList(invoiceService.getInvoiceDetail(request.getsInv()));
        	return response;
        }catch(Exception exception){
        	BaseResponse baseResponse=new BaseResponse();
        	return processException(baseResponse,exception);
        }
    }
    
    /**
     * Actualizar EmailBilling y activacion autamatica
     */
    @RequestMapping(value = "/updateEmailBillingAndActivation", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse updateEmailBillingAndActivation(@RequestBody UpdateEmailBillingAndActivationRequest request) {
    	LOGGER.debug("Update EmailBilling and Activation Request: {}", request);
    	UpdateEmailBillingAndActivationResponse response = new UpdateEmailBillingAndActivationResponse();
        try{
        	invoiceService.updateEmailBillingAndActivation(request.getInstallationNumber(), request.getEmailbilling(), request.getActivation(), request.getUpdateActivation(), request.getAgent().getAgentIBS());
        	response.setActivation(invoiceService.getActivation(request.getSins()));
        	response.setEmailbilling(invoiceService.getEmailBilling(request.getInstallationNumber()));
        	response.setAuditList(auditService.getAudit(request.getAgent().getAgentIBS(), "Facturacion", request.getAgent().getFechaInicioAudit()));
        	return response;
        }catch(Exception exception){
        	BaseResponse baseResponse=new BaseResponse();
        	baseResponse.setAuditList(auditService.getAudit(request.getAgent().getAgentIBS(), "Facturacion", request.getAgent().getFechaInicioAudit()));
        	return processException(baseResponse,exception);
        }
    }
    
    /** Función para mandar las fechas internacionalizadas */
    private Cuote setFechasInter(Cuote cuotas){
    	if (cuotas.getFechaActual()!=null) {
    		String[] fecha=cuotas.getFechaActual().split("/");
    		cuotas.setFechaActual(messageUtil.getProperty("meses.mes"+fecha[1])+" 20"+fecha[2]);
		}
    	if (cuotas.getFechaEneroActual()!=null) {
    		String[] fecha=cuotas.getFechaEneroActual().split("/");
    		cuotas.setFechaEneroActual(messageUtil.getProperty("meses.mes"+fecha[1])+" 20"+fecha[2]);
		}
    	if (cuotas.getFechaEneroPasado()!=null) {
    		String[] fecha=cuotas.getFechaEneroPasado().split("/");
    		cuotas.setFechaEneroPasado(messageUtil.getProperty("meses.mes"+fecha[1])+" 20"+fecha[2]);
		}
    	return cuotas;
    }
   
}
