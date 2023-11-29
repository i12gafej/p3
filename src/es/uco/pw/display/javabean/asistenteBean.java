package es.uco.pw.display.javabean;

import java.io.Serializable;


/**
*Clase asistenteBean
*@author Manuel Cabrera Crespo
*@author Alvaro Eusebio Pérez
*@author Francisco Javier Fernández Pastor
*@author Javier García Fernández
*/

public class asistenteBean implements Serializable{
	private static final long serialVersionUID = 1L;
	/** ID de USUARIO **/
	private String id = "";
	/**Nombre y Apellidos**/
	private String nombre_apellidos = "";
	/**Correo electrónico**/
	private String correo = "";
	/**Contraseña**/
	private String password = "";
	/**Rol ADMIN | ASISTENTE**/
	private String rol = "";
	/**Fecha de nacimiento**/
	private String fechaNacimiento = "";
	/**Requiere atencion especial **/
	private Boolean requiereAtencionEspecial=false;
	/*
	 * tiempo que lleva registrado tal vez hay que meterlo
	 * 
	 * 
	 * */
	
	/**
	 * Contructor vacio asistenteBean
	 */
	public asistenteBean() {
		
	}
	/**
	 * Obtener id de asistente
	 * @return String id
	 */
	public String getId() {
		return this.id;
	}
	/**
	 * Asigna id al asistente
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Obtener nombre y apellidos de asistente
	 * @return String nombre_apellidos
	 */
	public String getNombreApellidos() {
		return this.nombre_apellidos;
	}
	/**
	 * Asigna nombre y apellidos al asistente
	 * @param nombre_apellidos
	 */
	public void setNombreApellidos(String nombre_apellidos) {
		this.nombre_apellidos = nombre_apellidos;
	}
	/**
	 * Obtener correo electronico de asistente
	 * @return String correo
	 */
	public String getCorreo() {
		return this.correo;
	}
	/**
	 * Asigna correo al asistente
	 * @param correo
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	/**
	 * Obtener contraseña electronico de asistente
	 * @return String password
	 */
	public String getPassword() {
		return this.password;
	}
	/**
	 * Asigna password al asistente
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Obtener rol de asistente
	 * @return String rol
	 */
	public String getRol() {
		return this.rol;
	}
	/**
	 * Asigna rol al asistente
	 * @param String rol
	 */
	public void setRol(String rol) {
		this.rol= rol;
	}
	/**
	 * Obtener fecha de nacimiento de asistente
	 * @return String fechaNacimiento
	 */
	public String getFechaNacimiento() {
		return this.fechaNacimiento;
	}
	/**
	 * Asigna fecha de nacimiento al asistente
	 * @param String fechaNacimiento
	 */
	public void setFechaNacimiento(String fechaNacimiento) {	
		this.fechaNacimiento = fechaNacimiento;
	}
	/**
	 * Obtener si el asistente requiere de atención especial
	 * @return boolean requiereAtencionEspecial
	 */
	public boolean getRequiereAtencionEspecial() {
		return this.requiereAtencionEspecial;
	}
	/**
	 * Asigna si el asistente requiere de atención especial
	 * @param boolean requiereAtencionEspecial
	 */
	public void getRequiereAtencionEspecial(boolean requiereAtencionEspecial) {
		this.requiereAtencionEspecial = requiereAtencionEspecial;
	}
	
}