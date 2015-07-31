package es.securitasdirect.moduloweb.web.controller;

import es.securitasdirect.moduloweb.model.InvoiceInfo;
import es.securitasdirect.moduloweb.service.InvoiceService;
import es.securitasdirect.moduloweb.web.dto.response.InvoiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
    InvoiceResponse getInstallation(@RequestParam(value = "invoiceId", required = true) Integer invoiceId) {
       InvoiceResponse response = new InvoiceResponse();
        InvoiceInfo invoice = invoiceService.getInvoice(invoiceId);
        response.setInvoiceInfo(invoice);

        if (invoice != null) {
            response.success(messageUtil.getProperty("installationData.success"));
        } else {
            response.danger(messageUtil.getProperty("installationData.notFound"));
        }
        return response;
    }

}