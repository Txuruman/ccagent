package es.securitasdirect.moduloweb.web.controller;

import es.securitasdirect.moduloweb.model.DirectAccess;
import es.securitasdirect.moduloweb.model.InstallationData;
import es.securitasdirect.moduloweb.service.DirectAccessService;
import es.securitasdirect.moduloweb.service.InstallationService;
import es.securitasdirect.moduloweb.web.dto.request.SearchInstallationRequest;
import es.securitasdirect.moduloweb.web.dto.response.InstallationResponse;
import es.securitasdirect.moduloweb.web.dto.response.ListDirectAccessResponse;
import es.securitasdirect.moduloweb.web.dto.response.SearchInstallationResponse;
import es.securitasdirect.moduloweb.web.dto.support.BaseResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
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


    @RequestMapping(value = "getInstallation", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse getInstallation(@RequestParam(value = "installationId", required = true) String installationId) {
        InstallationResponse response = new InstallationResponse();
        try{
	        InstallationData installation = installationService.getInstallation(installationId);
	        response.setInstallation(installation);
      	         return response;
        }catch(Exception exception){
        	return processException(exception);
        }
//       if (installation != null) {
//            response.success(messageUtil.getProperty("installationData.success"));
//        } else {
//            response.danger(messageUtil.getProperty("installationData.notFound"));
//        }
    }
    
    @RequestMapping(value = "/searchInstallation", method = {RequestMethod.PUT}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse searchInstallation(@RequestBody SearchInstallationRequest request) {
        SearchInstallationResponse response = new SearchInstallationResponse();
        try{
	        response.setInstallationList(installationService.searchInstallations(request.getInstallationNumber(), request.getPhone(), request.getEmail(), request.getInstallationActive()));
	        return response;
        }catch(Exception exception){
        	return processException(exception);
        }
    }
}
