package es.securitasdirect.moduloweb.model;

/**
 * Representa un teléfono de contacto
 */
public class Phone {

    public interface TYPE {
        public static final String MOVIL="movil";
        public static final String FIJO="fijo";
    }

    /**
     * clave autonumerica para la tabla
     */
    private Integer id;
    /** Tipo de telefono, movil, fijo... */
    private String type;

    /** Número de telefono */
    private String number;

    public Phone() {
    }

    public Phone(String type, String number) {
        this.type = type;
        this.number = number;
    }

    //constructor copia de la clase Phone
    /*
    public Phone(final org.wso2.ws.dataservice.GetPhoneResult getPhoneResult) {
        this.id = getPhoneResult.getId().intValue();
        this.type = getPhoneResult.getType();
        this.number = getPhoneResult.getNumber();
    }
    */



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Phone{");
        sb.append("type='").append(type).append('\'');
        sb.append(", number='").append(number).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
