package es.securitasdirect.moduloweb.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author
 */

@Controller
@RequestMapping("/")
public class MainController extends BaseController {


    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);


    /**
     * Redirige a la página principal de marco
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
    public ModelAndView gotoTest (HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ///Redirect to buscartarea.html
        ModelAndView mv = new ModelAndView("test");
        return mv;
    }


}
