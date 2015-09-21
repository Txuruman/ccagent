package es.securitasdirect.moduloweb.web.controller;

import es.securitasdirect.moduloweb.model.Audit;
import es.securitasdirect.moduloweb.model.CombinationsKeys;
import es.securitasdirect.moduloweb.service.AdminService;
import es.securitasdirect.moduloweb.service.AuditService;
import es.securitasdirect.moduloweb.web.dto.response.AuditResponse;
import es.securitasdirect.moduloweb.web.dto.response.ListCombinationsKeysResponse;
import es.securitasdirect.moduloweb.web.dto.response.SimpleResponse;
import es.securitasdirect.moduloweb.web.dto.support.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/admin")
public class AdminController extends BaseController {


    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Inject
    protected AdminService adminService;


    @RequestMapping(value = "getFieldConfig", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    SimpleResponse getFieldConfig(@RequestParam(value = "app", required = true) String app) {
        SimpleResponse response = new SimpleResponse();
        response.setData(adminService.getFieldConfig(app));
        //TODO Respuesta?
        return response;
    }


    @RequestMapping(value = "listcombinationskeys", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse getListDirectAccess() {
        try{
            ListCombinationsKeysResponse response = new ListCombinationsKeysResponse();
            List<CombinationsKeys> list = adminService.getCombinationsKeys();
            response.setCombinationsKeys(list);
            return response;
        }catch(Exception exception){
            return processException(exception);
        }
    }

}
