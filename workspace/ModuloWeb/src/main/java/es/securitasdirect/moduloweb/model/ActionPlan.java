package es.securitasdirect.moduloweb.model;

/**
 * Una instalación posee un listado de planes de acción.
 */
public class ActionPlan {

    /**
     * clave interna del contacto
     */
    private String id;

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
    /** Posicion (indice) del contacto */
    private Integer position;

    //constructor copia de la clase ActionPlan
    /*
    public ActionPlan(final org.wso2.ws.dataservice.GetActionPlanResult getPhoneResult) {
        this.id = getActionPlanResult.getId().intValue();
        this.type = getActionPlanResult.getType();
        this.secuence = getActionPlanResult.getSecuence();
        this.contactName = getActionPlanResult.getContactName();
        this.phone1 = getActionPlanResult.getPhone1();
        this.phone2 = getActionPlanResult.getPhone2();
        this.phone3 = getActionPlanResult.getPhone3();

    }
    */


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
    
    public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "ActionPlan [id=" + id + ", type=" + type + ", secuence=" + secuence + ", contactName=" + contactName
				+ ", phone1=" + phone1 + ", phone2=" + phone2 + ", phone3=" + phone3 + ", position=" + position + "]";
	}
}
