package es.securitasdirect.moduloweb.service;

import es.securitasdirect.moduloweb.model.DirectAccess;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wso2.ws.dataservice.Audit;
import org.wso2.ws.dataservice.CCAGENTADMPortType;
import org.wso2.ws.dataservice.Entry;
import org.wso2.ws.dataservice.GetDirectAccessResult;

import javax.inject.Inject;
import javax.jws.WebParam;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by Javier Naval on 23/06/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//Evitamos cargar el applicationContext-jms para que funcionen los test
@ContextConfiguration(locations = {"classpath*:spring/applicationContext-bean.xml", "classpath*:spring/applicationContext-ws.xml"})
public class AdminServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceTest.class);


    @Inject
    AdminService adminService;

    @Inject
    CCAGENTADMPortType wsAdmin;


    @Test
    public void testDirecto() {

        /*
        java.lang.String name="test1";
        java.lang.String descripition="desc1";
        java.lang.String url="url1";
        java.lang.Integer position =12;

        try {
            List<Entry> le =  wsAdmin.insertDirectAccessOperation(
                    name,
                    descripition,
                    url,
                    position);

            BigInteger salida = le.get(0).getID();
        }
        catch (Exception e) {
            LOGGER.equals("Error");
        }
        */
    }

    @Test
    public void insertDirectAccess() {

    }


    @Test
    public void getDirectAccess() {


        List<DirectAccess> list = adminService.getDirectAccess();
        LOGGER.debug("lista", list);

    }


    @Test
    public void getCombinationsKeys() {


        //List<GetCombinationsKeys> list = adminService.getCombinationsKeys();
        //LOGGER.debug("lista", list);

    }

}
