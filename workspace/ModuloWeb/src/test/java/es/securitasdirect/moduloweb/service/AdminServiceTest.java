package es.securitasdirect.moduloweb.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wso2.ws.dataservice.Audit;
import org.wso2.ws.dataservice.CCAGENTADMPortType;

import javax.inject.Inject;
import javax.jws.WebParam;

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
        java.lang.Integer id=1;
        java.lang.String name="test1";
        java.lang.String descripition="desc1";
        java.lang.String url="url1";
        java.lang.Integer position =12;

        wsAdmin.insertDirectAccessOperation( id,
                name,
                descripition,
                url,
                position);
    }

    @Test
    public void insertDirectAccess() {

    }
}
