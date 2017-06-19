package mx.unam.ciencias.modelado;

import java.io.Serializable;

public class Cliente implements Serializable{

	/* El nombre del cliente. */
	private String nombre;
	/* El apellido paterno. */
	private String apellidoPaterno;
	/* El apellido materno. */
	private String apellidoMaterno;
	/* El login. */
	private String login;
	/* La contraseña. */
	private String password;

	/* Constructor. */
	public Cliente(String nombre,
					String apellidoPaterno,
					String apellidoMaterno,
					String login,
					String password) {
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.login = login;
		this.password = password;
	}
	
	/**
	 * Regresa el nombre del cliente.
	 * @return el nombre del cliente.
	 */
	public String getNombre() {
		return this.nombre;
	}
	/**
	 * Regresa el apellido paterno del cliente.
	 * @return el apellido paterno.
	 */
	public String getApellidoPaterno() {
		return this.apellidoPaterno;
	}
	/**
	 * Regresa el apellido materno del cliente.
	 * @return el apellido materno.
	 */
	public String getApellidoMaterno() {
		return this.apellidoMaterno;
	}
	/**
	 * Regresa el login.
	 * @return el login.
	 */
	public String getLogin() {
		return this.login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
