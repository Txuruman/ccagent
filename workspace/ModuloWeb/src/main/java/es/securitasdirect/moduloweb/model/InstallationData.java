package es.securitasdirect.moduloweb.model;

import java.util.List;

/**
 * Info of the Installation
 */
public class InstallationData {

    /** Modelo de Panel */
    private String panel;

    private String version;

    /** Número del cliente. Esto es el número de instalación????  */
    private Integer installationNumber;

    /** Teléfono del Panel */
    private String panelPhone;

    /** Nombre del cliente */
    private String customerName;

    private String emailMonitoring;

//    private String emailBilling; //Lo cambiamos a InvoiceInfo

    private String emailUpdate;

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

    public Integer getInstallationNumber() {
        return installationNumber;
    }

    public void setInstallationNumber(Integer installationNumber) {
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

//    public String getEmailBilling() {
//        return emailBilling;
//    }
//
//    public void setEmailBilling(String emailBilling) {
//        this.emailBilling = emailBilling;
//    }

    public String getEmailUpdate() {
        return emailUpdate;
    }

    public void setEmailUpdate(String emailUpdate) {
        this.emailUpdate = emailUpdate;
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("InstallationData{");
        sb.append("panel='").append(panel).append('\'');
        sb.append(", version='").append(version).append('\'');
        sb.append(", installationNumber=").append(installationNumber);
        sb.append(", panelPhone='").append(panelPhone).append('\'');
        sb.append(", customerName='").append(customerName).append('\'');
        sb.append(", emailMonitoring='").append(emailMonitoring).append('\'');
       // sb.append(", emailBilling='").append(emailBilling).append('\'');
        sb.append(", emailUpdate='").append(emailUpdate).append('\'');
        sb.append(", camera=").append(camera);
        sb.append(", aka='").append(aka).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", monitoringStatus='").append(monitoringStatus).append('\'');
        sb.append(", subtype='").append(subtype).append('\'');
//        sb.append(", ccc='").append(ccc).append('\'');
        sb.append(", customerPassword='").append(customerPassword).append('\'');
        sb.append(", securitasPassword='").append(securitasPassword).append('\'');
        sb.append(", coercionPassword='").append(coercionPassword).append('\'');
        sb.append(", actionplans=").append(actionplans);
        sb.append('}');
        return sb.toString();
    }
}
