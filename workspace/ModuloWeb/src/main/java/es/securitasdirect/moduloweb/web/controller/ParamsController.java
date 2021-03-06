package es.securitasdirect.moduloweb.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("params.htm")
public class ParamsController  {

    @RequestMapping
    public ModelAndView handleRequest(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ModelAndView mv = new ModelAndView("params");

        //TODO Borrar, temporal para ver los parámetros enviados a la página
        mv.addObject("todosparametros", createParameterMap(hsr));

        return mv;
    }


    public Map<String, String> createParameterMap(HttpServletRequest hsr) {
        Map<String, String> parameterValues = new HashMap<String, String>();

        for (Object parameterName : hsr.getParameterMap().keySet()) {
            parameterValues.put(parameterName.toString(), hsr.getParameter(parameterName.toString()));
        }
        return parameterValues;
    }
}
