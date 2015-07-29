package es.securitasdirect.moduloweb.web.controller;

import es.securitasdirect.moduloweb.model.Audit;
import es.securitasdirect.moduloweb.service.AuditService;
import es.securitasdirect.moduloweb.web.dto.response.AuditResponse;
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
@RequestMapping("/audit")
public class AuditController extends BaseController {


    private static final Logger LOGGER = LoggerFactory.getLogger(AuditController.class);

    @Inject
    protected AuditService auditService;


    @RequestMapping(value = "getaudit", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    AuditResponse getAudit(@RequestParam(value = "installationId", required = true) Integer installationId) {//TODO PARAMETROS INSTALACION; USUARIUO;otros
        AuditResponse response = new AuditResponse();
        List<Audit> audits = auditService.getAudit(null,null,null); //TODO PARAMETROS
        response.setAudit(audits);

        //Solo mensaje en error
        if (audits == null) {
            response.danger(messageUtil.getProperty("installationData.notFound")); //TODO MENSAJE
        }
        return response;
    }

}
