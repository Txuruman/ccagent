package es.securitasdirect.moduloweb.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wso2.ws.dataservice.Audit;
import org.wso2.ws.dataservice.CCAGENTAUDPortType;
import org.wso2.ws.dataservice.DataServiceFault;

import javax.inject.Inject;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by Javier Naval on 23/06/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//Evitamos cargar el applicationContext-jms para que funcionen los test
@ContextConfiguration(locations = {"classpath*:spring/applicationContext-bean.xml", "classpath*:spring/applicationContext-ws.xml"})
public class AuditServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditServiceTest.class);


    @Inject
    AuditService auditService;

    @Inject
    protected CCAGENTAUDPortType wsAudit;


    @Test
    public void testDirecto() throws DataServiceFault {
        String date = "11-12-2015 8:29:33";
        String actor = "JOSE";
        Integer id = 1;
        String app = "TAREA";
        String event = "evento";
        String result = "Ok";
        String detail = "detalle";



        wsAudit.insertAuditOperation(   date ,
                actor ,
                id ,
                app ,
                event ,
                result,
                detail);
    }

    @Test
    public void insert() {
        //TODO Borrar nuestro Audit
        Audit audit = new Audit();
        auditService.insert(audit);
    }
}
