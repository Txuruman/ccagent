package es.securitasdirect.moduloweb.web.controller;

import es.securitasdirect.moduloweb.web.dto.support.BaseResponse;
import es.securitasdirect.moduloweb.web.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import es.securitasdirect.moduloweb.web.dto.support.BaseRequest;
import org.wso2.ws.dataservice.DataServiceFault;

import javax.inject.Inject;
import java.util.Date;

public abstract class BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @Inject
    protected MessageUtil messageUtil;

    protected BaseResponse processException(Exception exception) {
        LOGGER.error("Error sent in BaseResponse {}" , exception.getMessage());
        BaseResponse response = new BaseResponse();
        response.danger(exception.getMessage()); //TODO MENSAGE GENERICO CON
        return response;
    }

    protected BaseResponse processException(Exception exception, String funcMsg){
        BaseResponse response = new BaseResponse();
        if(funcMsg!=null && !funcMsg.isEmpty()){
            response.danger(funcMsg);
        }
        LOGGER.error(funcMsg);
        if(exception instanceof DataServiceFault){
            DataServiceFault dataServiceFault = (DataServiceFault) exception;
            StringBuilder sb = new StringBuilder();
            sb.append("Error in data service: ")
                    .append("FaultInfo:").append(dataServiceFault.getFaultInfo())
                    .append("Message:").append(dataServiceFault.getMessage());
            response.danger(sb.toString());
            sb.append("\n").append(dataServiceFault.toString());
            LOGGER.error(sb.toString());
        }else{
            LOGGER.error("Error sent in BaseResponse {}\n{}" , exception.getMessage(),exception.toString());
        }
        return response;
    }

}
