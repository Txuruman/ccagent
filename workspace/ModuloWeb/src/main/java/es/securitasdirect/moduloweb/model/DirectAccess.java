package es.securitasdirect.moduloweb.model;

/**
 * Un acceso directo mantiene la información necesaria para proporcionar al módulo web la capacidad de poder abrir aplicaciones externas.
 * Los Accesos Directos se administran desde el Módulo de Administración. Los accesos directos podrán recibir los parámetros que
 * están disponibles en el Modulo Web.
 */
public class DirectAccess {

    /** Etiqueta que se muestra en el módulo web como texto del acceso directo. */
    private String name;
    /** Texto que describe el acceso directo, se podrá utilizar como texto a mostrar al pasar el ratón por el enlace del acceso directo. */
    private String description;
    /** Url http para abrir en navegadores web. */
    private String url;
    /** Numeral para indicar el orden en la vista. */
    private int position=0;

    //TODO MAPA DE PARAMETROS

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
