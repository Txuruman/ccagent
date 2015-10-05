package es.securitasdirect.moduloweb.model;

import java.util.Date;

/**
 * Modelo de Auditoría
 */
public class Audit {

    /** TODO Mientras no sepamos qué podemos guardar en Accion vamos a dejar unas constantes */
    public interface ACCTION {
        public static final String READ="read";
        public static final String WRITE="write";
    }

    /** TODO Mientras no sepamos qué podemos guardar en Accion vamos a dejar unas constantes */
    public interface RESULT {
        public static final String OK="ok";
        public static final String ERROR="error";
    }


    /**
     * Fecha y hora de registro del evento.
     */
    private Date date;

    /**
     * Nombre de usuario logado.
     */
    private String user;

    /**
     * CallID o ContactId
     * clave autonumerica para la tabla
     */
    private Integer id;

    /**
     * Clave de la aplicación
     */
    private String app;

    /**
     * Identificador de la operación que se ha realizado. Lectura o Escritura son los valores contemplados inicialmente.
     */
    private String action;

    /**
     *  Se utiliza para indicar si el resultado de la acción ejecutada ha sido correcto.
     * Tanto si es lectura como escritura se puede utilizar para registrar ambos intentos tanto de modificación como de acceso.
     */
    private String result;

    /**
     * Esto debe ser un “texto libre” pero lo más estructurado posible en el que se indica qué datos adicionales se han utilizado para la operación.
     * Por ejemplo en el caso de modificaciones los datos modificados. O la lista de con
     */
    private String detail;


  //constructor copia de la clase Audit
    public Audit(final org.wso2.ws.dataservice.Entry entry) {
        this.id = entry.getId().intValue();
        this.date = new Date(entry.getAuditDate().getMillisecond());
        this.user = entry.getActor();
        this.app = entry.getApp();
        this.action = entry.getEvent();
        this.result = entry.getResult();
        this.detail = entry.getDetail();

    }


    public Audit() {
		// TODO Auto-generated constructor stub
	}


	public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Audit{");
        sb.append("date=").append(date);
        sb.append(", user='").append(user).append('\'');
        sb.append(", id='").append(id).append('\'');
        sb.append(", app='").append(app).append('\'');
        sb.append(", action='").append(action).append('\'');
        sb.append(", result='").append(result).append('\'');
        sb.append(", detail='").append(detail).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
