package es.securitasdirect.moduloweb.web.controller;

import es.securitasdirect.moduloweb.web.dto.support.BaseResponse;
import es.securitasdirect.moduloweb.web.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Date;

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
            response.danger(messageUtil.getProperty(((BusinessException) exception).getErrorCode().toString(),((BusinessException) exception).getErrorParams()));
        } else {
            //Excepción General
            LOGGER.error("Server error {}" , exception.getMessage(),exception);
            response.danger(messageUtil.getProperty("exception", exception.getMessage()));
        }

        return response;
    }

}
