package es.securitasdirect.moduloweb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.ws.dataservice.DataServiceFault;
import org.wso2.ws.dataservice.RowErrorAA;
import org.wso2.ws.dataservice.SPAIOTAREAS2PortType;
import org.wso2.ws.dataservice.SPAVISOSOPERACIONESPortType;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.List;
import java.util.Map;

/**
 * Service to access the Direct Access
 */
@Named(value = "directAccessService")
@Singleton
public class DirectAccessService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DirectAccessService.class);




}
