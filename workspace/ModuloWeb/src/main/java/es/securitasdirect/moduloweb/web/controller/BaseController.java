package es.securitasdirect.moduloweb.web.controller;

import es.securitasdirect.moduloweb.exceptions.BusinessException;
import es.securitasdirect.moduloweb.web.dto.support.BaseResponse;
import es.securitasdirect.moduloweb.web.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @Inject
    protected MessageUtil messageUtil;

    /**
     * Procesa una excepción y añade el texto de error como respuesta del mensaje
     * @param exception
     * @return
     */
    protected BaseResponse processException(Exception exception) {
        return processException(new BaseResponse(), exception);
    }

    /**
     * Procesa una excepción y añade el texto de error como respuesta del mensaje a una respuesta ya existente
     * @param exception
     * @return
     */
    protected BaseResponse processException(BaseResponse response, Exception exception) {
        if (exception instanceof BusinessException) {
            //Excepción de negocio
            LOGGER.error("Business error {}" , exception.getMessage(),exception);
            response.danger(messageUtil.getProperty(((BusinessException) exception).getErrorCode().toString(),((BusinessException) exception).getErrorParams()==null?null:((BusinessException) exception).getErrorParams().toArray()));
        } else {
            //Excepción General
            LOGGER.error("Server error {}" , exception.getMessage(),exception);
            response.danger(messageUtil.getProperty("exception", exception.getMessage()));
        }

        return response;
    }
    
    protected BaseResponse processException(Exception exception, String funcMsgParam){
        LOGGER.error(exception.getMessage(),exception);
        BaseResponse response = new BaseResponse();
        if(funcMsgParam!=null && !funcMsgParam.isEmpty()){
            try {
                String localizedMessage = messageUtil.getProperty(funcMsgParam + ".error");
                response.danger(localizedMessage);
            } catch (Exception e) {
                response.danger(e.getMessage());
            }
        }
        return response;
    }



    protected BaseResponse processSuccessMessages(Object obj, String msg) {
        BaseResponse response = new BaseResponse();
        if (obj != null) {
            if (obj instanceof List) {
                if (!((List) obj).isEmpty()) {
                    response.success(messageUtil.getProperty(msg + ".success"));
                    LOGGER.debug("Service {} - Response {}", msg, "Success");
                } else {
                    //response.warning(messageUtil.getProperty(msg + ".notFound"));
                    LOGGER.debug("Service {} - Response {}", msg, "Not Found");
                }
            } else {
                response.success(messageUtil.getProperty(msg + ".success"));
                LOGGER.debug("Service {} - Response {}", msg, "Success");
            }
        } else {
            response.warning(messageUtil.getProperty(msg + ".notFound"));
            LOGGER.debug("Service {} - Response {}", msg, "Not Found");
        }
        return response;
    }

    protected BaseResponse processParamsError(String msg){
        BaseResponse response = new BaseResponse();
        response.warning(msg + ".paramsError");
        return response;
    }



    protected BaseResponse processSuccessMessages(boolean res, String msg){
        Object object = null;
        if(res){
            object=new Object();
        }
        return processSuccessMessages(object, msg);
    }

    public Map<String, String> createParameterMap(HttpServletRequest hsr) {
        Map<String, String> parameterValues = new HashMap<String, String>();

        for (Object parameterName : hsr.getParameterMap().keySet()) {
            parameterValues.put(parameterName.toString(), hsr.getParameter(parameterName.toString()));
        }
        return parameterValues;
    }
}
