package es.securitasdirect.moduloweb.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wso2.ws.dataservice.Audit;

import javax.inject.Inject;

/**
 * Created by Javier Naval on 23/06/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//Evitamos cargar el applicationContext-jms para que funcionen los test
@ContextConfiguration(locations = {"classpath*:spring/applicationContext-bean.xml","classpath*:spring/applicationContext-ws.xml"})
public class AdminServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceTest.class);



    @Inject
    AdminService adminService;




    @Test
    public void insertDirectAccess() {

    }
}
