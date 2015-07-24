package es.securitasdirect.moduloweb.web.controller;

import es.securitasdirect.moduloweb.model.DirectAccess;
import es.securitasdirect.moduloweb.service.DirectAccessService;
import es.securitasdirect.moduloweb.web.dto.response.ListDirectAccessResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author
 */

@Controller
@RequestMapping("/")
public class MainController extends BaseController {


    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @Inject
    protected DirectAccessService directAccessService;

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
        ModelAndView mv = new ModelAndView("marco");
        return mv;
    }


    @RequestMapping("test.html")
    public ModelAndView gotoTest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ///Redirect to buscartarea.html
        ModelAndView mv = new ModelAndView("test");
        return mv;
    }


    @RequestMapping(value = "/listdirectaccess", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    ListDirectAccessResponse getListDirectAccess() {
        ListDirectAccessResponse response = new ListDirectAccessResponse();
        List<DirectAccess> list = directAccessService.getDirectAccess();
        response.setDirectAcess(list);

        if (list != null) {
            response.success(messageUtil.getProperty("notificationTask.postpone.success"));
        } else {
            response.danger(messageUtil.getProperty("notificationTask.postpone.error"));
        }
        return response;
    }

}
