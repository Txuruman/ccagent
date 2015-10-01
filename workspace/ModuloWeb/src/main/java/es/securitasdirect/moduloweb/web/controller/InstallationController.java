package es.securitasdirect.moduloweb.web.controller;

import es.securitasdirect.moduloweb.model.FieldConfig;
import es.securitasdirect.moduloweb.model.InstallationData;
import es.securitasdirect.moduloweb.service.AdminService;
import es.securitasdirect.moduloweb.service.InstallationService;
import es.securitasdirect.moduloweb.service.model.SearchInstallationResult;
import es.securitasdirect.moduloweb.web.dto.request.CodewordChangeRequest;
import es.securitasdirect.moduloweb.web.dto.request.DeleteActionPlansRequest;
import es.securitasdirect.moduloweb.web.dto.request.SearchInstallationRequest;
import es.securitasdirect.moduloweb.web.dto.request.UpdateActionPlansRequest;
import es.securitasdirect.moduloweb.web.dto.request.UpdateInstallationRequest;
import es.securitasdirect.moduloweb.web.dto.response.CodewordChangeResponse;
import es.securitasdirect.moduloweb.web.dto.response.InstallationResponse;
import es.securitasdirect.moduloweb.web.dto.response.ListFieldConfigResponse;
import es.securitasdirect.moduloweb.web.dto.response.SearchInstallationResponse;
import es.securitasdirect.moduloweb.web.dto.support.BaseResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

import java.util.List;

/**
 * Controller for the Tab of the Installation
 */
@Controller
@RequestMapping("/installation")
public class InstallationController extends BaseController {


    private static final Logger LOGGER = LoggerFactory.getLogger(InstallationController.class);

    @Inject
    protected InstallationService installationService;
    @Inject
    protected AdminService adminService;

    @RequestMapping(value = "getInstallation", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse getInstallation(@RequestParam(value = "installationId", required = true) String installationId) {
    	LOGGER.debug("Get Installation ID: {}", installationId);
    	InstallationResponse response = new InstallationResponse();
        try{
	        InstallationData installation = installationService.getInstallation(installationId);
	        response.setInstallation(installation);
      	         return response;
        }catch(Exception exception){
        	return processException(exception);
        }
    }
    
    @RequestMapping(value = "getFieldConfigByApp", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse getFieldConfig(@RequestParam(value = "app", required = true) String app) {
    	LOGGER.debug("Get Field Config By App: {}", app);
        ListFieldConfigResponse response = new ListFieldConfigResponse();
        try{
	        List<FieldConfig> list = adminService.getFieldConfigByApp(app);
	        response.setFieldConfig(list);
	        return response;
        }catch(Exception exception){
        	return processException(exception);
        }
    }
    
    
    
    @RequestMapping(value = "/searchInstallation", method = {RequestMethod.PUT}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse searchInstallation(@RequestBody SearchInstallationRequest request) {
    	LOGGER.debug("Search Installation Request: {}", request);
        SearchInstallationResponse response = new SearchInstallationResponse();
        try{
        	SearchInstallationResult searchInstallationResult=installationService.searchInstallations(request.getInstallationNumber(), request.getPhone(), request.getEmail(), request.getInstallationActive());    	
	        if (searchInstallationResult.isNoSearched()) {
				response.setNoSearched(true);
			}else{
				response.setInstallationList(searchInstallationResult.getInstallationList());
				response.setSearchBy(searchInstallationResult.getSearchBy());
			}
	        return response;
        }catch(Exception exception){
        	return processException(exception);
        }
    }
    
    @RequestMapping(value = "/codewordChange", method = {RequestMethod.PUT}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse codewordChange(@RequestBody List<CodewordChangeRequest> request) {
    	LOGGER.debug("Change CodeWord Request: {}", request);
    	CodewordChangeResponse response = new CodewordChangeResponse();
        try{
        	for (CodewordChangeRequest codewordChangeRequest : request) {
        		installationService.codewordChange(codewordChangeRequest.getAgent(),codewordChangeRequest.getCodeword(), codewordChangeRequest.getIx(), codewordChangeRequest.getInstallationNumber());
			}
        	//Refrescamos la instalación
        	response.setInstallation(installationService.getInstallation(request.get(0).getInstallationNumber()));
	        return response;
        }catch(Exception exception){
        	return processException(exception);
        }
    }
    
    @RequestMapping(value = "/updateInstallation", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse updateInstallation(@RequestBody UpdateInstallationRequest request) {
    	LOGGER.debug("Update Installation Request: {}", request);
        InstallationResponse response = new InstallationResponse();
        try{
	        installationService.updateInstallation(request.getAgent(), request.getInstallation());
	        response.setInstallation(installationService.getInstallation(request.getInstallation().getInstallationNumber()));
	        return response;
        }catch(Exception exception){
        	return processException(exception);
        }
    }
    
    /**
     * Eliminar action plans
     */
    @RequestMapping(value = "/deleteActionPlans", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse deleteActionPlans(@RequestBody DeleteActionPlansRequest request) {
    	LOGGER.debug("Delete ActionPlans Request: {}", request);
        InstallationResponse response = new InstallationResponse();
        try{
	        installationService.deleteActionPlans(request.getAgent(), request.getInstallationNumber(), request.getContactos());
	        response.setInstallation(installationService.getInstallation(request.getInstallationNumber()));
	        return response;
        }catch(Exception exception){
        	return processException(exception);
        }
    }
    
    /**
     * Modificar o insertar action plans
     */
    @RequestMapping(value = "/updateActionPlans", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse updateActionPlans(@RequestBody UpdateActionPlansRequest request) {
    	LOGGER.debug("Update ActionPlans Request: {}", request);
        InstallationResponse response = new InstallationResponse();
        try{
	        installationService.updateActionPlans(request.getAgent(), request.getInstallationNumber(), request.getContactos(), request.getAddedPlan());
	        response.setInstallation(installationService.getInstallation(request.getInstallationNumber()));
	        return response;
        }catch(Exception exception){
        	return processException(exception);
        }
    }
}
