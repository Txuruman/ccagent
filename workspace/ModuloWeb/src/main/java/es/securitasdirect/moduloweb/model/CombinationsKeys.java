package es.securitasdirect.moduloweb.model;

/**
 * Created by Pedro on 21/09/2015.
 */
public class CombinationsKeys {



    // clave autonumerica para la tabla
    private Integer id;

    private String key1;
    private String key2;
    private String key3;
    private String tab;


    public CombinationsKeys() {

    }

    //constructor copia de la clase CombinationsKeys
    public CombinationsKeys(final org.wso2.ws.dataservice.GetCombinationsKeysResult getCombinationsKeysResult) {
        this.id = getCombinationsKeysResult.getId().intValue();
        this.key1 = getCombinationsKeysResult.getKey1();
        this.key2 = getCombinationsKeysResult.getKey2();
        this.key3 = getCombinationsKeysResult.getKey3();
        this.tab = getCombinationsKeysResult.getTab();

    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }





}
