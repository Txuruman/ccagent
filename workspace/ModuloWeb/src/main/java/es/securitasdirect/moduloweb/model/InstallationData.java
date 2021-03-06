package es.securitasdirect.moduloweb.model;

import java.util.List;

import org.wso2.ws.dataservice.Installationcontactsresult;

/**
 * Info of the Installation
 */
public class InstallationData {

    /**
     * clave autonumerica para la tabla
     */
    private Integer id;

    /** Modelo de Panel */
    private String panel;

    private String version;

    /** Número del cliente. Esto es el número de instalación????  */
    private String installationNumber;
    /** Número interno de la instalacion*/
    private String sins;
    /** Teléfonos del Panel */
    private String panelPhone;
    private String phone2;
    private String phone3;
    private String servicesPhone;
    
    
    /** Nombre del cliente */
    private String customerName;

    private String emailMonitoring;

    private String emailBilling; //Lo cambiamos a InvoiceInfo

    private String emailUpdate;
    
    /** Email de servicios*/
    private String emailServices;
    
    /** Indica la marca de la c´mara que la instalación tiene */
    private String camera;

    /** Identificación cliente empresa */
    private String aka;

    /** Dirección de la instalación */
    private String address;

    /** Ciudad de la instalación */
    private String city;

    /** Estado de la monitorización */
    private String monitoringStatus;

    /** Segmento */
    private String subtype;
    
    /** Idioma */
    private String language;
    
//    /** Cuenta corriente */
//    private String ccc;

    /** Palabra clave del Cliente */
    private String customerPassword;

    /** Palabra clave de Securitas */
    private String securitasPassword;

    /** Palabra clave de Coacción */
    private String coercionPassword;

    /** Lista de los planes de acción de la instalación */
    private List<ActionPlan> actionplans;
    
    /** Teléfonos ¿? */
    private String telefono1;
    private String telefono2;
    private String telefono3;
    private String telefonoServicios;
    
    /** Email de servicios*/
    private String emailServicios;
    
    /**True si los email de monitoring y billing son diferentes*/
    private boolean emailsAreDiferents=false;
    
    private String dealer;

    //constructor copia de la clase InstallationData
    /*
    public InstallationData(final org.wso2.ws.dataservice.GetInstallationDataResult getInstallationDataResult) {
        this.id = getInstallationDataResult.getId().intValue();
        this.panel = getInstallationDataResult.getPanel();
        this.version = getInstallationDataResult.getVersion();
        this.installationNumber = getInstallationDataResult.getInstallationNumber();
        this.panelPhone = getInstallationDataResult.getPanelPhone();
        this.customerName = getInstallationDataResult.getCustomerName();
        this.emailMonitoring = getInstallationDataResult.getEmailMonitoring();
        this.emailBilling = getInstallationDataResult.getEmailBilling();
        this.emailUpdate = getInstallationDataResult.getEmailUpdate();
        this.emailServices = getInstallationDataResult.getEmailServices();
        this.camera = getInstallationDataResult.getCamera();
        this.aka = getInstallationDataResult.getAka();
        this.address = getInstallationDataResult.getAddress();
        this.city = getInstallationDataResult.getCity();
        this.monitoringStatus = getInstallationDataResult.getMonitoringStatus();
        this.subtype = getInstallationDataResult.getSubtype();
        this.language = getInstallationDataResult.getLanguage();
        this.customerPassword = getInstallationDataResult.getCustomerPassword();
        this.securitasPassword = getInstallationDataResult.getSecuritasPassword();
        this.coercionPassword = getInstallationDataResult.getCoercionPassword();
        this.actionplans = getInstallationDataResult.getActionplans();
        this.telefono1 = getInstallationDataResult.getTelefono1();
        this.telefono2 = getInstallationDataResult.getTelefono2();
        this.telefono3 = getInstallationDataResult.getTelefono3();
        this.telefonoServicios = getInstallationDataResult.getTelefonoServicios();
        this.emailServicios = getInstallationDataResult.getEmailServicios();
    }
    */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPanel() {
        return panel;
    }

    public void setPanel(String panel) {
        this.panel = panel;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getInstallationNumber() {
        return installationNumber;
    }

    public void setInstallationNumber(String installationNumber) {
        this.installationNumber = installationNumber;
    }

    public String getPanelPhone() {
        return panelPhone;
    }

    public void setPanelPhone(String panelPhone) {
        this.panelPhone = panelPhone;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmailMonitoring() {
        return emailMonitoring;
    }

    public void setEmailMonitoring(String emailMonitoring) {
        this.emailMonitoring = emailMonitoring;
    }

    public String getEmailBilling() {
        return emailBilling;
    }

    public void setEmailBilling(String emailBilling) {
        this.emailBilling = emailBilling;
    }

    public String getEmailUpdate() {
        return emailUpdate;
    }

    public void setEmailUpdate(String emailUpdate) {
        this.emailUpdate = emailUpdate;
    }
    
    public String getEmailServices() {
		return emailServices;
	}

	public void setEmailServices(String emailServices) {
		this.emailServices = emailServices;
	}

	public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getAka() {
        return aka;
    }

    public void setAka(String aka) {
        this.aka = aka;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMonitoringStatus() {
        return monitoringStatus;
    }

    public void setMonitoringStatus(String monitoringStatus) {
        this.monitoringStatus = monitoringStatus;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }
    
//    public String getCcc() {
//        return ccc;
//    }
//
//    public void setCcc(String ccc) {
//        this.ccc = ccc;
//    }

    public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getSecuritasPassword() {
        return securitasPassword;
    }

    public void setSecuritasPassword(String securitasPassword) {
        this.securitasPassword = securitasPassword;
    }

    public String getCoercionPassword() {
        return coercionPassword;
    }

    public void setCoercionPassword(String coercionPassword) {
        this.coercionPassword = coercionPassword;
    }

    public List<ActionPlan> getActionplans() {
        return actionplans;
    }

    public void setActionplans(List<ActionPlan> actionplans) {
        this.actionplans = actionplans;
    }

	public String getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public String getTelefono3() {
		return telefono3;
	}

	public void setTelefono3(String telefono3) {
		this.telefono3 = telefono3;
	}

	public String getTelefonoServicios() {
		return telefonoServicios;
	}

	public void setTelefonoServicios(String telefonoServicios) {
		this.telefonoServicios = telefonoServicios;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public String getServicesPhone() {
		return servicesPhone;
	}

	public void setServicesPhone(String servicesPhone) {
		this.servicesPhone = servicesPhone;
	}

	public String getEmailServicios() {
		return emailServicios;
	}

	public void setEmailServicios(String emailServicios) {
		this.emailServicios = emailServicios;
	}

	public boolean isEmailsAreDiferents() {
		return emailsAreDiferents;
	}

	public void setEmailsAreDiferents(boolean emailsAreDiferents) {
		this.emailsAreDiferents = emailsAreDiferents;
	}

	public String getDealer() {
		return dealer;
	}

	public void setDealer(String dealer) {
		this.dealer = dealer;
	}
	
	public String getSins() {
		return sins;
	}

	public void setSins(String sins) {
		this.sins = sins;
	}

	@Override
	public String toString() {
		return "InstallationData [id=" + id + ", panel=" + panel + ", version=" + version + ", installationNumber="
				+ installationNumber + ", sins=" + sins + ", panelPhone=" + panelPhone + ", phone2=" + phone2
				+ ", phone3=" + phone3 + ", servicesPhone=" + servicesPhone + ", customerName=" + customerName
				+ ", emailMonitoring=" + emailMonitoring + ", emailBilling=" + emailBilling + ", emailUpdate="
				+ emailUpdate + ", emailServices=" + emailServices + ", camera=" + camera + ", aka=" + aka
				+ ", address=" + address + ", city=" + city + ", monitoringStatus=" + monitoringStatus + ", subtype="
				+ subtype + ", language=" + language + ", customerPassword=" + customerPassword + ", securitasPassword="
				+ securitasPassword + ", coercionPassword=" + coercionPassword + ", actionplans=" + actionplans
				+ ", telefono1=" + telefono1 + ", telefono2=" + telefono2 + ", telefono3=" + telefono3
				+ ", telefonoServicios=" + telefonoServicios + ", emailServicios=" + emailServicios
				+ ", emailsAreDiferents=" + emailsAreDiferents + ", dealer=" + dealer + "]";
	}


	
}
