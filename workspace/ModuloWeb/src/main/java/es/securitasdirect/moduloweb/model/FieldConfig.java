package es.securitasdirect.moduloweb.model;

import org.wso2.ws.dataservice.EntrygetFieldConfigByAppResult;
import org.wso2.ws.dataservice.GetFieldConfigResult;

/**
 * El sistema debe almacenar un listado de campos de las aplicaciones que se mostrarán dentro de las pestañas.
 * Asociados a dichos campos están los valores de editable y visible que permiten configurar dinámicamente el comportamiento de sub módulos.
 */
public class FieldConfig {

    /**
     * clave autonumerica para la tabla
     */
    private Integer id;

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
    
    /** Indica si el campo es administrable. */
    private boolean administrable;
    
    /** Indica posicion del campo. */
    private Integer position;

    
    public FieldConfig() {

    }

    //constructor copia de la clase FieldConfig
    public FieldConfig(final EntrygetFieldConfigByAppResult getFieldConfigResult) {
        this.id = getFieldConfigResult.getId().intValue();
        this.app = getFieldConfigResult.getApp();
        this.identifier = getFieldConfigResult.getIdentifier();
        this.description = getFieldConfigResult.getDescription();
        this.visible = getFieldConfigResult.isVisible();
        this.editable = getFieldConfigResult.isEditable();
        this.administrable = getFieldConfigResult.isAdministrable();
        this.position = getFieldConfigResult.getPosition().intValue();
    }
    
    //constructor copia de la clase FieldConfig
    public FieldConfig(final GetFieldConfigResult getFieldConfigResult) {
        this.id = getFieldConfigResult.getId().intValue();
        this.app = getFieldConfigResult.getApp();
        this.identifier = getFieldConfigResult.getIdentifier();
        this.description = getFieldConfigResult.getDescription();
        this.visible = getFieldConfigResult.isVisible();
        this.editable = getFieldConfigResult.isEditable();
        this.administrable = getFieldConfigResult.isAdministrable();
        this.position = getFieldConfigResult.getPosition().intValue();
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

	public boolean isAdministrable() {
		return administrable;
	}

	public void setAdministrable(boolean administrable) {
		this.administrable = administrable;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "FieldConfig [id=" + id + ", app=" + app + ", identifier=" + identifier + ", description=" + description
				+ ", visible=" + visible + ", editable=" + editable + ", administrable=" + administrable + ", position="
				+ position + "]";
	}
    
    
   
}
