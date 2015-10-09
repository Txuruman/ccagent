package es.securitasdirect.moduloweb.web.controller;

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

import es.securitasdirect.moduloweb.model.InvoiceGlobal;
import es.securitasdirect.moduloweb.service.InvoiceService;
import es.securitasdirect.moduloweb.web.dto.request.UpdateActionPlansRequest;
import es.securitasdirect.moduloweb.web.dto.request.UpdateCCCRequest;
import es.securitasdirect.moduloweb.web.dto.response.InstallationResponse;
import es.securitasdirect.moduloweb.web.dto.response.InvoiceResponse;
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


    @RequestMapping(value = "getInvoice", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse getInvoice(@RequestParam(value = "sIns", required = true) String sIns) {
    	try{
    		InvoiceResponse response = new InvoiceResponse();
    		InvoiceGlobal invoiceGlobal=invoiceService.getInvoiceGlobal(sIns);
    		
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
     * Modificar o insertar action plans
     */
    @RequestMapping(value = "/updateCCC", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse updateCCC(@RequestBody UpdateCCCRequest request) {
    	LOGGER.debug("Update ActionPlans Request: {}", request);
    	InvoiceResponse response = new InvoiceResponse();
        try{
        	invoiceService.updateCCC(request.getDebIban(), request.getUmr(), request.getsIns());
	        response.setInvoiceGlobal(invoiceService.getInvoiceGlobal(request.getsIns()));
	        return response;
        }catch(Exception exception){
        	BaseResponse baseResponse=new BaseResponse();
        	return processException(baseResponse,exception);
        }
    }
    
    
}
