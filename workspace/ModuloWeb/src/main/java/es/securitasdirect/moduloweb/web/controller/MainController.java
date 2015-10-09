package es.securitasdirect.moduloweb.web.controller;

import es.securitasdirect.moduloweb.model.DirectAccess;
import es.securitasdirect.moduloweb.service.AdminService;
import es.securitasdirect.moduloweb.service.HappyService;
import es.securitasdirect.moduloweb.service.model.HappyData;
import es.securitasdirect.moduloweb.web.dto.response.ListDirectAccessResponse;
import es.securitasdirect.moduloweb.web.dto.support.BaseResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.List;

/**
 * @author
 */

@Controller
@RequestMapping("/")
public class MainController extends BaseController {
	
//	@Inject
//    protected HappyService happyService;
	
    /**
     * Parámetros post que se reciven en la request al cargar el marco principal
     */
    public interface EXTERNAL_PARAMS {
        String INSTALLATION = "bp_out_INSTALACION";
        String KEY1 = "key1";
        String KEY2 = "key2";
        String KEY3 = "key3";
        String MATRICULA = "bp_agentIBS";
        String CALL_ID = "call_id";
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @Inject
    protected AdminService adminService;


    /**
     * Redirige a la página principal de marco
     *
     * @param hsr
     * @param hsr1
     * @return
     * @throws Exception
     */
    @RequestMapping("marco.html")
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ///Redirect to buscartarea.html
        ModelAndView mv = new ModelAndView("mainFrame");

        String key1 = hsr.getParameter(EXTERNAL_PARAMS.KEY1);
        String key2 = hsr.getParameter(EXTERNAL_PARAMS.KEY2);
        String key3 = hsr.getParameter(EXTERNAL_PARAMS.KEY3);
        String installation = hsr.getParameter(EXTERNAL_PARAMS.INSTALLATION);
        String agent = hsr.getParameter(EXTERNAL_PARAMS.MATRICULA);
        String call_id = hsr.getParameter(EXTERNAL_PARAMS.CALL_ID);
        String activeTab=adminService.getActiveTabFromKeys(key1, key2, key3);
        mv.addObject("activeTab", activeTab);
        mv.addObject("installation", installation);
        mv.addObject("agent", agent);
        mv.addObject("fechaInicioAudit", System.currentTimeMillis());
        mv.addObject("key1", key1);
        mv.addObject("key2", key2);
        mv.addObject("key3", key3);
        mv.addObject("call_id", call_id);
        
        return mv;
    }


    @RequestMapping("test.html")
    public ModelAndView gotoTest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ///Redirect to buscartarea.html
        ModelAndView mv = new ModelAndView("test");
        return mv;
    }


    @RequestMapping(value = "listdirectaccess", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse getListDirectAccess() {
       try{
	    	ListDirectAccessResponse response = new ListDirectAccessResponse();
	        List<DirectAccess> list = adminService.getDirectAccess();
	        response.setDirectAcess(list);
	        return response;
	    }catch(Exception exception){
	    	return processException(exception);
	    }  
    }
    
//    @RequestMapping(value = "/happy", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
//    public     @ResponseBody
//    HappyData getStatus() {
//        HappyData happyData = happyService.getHappyData();
//        LOGGER.debug("Returning happy data {}", happyData);
//        return happyData;
//    }

}
