package es.securitasdirect.moduloweb.model;

public class DirectAccessParams {

    /**
     * clave autonumerica para la tabla
     */
    private Integer id;
	private String name;
	private String value;
    private Integer directAccess;
	
	public DirectAccessParams() {}
	
	public DirectAccessParams(String name, String value, Integer directAccess) {
		super();
		this.name = name;
		this.value = value;
        this.directAccess=directAccess;
	}

    //constructor copia de la clase DirectAccessParams
    public DirectAccessParams(final org.wso2.ws.dataservice.GetDirectAccessParamsResult getDirectAccessParamsResult) {
        this.id = getDirectAccessParamsResult.getId().intValue();
        this.name = getDirectAccessParamsResult.getName();
        this.value = getDirectAccessParamsResult.getValue();
        this.directAccess = getDirectAccessParamsResult.getDirectAccess().intValue();
    }

    public Integer getId() {
        return id;
    }
    public void setName(Integer id) {
        this.id = id;
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
    public Integer getDirectAccess() {
        return directAccess;
    }
    public void setDirectAccess(Integer directAccess) {
        this.directAccess = directAccess;
    }

	
	
}
