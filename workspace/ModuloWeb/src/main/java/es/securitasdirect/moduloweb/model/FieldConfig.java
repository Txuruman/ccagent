package es.securitasdirect.moduloweb.model;

/**
 * El sistema debe almacenar un listado de campos de las aplicaciones que se mostrarán dentro de las pestañas.
 * Asociados a dichos campos están los valores de editable y visible que permiten configurar dinámicamente el comportamiento de sub módulos.
 */
public class FieldConfig {

    //TODO Id?? para poder gestionar en bbdd tendremos que tener un ID unico, podría ser app+identifier

    /** Nombre del módulo (Instalacion, Facturación, otros) para poder filtrar por aplicación. */
    private String app;

    /** Clave que identifica un campo. */
    private String identifier;

    /** Descripción del campo al que se hace referencia. */
    private String description;

    /** Indica si el campo es visible. */
    private boolean visible;

    /** Indica si el campo es editable. */
    private boolean editable;

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("FieldConfig{");
        sb.append("app='").append(app).append('\'');
        sb.append(", identifier='").append(identifier).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", visible=").append(visible);
        sb.append(", editable=").append(editable);
        sb.append('}');
        return sb.toString();
    }
}