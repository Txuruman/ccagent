package es.securitasdirect.moduloweb.model;
/**
 * Modelo para la configuración de la pestaña activa
 * @author JAS
 * 	tabId: id de la pestaña
 *	name: Nombre de la pestaña
 *	Key1, Key2, Key3: Combinación para determinar la pestaña activa
 */
public class TabKeys {

    /**
     * clave autonumerica para la tabla
     */
    private Integer id;
	/**
	 * id de la pestaña
	 */
	private String tabId;
	/**
	 * Nombre de la pestaña
	 */
	private String name;
	/**
	 * Key1
	 */
	private String key1;
	/**
	 * Key2
	 */
	private String key2;
	/**
	 * Key3
	 */
	private String key3;

    //constructor copia de la clase TabKeys
    /*
    public TabKeys(final org.wso2.ws.dataservice.GetTabKeysResult getTabKeysResult) {
        this.id = getTabKeysResult.getId().intValue();
        this.tabId = getTabKeysResult.getTabId();
        this.name = getTabKeysResult.getName();
        this.key1 = getTabKeysResult.getKey1();
        this.key2 = getTabKeysResult.getKey2();
        this.key3 = getTabKeysResult.getKey3();
    }
    */




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getTabId() {
		return tabId;
	}
	public void setTabId(String tabId) {
		this.tabId = tabId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKey1() {
		return key1;
	}
	public void setKey1(String key1) {
		this.key1 = key1;
	}
	public String getKey2() {
		return key2;
	}
	public void setKey2(String key2) {
		this.key2 = key2;
	}
	public String getKey3() {
		return key3;
	}
	public void setKey3(String key3) {
		this.key3 = key3;
	}
	
	
	
}
