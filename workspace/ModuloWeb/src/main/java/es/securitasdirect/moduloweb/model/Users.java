package es.securitasdirect.moduloweb.model;

/**
 * Modelo de usuarios, para determinar si se es administrador o no
 * @author JAS
 *	userId: id del usuario
 *	name: nombre del usuario
 *	isAdmin: el usuario es administrador o no
 */
public class Users {
	/**
	 * Id del usuario
	 */
	private String userId;
	/**
	 * Nombre del usuario
	 */
	private String name;
	/**
	 * Usuario administrador o no
	 */
	private boolean isAdmin;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	
}
