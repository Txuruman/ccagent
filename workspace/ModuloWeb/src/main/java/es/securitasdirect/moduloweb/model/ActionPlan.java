package es.securitasdirect.moduloweb.model;

/**
 * Una instalación posee un listado de planes de acción.
 */
public class ActionPlan {

    /** Tipo de plan */ /* TODO PASAR A ENUM??? */
    private String type;

    /** Secuencia para ordenación */
    private Integer secuence;

    /** Datos personales de la persona de contacto. */
    private String contactName;

    /** Datos de contacto 1 de la persona */
    private Phone phone1;

    /** Datos de contacto 2 de la persona */
    private Phone phone2;

    /** Datos de contacto 3 de la persona */
    private Phone phone3;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSecuence() {
        return secuence;
    }

    public void setSecuence(Integer secuence) {
        this.secuence = secuence;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Phone getPhone1() {
        return phone1;
    }

    public void setPhone1(Phone phone1) {
        this.phone1 = phone1;
    }

    public Phone getPhone2() {
        return phone2;
    }

    public void setPhone2(Phone phone2) {
        this.phone2 = phone2;
    }

    public Phone getPhone3() {
        return phone3;
    }

    public void setPhone3(Phone phone3) {
        this.phone3 = phone3;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ActionPlan{");
        sb.append("type='").append(type).append('\'');
        sb.append(", secuence=").append(secuence);
        sb.append(", contactName='").append(contactName).append('\'');
        sb.append(", phone1=").append(phone1);
        sb.append(", phone2=").append(phone2);
        sb.append(", phone3=").append(phone3);
        sb.append('}');
        return sb.toString();
    }
}
