package es.securitasdirect.moduloweb.web.controller;

import es.securitasdirect.moduloweb.model.FieldConfig;
import es.securitasdirect.moduloweb.model.InstallationData;
import es.securitasdirect.moduloweb.service.AdminService;
import es.securitasdirect.moduloweb.service.AuditService;
import es.securitasdirect.moduloweb.service.InstallationService;
import es.securitasdirect.moduloweb.service.model.SearchInstallationResult;
import es.securitasdirect.moduloweb.web.dto.request.CodewordChangeRequest;
import es.securitasdirect.moduloweb.web.dto.request.DeleteActionPlansRequest;
import es.securitasdirect.moduloweb.web.dto.request.InstallationRequest;
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
    @Inject
    protected AuditService auditService;
    
    @RequestMapping(value = "getInstallation", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse getInstallation(@RequestBody InstallationRequest request) {
    	LOGGER.debug("Get Installation ID: {}", request.getInstallationNumber());
    	InstallationResponse response = new InstallationResponse();
        try{
	        InstallationData installation = installationService.getInstallation(request.getInstallationNumber());
	        response.setInstallation(installation);
	        installationService.crearComentario(installation, request.getAgent(), "Obteniendo instalación "+request.getInstallationNumber());
	        if (installation.getEmailMonitoring().isEmpty()) {
	        	response.info(messageUtil.getProperty("info.nomonitoringmail"));
			}
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
				
				/** Crear Comentario */
				String event= "Buscando instalacion por ";
				if(searchInstallationResult.getSearchBy()==0){
					event+="número de instalación";
				}else if(searchInstallationResult.getSearchBy()==1){
					event+="teléfono";
				}else if(searchInstallationResult.getSearchBy()==2){
					event+="email";
				}
				installationService.crearComentario(searchInstallationResult.getInstallationList().get(0), request.getAgent(), event);
				
//				if (searchInstallationResult.getInstallationList().get(0).getEmailMonitoring().isEmpty()) {
//		        	response.info(messageUtil.getProperty("info.nomonitoringmail"));
//				}
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
        		installationService.codewordChange(codewordChangeRequest.getAgent().getAgentIBS(),codewordChangeRequest.getCodeword(), codewordChangeRequest.getIx(), codewordChangeRequest.getInstallationNumber());
			}
        	//Refrescamos la instalación
        	response.setInstallation(installationService.getInstallation(request.get(0).getInstallationNumber()));
        	installationService.crearComentario(response.getInstallation(), request.get(0).getAgent(), "Cambio claves instalación "+request.get(0).getInstallationNumber());
	        response.setAuditList(auditService.getAudit(request.get(0).getAgent().getAgentIBS(), "Info instalacion", request.get(0).getAgent().getFechaInicioAudit()));
        	return response;
        }catch(Exception exception){
        	BaseResponse baseResponse=new BaseResponse();
        	baseResponse.setAuditList(auditService.getAudit(request.get(0).getAgent().getAgentIBS(), "Info instalacion", request.get(0).getAgent().getFechaInicioAudit()));
        	return processException(baseResponse,exception);
        }
    }
    
    @RequestMapping(value = "/updateInstallation", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse updateInstallation(@RequestBody UpdateInstallationRequest request) {
    	LOGGER.debug("Update Installation Request: {}", request);
        InstallationResponse response = new InstallationResponse();
        try{
	        installationService.updateInstallation(request.getAgent().getAgentIBS(), request.getInstallation());
	        response.setInstallation(installationService.getInstallation(request.getInstallation().getInstallationNumber()));
	        response.setAuditList(auditService.getAudit(request.getAgent().getAgentIBS(), "Info instalacion", request.getAgent().getFechaInicioAudit()));
	        return response;
        }catch(Exception exception){
        	BaseResponse baseResponse=new BaseResponse();
        	baseResponse.setAuditList(auditService.getAudit(request.getAgent().getAgentIBS(), "Info instalacion", request.getAgent().getFechaInicioAudit()));
        	return processException(baseResponse,exception);
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
	        installationService.deleteActionPlans(request.getAgent().getAgentIBS(), request.getContactos());
	        response.setInstallation(installationService.getInstallation(request.getInstallationNumber()));
	        response.setAuditList(auditService.getAudit(request.getAgent().getAgentIBS(), "Info instalacion", request.getAgent().getFechaInicioAudit()));
	        return response;
        }catch(Exception exception){
        	BaseResponse baseResponse=new BaseResponse();
        	baseResponse.setAuditList(auditService.getAudit(request.getAgent().getAgentIBS(), "Info instalacion", request.getAgent().getFechaInicioAudit()));
        	return processException(baseResponse,exception);
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
	        installationService.updateActionPlans(request.getAgent().getAgentIBS(), request.getInstallationNumber(), request.getContactos(), request.getAddedPlan());
	        response.setInstallation(installationService.getInstallation(request.getInstallationNumber()));
	        response.setAuditList(auditService.getAudit(request.getAgent().getAgentIBS(), "Info instalacion", request.getAgent().getFechaInicioAudit()));
	        return response;
        }catch(Exception exception){
        	BaseResponse baseResponse=new BaseResponse();
        	baseResponse.setAuditList(auditService.getAudit(request.getAgent().getAgentIBS(), "Info instalacion", request.getAgent().getFechaInicioAudit()));
        	return processException(baseResponse,exception);
        }
    }
}
