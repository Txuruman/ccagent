package es.securitasdirect.moduloweb.model;

public class DirectAccessParams {

    /**
     * clave autonumerica para la tabla
     */
    private Integer id;
	private String name;
	private String value;	
	
	public DirectAccessParams() {}
	
	public DirectAccessParams(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

    //constructor copia de la clase DirectAccessParams
    public DirectAccessParams(final org.wso2.ws.dataservice.GetDirectAccessParamsResult getDirectAccessParamsResult) {
        this.id = getDirectAccessParamsResult.getId().intValue();
        this.name = getDirectAccessParamsResult.getName();
        this.value = getDirectAccessParamsResult.getValue();
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DirectAccessParams other = (DirectAccessParams) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	
}
