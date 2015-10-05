package es.securitasdirect.moduloweb.model;

/**
 * Una instalación posee un listado de planes de acción.
 */
public class ActionPlan {

    /**
     * numero interno de instalacion
     */
    private String sins;

    /** Tipo de plan */
    private String type;

    /** Secuencia para ordenación */
    private Integer seq;

    /** Datos personales de la persona de contacto. */
    private String name;

    /** Datos de contacto 1 de la persona */
    private Phone phone1;

    /** Datos de contacto 2 de la persona */
    private Phone phone2;

    /** Datos de contacto 3 de la persona */
    private Phone phone3;
    
    /** Parametros devueltos por el metodo que obtiene los tipos de los telefonos.
     *  Los guardamos en el ActionPlan para la modificacion, borrado e inserción.
     */
    private String spc;
    
    private String scont;
    
    private String scix;
    
    private String pix;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
	
    public String getSpc() {
		return spc;
	}

	public void setSpc(String spc) {
		this.spc = spc;
	}

	public String getScont() {
		return scont;
	}

	public void setScont(String scont) {
		this.scont = scont;
	}

	public String getScix() {
		return scix;
	}

	public void setScix(String scix) {
		this.scix = scix;
	}

	public String getPix() {
		return pix;
	}

	public void setPix(String pix) {
		this.pix = pix;
	}

	public String getSins() {
		return sins;
	}

	public void setSins(String sins) {
		this.sins = sins;
	}

	@Override
	public String toString() {
		return "ActionPlan [sins=" + sins + ", type=" + type + ", seq=" + seq + ", name=" + name + ", phone1=" + phone1
				+ ", phone2=" + phone2 + ", phone3=" + phone3 + ", spc=" + spc + ", scont=" + scont + ", scix=" + scix
				+ ", pix=" + pix + "]";
	}


}
